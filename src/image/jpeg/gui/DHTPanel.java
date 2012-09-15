/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DHTPanel.java
 *
 * Created on 22-Nov-2011, 21:31:24
 */
package image.jpeg.gui;

import image.gui.ImagePartPropertyPanel;
import image.jpeg.events.DHTTableModelEvent;
import java.awt.Color;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Dietmar
 */
public class DHTPanel extends ImagePartPropertyPanel {

    private JPEGHuffmanTable table = null;
    private TableHexEditor editor = new TableHexEditor(0xFF);
    private DHTTableCellRenderer renderer = new DHTTableCellRenderer();
    private DHTLengthsTableCellRenderer renderer1 = new DHTLengthsTableCellRenderer();
    private Color[] colors = new Color[16];

    /** Creates new form DQTPanel
     * @param table 
     */
    public DHTPanel(JPEGHuffmanTable table) {
        initComponents();
        this.table = table;
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            TableColumn col = jTable1.getColumnModel().getColumn(i);
            col.setCellEditor(editor);
        }
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            TableColumn col = jTable2.getColumnModel().getColumn(i);
            col.setCellEditor(editor);
        }
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            TableColumn col = jTable1.getColumnModel().getColumn(i);
            col.setCellRenderer(renderer1);
        }
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            TableColumn col = jTable2.getColumnModel().getColumn(i);
            col.setCellRenderer(renderer);
        }
        int r = 0xFF;
        int g = 0xFF;
        int b = 0xFF;
        for (int j = 1; j < 17; j++) {
            for (; j % 3 == 0; j++) {
                for (; j % 3 == 0; j++) {
                    b -= 35;
                    if (j < 17) {
                        colors[j - 1] = new Color(r, g, b);
                    }
                }
                g -= 35;
                if (j < 17) {
                    colors[j - 1] = new Color(r, g, b);
                }
            }
            r -= 35;
            if (j < 17) {
                colors[j - 1] = new Color(r, g, b);
            }
        }

    }

    /**
     * 
     */
    public class DHTTableModel extends javax.swing.table.AbstractTableModel {

        @Override
        public int getRowCount() {
//            return (table.getValues().length + 7) / getColumnCount();
            return 32;
        }

        @Override
        public int getColumnCount() {
            return 8;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex * getColumnCount() + columnIndex < table.getValues().length) {
                return String.format("0x%02X", table.getValues()[rowIndex * getColumnCount() + columnIndex]);
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex * getColumnCount() + columnIndex < table.getValues().length) {
                if (((String) aValue).isEmpty()) {
                    aValue = "0";
                }
                short[] values = table.getValues();
                values[rowIndex * getColumnCount() + columnIndex] = Short.parseShort((String) aValue, 16);
                table = new JPEGHuffmanTable(table.getLengths(), values);
                fireTableChanged(new DHTTableModelEvent(this, columnIndex, rowIndex, table));
            }
        }

        @Override
        public String getColumnName(int column) {
            return "";
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (rowIndex * getColumnCount() + columnIndex < table.getValues().length) {
                return true;
            }
            return false;
        }

        /**
         * 
         * @return the Huffman Table
         */
        public JPEGHuffmanTable getTable() {
            return table;
        }

        /**
         * 
         * @return the array of the colors used to render the tables
         */
        public Color[] getColors() {
            return colors;
        }
    }

    /**
     * 
     */
    public class DHTLengthsTableModel extends javax.swing.table.AbstractTableModel {

        @Override
        public int getRowCount() {
            return 2;
        }

        @Override
        public int getColumnCount() {
            return 8;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return String.format("%3d", table.getLengths()[rowIndex * getColumnCount() + columnIndex]);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex * getColumnCount() + columnIndex < table.getValues().length) {
                if (((String) aValue).isEmpty()) {
                    aValue = "0";
                }
                short[] lengths = table.getLengths();
                lengths[rowIndex * getColumnCount() + columnIndex] = Short.parseShort((String) aValue, 16);
                table = new JPEGHuffmanTable(lengths, table.getValues());
                fireTableChanged(new DHTTableModelEvent(this, columnIndex, rowIndex, table));
            }
        }

        @Override
        public String getColumnName(int column) {
            return "";
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            /*
            if (rowIndex * getColumnCount() + columnIndex < part.getDHTTable().getLengths().length) {
            return true;
            }
             */
            return false;
        }

        /**
         * 
         * @return the Huffman Table
         */
        public JPEGHuffmanTable getTable() {
            return table;
        }

        /**
         * 
         * @return the array of the colors used to render the tables
         */
        public Color[] getColors() {
            return colors;
        }
    }

    /**
     * 
     * @param listener
     */
    public void addTableModelListener(TableModelListener listener) {
        jTable1.getModel().addTableModelListener(listener);
        jTable2.getModel().addTableModelListener(listener);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTable2 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(272, 600));

        jTable1.setModel(new DHTLengthsTableModel());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Huffman Table Lengths");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Huffman Table Values");

        jTable2.setModel(new DHTTableModel());
        jTable2.setAutoscrolls(false);
        jTable2.setMaximumSize(new java.awt.Dimension(5000, 200));
        jTable2.setPreferredSize(new java.awt.Dimension(50, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
            .addComponent(jTable2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTable1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTable2, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(542, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
