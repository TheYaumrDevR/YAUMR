package de.ethasia.yaumr.presenters.implementation;

import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.Chunk;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;
import de.ethasia.yaumr.presenters.meshcreation.ChunkMesh;

/**
 *
 * @author R
 */
public class IslandRendererImpl implements IslandRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String ROOT_NODE_NAME = "chunkMeshesRootNode";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    
    private final Node rootNode;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public IslandRendererImpl() {
        rootNode = new Node(ROOT_NODE_NAME);
        YaumrGame.getInstance().getRootNode().attachChild(rootNode);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void updateModifiedChunk(Island island, Chunk chunk) {
        String uniqueChunkName = chunk.getChunkPositionX() + "." + chunk.getChunkPositionY();
        
        if (null != rootNode.getChild(uniqueChunkName)) {
            Geometry chunkGeometry = createChunkGeometry(island, chunk);
            rootNode.attachChild(chunkGeometry);
            rootNode.detachChildNamed(uniqueChunkName);
        } else {
            Geometry chunkGeometry = createChunkGeometry(island, chunk);
            rootNode.attachChild(chunkGeometry);
        }
    }    
    
    @Override
    public void removeRenderedData() {
        rootNode.detachAllChildren();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Geometry createChunkGeometry(Island island, Chunk chunk) {
        String uniqueChunkName = chunk.getChunkPositionX() + "." + chunk.getChunkPositionY();
        
        ChunkMesh chunkMesh = new ChunkMesh();
        
        if (chunk.hasVisibleBlocks()) {
            chunkMesh.updateRenderingData(chunk.getBlocks());            
        }
        
        Geometry geometry = new Geometry(uniqueChunkName, chunkMesh);
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        Texture blockTextures = YaumrGame.getInstance().getAssetManager().loadTexture(new TextureKey("Textures/Blocks/TextureAtlas.png", false));
        blockTextures.setMinFilter(Texture.MinFilter.BilinearNoMipMaps);
        blockTextures.setMagFilter(Texture.MagFilter.Nearest);
        material.setTexture("ColorMap", blockTextures);
        material.setFloat("AlphaDiscardThreshold", 0.9961f);
        geometry.setMaterial(material);
        
        float[] originVertex = island.calculateOrigin();
        geometry.setLocalTranslation(originVertex[0] + 8 * chunk.getChunkPositionX(), 0, originVertex[2] + 8 * chunk.getChunkPositionY());
        
        return geometry;
    }
    
    //</editor-fold>
}
