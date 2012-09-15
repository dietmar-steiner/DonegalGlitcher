/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package image.jpeg.gui;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Dietmar
 */
public class TableHexEditor extends AbstractCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value
    JTextField component = new JTextField();

    /**
     * 
     * @param maxValue
     */
    public TableHexEditor(int maxValue) {
        component.setDocument(new HexDocument(maxValue));
    }

    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at (rowIndex, vColIndex)

        if (isSelected) {
            // cell (and perhaps other cells) are selected
        }

        // Configure the component with the specified value
        component.setText(((String)value).substring(2));

        // Return the configured component
        return component;
    }

    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue() {
        return ((JTextField)component).getText();
    }
}