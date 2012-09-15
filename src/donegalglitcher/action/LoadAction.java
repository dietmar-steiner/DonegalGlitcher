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
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Dietmar
 */
public class LoadAction extends AbstractAction {
    
    
    /**
     * 
     */
    public LoadAction() {
        super("Load");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control L"));
        putValue(Action.MNEMONIC_KEY, new Integer(java.awt.event.KeyEvent.VK_L));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View.getInstance().setStatus("Loading...");
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser("C:/Users/Public/Pictures/Sample Pictures");
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fc.getSelectedFile();
            final Image image = View.getInstance().getImage();
            new Thread(new FileLoadTask(file, image)).start();
        }
        fc = null;
    }

    class FileLoadTask implements Runnable {

        private File file = null;
        private Image image = null;

        FileLoadTask(File file, Image image) {
            this.file = file;
            this.image = image;
        }

        @Override
        public void run() {
            System.out.println("Load Action Start");
            StatusPanel statusPanel = View.getInstance().getStatusPanel();
            statusPanel.setText("Loading: " + file.getAbsolutePath());
            image.load(file,statusPanel);
            if (null == image.getTyp()) {
                View.getInstance().setDisplayImage(null);
                View.getInstance().addImageToTree(null);
                View.getInstance().setImageLoaded(false);
            statusPanel.setText("Loading failed for: " + file.getAbsolutePath());
            }
            if (image.getTyp() != null) {
                View.getInstance().setDisplayImage(image);
                View.getInstance().addImageToTree(image.getTree(View.getInstance()));
            }
            statusPanel.setText("Loaded: " + file.getAbsolutePath());
            View.getInstance().setImageLoaded(true);
            View.getInstance().invalidate();
        }
    }
}
