package com.pocplayermobile;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class NativePlayerManager extends SimpleViewManager<NativePlayer> {
    @Override
    public String getName() {
        return "Player";
    }

    @Override
    protected NativePlayer createViewInstance(ThemedReactContext reactContext) {

        return new NativePlayer(reactContext);

    }
}
