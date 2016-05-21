package ru.kokorin.jfxseabattle.models;

import java.util.ArrayList;


public class Field {
    public static final char SHIP = 'o';
    public static final char DEADHSHIP = 'x';
    public static final char MISS = '`';
    public static final char SPASE = '_';
    private Cell[][] cells;
    private Boolean[][] map; // вспомогательный массив для расстановки кораблей
    public static final int SIZE = 10;
    private ArrayList<OneDeckShip> oneDeckShips = new ArrayList<>();
    private ArrayList<TwoDeckShip> twoDeckShips = new ArrayList<>();
    private ArrayList<ThreeDeckShip> threeDeckShips = new ArrayList<>();
    private ArrayList<FourDeckShip> fourDeckShips = new ArrayList<>();

    public Field() {
        cells = new Cell[SIZE][SIZE];
        map = new Boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell();
                map[i][j] = true; // изначально все ячейки доступны для размещения кораблей
            }
        }
    }

    public void print() {
        int number = 0;
        System.out.println("   0| 1| 2| 3| 4| 5| 6| 7| 8| 9|");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(number + "|");
            number++;
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + cells[i][j].getStatus() + "|");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(SHIP + " - целый корабль;");
        System.out.println(DEADHSHIP + " - раненый корабль;");
        System.out.println(MISS + " - промах;");
        System.out.println();
    }

    public String fire(Cell cell) {

        for (OneDeckShip ship : oneDeckShips) { // проверка на попадание в однопалубник
            if (cell.equals(ship.getCell())) {
                ship.setCell(null);
                cell.setStatus(DEADHSHIP);
                return "Убил!";

            }
        }
        for (TwoDeckShip ship : twoDeckShips) { // проверка на попадание в двухпалубник
            if (cell.equals(ship.getCell(0))) {
                ship.setCell(0, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(1) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(1))) {
                ship.setCell(1, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(0) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
        }
        for (ThreeDeckShip ship : threeDeckShips) { // проверка на попадание в трехпалубник
            if (cell.equals(ship.getCell(0))) {
                ship.setCell(0, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(1) == null && ship.getCell(2) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(1))) {
                ship.setCell(1, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(0) == null && ship.getCell(2) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(2))) {
                ship.setCell(2, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(1) == null && ship.getCell(0) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
        }
        for (FourDeckShip ship : fourDeckShips) { // проверка на попадание в четырехпалубник
            if (cell.equals(ship.getCell(0))) {
                ship.setCell(0, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(1) == null && ship.getCell(2) == null && ship.getCell(3) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(1))) {
                ship.setCell(1, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(0) == null && ship.getCell(2) == null && ship.getCell(3) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(2))) {
                ship.setCell(2, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(0) == null && ship.getCell(1) == null && ship.getCell(3) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
            if (cell.equals(ship.getCell(3))) {
                ship.setCell(3, null);
                cell.setStatus(DEADHSHIP);
                if (ship.getCell(0) == null && ship.getCell(2) == null && ship.getCell(1) == null) {
                    return "Убил!";
                } else return "Ранил!";
            }
        }
        if (cell.getStatus() == '_') { // если попали в пустую ячейку
            cell.setStatus(MISS);
            return "Мимо!";

        } else return "В эту ячейку уже стреляли!";
    }

    public void createShips() {
        createThreeDeckShip();
        createFourDeckShip();
        createTwoDeckShip();
        createOneDeckShips();
    }

    private void createOneDeckShips() {
        for (int i = 0; i < 4; i++) {
            boolean result = false;
            while (!result) {
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);
                if (map[x][y]) {
                    oneDeckShips.add(new OneDeckShip(cells[x][y]));
                    cells[x][y].setStatus(SHIP);
                    initMap(x, y);
                    result = true;
                }
            }
        }
    }

    private void createTwoDeckShip() {
        for (int i = 0; i < 3; i++) {
            boolean result = false;
            while (!result) {
                int x = (int) (Math.random() * 9);
                int y = (int) (Math.random() * 9);
                if (map[x][y] && map[x][y + 1]) { //vertical
                    twoDeckShips.add(new TwoDeckShip(cells[x][y], cells[x][y + 1]));
                    cells[x][y].setStatus(SHIP);
                    initMap(x, y);
                    cells[x][y + 1].setStatus(SHIP);
                    initMap(x, y + 1);
                    result = true;
                } else if (map[x][y] && map[x + 1][y]) { //horizontal
                    twoDeckShips.add(new TwoDeckShip(cells[x][y], cells[x + 1][y]));
                    cells[x][y].setStatus(SHIP);
                    initMap(x, y);
                    cells[x + 1][y].setStatus(SHIP);
                    initMap(x + 1, y);
                    result = true;
                }
            }
        }
    }

    private void createThreeDeckShip() {
        for (int i = 0; i < 2; i++) {
            boolean result = false;
            while (!result) {
                int x = (int) (Math.random() * 8);
                int y = (int) (Math.random() * 8);
                if (map[x][y] && map[x][y + 1] && map[x][y + 2]) { //vertical
                    threeDeckShips.add(new ThreeDeckShip(cells[x][y], cells[x][y + 1], cells[x][y + 2]));
                    cells[x][y].setStatus(SHIP);
                    initMap(x, y);
                    cells[x][y + 1].setStatus(SHIP);
                    initMap(x, y + 1);
                    cells[x][y + 2].setStatus(SHIP);
                    initMap(x, y + 2);
                    result = true;
                } else if (map[x][y] && map[x + 1][y] && map[x + 2][y]) { //horizontal
                    threeDeckShips.add(new ThreeDeckShip(cells[x][y], cells[x + 1][y], cells[x + 2][y]));
                    cells[x][y].setStatus(SHIP);
                    initMap(x, y);
                    cells[x + 1][y].setStatus(SHIP);
                    initMap(x + 1, y);
                    cells[x + 2][y].setStatus(SHIP);
                    initMap(x + 2, y);
                    result = true;
                }
            }
        }
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    private void createFourDeckShip() {
        boolean result = false;
        while (!result) {
            int x = (int) (Math.random() * 7);
            int y = (int) (Math.random() * 7);
            if (map[x][y] && map[x][y + 1] && map[x][y + 2] && map[x][y + 3]) { //vertical
                fourDeckShips.add(new FourDeckShip(cells[x][y], cells[x][y + 1], cells[x][y + 2], cells[x][y + 3]));
                cells[x][y].setStatus(SHIP);
                initMap(x, y);
                cells[x][y + 1].setStatus(SHIP);
                initMap(x, y + 1);
                cells[x][y + 2].setStatus(SHIP);
                initMap(x, y + 2);
                cells[x][y + 3].setStatus(SHIP);
                initMap(x, y + 3);
                result = true;
            } else if (map[x][y] && map[x + 1][y] && map[x + 2][y] && map[x + 3][y]) { //horizontal
                fourDeckShips.add(new FourDeckShip(cells[x][y], cells[x + 1][y], cells[x + 2][y], cells[x + 3][y]));
                cells[x][y].setStatus(SHIP);
                initMap(x, y);
                cells[x + 1][y].setStatus(SHIP);
                initMap(x + 1, y);
                cells[x + 2][y].setStatus(SHIP);
                initMap(x + 2, y);
                cells[x + 3][y].setStatus(SHIP);
                initMap(x + 3, y);
                result = true;
            }
        }
    }

    private void initMap(int x, int y) {
        map[x][y] = false;
        if (x == 0 && y == 0) { // левый верхний угол
            y++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y--;
            map[x][y] = false;

        } else if (x == 0 && y == 9) { // правый верхний угол
            y--;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y++;
            map[x][y] = false;

        } else if (x == 9 && y == 0) { // левый нижний угол
            y++;
            map[x][y] = false;
            x--;
            map[x][y] = false;
            y--;
            map[x][y] = false;

        } else if (x == 9 && y == 9) { // правый нижний угол
            y--;
            map[x][y] = false;
            x--;
            map[x][y] = false;
            y++;
            map[x][y] = false;

        } else if (x == 0 && y > 0 && y < 9) { // верхний край
            y++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            x--;
            map[x][y] = false;

        } else if (x == 9 && y > 0 && y < 9) { // нижний край
            y++;
            map[x][y] = false;
            x--;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            x++;
            map[x][y] = false;

        } else if (y == 0 && x > 0 && x < 9) { // левый край
            x--;
            map[x][y] = false;
            y++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y--;
            map[x][y] = false;

        } else if (y == 9 && x > 0 && x < 9) { // правый край
            x--;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y++;
            map[x][y] = false;

        } else { // все поле кроме краев
            x--;
            map[x][y] = false;
            y++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            x++;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            y--;
            map[x][y] = false;
            x--;
            map[x][y] = false;
            x--;
            map[x][y] = false;
        }
    }
}
