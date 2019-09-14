package com.pocplayermobile.model;

public class PlayerFields {

    private long position = 0;
    private boolean playing = false;

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getPosition() {
        return position;
    }

    public boolean isPlaying() {
        return playing;
    }
}
