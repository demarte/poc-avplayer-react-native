package com.pocplayermobile;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class NativePlayerManager extends SimpleViewManager<NativePlayer> {

    @ReactProp(name="file")
    public void setNativePlayerFile(NativePlayer nativePlayer, String file) {
        nativePlayer.setFile(file);
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    protected NativePlayer createViewInstance(ThemedReactContext reactContext) {

        return new NativePlayer(reactContext);

    }
}
