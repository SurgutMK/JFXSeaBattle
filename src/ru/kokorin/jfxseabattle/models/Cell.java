package ru.kokorin.jfxseabattle.models;

import javafx.scene.control.Button;

/**
 * Created by Misha on 23.10.2015.
 */
public class Cell extends Button {
    private char status;


    public Cell() {
        status = Field.SPASE;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
