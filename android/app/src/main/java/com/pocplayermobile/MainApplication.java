package com.pocplayermobile;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication, Application.ActivityLifecycleCallbacks {

    private final String EXTRA_POSITION = "extra_position";
    private final String EXTRA_IS_PLAYING = "extra_is_playing";
    private NativePlayerPackage nativePlayerPackage;
    private Activity activity;
    private MainApplication mainApplication;
    private long playerPosition;
    private boolean playing;

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {

            nativePlayerPackage.setApplication(mainApplication);
//            nativePlayerPackage.setPlayerPosition(playerPosition);

            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    nativePlayerPackage
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this,false);
        registerActivityLifecycleCallbacks(this);
        nativePlayerPackage = new NativePlayerPackage();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        this.activity = activity;
        this.mainApplication = this;
        Bundle extras = activity.getIntent().getExtras();

        if (extras != null) {
            playerPosition = extras.getLong(EXTRA_POSITION);
            playing = extras.getBoolean(EXTRA_IS_PLAYING);

            if (nativePlayerPackage != null) {

                String currentKey = Globals.getInstance().getCurrentPlayerFile();
                Globals.getInstance().getPlayerFieldsArrayMap().get(currentKey).setPosition(playerPosition);
                Globals.getInstance().getPlayerFieldsArrayMap().get(currentKey).setPlaying(playing);
                nativePlayerPackage.updatePosition();
                nativePlayerPackage.updatePlaying();
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) { }

    @Override
    public void onActivityResumed(Activity activity) { }

    @Override
    public void onActivityPaused(Activity activity) {
        String currentKey = Globals.getInstance().getCurrentPlayerFile();
        Globals.getInstance().getPlayerFieldsArrayMap().get(currentKey).setPlaying(false);
        nativePlayerPackage.updatePlaying();
    }

    @Override
    public void onActivityStopped(Activity activity) { }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }

    @Override
    public void onActivityDestroyed(Activity activity) { }

    public void reloadScreen(long position, boolean playing) {
        Intent intent = activity.getIntent();
        intent.putExtra(EXTRA_POSITION, position);
        intent.putExtra(EXTRA_IS_PLAYING, playing);
        activity.finish();
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
