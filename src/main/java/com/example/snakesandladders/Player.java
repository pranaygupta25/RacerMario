package com.example.snakesandladders;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Player {

    // -----------------------------------------------------------------------------------------------------------------

    @FXML
    final private ImageView playerMover;

    @FXML
    final private ImageView playerToken;

    private int currentPosition;

    private int currentDieRoll;

    private boolean locked;

    // -----------------------------------------------------------------------------------------------------------------

    public Player(ImageView playerMover, ImageView playerToken) {
        this.playerMover = playerMover;
        this.playerToken = playerToken;
        this.currentPosition = 0;
        this.currentDieRoll = 0;
        this.locked = true;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public ImageView getPlayerToken() {
        return playerToken;
    }

    public void setCurrentDieRoll(int currentDieRoll) {
        this.currentDieRoll = currentDieRoll;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void moveToCell(int cellNumber) {

    }

    synchronized void moveByOne() {
        int[] coordinates = CoordinateLookup.getCoordinates(this.currentPosition);
        this.playerToken.setTranslateX(coordinates[0]);
        this.playerToken.setTranslateY(coordinates[1]);
        notify();
    }

    synchronized void repeat(MouseEvent event){
        for (int i = 1; i <= this.currentDieRoll; i++) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Error while incrementing the steps");
            }
            this.currentPosition++;
            playerToken.fireEvent(event);
            System.out.println("Current: " + this.currentPosition);
        }
        this.currentDieRoll = 0;
    }

    // -----------------------------------------------------------------------------------------------------------------

}
