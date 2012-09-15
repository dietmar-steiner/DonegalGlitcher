/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

import image.gui.ImagePartPropertyPanel;
import image.jpeg.events.DHTTableModelEvent;
import image.jpeg.gui.DHTPanel;
import donegalglitcher.action.Actions;
import java.awt.Color;
import java.util.ArrayList;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Dietmar
 */
public class DHT extends JPEGPart implements TableModelListener {

    private int id = 0;
    private int tableClass = 0;
    private JPEGHuffmanTable table;

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, ArrayList<Byte> data) {
        super.init(pos, data, false);
        if (0x01 == ((data.get(pos + 4) >> 4) & 0xF)) {
            tableClass = ((data.get(pos + 4) >> 4) & 0xF);
        }
        id = (data.get(pos + 4) & 0xF);
        int total = 0;
        short valueCounts[] = new short[16];
        for (int i = 0; i < 16; i++) {
            valueCounts[i] = (short) (data.get(pos + 5 + i) & 0xFF);
            total += valueCounts[i];
        }
        short[] values = new short[total];
        for (int i = 0; i < total; i++) {
            values[i] = (short) (data.get(pos + 21 + i) & 0xFF);
        }
        table = new JPEGHuffmanTable(valueCounts, values);
    }

    /**
     * 
     * @return the detailed information of this part
     */
    @Override
    public String getInfo() {
        String ret = "Name: " + getPart().name() + "(0x" + String.format("%02X", getPart().getValue()) + ")"
                + "\n Length: " + getLength() + ":0x" + String.format("%04X", getLength())
                + "\n Table ID: " + id
                + "\n Table Class: ";
        if (0 == tableClass) {
            ret += "DC table";
        } else {
            ret += "AC table";
        }
        ret += "\n length count pairs \n";
        for (int i = 0; i < table.getLengths().length; i++) {
            ret += " " + (i + 1) + ":" + table.getLengths()[i];
        }
        ret += "\n Symbol:Huffman Values:\n";
        for (int i = 0; i < table.getValues().length; i++) {
            ret += " 0x" + String.format("%02X", (table.getValues()[i] & 0xFF));
        }
        return ret;
    }

    @Override
    public String toString() {
        if (0 == tableClass) {
            return getPart().name() + " ID: " + id + " DC table";
        }
        return getPart().name() + " ID: " + id + " AC table";
    }

    /**
     * 
     */
    @Override
    public void randomize() {
        short[] values = new short[table.getValues().length];
        for (int i = 0; i < table.getValues().length; i++) {
            values[i] = (short) (new java.util.Random().nextInt(256) & 0xFF);
        }
        JPEGHuffmanTable newTable = new JPEGHuffmanTable(table.getLengths(), values);
        setTable(newTable);
    }

    /**
     * 
     * @return the property panel
     */
    @Override
    public ImagePartPropertyPanel getPropertyPanel() {
        DHTPanel panel = new DHTPanel(table);
        panel.addTableModelListener(this);
        return panel;
    }

    /**
     * 
     * @param newTable
     */
    public void setTable(JPEGHuffmanTable newTable) {
        int pos = getPos() + 5;
        short[] values = newTable.getLengths();
        for (int i = 0; i < values.length; i++) {
            getData().set(pos + i, (byte) (values[i] & 0xFF));
        }
        pos = getPos() + 21;
        values = newTable.getValues();
        for (int i = 0; i < values.length; i++) {
            getData().set(pos + i, (byte) (values[i] & 0xFF));
        }
        table = newTable;
        fireDataChangeEvent();
    }

    /**
     * 
     * @return the Huffman Table
     */
    public JPEGHuffmanTable getDHTTable() {
        return table;
    }

    /**
     * 
     * @return always true
     */
    @Override
    public boolean canBeRandomized() {
        return true;
    }

    /**
     * 
     * @param label
     */
    @Override
    public void render(JLabel label) {
        label.setForeground(Color.GREEN);
    }
    
    /**
     * 
     * @return the pop-up menu
     */
    @Override
    public JPopupMenu getPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(Actions.RANDOMIZE.getMenuItem());
        return menu;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("DQT Table changed");
        DHTTableModelEvent event = (DHTTableModelEvent) e;
        setTable(event.getTable());
    }
}
