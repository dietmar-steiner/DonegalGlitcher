/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image.jpeg;

import image.gui.ImagePartPropertyPanel;
import image.jpeg.gui.SOFPanel;

/**
 *
 * @author Dietmar
 */
public class SOF extends JPEGPart{

    private int presision = 8;
    private int dimY = 0;
    private int dimX = 0;
    private int componentCount = 1;
    private int[][] components = null;

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, java.util.ArrayList<Byte> data) {
        super.init(pos,data,false);
        presision = data.get(pos+4) & 0xFF;
        dimY = ((data.get(pos + 5) & 0xFF) << 8 | (data.get(pos + 6) & 0xFF)) & 0xFFFF;
        dimX = ((data.get(pos + 7) & 0xFF) << 8 | (data.get(pos + 8) & 0xFF)) & 0xFFFF;
        componentCount = data.get(pos+9) & 0xFF;
        components = new int[componentCount][4];
        for (int i = 0; i < componentCount; i++) {
            components[i][0] = data.get(pos+10+i*3) & 0xFF;
            components[i][1] = ((data.get(pos + 11+i*3) & 0xFF) >> 4) & 0xF;
            components[i][2] = (data.get(pos + 11+i*3) & 0xFF) & 0xF;
            components[i][3] = data.get(pos + 12+i*3) & 0xFF;
        }
    }
    /**
     * 
     * @return the encoding of this Frame
     */
    public String getEncoding() {
         switch (getPart()) {
             case SOF0: return "Baseline DCT";
             case SOF1: return "Extended sequential DCT, Huffman coding";
             case SOF2: return "Progressive DCT, Huffman coding";
             case SOF3: return "Lossless (sequential), Huffman coding";
             case SOF5: return "Differential sequential DCT, Huffman coding";
             case SOF6: return "Differential progressive DCT, Huffman coding";
             case SOF7: return "Differential lossless (sequential), Huffman coding";
             case SOF9: return "Extended sequential DCT, arithmetic coding";
             case SOF10: return "Progressive DCT, arithmetic coding";
             case SOF11: return "Lossless (sequential), arithmetic coding";
             case SOF13: return "Differential sequential DCT, arithmetic coding";
             case SOF14: return "Differential progressive DCT, arithmetic coding";
             case SOF15: return "Differential lossless (sequential), arithmetic coding";
         }
         return null;
     }

     /**
      * 
      * @return the detailed information of this part
      */
     @Override
    public String getInfo() {
        String ret = "Name: " + getPart().name() + "(0x"+ String.format("%02X", getPart().getValue()) + ")"
                + "\n Encoding: " + getEncoding()
                + "\n Length: " + getLength() + ":0x" + String.format("%04X", getLength())
                + "\n Presision: " + presision
                + "\n Image Size: " + dimX + ":" + dimY
                + "\n Component Count: " + componentCount;
        for (int i = 0; i < componentCount; i++) {
            ret += " \n Component: " + components[i][0]
                    + " Horizontal Factor: " + components[i][1]
                    + " Vertical Factor: " + components[i][2]
                    + " Quantization Table: " + components[i][3];
        }
        return ret;
    }

    @Override
    public String toString() {
        return getPart().name() + " " + getEncoding();
    }
    
    /**
     * 
     * @return the Property panel of this Part
     */
    @Override
    public ImagePartPropertyPanel getPropertyPanel() {
        SOFPanel panel = new SOFPanel(presision, dimY, dimX, componentCount, components);
        return panel;
    }

}
