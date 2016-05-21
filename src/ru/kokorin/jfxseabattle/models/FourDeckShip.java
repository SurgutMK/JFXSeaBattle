package ru.kokorin.jfxseabattle.models;

public class FourDeckShip {
    private Cell[] cells = new Cell[4];

    FourDeckShip(Cell cell, Cell cell2, Cell cell3, Cell cell4) {
        cells[0] = cell;
        cells[1] = cell2;
        cells[2] = cell3;
        cells[3] = cell4;
    }

    public void setCell(int index, Cell cell) {
        cells[index] = cell;
    }

    public Cell getCell(int index) {
        return cells[index];
    }
}
