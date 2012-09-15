/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import image.gui.ImagePartPropertyPanel;
import image.gui.ImageStatus;
import image.gui.ImageTreeItem;
import donegalglitcher.action.Actions;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This Objects hold all information of an Image or File
 * 
 * It keeps a clean copy of the original loaded to easily revert back to a clean image
 * @author Dietmar
 */
public class Image implements ImageTreeItem {

    private ArrayList<Byte> original = new ArrayList<Byte>();
    private ArrayList<Byte> work = new ArrayList<Byte>();
    private image.ImageTyp typ = null;
    private ArrayList<ImagePart> parts = new ArrayList<ImagePart>();
    private File file = null;

    /**
     * Creates a empty Image object of type NOFILE
     */
    public Image() {
        typ = ImageTyp.getImageTyp(work);
    }

    /**
     * loads the given file into the Image objects and creates a original and work copy of it
     * @param inFile the file to load
     * @param status the status panel to output progress
     * @return returns true if load was successful or false when loading failed
     */
    public boolean load(java.io.File inFile, ImageStatus status) {
        file = inFile;
        try {
            original.clear();
            work.clear();
            parts.clear();
            typ = null;
            status.setProgress(0, 0, inFile.length());
            InputStream inputStream = new FileInputStream(file);
            byte[] ret = new byte[1];
            long pos = 0;
            while (inputStream.read(ret) != -1) {
                pos++;
                if ((pos % 1024) == 0) {
                    status.setProgress(pos);
                }
                original.add(ret[0]);
                work.add(ret[0]);
            }
            inputStream.close();
        } catch (java.lang.Exception e) {
            status.setProgressVisible(false);
            System.out.println(e.toString());
            file = null;
            return false;
        }
        status.setProgressVisible(false);
        typ = ImageTyp.getImageTyp(work);
        System.out.println(typ);
        if (null == typ) {
            return false;
        }
        parts = typ.scan(work);
        return true;

    }

    /**
     * Creates a BufferedImage from the work data set
     * @return the BufferedImage
     */
    public java.awt.image.BufferedImage getImage() {
        byte[] bytes = new byte[work.size()];
        int i = 0;
        for (byte value : work) {
            bytes[i] = value;
            i++;
        }
        try {
            return ImageIO.read(new ByteArrayInputStream((byte[]) bytes));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Saves the work copy to the given file
     * @param outFile the file data should be written to
     * @param status Status panel for progress and text output
     * @return true when successful else false
     */
    public boolean save(File outFile, ImageStatus status) {
        try {
            FileOutputStream out = new FileOutputStream(outFile);
            status.setProgress(0, 0, work.size());
            status.setText("Writing: " + outFile.getAbsolutePath());
            long pos = 0;
            for (byte outData : work) {
                pos++;
                if ((pos % 1024) == 0) {
                    status.setProgress(pos);
                }
                out.write(outData);
            }
            out.flush();
            out.close();
        } catch (java.lang.Exception e) {
            System.out.println(e.toString());
            status.setText("failed saving: " + outFile.getAbsolutePath());
            status.setProgressVisible(false);
            return false;
        }
        status.setText("finished saving: " + outFile.getAbsolutePath());
        status.setProgressVisible(false);
        return true;
    }

    /**
     * Swaps 2 random bytes in the work dataset
     */
    public void swap() {
        Random random = new Random();
        int a = random.nextInt(work.size());
        while (work.get(a) == 0xFF) {
            a = random.nextInt(work.size());
            System.out.println("a: FF found");
        }
        int b = random.nextInt(work.size());
        while (work.get(b) == 0xFF & a == b) {
            b = random.nextInt(work.size());
            System.out.println("b: FF found");
        }
        Byte value = work.get(a);
        work.set(a, work.get(b));
        work.set(b, value);
        System.out.println("Swap Position a: " + a + " b: " + b);
    }

    /**
     * Resets the work dataset to the original loaded dataset
     */
    public void reset() {
        work = new ArrayList<Byte>();
        work.addAll(Arrays.asList(original.toArray(new Byte[0])));
        for (ImagePart part : parts) {
            part.init(part.getPos(), work);
        }
    }

    /**
     * Queries the file type of this dataset
     * @return the file type of the dataset
     */
    public ImageTyp getTyp() {
        return typ;
    }

    /**
     * returns the loaded file object of the Image
     * @return the file object or null if no file is loaded
     */
    public File getFile() {
        return file;
    }

    /**
     * Creates and returns a tree of the parts of the loaded file
     * @param listener PartListener added to all Parts or null if no listener required
     * @return the part tree
     */
    public DefaultMutableTreeNode getTree(PartListener listener) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this);
        for (ImagePart part : parts) {
            root.add(new DefaultMutableTreeNode(part));
            part.addListener(listener);
        }
        return root;
    }

    @Override
    public String toString() {
        String ret = "";
        if (typ == null) {
            ret = "Image: ";
        } else if (file == null) {
            ret = "Image: " + typ.toString();
        } else {
            ret = "Image: " + typ.toString() + " " + file.getName() + " : " + original.size() + "Bytes";
        }
        return ret;
    }

    /**
     * Returns null always
     * @return null
     */
    @Override
    public ImagePartPropertyPanel getPropertyPanel() {
        return null;
    }

    /**
     * Can this Image be randomized
     * @return false always
     */
    @Override
    public boolean canBeRandomized() {
        return false;
    }

    /**
     * Empty Implementation
     */
    @Override
    public void randomize() {
    }

    /**
     * Customize the given JLabel for output
     * @param label The JLabel to customize 
     */
    @Override
    public void render(JLabel label) {
        label.setForeground(Color.BLUE);
    }

    /**
     * Creates a pop-up menu for this Image.
     * @return the pop-up menu
     */
    @Override
    public JPopupMenu getPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(Actions.LOAD.getMenuItem());
        menu.add(Actions.RESET.getMenuItem());
        menu.add(Actions.SAVE.getMenuItem());
        return menu;
    }
}
