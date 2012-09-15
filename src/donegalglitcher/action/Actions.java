/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package donegalglitcher.action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

/**
 *
 * @author Dietmar
 */
public enum Actions {
    /**
     * Executes loading a file with file dialog
     */
    LOAD ("donegalglitcher.action.LoadAction", true),
    /**
     * Executes reseting the image to its original state
     */
    RESET ("donegalglitcher.action.ResetAction", false),
    /**
     * Executes saving the image to a file with file dialog
     */
    SAVE ("donegalglitcher.action.SaveAction", false),
    /**
     * Executes randomize data on certain entities
     */
    RANDOMIZE ("donegalglitcher.action.RandomizeAction", false),
    /**
     * Toggles automatic scaling of the image display
     */
    AUTOSCALE ("donegalglitcher.action.AutoscaleAction", true),
    /**
     * Leaves the program
     */
    EXIT ("donegalglitcher.action.ExitAction", true);
    
    private AbstractAction action = null;
    
    private Actions(String className, boolean initialState) {
        try {
            action = (AbstractAction) Class.forName(className).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        action.setEnabled(initialState);
    }
    
    /**
     * Retrieves the Action Object
     * @return the Action Object for this action
     */
    public AbstractAction getAction() {
        return action;
    }
    
    /**
     * 
     * @return the menuItem with this action
     */
    public JMenuItem getMenuItem() {
        JMenuItem item = new JMenuItem();
        item.setAction(action);
        return item;
    }
    
    /**
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        action.setEnabled(enabled);
    }
}
