package de.ethasia.yaumr.presenters.implementation;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.debug.WireBox;
import com.jme3.scene.shape.Box;
import de.ethasia.yaumr.base.YaumrGame;
import de.ethasia.yaumr.blockengine.entities.GlobalBlockPosition;
import de.ethasia.yaumr.blockengine.entities.Island;
import de.ethasia.yaumr.presenters.interfaces.BlockOutlineRenderer;

/**
 * 
 * @author R
 */
public class BlockOutlineRendererImpl implements BlockOutlineRenderer {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String ROOT_NODE_NAME = "blockPlacementIndicatorNode";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Node rootNode;
    private GlobalBlockPosition blockPositionCurrentOutlineIsOn;
    private Geometry renderedCube;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public BlockOutlineRendererImpl() {
        rootNode = new Node(ROOT_NODE_NAME);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementation">
    
    @Override
    public void renderBlockOutline(Island islandBlockIsOn, GlobalBlockPosition blockPosition) {
        if (null != blockPositionCurrentOutlineIsOn && blockPositionCurrentOutlineIsOn.equals(blockPosition)) {
            return;
        }
        
        blockPositionCurrentOutlineIsOn = blockPosition;
        
        if (null == blockPosition) {
            unrenderCurrentlyRenderedObjects();
            return;
        }
        
        float[] origin = islandBlockIsOn.calculateOrigin();
        float blockXPoint = 0.25f + origin[0] + (blockPosition.getChunkPositionX() * 8 + blockPosition.getBlockPositionX() * 0.5f);
        float blockYPoint = 0.25f + origin[1] + blockPosition.getBlockPositionY() * 0.5f;
        float blockZPoint = 0.25f + origin[2] + (blockPosition.getChunkPositionY() * 8 + blockPosition.getBlockPositionZ() * 0.5f);
        
        if (null == renderedCube) {
            renderedCube = createWireCube();
            rootNode.attachChild(renderedCube);
        }

        renderedCube.setLocalTranslation(blockXPoint, blockYPoint, blockZPoint);
        
        YaumrGame.getInstance().getRootNode().attachChild(rootNode);
    }    
    
    @Override
    public void unrenderCurrentlyRenderedObjects() {
        YaumrGame.getInstance().getRootNode().detachChildNamed(ROOT_NODE_NAME);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Geometry createWireCube() {
        WireBox wireCube = new WireBox(0.25f, 0.25f, 0.25f);
        Geometry geometry = new Geometry("BlockPlacementIndicator", wireCube );
        Material material = new Material(YaumrGame.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Black);
        geometry.setMaterial(material);
        
        return geometry;
    }
    
    //</editor-fold>
}
