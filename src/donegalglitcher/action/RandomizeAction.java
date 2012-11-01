/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package donegalglitcher.action;

import donegalglitcher.gui.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Dietmar
 */
public class RandomizeAction extends AbstractAction{

    /**
     * 
     */
    public RandomizeAction() {
        super("Randomize");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
        putValue(Action.MNEMONIC_KEY, new Integer(java.awt.event.KeyEvent.VK_Q));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View.getInstance().randomize();
    }
    
}
