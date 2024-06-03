package com.example.dashboard.ui.widgets;

import com.example.dashboard.ui.model.Employee;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApiReportTable extends TableView<Employee> {
    private ListTableModel<Employee> model;

    public ApiReportTable() {
        initializeTable();
    }

    private void initializeTable() {
        // 定义列信息并设置排序比较器
        ColumnInfo<Employee, String> nameColumn = new ColumnInfo<Employee, String>("Name") {
            @Override
            public String valueOf(Employee employee) {
                return employee.getName();
            }

            @Override
            public Comparator<Employee> getComparator() {
                return Comparator.comparing(Employee::getName);
            }
        };

        ColumnInfo<Employee, Integer> ageColumn = new ColumnInfo<Employee, Integer>("Age") {
            @Override
            public Integer valueOf(Employee employee) {
                return employee.getAge();
            }

            @Override
            public Comparator<Employee> getComparator() {
                return Comparator.comparingInt(Employee::getAge);
            }
        };

        ColumnInfo<Employee, Boolean> overtimeWorkColumn = new ColumnInfo<Employee, Boolean>("OvertimeWork") {
            @Override
            public Boolean valueOf(Employee employee) {
                return employee.isOvertimeWork();
            }

            @Override
            public Comparator<Employee> getComparator() {
                return Comparator.comparing(Employee::isOvertimeWork);
            }
        };

        ColumnInfo<Employee, Double> salaryColumn = new ColumnInfo<Employee, Double>("Salary") {
            @Override
            public Double valueOf(Employee employee) {
                return employee.getSalary();
            }

            @Override
            public Comparator<Employee> getComparator() {
                return Comparator.comparingDouble(Employee::getSalary);
            }
        };

        ColumnInfo<Employee, String> boundaryColumn = new ColumnInfo<Employee, String>("Boundary") {
            @Override
            public String valueOf(Employee employee) {
                return employee.getBoundary();
            }

            @Override
            public Comparator<Employee> getComparator() {
                return Comparator.comparing(Employee::getBoundary);
            }
        };

        ColumnInfo<Employee, ?>[] columns = new ColumnInfo[] {
                nameColumn, ageColumn, overtimeWorkColumn, salaryColumn, boundaryColumn
        };

        // 初始化表格模型
        model = new ListTableModel<>(columns);

        // 设置模型
        setModelAndUpdateColumns(model);

        // 启用排序
        setAutoCreateRowSorter(true);
    }

    public void addEmployee(Employee employee) {
        List<Employee> items = new ArrayList<>(model.getItems());
        items.add(employee);
        model.setItems(items);
    }

    public void removeEmployee(int index) {
        List<Employee> items = new ArrayList<>(model.getItems());
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            model.setItems(items);
        }
    }

    public void clearEmployees() {
        model.setItems(new ArrayList<>());
    }

    public Employee getSelectedEmployee() {
        int selectedRow = getSelectedRow();
        if (selectedRow >= 0) {
            return model.getItem(convertRowIndexToModel(selectedRow));
        }
        return null;
    }

    public void updateEmployee(int index, Employee employee) {
        List<Employee> items = new ArrayList<>(model.getItems());
        if (index >= 0 && index < items.size()) {
            items.set(index, employee);
            model.setItems(items);
        }
    }
}