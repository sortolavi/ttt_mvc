
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
    private Minimax ai;
//    private Casual ai;
    private String player;

    public Controller(Board board, Game game) {
        this.board = board;
        this.game = game;
        this.ai = new Minimax();
//        this.ai = new Casual();
        this.player = Game.PLAYER1;
    }
    public void newGame(){
        game.resetBoard();
        board.resetView();
        this.player = Game.PLAYER1;
        initGame(!game.aiStart);
    }
    public void initGame(boolean start){
        game.aiStart = start;
        if(game.aiStart()){
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
        String statusMsg = game.set(player, x);
        
        board.notifyChanges(player, x);
        
        if(statusMsg.equals("game over")) {
            board.displayMessage(statusMsg);
            return;
        }
        else if(statusMsg.equals("illegal move")){
            return;
        }

        if(checkForWinner()) {
            return;
        }
        // let AI make its move
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
            board.displayMessage("Draw");			
            break;		
        }
        
        return true;
    }
}
