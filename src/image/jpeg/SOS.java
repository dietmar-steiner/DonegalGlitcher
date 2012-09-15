/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

/**
 *
 * @author Dietmar
 */
public class SOS extends JPEGPart {

    private int componentCount = 0;
    private int[][] components = null;
    private int startSelector = 0;
    private int endSelector = 0;
    private int bitPositionHigh = 0;
    private int bitPositionLow = 0;

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, java.util.ArrayList<Byte> data) {
        super.init(pos, data, false);
        componentCount = data.get(pos + 4) & 0xFF;
        components = new int[componentCount][3];
        for (int i = 0; i < componentCount; i++) {
            components[i][0] = data.get(pos + 5 + i * 2) & 0xFF;
            components[i][1] = ((data.get(pos + 6 + i * 2) & 0xFF) >> 4) & 0xF;
            components[i][2] = (data.get(pos + 6 + i * 2) & 0xFF) & 0xF;
        }
        startSelector = data.get(pos + 5 + componentCount*2) & 0xFF;
        endSelector = data.get(pos + 5  + componentCount*2 + 1) & 0xFF;
        bitPositionHigh = ((data.get(pos + 5  + componentCount*2 + 2) & 0xFF) >> 4) & 0xF;
        bitPositionLow = (data.get(pos + 5  + componentCount*2 + 2) & 0xFF) & 0xF;
    }

    /**
     * 
     * @return the detailed information of this part
     */
    @Override
    public String getInfo() {
        String ret = "Name: " + getPart().name() + "(0x" + String.format("%02X", getPart().getValue()) + ")"
                + "\n Length: " + getLength() + ":0x" + String.format("%04X", getLength())
                + "\n Component Count: " + componentCount;
        for (int i = 0; i < componentCount; i++) {
            ret += " \n Component: " + components[i][0]
                    + " DC entropy coding table: " + components[i][1]
                    + " AC entropy coding table: " + components[i][2];
        }
        ret += "\n Start of spectral selection: " + startSelector
                + "\n End of spectral selection: " + endSelector
                + "\n Successive approximation bit position high: " + bitPositionHigh
                + "\n Successive approximation bit position low: " + bitPositionLow;
        return ret;
    }

    public String toString() {
        return getPart().name();
    }
}
