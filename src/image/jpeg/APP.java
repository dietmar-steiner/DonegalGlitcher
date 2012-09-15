/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

/**
 *
 * @author Dietmar
 */
public class APP extends JPEGPart {

    private String name="";

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, java.util.ArrayList<Byte> data) {
        super.init(pos, data, false);
        int i = 0;
        byte[] buffer = new byte[getLength()];
        buffer[i] = data.get(pos+4+i);
        while (buffer[i] != 0) {
            i++;
            buffer[i] = data.get(pos+4+i);
        }
        name = new String(buffer,0,i);
    }

    /**
     * 
     * @return the detailed information of this part
     */
    @Override
    public String getInfo() {
        return "Name: " + getPart().name() + "\n Identifier: " + name + "\n Length: " + getLength() + ":0x" + String.format("%04X", getLength());
    }

    public String toString() {
        return getPart().name() + " ID: " + name;
    }
}
