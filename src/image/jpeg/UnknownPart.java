/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

/**
 *
 * @author Dietmar
 */
public class UnknownPart extends JPEGPart {

    private int value = 0;

    /**
     * 
     * @param pos
     * @param data
     */
    public void init(int pos, java.util.ArrayList<Byte> data) {
        super.init(pos, data, false);
        value = (data.get(pos) & 0xFF) << 8 | (data.get(pos+1) & 0xFF);
    }

    /**
     * 
     * @return the marker value for this part
     */
    public int getValue() {
        return value;
    }

    /**
     * 
     * @return the detailed information of this part
     */
    @Override
    public String getInfo() {
        return getPart().name() + " Value: " + String.format("%4X", value)+" length: "+getLength() + ":0x" + String.format("%04X", getLength());
    }

    public String toString() {
        return getPart().name() + " (0x" + String.format("%02X)", value);
    }
}
