/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.gui;

import donegalglitcher.action.Actions;
import donegalglitcher.gui.View;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Dietmar
 */
public class ImageTreeControl extends JTree implements TreeSelectionListener, TreeModelListener {

    /**
     * 
     */
    public ImageTreeControl() {
        addTreeSelectionListener(this);
        setCellRenderer(new ImageTreeCellRenderer());
        addMouseListener(new PopupMenuMouseAdapter());
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        if (null != e.getNewLeadSelectionPath()) {
            ImageTreeItem part = (ImageTreeItem) ((DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent()).getUserObject();
            System.out.println("selected object: " + part);
            View.getInstance().setPropertyPanel(part.getPropertyPanel());
            Actions.RANDOMIZE.setEnabled(part.canBeRandomized());
            invalidate();
        }

    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class PopupMenuMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                doPopupMenu(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                doPopupMenu(e);
            }
        }

        public void doPopupMenu(MouseEvent e) {
            TreePath selPath = ((JTree)e.getSource()).getPathForLocation(e.getX(),e.getY());
            if (selPath != null) {
                getSelectionModel().setSelectionPath(selPath);
                JPopupMenu menu = ((ImageTreeItem)((DefaultMutableTreeNode)selPath.getLastPathComponent()).getUserObject()).getPopupMenu();
                if (menu != null) {
                    menu.show((JTree)e.getSource(), e.getX(), e.getY());
                }
            }
        }
    };
}
