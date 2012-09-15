/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

import image.ImagePart;
import java.util.ArrayList;

/**
 *
 * @author Dietmar
 */
public class JPEGTyp extends image.ImageTyp {

    /**
     * 
     */
    public enum Part {

        /**
         * 
         */
        SOI(false, 0xD8, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        EOI(false, 0xD9, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST0(false, 0xD0, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST1(false, 0xD1, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST2(false, 0xD2, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST3(false, 0xD3, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST4(false, 0xD4, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST5(false, 0xD5, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST6(false, 0xD6, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        RST7(false, 0xD7, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        TEM(false, 0x01, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        APP0(true, 0xE0, "image.jpeg.APP0"),
        /**
         * 
         */
        APP1(true, 0xE1, "image.jpeg.APP"),
        /**
         * 
         */
        APP2(true, 0xE2, "image.jpeg.APP"),
        /**
         * 
         */
        APP3(true, 0xE3, "image.jpeg.APP"),
        /**
         * 
         */
        APP4(true, 0xE4, "image.jpeg.APP"),
        /**
         * 
         */
        APP5(true, 0xE5, "image.jpeg.APP"),
        /**
         * 
         */
        APP6(true, 0xE6, "image.jpeg.APP"),
        /**
         * 
         */
        APP7(true, 0xE7, "image.jpeg.APP"),
        /**
         * 
         */
        APP8(true, 0xE8, "image.jpeg.APP"),
        /**
         * 
         */
        APP9(true, 0xE9, "image.jpeg.APP"),
        /**
         * 
         */
        APP10(true, 0xEA, "image.jpeg.APP"),
        /**
         * 
         */
        APP11(true, 0xEB, "image.jpeg.APP"),
        /**
         * 
         */
        APP12(true, 0xEC, "image.jpeg.APP"),
        /**
         * 
         */
        APP13(true, 0xED, "image.jpeg.APP"),
        /**
         * 
         */
        APP14(true, 0xEE, "image.jpeg.APP"),
        /**
         * 
         */
        APP15(true, 0xEF, "image.jpeg.APP"),
        /**
         * 
         */
        SOF0(true, 0xC0, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF1(true, 0xC1, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF2(true, 0xC2, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF3(true, 0xC3, "image.jpeg.SOF"),
        /**
         * 
         */
        DHT(true, 0xC4, "image.jpeg.DHT"),
        /**
         * 
         */
        SOF5(true, 0xC5, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF6(true, 0xC6, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF7(true, 0xC7, "image.jpeg.SOF"),
        /**
         * 
         */
        JPG(true, 0xC8, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        SOF9(true, 0xC9, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF10(true, 0xCA, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF11(true, 0xCB, "image.jpeg.SOF"),
        /**
         * 
         */
        DAC(true, 0xCC, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        SOF13(true, 0xCD, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF14(true, 0xCE, "image.jpeg.SOF"),
        /**
         * 
         */
        SOF15(true, 0xCF, "image.jpeg.SOF"),
        /**
         * 
         */
        SOS(true, 0xDA, "image.jpeg.SOS"),
        /**
         * 
         */
        DQT(true, 0xDB, "image.jpeg.DQT"),
        /**
         * 
         */
        DNL(true, 0xDC, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        DRI(true, 0xDD, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        DHP(true, 0xDE, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        EXP(true, 0xDF, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG0(true, 0xF0, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG1(true, 0xF1, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG2(true, 0xF2, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG3(true, 0xF3, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG4(true, 0xF4, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG5(true, 0xF5, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG6(true, 0xF6, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG7(true, 0xF7, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG8(true, 0xF8, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG9(true, 0xF9, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG10(true, 0xFA, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG11(true, 0xFB, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG12(true, 0xFC, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        JPG13(true, 0xFD, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        COM(true, 0xFE, "image.jpeg.UnknownPart"),
        /**
         * 
         */
        DATA(false, 0x00, "image.jpeg.Data"),
        /**
         * 
         */
        UNKNOWN(true, 0xFF, "image.jpeg.UnknownPart");
        
        private boolean variable;
        private Byte value;
        private String className = null;

        private Part(boolean variable, int value, String className) {
            this.variable = variable;
            this.value = (byte) value;
            this.className = className;
        }

        /**
         * 
         * @param value
         * @return the type of this Part or unknown if marker is not found
         */
        public static Part getPart(Byte value) {
            for (Part i : Part.values()) {
                if (i.getValue() == value) {
                    return i;
                }
            }
            return UNKNOWN;
        }

        /**
         * 
         * @return the byte value for this part
         */
        public byte getValue() {
            return value;
        }

        /**
         * 
         * @param pos
         * @param data
         * @return the length of this part
         */
        public int getLength(int pos, java.util.ArrayList<Byte> data) {
            if (variable) {
                return 2 + ((data.get(pos + 2) & 0xFF) << 8 | (data.get(pos + 3) & 0xFF)) & 0xFFFF;
            }
            return 2;
        }

        /**
         * 
         * @param pos
         * @param data
         * @param isData
         * @return an instance of the Part object
         */
        public JPEGPart getPartInstance(int pos, java.util.ArrayList<Byte> data, boolean isData) {
            JPEGPart part = null;
            try {
                part = (JPEGPart) Class.forName(className).newInstance();
            } catch (java.lang.Exception e) {
                System.out.println(e.toString());
            }
            part.init(pos, data);
            return part;
        }
    }

    /**
     * 
     */
    public JPEGTyp() {
    }

    @Override
    public String toString() {
        return "JPEG";
    }

    /**
     * 
     * @param data
     * @return the part list of this JPEG
     */
    @Override
    public ArrayList<ImagePart> scan(ArrayList<Byte> data) {
        ArrayList<ImagePart> parts = new ArrayList<ImagePart>();
        int i = 0;

        while (i < data.size()) {
            JPEGPart part = Part.getPart(data.get(i + 1)).getPartInstance(i, data, false);
            parts.add(part);
            i += part.getLength();
            System.out.println(part.getInfo());
            if (part.getPart() == Part.SOS) {
                part = Part.getPart((byte) 0x00).getPartInstance(i, data, true);
                parts.add(part);
                while (part.getPart() != Part.EOI) {
                    if (data.get(i) == -1 && data.get(i + 1) != 0) {
                        part.setLength(i - part.getPos() - 1);
                        System.out.println(part.getInfo());
                        part = Part.getPart(data.get(i + 1)).getPartInstance(i, data, false);
                        parts.add(part);
                        i += part.getLength();
                        if (part.getPart() != Part.EOI) {
                            part = Part.getPart((byte) 0x00).getPartInstance(i, data, true);
                            parts.add(part);
                            System.out.println(part.getInfo());
                        }
                        System.out.println(part.getInfo());
                    } else {
                        i++;
                    }
                }
            }
        }
        return parts;
    }
}
