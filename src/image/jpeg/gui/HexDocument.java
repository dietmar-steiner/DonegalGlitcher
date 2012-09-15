/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg.gui;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Dietmar
 */
class HexDocument
        extends PlainDocument {

    private int maxValue = 0xFF;

    public HexDocument(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void insertString(int offset, String string, AttributeSet attributes)
            throws BadLocationException {

        if (string == null) {
            return;
        } else {
            String newValue;
            int length = getLength();
            if (length == 0) {
                newValue = string;
            } else {
                String currentContent = getText(0, length);
                StringBuilder currentBuffer = new StringBuilder(currentContent);
                currentBuffer.insert(offset, string);
                newValue = currentBuffer.toString();
            }
            try {
                int value = checkInput(newValue);
                if (maxValue >= value) {
                    super.insertString(offset, string, attributes);
                }
            } catch (Exception exception) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

    }

    @Override
    public void remove(int offset, int length)
            throws BadLocationException {

        int currentLength = getLength();
        String currentContent = getText(0,
                currentLength);
        String before = currentContent.substring(
                0, offset);
        String after = currentContent.substring(
                length + offset, currentLength);
        String newValue = before + after;
        try {
            int value = checkInput(newValue);
            if (maxValue >= value) {
                super.remove(offset, length);
            }
        } catch (Exception exception) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private int checkInput(String proposedValue)
            throws NumberFormatException {

        int newValue = 0;
        if (proposedValue.length() > 0) {
            newValue = Integer.parseInt(proposedValue, 16);
        }
        return newValue;
    }
}
