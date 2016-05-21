package ru.kokorin.jfxseabattle.models;

public class OneDeckShip {
    private Cell cell;

    OneDeckShip(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell){
        this.cell = cell;
    }
}
