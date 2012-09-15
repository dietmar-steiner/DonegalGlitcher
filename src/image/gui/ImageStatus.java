/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.gui;

import javax.swing.JPanel;

/**
 *
 * @author Dietmar
 */
public interface ImageStatus {

    /**
     * 
     * @param text
     */
    abstract public void setText(String text);

    /**
     * 
     * @param current
     * @param min
     * @param max
     */
    abstract public void setProgress(long current, long min, long max);

    /**
     * 
     * @param current
     */
    abstract public void setProgress(long current);

    /**
     * 
     * @param visible
     */
    abstract public void setProgressVisible(boolean visible);
}
