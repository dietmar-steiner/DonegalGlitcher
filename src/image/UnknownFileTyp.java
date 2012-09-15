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
public class UnknownFileTyp extends ImageTyp {
    
    /**
     * 
     */
    protected UnknownFileTyp() {
        super(false);
    }

    @Override
    public String toString() {
        return "UnknownTyp";
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
