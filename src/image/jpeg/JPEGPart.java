/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

import image.ImagePart;

/**
 *
 * @author Dietmar
 */
public abstract class JPEGPart extends ImagePart {

    private JPEGTyp.Part part;
    private int length = 2;

    /**
     * 
     */
    protected JPEGPart() {
    }

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public abstract void init(int pos, java.util.ArrayList<Byte> data);

    /**
     * 
     * @param pos
     * @param data
     * @param isData
     */
    protected void init(int pos, java.util.ArrayList<Byte> data, boolean isData) {
        super.init(pos, data);
        if (!isData) {
            part = JPEGTyp.Part.getPart(data.get(pos + 1));
            length = part.getLength(pos, data);
        } else {
            part = JPEGTyp.Part.getPart((byte)0x00);
            length = 0;
        }
    }

    /**
     * 
     * @return the part
     */
    public JPEGTyp.Part getPart() {
        return part;
    }

    /**
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * 
     * @param length
     */
    protected void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        return "JPEG Image";
    }

}
