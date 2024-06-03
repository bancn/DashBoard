package com.example.dashboard.ui;

import com.example.dashboard.ui.model.Employee;
import com.example.dashboard.ui.widgets.ApiReportTable;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class DashboardFormTest extends BasePlatformTestCase {

    private DashboardForm dashboardForm;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        dashboardForm = new DashboardForm();
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("John Doe", 28, true, 60000.0, "B1");
        dashboardForm.addEmployee(employee);

        ApiReportTable table = getTableFromDashboardForm();
        assertEquals(1, table.getRowCount());
        assertEquals("John Doe", table.getValueAt(0, 0));
    }

    @Test
    public void testRemoveEmployee() {
        Employee employee1 = new Employee("John Doe", 28, true, 60000.0, "B1");
        Employee employee2 = new Employee("Jane Smith", 32, false, 70000.0, "A2");
        dashboardForm.addEmployee(employee1);
        dashboardForm.addEmployee(employee2);

        ApiReportTable table = getTableFromDashboardForm();
        assertEquals(2, table.getRowCount());

        dashboardForm.removeEmployee(0);
        assertEquals(1, table.getRowCount());
        assertEquals("Jane Smith", table.getValueAt(0, 0));
    }

    @Test
    public void testClearEmployees() {
        Employee employee1 = new Employee("John Doe", 28, true, 60000.0, "B1");
        Employee employee2 = new Employee("Jane Smith", 32, false, 70000.0, "A2");
        dashboardForm.addEmployee(employee1);
        dashboardForm.addEmployee(employee2);

        ApiReportTable table = getTableFromDashboardForm();
        assertEquals(2, table.getRowCount());

        dashboardForm.clearEmployees();
        assertEquals(0, table.getRowCount());
    }

    @Test
    public void testGetSelectedEmployee() {
        Employee employee1 = new Employee("John Doe", 28, true, 60000.0, "B1");
        Employee employee2 = new Employee("Jane Smith", 32, false, 70000.0, "A2");
        dashboardForm.addEmployee(employee1);
        dashboardForm.addEmployee(employee2);

        ApiReportTable table = getTableFromDashboardForm();
        table.setRowSelectionInterval(1, 1);

        Employee selectedEmployee = dashboardForm.getSelectedEmployee();
        assertNotNull(selectedEmployee);
        assertEquals("Jane Smith", selectedEmployee.getName());
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee1 = new Employee("John Doe", 28, true, 60000.0, "B1");
        Employee employee2 = new Employee("Jane Smith", 32, false, 70000.0, "A2");
        dashboardForm.addEmployee(employee1);
        dashboardForm.addEmployee(employee2);

        Employee updatedEmployee = new Employee("John Smith", 30, true, 75000.0, "C3");
        dashboardForm.updateEmployee(0, updatedEmployee);

        ApiReportTable table = getTableFromDashboardForm();
        assertEquals("John Smith", table.getValueAt(0, 0));
        assertEquals(30, table.getValueAt(0, 1));
    }

    private ApiReportTable getTableFromDashboardForm() {
        JScrollPane scrollPane = (JScrollPane) dashboardForm.getRootPanel().getComponent(0);
        JViewport viewport = scrollPane.getViewport();
        return (ApiReportTable) viewport.getView();
    }
}
