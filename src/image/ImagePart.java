/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import image.gui.ImagePartPropertyPanel;
import image.gui.ImageTreeItem;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

/**
 * 
 * @author Dietmar
 */
public abstract class ImagePart implements ImageTreeItem {

    private int pos = 0;
    private java.util.ArrayList<Byte> data = null;
    private java.util.ArrayList<PartListener> listeners = new java.util.ArrayList<PartListener>();

    /**
     * 
     */
    protected ImagePart() {
    }

    /**
     * 
     * @param pos
     * @param data
     */
    public void init(int pos, java.util.ArrayList<Byte> data) {
        this.pos = pos;
        this.data = data;
    }

    /**
     * 
     * @return the position within the dataset of this Part
     */
    public int getPos() {
        return pos;
    }

    /**
     * 
     * @return the data object where this part is contained
     */
    protected java.util.ArrayList<Byte> getData() {
        return data;
    }

    /**
     * 
     * @return always null
     */
    @Override
    public ImagePartPropertyPanel getPropertyPanel() {
        return null;
    }

    /**
     * 
     * @param listener
     */
    public void addListener(PartListener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    /**
     * 
     * @param listener
     */
    public void removeListener(PartListener listener) {
        listeners.remove(listener);
    }

    /**
     * 
     */
    protected void fireDataChangeEvent() {
        PartEvent event = new PartEvent(this);
        for (PartListener listener : listeners) {
            listener.dataChanged(event);
        }
    }

    /**
     * 
     * @return always false
     */
    @Override
    public boolean canBeRandomized() {
        return false;
    }

    /**
     * 
     */
    @Override
    public void randomize() {
    }

    /**
     * 
     * @param label
     */
    @Override
    public void render(JLabel label) {
    }
    
    /**
     * 
     * @return null always
     */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

    /**
     * 
     * @return the String describing this Part
     */
    abstract public String getInfo();
}
