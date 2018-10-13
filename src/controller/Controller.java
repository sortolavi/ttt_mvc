
package controller;

import java.util.Arrays;
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
    private MoveStrategy ms;
    private String player;
    private boolean aiStart;

    public Controller(Board board, Game game, MoveStrategy ms) {
        this.board = board;
        this.game = game;
        this.ms = ms;
    }
    
    public void newGame(){
        board.resetView();
        game.resetData();
        initGame(!aiStart);
    }
    
    public void initGame(boolean start){
        player = Game.PLAYER1;
        aiStart = start;
        if(aiStart){
            aiMakeMove();
        }
    }
    
    public void changeTurn(){
        player = player.equals(Game.PLAYER1)? Game.PLAYER2 : Game.PLAYER1;
    }

    public void aiMakeMove(){
        BestMove aiMove = ms.bMove(game, player);
        if(aiMove != null) {
            move(aiMove.index);
        }
    }
    
    public void move(int index){
        game.set(player, index);
        board.notifyChanges(player, index);
        changeTurn();
    }

    public void notifyButtonPressed(String buttonName) {

        int x = Integer.valueOf(buttonName);
        move(x);
        if(checkForWinner())return;
        
        aiMakeMove();
        checkForWinner();
    }
    
    private boolean checkForWinner(){
        String winner = game.getWinner();
        if(winner == null) {
            return false;
        }
        
        switch (winner) {		
        case Game.PLAYER1:			
        case Game.PLAYER2:
            board.showWinningRow(game.requestWinningRow());
            board.displayMessage("Player " + winner + " won");
            break;
        default:
            board.displayMessage(winner);			
            break;		
        }
        return true;
    }
}
