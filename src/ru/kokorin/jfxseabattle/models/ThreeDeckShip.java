package ru.kokorin.jfxseabattle.models;

public class ThreeDeckShip {
    private Cell[] cells = new Cell[3];

    ThreeDeckShip(Cell cell, Cell cell2, Cell cell3) {
        cells[0] = cell;
        cells[1] = cell2;
        cells[2] = cell3;
    }

    public void setCell(int number, Cell cell) {
        cells[number] = cell;
    }

    public Cell getCell(int number) {
        return cells[number];
    }
}
