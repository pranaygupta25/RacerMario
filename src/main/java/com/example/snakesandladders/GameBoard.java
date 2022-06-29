package com.example.snakesandladders;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

public class GameBoard {

    // -----------------------------------------------------------------------------------------------------------------

    private final PowerElement[] powerElements;

    private final Player[] players;

    private final Dice dice;

    // -----------------------------------------------------------------------------------------------------------------

    public GameBoard(ImageView player1mover, ImageView player1token, ImageView player2mover, ImageView player2token,
                     Button diceButton, ImageView diceHolder) {
        int[][] ladders = {{2, 9}, {13, 22}};
        Ladder ladders1 = new Ladder(ladders);
        int[][] snakes = {{24, 7}, {17, 7}, {14, 7}, {8, 3}, {19, 12}};
        Snake snakes1 = new Snake(snakes);
        this.powerElements = new PowerElement[2];
        this.powerElements[0] = snakes1;
        this.powerElements[1] = ladders1;
        this.players = new Player[2];
        this.players[0] = new Player(player1mover, player1token, 1);
        this.players[1] = new Player(player2mover, player2token, 2);
        this.dice = new Dice(diceButton, diceHolder);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private <T extends PowerElement> boolean checkForSnakesAndLadders(int player, int currentPlayerPosition, T element){
        int destinationTile = currentPlayerPosition;
        int[] destination = CoordinateLookup.getCoordinates(currentPlayerPosition);
        if (element.isPowerElement(currentPlayerPosition)){
            destination = element.destinationCoordinates(currentPlayerPosition);
            destinationTile = element.destinationTileNumber(currentPlayerPosition);
            this.players[player-1].moveToTile(destinationTile, destination);
            return true;
        }
        return false;
    }

    private int checkForSpecialTiles(int player) {
        int currentPlayerPosition = this.players[player-1].getCurrentPosition();
        if (currentPlayerPosition == 25)
            return 1;
        for (PowerElement element: this.powerElements) {
            if(checkForSnakesAndLadders(player, currentPlayerPosition, element))
                break;
        }
        return 0;
    }

    public int play(MouseEvent event, int player){
        if (!(this.players[player-1].isLocked()))
            this.players[player-1].repeat(event);
        return checkForSpecialTiles(player);
    }

    public void moveTokenByOne(MouseEvent event, int player) {
        this.players[player-1].moveByOne();
    }

    public void rollDice(int player) {
        int dieRoll = dice.roll();
        if (this.players[player-1].getCurrentPosition() + dieRoll > 25)
            return;
        this.players[player-1].setCurrentDieRoll(dieRoll);
        if (dieRoll == 1)
            this.players[player-1].setLocked(false);
    }

    // -----------------------------------------------------------------------------------------------------------------
    
}
