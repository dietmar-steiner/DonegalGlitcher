/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image;

/**
 *
 * @author Dietmar
 */
public class PartEvent {

    private ImagePart part = null;

    /**
     * 
     * @param part
     */
    public PartEvent(ImagePart part) {
        this.part = part;
    }

    /**
     * 
     * @return the Part this event belongs to
     */
    public ImagePart getPart() {
        return part;
    }

}
