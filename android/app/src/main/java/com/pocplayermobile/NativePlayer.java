package com.pocplayermobile;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class NativePlayer extends PlayerView {


    public void setPosition(long playerPosition) {
        player.seekTo(playerPosition);
    }

    public void setPlaying(Boolean playing) {

        if (player.getPlaybackState() == Player.STATE_READY) {
            if (!this.playing && playing) {
                player.setPlayWhenReady(true);
                imageButtonPlay.setVisibility(GONE);
                imageButtonPause.setVisibility(VISIBLE);
                this.playing = true;
            } else if (this.playing && !playing) {
                player.setPlayWhenReady(false);
                imageButtonPause.setVisibility(GONE);
                imageButtonPlay.setVisibility(VISIBLE);
                this.playing = false;
            }
        } else if (player.getPlaybackState() == Player.STATE_BUFFERING) {
            if (!this.playing && playing) {
                final Handler handler = new Handler();
                handler.postDelayed(() -> setPlaying(true), 1000);
            }
        }
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void updatePosition() {
        final String currentKey = Globals.getInstance().getCurrentPlayerFile();
        if (Globals.getInstance().getPlayerFieldsArrayMap().containsKey(currentKey)) {
            final long position = Globals.getInstance().getPlayerFieldsArrayMap().get(currentKey).getPosition();
            this.setPosition(position);
        }
    }

    public void updatePlaying() {
        final String currentKey = Globals.getInstance().getCurrentPlayerFile();
        if (Globals.getInstance().getPlayerFieldsArrayMap().containsKey(currentKey)) {
            final boolean playing = Globals.getInstance().getPlayerFieldsArrayMap().get(currentKey).isPlaying();
            this.setPlaying(playing);
        }
    }

    public interface FullScreenListener {
        void onEnter();

        void onExit();
    }

    private SimpleExoPlayer player;
    private MediaSource mVideoSource;
    public boolean mExoPlayerFullscreen = false;
    private FrameLayout mFullScreenButton;
    public ImageView mFullScreenIcon;
    public Dialog mFullScreenDialog;

    public String file;
    private Context context;

    private ImageButton imageButtonPlay;
    private ImageButton imageButtonPause;
    private Boolean playing = false;

    private FullScreenListener fullScreenListener;

    @Override
    public SimpleExoPlayer getPlayer() {
        return player;
    }

    public void setFile(String file) {
        this.file = file;
        createPlayer();
    }

    public NativePlayer(Context context) {
        super(context);
        this.context = context;
    }

    public NativePlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    private void createPlayer() {

        fullScreenListener = new FullScreenListener() {
            @Override
            public void onEnter() {

            }

            @Override
            public void onExit() {

            }
        };

        imageButtonPlay = findViewById(R.id.play);
        imageButtonPause = findViewById(R.id.pause);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(this.context, trackSelector, loadControl);
        this.setPlayer(player);

        String streamUrl = this.file;
        Uri daUri = Uri.parse(streamUrl);
        String userAgent = Util.getUserAgent(this.context, this.context.getApplicationContext().getApplicationInfo().packageName);
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, null, DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS, DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this.context, null, httpDataSourceFactory);

        mVideoSource = buildMediaSource(daUri, null, dataSourceFactory);

        imageButtonPlay.setOnClickListener(v -> {
            setPlaying(true);
        });

        imageButtonPause.setOnClickListener(v -> {
            setPlaying(false);
        });

        player.prepare(mVideoSource);

        initFullscreenDialog();
        initFullscreenButton();

    }

    private void initFullscreenDialog() {

        mFullScreenDialog = new Dialog(this.context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    fullScreenListener.onExit();
                super.onBackPressed();
            }
        };
    }

    private void initFullscreenButton() {

        mFullScreenIcon = this.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenButton = this.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(v -> {
            if (!mExoPlayerFullscreen)
                fullScreenListener.onEnter();
            else
                fullScreenListener.onExit();
        });
    }

    public void setFullScreenListener(FullScreenListener fullScreenListener) {
        this.fullScreenListener = fullScreenListener;
    }

    private MediaSource buildMediaSource(Uri uri, @Nullable String overrideExtension, DataSource.Factory dataSourceFactory) {
        @C.ContentType int type = Util.inferContentType(uri, overrideExtension);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }
}
