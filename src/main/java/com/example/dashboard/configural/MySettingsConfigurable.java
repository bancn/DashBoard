package com.example.dashboard.configural;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MySettingsConfigurable implements Configurable {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField chromePathField;
    private JPanel mainPanel;

    @Nls
    @Override
    public String getDisplayName() {
        return "My Plugin Settings";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mainPanel = new JPanel(new GridLayout(3, 2));

        mainPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        mainPanel.add(usernameField);

        mainPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);

        mainPanel.add(new JLabel("Chrome Path:"));
        chromePathField = new JTextField();
        mainPanel.add(chromePathField);

        return mainPanel;
    }

    @Override
    public boolean isModified() {
        MySettingsState settings = MySettingsState.getInstance();
        return !usernameField.getText().equals(settings.username)
                || !new String(passwordField.getPassword()).equals(settings.password)
                || !chromePathField.getText().equals(settings.chromePath);
    }

    @Override
    public void apply() {
        MySettingsState settings = MySettingsState.getInstance();
        settings.username = usernameField.getText();
        settings.password = new String(passwordField.getPassword());
        settings.chromePath = chromePathField.getText();
    }

    @Override
    public void reset() {
        MySettingsState settings = MySettingsState.getInstance();

        usernameField.setText(settings.username);
        passwordField.setText(settings.password);
        chromePathField.setText(settings.chromePath);
    }
}