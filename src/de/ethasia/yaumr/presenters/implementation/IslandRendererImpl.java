package de.ethasia.yaumr.presenters.implementation;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.Chunk;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.presenters.interfaces.IslandRenderer;
import de.ethasia.yaumr.presenters.meshes.ChunkMesh;

/**
 *
 * @author R
 */
public class IslandRendererImpl implements IslandRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Private Fields">
    
    private final Node rootNode;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public IslandRendererImpl() {
        rootNode = new Node();
        YaumrGame.getInstance().getRootNode().attachChild(rootNode);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void updateModifiedChunk(int[] chunkPosition, Island island, Chunk chunk) {
        String uniqueChunkName = chunkPosition[0] + "." + chunkPosition[1];
        
        if (null != rootNode.getChild(uniqueChunkName)) {
            Geometry chunkGeometry = createChunkGeometry(chunkPosition, island, chunk);
            rootNode.attachChild(chunkGeometry);
            rootNode.detachChildNamed(uniqueChunkName);
        } else {
            Geometry chunkGeometry = createChunkGeometry(chunkPosition, island, chunk);
            rootNode.attachChild(chunkGeometry);
        }
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Geometry createChunkGeometry(int[] chunkPosition, Island island, Chunk chunk) {
        String uniqueChunkName = chunkPosition[0] + "." + chunkPosition[1];
        
        ChunkMesh chunkMesh = new ChunkMesh();
        chunkMesh.updateRenderingData(chunk.getBlocks());
        Geometry geometry = new Geometry(uniqueChunkName, chunkMesh);
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        geometry.setMaterial(material);
        
        float[] originVertex = island.calculateOrigin();
        geometry.setLocalTranslation(originVertex[0] + 8 * chunkPosition[0], 0, originVertex[2] + 8 * chunkPosition[1]);
        
        return geometry;
    }
    
    //</editor-fold>
}
