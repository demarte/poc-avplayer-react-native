package com.pocplayermobile;

import android.util.ArrayMap;

import com.pocplayermobile.model.PlayerFields;

public class Globals {

    private static final Globals ourInstance = new Globals();
    private ArrayMap<String, PlayerFields> playerFieldsArrayMap = new ArrayMap<>();
    private String currentPlayerFile = "";

    public static Globals getInstance() {
        return ourInstance;
    }

    private Globals() {
    }

    public ArrayMap<String, PlayerFields> getPlayerFieldsArrayMap() {
        return playerFieldsArrayMap;
    }

    public String getCurrentPlayerFile() {
        return currentPlayerFile;
    }

    public void setCurrentPlayerFile(String currentPlayerFile) {
        this.currentPlayerFile = currentPlayerFile;
    }
}


