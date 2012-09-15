/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.gui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Dietmar
 */
public class ImageTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (((DefaultMutableTreeNode) value).getUserObject() instanceof ImageTreeItem) {
            ImageTreeItem item = (ImageTreeItem) ((DefaultMutableTreeNode) value).getUserObject();
            item.render(label);
        }
        return label;
    }
}
