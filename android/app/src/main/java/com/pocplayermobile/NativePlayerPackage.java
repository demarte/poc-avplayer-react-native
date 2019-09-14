package com.pocplayermobile;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

public class NativePlayerPackage implements ReactPackage {

    private NativePlayerManager nativePlayerManager;
    private MainApplication application;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.singletonList(
                nativePlayerManager = new NativePlayerManager(application)
        );
    }

    public void setApplication(MainApplication application) {
        this.application = application;
    }

    public void updatePosition() {
        nativePlayerManager.updatePosition();
    }

    public void updatePlaying() {
        nativePlayerManager.updatePlaying();
    }
}
