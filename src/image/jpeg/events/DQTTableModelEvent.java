/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg.events;

import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

/**
 *
 * @author Dietmar
 */
public class DQTTableModelEvent extends TableModelEvent{
    
    private JPEGQTable table = null;
    
    /**
     * 
     * @param source
     * @param column
     * @param row
     * @param table
     */
    public DQTTableModelEvent(TableModel source, int column, int row, JPEGQTable table) {
        super(source, row, row, column);
        this.table = table;
    }
    
    /**
     * 
     * @return The current Quantization Table
     */
    public JPEGQTable getTable() {
        return table;
    }
}
