/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.util.ArrayList;

/**
 *
 * @author Dietmar
 */
public class NoFileTyp extends ImageTyp{
    
    /**
     * 
     */
    public NoFileTyp() {
        super(false);
    }

    @Override
    public String toString() {
        return "No File loaded";
    }

    /**
     * 
     * @param data
     * @return always an empty Part list
     */
    @Override
    public ArrayList<ImagePart> scan(ArrayList<Byte> data) {
        return new ArrayList<ImagePart>(0);
    }
    
    
}
