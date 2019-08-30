package com.pocplayermobile;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class NativePlayer extends PlayerView {

    public NativePlayer(Context context) {
        super(context);
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context);


        this.setControllerAutoShow(true);

        this.setPlayer(player);

        Uri uri = Uri.parse("https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8");

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "com.example.testplayer"));


        MediaSource mediaSource = buildMediaSource(uri, null, dataSourceFactory);

        player.prepare(mediaSource);

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
