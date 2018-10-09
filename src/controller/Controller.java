
package controller;

import view.Board;
import model.Game;
import model.ai.*;

/**
 *
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Controller {
    private Board board;
    private Game game;
    private Ai ai;
    private String player;

    public Controller(Board board, Game game, Ai ai) {
        this.board = board;
        this.game = game;
        this.ai = ai;
    }
    public void newGame(){
        board.resetView();
        game.resetBoard();
        initGame(!game.aiStart);
    }
    public void initGame(boolean start){
        this.player = Game.PLAYER1;
        game.aiStart = start;
        if(game.aiStart){
            aiMoves();
        }
    }
    public void changeTurn(){
        this.player = this.player.equals(Game.PLAYER1)? Game.PLAYER2 : Game.PLAYER1;
    }

    public void aiMoves(){
        BestMove aiMove = ai.bMove(game, player);
        if(aiMove != null) {
            game.set(player, aiMove.index);
            board.notifyChanges(player, aiMove.index);
            changeTurn();
        }
    }
    public void notifyButtonPressed(String buttonName) {

        int x = Integer.valueOf(buttonName);
        
        // set player's move to model
        if(!game.set(player, x)){
            return;
        }
        
        // update board graphics
        board.notifyChanges(player, x);
        
        // stop game if winner found
        if(checkForWinner()) {
            return;
        }
        // otherwise let AI make its move
        changeTurn();
        aiMoves();
        checkForWinner();

    }
    
    private boolean checkForWinner(){
        String winner = game.getWinner();
        if(winner.equals("none")) {
            return false;
        }

        switch (winner) {		
        case Game.PLAYER1:
            board.displayMessage("Player 1 won");
            break;			
        case Game.PLAYER2:
            board.displayMessage("Player 2 won");
            break;
        default:
            board.displayMessage("Tie");			
            break;		
        }
        
        return true;
    }
}
