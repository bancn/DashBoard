package com.example.dashboard.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class DashBoardWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        DashboardForm dashboardForm = new DashboardForm();
        Content content = ContentFactory.getInstance().createContent(dashboardForm.getRootPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
