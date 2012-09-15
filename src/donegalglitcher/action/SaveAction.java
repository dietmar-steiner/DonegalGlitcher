/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package donegalglitcher.action;

import image.Image;
import donegalglitcher.gui.StatusPanel;
import donegalglitcher.gui.View;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Dietmar
 */
public class SaveAction extends AbstractAction {

    /**
     * 
     */
    public SaveAction() {
        super("Save");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
        putValue(Action.MNEMONIC_KEY, new Integer(java.awt.event.KeyEvent.VK_S));
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StatusPanel status = View.getInstance().getStatusPanel();
        Image image = View.getInstance().getImage();
        File outFile = image.getFile();
        if (null == image.getTyp()) {
            return;
        }
        status.setText("Saving: " + outFile.getAbsolutePath());
        JFileChooser fc = new JFileChooser() {

            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (getDialogType() == SAVE_DIALOG) {
                    if (f.exists()) {
                        int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                        switch (result) {
                            case JOptionPane.YES_OPTION:
                                super.approveSelection();
                                break;

                            default: {
                                break;
                            }
                        }
                    } else {
                        super.approveSelection();
                    }
                }
            }
        };
        fc.setSelectedFile(outFile);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            outFile = fc.getSelectedFile();
            new Thread(new FileSaveTask(outFile, image)).start();
        }
        fc = null;
    }
// TODO add your handling code here:

    class FileSaveTask implements Runnable {

        private File file = null;
        private Image image = null;

        FileSaveTask(File file, Image image) {
            this.file = file;
            this.image = image;
        }

        @Override
        public void run() {
            System.out.println("Save Action Start");
            StatusPanel statusPanel = View.getInstance().getStatusPanel();
            statusPanel.setText("Saving: " + file.getAbsolutePath());
            boolean load = image.save(file, statusPanel);
        }
    }
}
