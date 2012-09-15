/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package donegalglitcher;

import donegalglitcher.gui.View;
import java.awt.Dimension;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Dietmar
 */
public class DonegalGlitcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger.getLogger(DonegalGlitcher.class.getName()).addHandler(new ConsoleHandler());
        try {
            UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(DonegalGlitcher.class.getName()).log(Level.SEVERE, null, ex);
        } 
        java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
            public void run() {
                View frame = View.getInstance();
                frame.setSize(new Dimension(800,600));
                frame.validate();
                frame.setVisible(true);
            }
        });
    }
}
