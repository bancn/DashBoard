package com.example.dashboard.ui;

import com.example.dashboard.ui.model.Employee;
import com.intellij.testFramework.LightPlatformTestCase;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.TempDirTestFixture;
import com.intellij.testFramework.fixtures.TestFixtureBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class DashboardFormIntegrationTest extends BasePlatformTestCase {

    @Test
    public void testShowDashboardForm() {
        SwingUtilities.invokeLater(() -> {
            DashboardForm dashboardForm = new DashboardForm();
            JFrame frame = new JFrame("Dashboard Form Test");
            frame.setContentPane(dashboardForm.getRootPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Add some test data
            dashboardForm.addEmployee(new Employee("John Doe", 28, true, 60000.0, "B1"));
            dashboardForm.addEmployee(new Employee("Jane Smith", 32, false, 70000.0, "A2"));
        });

        // Keep the test running to allow manual inspection
        try {
            Thread.sleep(30000); // 30 seconds for manual inspection
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}