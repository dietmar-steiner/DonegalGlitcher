/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.gui;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Dietmar
 */
public interface ImageTreeItem {

    /**
     * Returns the Property Panel for this item
     * @return the Property Panel or null if none is defined
     */
    public ImagePartPropertyPanel getPropertyPanel();

    /**
     * Query if this part can be randomized
     * @return true if this part can be randomized else false
     */
    public boolean canBeRandomized();
    
    /**
     * Randomize this Part
     */
    public void randomize();
    
    /**
     * Customizes the given label for rendering
     * @param label the label to customize
     */
    public void render(JLabel label);
    
    /**
     * Returns the pop-up menu for this Part
     * @return the pop-up menu or null if none is defined
     */
    public JPopupMenu getPopupMenu();
    
}
