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
public class ResetAction extends AbstractAction {

    /**
     * 
     */
    public ResetAction() {
        super("Reset");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control R"));
        putValue(Action.MNEMONIC_KEY, new Integer(java.awt.event.KeyEvent.VK_R));
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View.getInstance().getImage().reset();
        View.getInstance().setImageChanged(false);
    }
}
