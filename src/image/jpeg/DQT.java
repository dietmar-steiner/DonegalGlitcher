/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.jpeg;

import image.gui.ImagePartPropertyPanel;
import image.jpeg.events.DQTTableModelEvent;
import image.jpeg.gui.DQTPanel;
import donegalglitcher.action.Actions;
import java.awt.Color;
import java.util.ArrayList;
import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Dietmar
 */
public class DQT extends JPEGPart implements TableModelListener {

    private Presision presision = Presision.PRESISION_8BIT;
    private int id = 0;
    private JPEGQTable table;

    /**
     * 
     */
    public enum Presision {

        /**
         * 
         */
        PRESISION_8BIT,
        /**
         * 
         */
        PRESISION_16BIT;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("DQT Table changed");
        DQTTableModelEvent event = (DQTTableModelEvent) e;
        setTable(event.getTable());
    }

    /**
     * 
     * @param pos
     * @param data
     */
    @Override
    public void init(int pos, ArrayList<Byte> data) {
        super.init(pos, data, false);
        int[] values = new int[64];
        if (0x01 == ((data.get(pos + 4) >> 4) & 0xF)) {
            presision = Presision.PRESISION_16BIT;
        }
        id = (data.get(pos + 4) & 0xF);
        for (int i = 0; i < 64; i++) {
            if (Presision.PRESISION_8BIT == presision) {
                values[i] = data.get(pos + 5 + i);
            } else {
                values[i] = ((data.get(pos + 5 + i * 2) & 0xFF) << 8 | (data.get(pos + 6 + i * 2) & 0xFF)) & 0xFFFF;
            }
        }
        table = new JPEGQTable(values);
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
                + "\n Presision: " + presision + "Bit";
        ret += " \n Values: ";
        String format = "%02X";
        if (Presision.PRESISION_16BIT == presision) {
            format = "%04X";
        }
        for (int i = 0; i < 4; i++) {
            ret += "\n";
            for (int j = 0; j < 16; j++) {
                ret += " 0x" + String.format(format, table.getTable()[i * 16 + j]);
            }

        }
        return ret;
    }

    @Override
    public String toString() {
        return getPart().name() + " ID: " + id;
    }

    /**
     * 
     */
    @Override
    public void randomize() {
        int[] values = new int[64];
        for (int i = 0; i < values.length; i++) {
            if (Presision.PRESISION_8BIT == presision) {
                values[i] = (new java.util.Random().nextInt(256) & 0xFF);
            } else {
                values[i] = (new java.util.Random().nextInt(0xFFFF) & 0xFFFF);
            }
        }
        JPEGQTable newTable = new JPEGQTable(values);
        setTable(newTable);
    }

    /**
     * 
     * @return the Property panel
     */
    @Override
    public ImagePartPropertyPanel getPropertyPanel() {
        DQTPanel panel = new DQTPanel(table, presision);
        panel.addTableModelListener(this);
        return panel;
    }

    /**
     * 
     * @return the Quantization Table
     */
    public JPEGQTable getDQTTable() {
        return table;
    }

    /**
     * 
     * @param newTable
     */
    public void setTable(JPEGQTable newTable) {
        int pos = getPos() + 5;
        int[] values = newTable.getTable();
        for (int i = 0; i < values.length; i++) {
            if (Presision.PRESISION_8BIT == presision) {
                getData().set(pos + i, (byte) (values[i] & 0xFF));
            } else {
                getData().set(pos + (i * 2), (byte) ((values[i] >> 8) & 0xFF));
                getData().set(pos + (i * 2) + 1, (byte) (values[i] & 0xFF));

            }
        }
        table = newTable;
        fireDataChangeEvent();
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

    /**
     * 
     * @return the precision
     */
    public Presision getPresision() {
        return presision;
    }
}
