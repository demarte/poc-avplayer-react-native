package com.pocplayermobile;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.pocplayermobile.model.PlayerFields;

public class NativePlayerManager extends SimpleViewManager<NativePlayerContainer> {

    private NativePlayerContainer nativePlayerContainer;
    private MainApplication application;

    public NativePlayerManager(MainApplication application) {
        this.application = application;
    }

    public void setPlayerPosition(long playerPosition) {
        if (nativePlayerContainer != null) {
            nativePlayerContainer.setPosition(playerPosition);
        }
    }

    public void setPlaying(boolean playing) {
        if (nativePlayerContainer != null) {
            nativePlayerContainer.setPlaying(playing);
        }
    }

    @ReactProp(name = "file")
    public void setNativePlayerFile(NativePlayerContainer nativePlayerContainer, String file) {
        nativePlayerContainer.setFile(file);
        Globals.getInstance().setCurrentPlayerFile(file);
        if (!Globals.getInstance().getPlayerFieldsArrayMap().containsKey(file)) {
            Globals.getInstance().getPlayerFieldsArrayMap().put(file, new PlayerFields());
        }
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    protected NativePlayerContainer createViewInstance(ThemedReactContext reactContext) {
        nativePlayerContainer = new NativePlayerContainer(reactContext, application);
        return nativePlayerContainer;

    }

    public void updatePosition() {
        nativePlayerContainer.updatePosition();
    }

    public void updatePlaying() {
        nativePlayerContainer.updatePlaying();
    }
}
