package ru.kokorin.jfxseabattle.models;

public class TwoDeckShip {
    private Cell[] cells = new Cell[2];

    TwoDeckShip(Cell cell, Cell cell2) {
        cells[0] = cell;
        cells[1] = cell2;
    }

    public void setCell(int index, Cell cell){
        cells[index] = cell;
    }

    public Cell getCell(int index){
        return cells[index];
    }
}
