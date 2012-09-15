/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg.events;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

/**
 *
 * @author Dietmar
 */
public class DHTTableModelEvent extends TableModelEvent{
    
    private JPEGHuffmanTable table = null;
    
    /**
     * 
     * @param source
     * @param column
     * @param row
     * @param table
     */
    public DHTTableModelEvent(TableModel source, int column, int row, JPEGHuffmanTable table) {
        super(source, row, row, column);
        this.table = table;
    }
    
    /**
     * 
     * @return the current Huffman Table
     */
    public JPEGHuffmanTable getTable() {
        return table;
    }
}
