package com.example.dashboard.configural;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.Nullable;
@State(
        name = "com.example.dashboard.configural.MySettingsState",
        storages = {@Storage("MyPluginSettings.xml")}
)
public class MySettingsState implements PersistentStateComponent<MySettingsState> {
    public String username = "";
    public String password = "";
    public String chromePath = "";

    public static MySettingsState getInstance() {
        return ServiceManager.getService(MySettingsState.class);
    }

    @Nullable
    @Override
    public MySettingsState getState() {
        return this;
    }

    @Override
    public void loadState(MySettingsState state) {
        this.username = state.username;
        this.password = state.password;
        this.chromePath = state.chromePath;
    }
}
