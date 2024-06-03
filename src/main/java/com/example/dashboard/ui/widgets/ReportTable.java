package com.example.dashboard.ui.widgets;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ReportTable extends JBTable {

    private TableRowSorter<DefaultTableModel> sorter;

    public ReportTable() {
        super(new DefaultTableModel(new Object[]{"Name", "Age", "OvertimeWork", "Salary", "Boundary"}, 0));
        sorter = new TableRowSorter<>((DefaultTableModel) getModel());
        setRowSorter(sorter);
        getTableHeader().setDefaultRenderer(new SortableHeaderRenderer(getTableHeader().getDefaultRenderer()));
        getTableHeader().addMouseListener(new HeaderMouseListener());

        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[]{"Alice", 30, 10, 5000, 300});
        model.addRow(new Object[]{"Bob", 25, 5, 4000, 200});
        model.addRow(new Object[]{"Carol", 35, 15, 6000, 400});
    }

    private class SortableHeaderRenderer implements TableCellRenderer {
        private final TableCellRenderer delegate;

        public SortableHeaderRenderer(TableCellRenderer delegate) {
            this.delegate = delegate;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, -1, column);
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                int modelColumn = table.convertColumnIndexToModel(column);
                if (sorter.getSortKeys().size() > 0 && sorter.getSortKeys().get(0).getColumn() == modelColumn) {
                    SortOrder sortOrder = sorter.getSortKeys().get(0).getSortOrder();
                    if (sortOrder == SortOrder.ASCENDING) {
                        label.setIcon(UIManager.getIcon("Table.ascendingSortIcon"));
                    } else if (sortOrder == SortOrder.DESCENDING) {
                        label.setIcon(UIManager.getIcon("Table.descendingSortIcon"));
                    } else {
                        label.setIcon(null);
                    }
                } else {
                    label.setIcon(null);
                }
            }
            return comp;
        }
    }

    private class HeaderMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int column = columnAtPoint(e.getPoint());
            if (column != -1) {
                int modelColumn = convertColumnIndexToModel(column);
                List<? extends RowSorter.SortKey> sortKeys = sorter.getSortKeys();
                if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() == modelColumn) {
                    SortOrder sortOrder = sortKeys.get(0).getSortOrder() == SortOrder.ASCENDING ? SortOrder.DESCENDING : SortOrder.ASCENDING;
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(modelColumn, sortOrder)));
                } else {
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(modelColumn, SortOrder.ASCENDING)));
                }
            }
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("ReportTable Example");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            ReportTable table = new ReportTable();
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            model.addRow(new Object[]{"Alice", 30, 10, 5000, 300});
//            model.addRow(new Object[]{"Bob", 25, 5, 4000, 200});
//            model.addRow(new Object[]{"Carol", 35, 15, 6000, 400});
//
//            frame.add(new JScrollPane(table), BorderLayout.CENTER);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
}
