package uielement;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class RowTable
extends JTable {
    Map<Integer, Color> rowColor = new HashMap<Integer, Color>();
    Map<Integer, Color> rowDarkColor = new HashMap<Integer, Color>();

    public RowTable(TableModel model) {
        super(model);
    }
    
    public RowTable(){
    	super();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (!this.isRowSelected(row)) {
            Color color = this.rowColor.get(row);
            c.setBackground(color == null ? this.getBackground() : color);
        } else {
            Color color = this.rowDarkColor.get(row);
            c.setBackground(color == null ? this.getBackground() : color);
        }
        return c;
    }

    public void setRowColor(int row, Color color) {
        this.rowColor.put(row, color);
    }

    public void setRowDarkColor(int row, Color color) {
        this.rowDarkColor.put(row, color);
    }

    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel(10, 4);
        RowTable table = new RowTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setRowColor(1, Color.YELLOW);
        table.setRowDarkColor(1, Color.BLUE);
        table.setRowDarkColor(4, new Color(13369344));
        table.setRowDarkColor(7, Color.BLUE);
        table.setRowColor(4, new Color(16711680));
        table.setRowColor(7, Color.ORANGE);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
