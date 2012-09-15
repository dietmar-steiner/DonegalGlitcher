/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Dietmar
 */
public class DHTTableCellRenderer extends DefaultTableCellRenderer {
    // This method is called each time a cell in a column
    // using this renderer needs to be rendered.

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, colIndex);
        cell.setBackground(Color.WHITE);
        int index = rowIndex * table.getColumnCount() + colIndex;
        if (index >= ((DHTPanel.DHTTableModel) table.getModel()).getTable().getValues().length) {
            return cell;
        }
        int color = -1;
        int counter = 0;
        int valueIndex = 0;
        while (counter <= index) {
            counter += ((DHTPanel.DHTTableModel) table.getModel()).getTable().getLengths()[valueIndex];
            if (((DHTPanel.DHTTableModel) table.getModel()).getTable().getLengths()[valueIndex] > 0)  {
                color++;
            }
            valueIndex++;
        }
        cell.setBackground(((DHTPanel.DHTTableModel) table.getModel()).getColors()[color]);
        return cell;
    }

    // The following methods override the defaults for performance reasons
    public void validate() {
    }

    public void revalidate() {
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    }

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
    }
}
