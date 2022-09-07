package com.example.untitled6;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    protected TextArea textArea;
    @FXML
    protected Canvas canvas;
    protected GraphicsContext graphicsContext = null;

    @FXML
    protected void onToXButtonClick() {
        textArea.setText(Application.applicationLogic.graphToX());
    }

    @FXML
    protected void onToGraphButtonClick() {
        if (graphicsContext == null) graphicsContext = canvas.getGraphicsContext2D();
        boolean[] render = Application.applicationLogic.xToGraph(textArea.getText());
        for (int i = 0; i < 1802; i++) {
            graphicsContext.setFill(render[i] ? Color.BLACK : Color.WHITE);
            int y = i % 17;
            int x = (1801 - i) / 17;
            Application.applicationLogic.paint(x, y, render[i]);
            graphicsContext.fillRect(x * 15 + 1, y * 15 + 1, 13, 13);
        }
    }

    @FXML
    protected void onCanvasPressed() {
        if (graphicsContext == null) graphicsContext = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            int x = (int) e.getX() / 15 * 15;
            int y = (int) e.getY() / 15 * 15;

            if (x / 15 > 106 || y / 15 > 17 || x / 15 < 0 || y / 15 < 0) return;

            boolean value = false;

            if (e.getButton() == MouseButton.PRIMARY) {
                graphicsContext.setFill(Color.BLACK);
                value = true;
            } else if (e.getButton() == MouseButton.SECONDARY) graphicsContext.setFill(Color.WHITE);
            Application.applicationLogic.paint(x / 15, y / 15, value);
            graphicsContext.fillRect(x + 1, y + 1, 13, 13);
        });
    }

    @FXML
    protected void onCanvasReleased() {
        canvas.setOnMouseDragged(e -> {
        });
    }
}