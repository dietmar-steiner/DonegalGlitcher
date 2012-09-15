/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package donegalglitcher.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Dietmar
 */
public class ExitAction extends AbstractAction {
    
    /**
     * 
     */
    public ExitAction() {
        super("Exit");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control E"));
        putValue(Action.MNEMONIC_KEY, new Integer(java.awt.event.KeyEvent.VK_E));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
