package com.example.dashboard.ui;

import com.example.dashboard.ui.model.Employee;
import com.example.dashboard.ui.widgets.ApiReportTable;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DashboardForm {
    private JPanel rootPanel;
    private JScrollPane apiReportScroolPane;
    private ApiReportTable apiReportTable;

    public DashboardForm() {
        apiReportTable = new ApiReportTable();
        apiReportScroolPane.setViewportView(apiReportTable);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    // 其他可能的方法用于访问和操作 apiReportTable
    public void addEmployee(Employee employee) {
        apiReportTable.addEmployee(employee);
    }

    public void removeEmployee(int index) {
        apiReportTable.removeEmployee(index);
    }

    public void clearEmployees() {
        apiReportTable.clearEmployees();
    }

    public Employee getSelectedEmployee() {
        return apiReportTable.getSelectedEmployee();
    }

    public void updateEmployee(int index, Employee employee) {
        apiReportTable.updateEmployee(index, employee);
    }
}
