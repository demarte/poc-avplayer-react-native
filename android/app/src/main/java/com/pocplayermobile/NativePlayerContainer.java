package com.pocplayermobile;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.Player;

public class NativePlayerContainer extends LinearLayout {

    private NativePlayer nativePlayer;
    private Context context;
    private MainApplication application;
    private ProgressBar progressBar;

    public NativePlayerContainer(@NonNull Context context, MainApplication application) {
        super(context);
        this.context = context;
        this.application = application;

        LayoutInflater.from(context).inflate(R.layout.player_container, this);
    }

    public void setFile(String file) {

        nativePlayer = findViewById(R.id.exoplayer);
        progressBar = findViewById(R.id.progress_bar);
        nativePlayer.setFile(file);
        verifyBuffering();
        nativePlayer.updatePosition();
        nativePlayer.updatePlaying();
        nativePlayer.setFullScreenListener(new NativePlayer.FullScreenListener() {
            @Override
            public void onEnter() {
                openFullscreenDialog();
            }

            @Override
            public void onExit() {
                closeFullscreenDialog();
            }
        });
    }

    private void verifyBuffering() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
           if (nativePlayer.getPlayer().getPlaybackState() == Player.STATE_BUFFERING) {
               verifyBuffering();
           } else {
                progressBar.setVisibility(GONE);
           }
        }, 1000);
    }

    private void openFullscreenDialog() {

//        ((ViewGroup) nativePlayer.getParent()).removeView(nativePlayer);
//        nativePlayer.mFullScreenDialog.addContentView(nativePlayer, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        nativePlayer.mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.ic_fullscreen_skrink));
//        nativePlayer.mExoPlayerFullscreen = true;
//        nativePlayer.mFullScreenDialog.show();
        nativePlayer.setLayoutParams(new FrameLayout.LayoutParams(500, 500));


    }

    private void closeFullscreenDialog() {

        ((ViewGroup) nativePlayer.getParent()).removeView(nativePlayer);
        ((FrameLayout) findViewById(R.id.main_media_frame_player)).addView(nativePlayer);

        nativePlayer.mExoPlayerFullscreen = false;
        application.reloadScreen(
                nativePlayer.getPlayer().getContentPosition(),
                nativePlayer.isPlaying());
        nativePlayer.mFullScreenDialog.dismiss();
        nativePlayer.mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.ic_fullscreen_expand));
    }

    public void setPosition(long playerPosition) {
        nativePlayer.setPosition(playerPosition);
    }

    public void setPlaying(boolean playing) {
        nativePlayer.setPlaying(playing);
    }

    public void updatePosition() {
        nativePlayer.updatePosition();
    }

    public void updatePlaying() {
        nativePlayer.updatePlaying();
    }
}
