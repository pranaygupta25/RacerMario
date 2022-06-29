package com.example.snakesandladders;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.FileInputStream;

public class Player {

    // -----------------------------------------------------------------------------------------------------------------

    @FXML
    final private ImageView playerMover;

    @FXML
    final private ImageView playerToken;

    private int currentPosition;

    private int currentDieRoll;

    private boolean locked;

    private Image right;
    private Image left;

    // -----------------------------------------------------------------------------------------------------------------

    public Player(ImageView playerMover, ImageView playerToken, int playerNumber) {
        this.playerMover = playerMover;
        this.playerToken = playerToken;
        this.currentPosition = 1;
        this.currentDieRoll = 0;
        this.locked = false;
        String rightURL = "src/main/resources/img/playerTokens/player" + Integer.toString(playerNumber) + "r.png";
        try {
            this.right = new Image(new FileInputStream(rightURL));
        }
        catch (Exception e){
            System.out.println("Error occurred while reading");
        }
        String leftURL = "src/main/resources/img/playerTokens/player" + Integer.toString(playerNumber) + "l.png";
        try {
            this.left = new Image(new FileInputStream(leftURL));
        }
        catch (Exception e){
            System.out.println("Error occurred while reading");
        }
        if (((((this.currentPosition - 1) / 5) & 1) == 0) && (playerToken.getImage() != this.right))
            playerToken.setImage(this.right);
        else if (((((this.currentPosition - 1) / 5) & 1) != 0) && (playerToken.getImage() != this.left))
            playerToken.setImage(this.left);
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setCurrentDieRoll(int currentDieRoll) {
        this.currentDieRoll = currentDieRoll;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void moveToTile(int tileNumber, int[] coordinates) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(this.playerToken);
        translate.setToX(coordinates[0]);
        translate.setToY(coordinates[1]);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(1);
        translate.setAutoReverse(false);
        translate.play();
        this.currentPosition = tileNumber;
        if (((((this.currentPosition - 1) / 5) & 1) == 0) && (playerToken.getImage() != this.right))
            playerToken.setImage(this.right);
        else if (((((this.currentPosition - 1) / 5) & 1) != 0) && (playerToken.getImage() != this.left))
            playerToken.setImage(this.left);
    }

    synchronized void moveByOne() {
        int[] coordinates = CoordinateLookup.getCoordinates(this.currentPosition);
        this.playerToken.setTranslateX(coordinates[0]);
        this.playerToken.setTranslateY(coordinates[1]);
        if (((((this.currentPosition - 1) / 5) & 1) == 0) && (playerToken.getImage() != this.right))
            playerToken.setImage(this.right);
        else if (((((this.currentPosition - 1) / 5) & 1) != 0) && (playerToken.getImage() != this.left))
            playerToken.setImage(this.left);
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
