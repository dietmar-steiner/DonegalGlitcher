/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image.jpeg;

/**
 *
 * @author Dietmar
 */
public class Data extends JPEGPart{

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, java.util.ArrayList<Byte> data) {
        super.init(pos, data, true);
    }
    /**
     * 
     * @return the detailed information of this part
     */
    @Override
    public String getInfo() {
        return "Name: " + getPart().name()
                + "\n Length: "+ getLength();
    }

    /**
     * 
     * @param length
     */
    @Override
    public void setLength(int length) {
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "Data length: "+getLength();
    }
}
