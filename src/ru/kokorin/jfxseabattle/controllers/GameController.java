package ru.kokorin.jfxseabattle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.kokorin.jfxseabattle.models.Cell;
import ru.kokorin.jfxseabattle.models.Field;

public class GameController {
    private Field computerField;
    private Field playerField;
    @FXML
    private GridPane playerGraphicField;
    @FXML
    private GridPane computerGraphicField;
    @FXML
    private Label labelPrinter;
    @FXML
    private Label labelNamePlayer;

    @FXML
    private void initialize() {
        createComputerGraphicField();
        createPlayerGraphicField();
        labelNamePlayer.setText("Игрок: " + MainController.getPlayer().getName());
    }

    public void btnExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void createComputerGraphicField() {
        double width;
        double height;
        computerField = new Field();
        computerField.createShips();
        for (int i = 0; i < Field.SIZE; i++) {
            height = computerGraphicField.getRowConstraints().get(i).getPrefHeight();
            for (int j = 0; j < Field.SIZE; j++) {
                Cell cell = computerField.getCell(i, j);
                width = computerGraphicField.getColumnConstraints().get(j).getPrefWidth();
                cell.setPrefSize(width, height);
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellClear");
                cell.setOnMouseClicked(event -> {
                    Cell compCell = (Cell) event.getSource();
                    labelPrinter.setText(computerField.fire(compCell));
                    updateViewCell(compCell);
                    computerField.print();
                    int x = (int)(Math.random()*10);
                    int y = (int)(Math.random()*10);
                    Cell playerCell = playerField.getCell(x,y);
                    playerField.fire(playerCell);
                    updateViewCell(playerCell);
                });
                computerGraphicField.add(cell, j, i);
            }
        }
    }

    private void createPlayerGraphicField() {
        double width;
        double height;
        playerField = new Field();
        playerField.createShips();
        for (int i = 0; i < Field.SIZE; i++) {
            height = playerGraphicField.getRowConstraints().get(i).getPrefHeight();
            for (int j = 0; j < Field.SIZE; j++) {
                Cell cell = playerField.getCell(i, j);
                width = playerGraphicField.getColumnConstraints().get(j).getPrefWidth();
                cell.setPrefSize(width, height);
                playerGraphicField.add(cell, j, i);
                updateViewCell(cell);
            }
        }
    }

    private void updateViewCell(Cell cell) {
        switch (cell.getStatus()) {
            case Field.SPASE:
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellClear");
                break;
            case Field.SHIP:
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellShip");
                break;
            case Field.DEADHSHIP:
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellShipDead");
                break;
            case Field.MISS:
                cell.getStyleClass().clear();
                cell.getStyleClass().add("cellMiss");
                break;
        }
    }

}
