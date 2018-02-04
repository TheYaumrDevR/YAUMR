package de.ethasia.yaumr.outsidedependencies.renderers;

import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.ioadapters.interfaces.ChunkRenderer;
import de.ethasia.yaumr.ioadapters.presenters.ChunkPresenterImpl;
import de.ethasia.yaumr.ioadapters.presenters.chunkpresenting.VisualChunkData;

/**
 *
 * @author R
 */
public class ChunkRendererImpl implements ChunkRenderer, RootNodeProvider {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Node rootNode;
    private final Texture blockTexture;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ChunkRendererImpl() {
        rootNode = new Node();
        blockTexture = loadTexture();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void renderChunk(VisualChunkData chunkData) {
        String uniqueChunkName = getUniqueChunkName(chunkData);
        
        if (null != rootNode.getChild(uniqueChunkName)) {
            Geometry chunkGeometry = createChunkGeometry(chunkData);
            rootNode.detachChildNamed(uniqueChunkName);
            rootNode.attachChild(chunkGeometry);
        } else {
            Geometry chunkGeometry = createChunkGeometry(chunkData);
            rootNode.attachChild(chunkGeometry);
        }
    } 
    
    @Override
    public Node getRootNode() {
        return rootNode;
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Texture loadTexture() {
        Texture blockTextures = null;
        
        try {
            blockTextures = YaumrGame.getInstance().getAssetManager().loadTexture(new TextureKey("Textures/Blocks/TextureAtlas.png", false));
            blockTextures.setMinFilter(Texture.MinFilter.BilinearNoMipMaps);
            blockTextures.setMagFilter(Texture.MagFilter.Nearest);            
        } catch (Exception ex) {
            // Create dummy texture
            blockTextures = new Texture2D();
        }
        
        return blockTextures;
    }
    
    private Geometry createChunkGeometry(VisualChunkData chunkData) {
        String uniqueChunkName = getUniqueChunkName(chunkData);
        ChunkMesh chunkMesh = new ChunkMesh();
        chunkMesh.updateMeshBasedOnChunkData(chunkData);
        
        Geometry geometry = new Geometry(uniqueChunkName, chunkMesh);
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"); 
        material.setTexture("ColorMap", blockTexture);
        material.setFloat("AlphaDiscardThreshold", 0.9961f);
        geometry.setMaterial(material);
        
        geometry.setLocalTranslation(chunkData.getX() * ChunkPresenterImpl.CHUNK_EDGE_LENGTH_IN_BLOCKS * 0.5f, 0, chunkData.getZ() * ChunkPresenterImpl.CHUNK_EDGE_LENGTH_IN_BLOCKS * 0.5f);
        
        return geometry;
    } 
    
    private String getUniqueChunkName(VisualChunkData chunkData) {
        int chunkPositionX = chunkData.getX();
        int chunkPositionZ = chunkData.getZ();
        
        return "Chunk: " + chunkPositionX + ", " + chunkPositionZ;        
    }    
    
    //</editor-fold>
}
