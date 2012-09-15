/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import image.jpeg.JPEGTyp;
import java.util.ArrayList;

/**
 *
 * @author Dietmar
 */
public abstract class ImageTyp {
    
    boolean isImage = true;
    
    
    /**
     * 
     */
    protected ImageTyp() {
    }
    
    /**
     * 
     * @param isImage
     */
    protected ImageTyp(boolean isImage) {
        this.isImage = false;
    }

    /**
     * 
     * @param data
     * @return the image type of the given data
     */
    public static ImageTyp getImageTyp(ArrayList<Byte> data) {
        if (0 == data.size()) {
            return new NoFileTyp();
        }
        if (0xFF == (data.get(0) & 0xFF)
                && 0xD8 == (data.get(1) & 0xFF)
                && 0xFF == (data.get(2) & 0xFF)
                && 0xE0 == (data.get(3) & 0xFF)
                && 0x4a == (data.get(6) & 0xFF)
                && 0x46 == (data.get(7) & 0xFF)
                && 0x49 == (data.get(8) & 0xFF)
                && 0x46 == (data.get(9) & 0xFF)
                && 0x00 == (data.get(10) & 0xFF)) {
            return new JPEGTyp();
        }
        return new UnknownFileTyp();
    }
    
    /**
     * 
     * @return true if the data represents an image else false
     */
    public boolean isImage() {
        return isImage;
    }
    
    @Override
    public abstract String toString();
    
    /**
     * 
     * @param data
     * @return the list of Parts for the given dataset
     */
    public abstract ArrayList<ImagePart> scan(ArrayList<Byte> data);
}
