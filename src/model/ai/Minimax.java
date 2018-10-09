package model.ai;

import model.Game;
import model.ai.*;
/**
 *
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Minimax implements Ai {
    private Game game;

    // constant evaluatePosition function return values
    private static final int PLAYER1WIN = 4; // smallest value
    private static final int PLAYER2WIN = 7; // biggest value
    private static final int TIE 	= 5; // tie
    private static final int GOAHEAD	= 6; // continue
    
    public Minimax(){
        
    }
    
    /**
     * Function to evaluate current move
     * @return integer representing win, TIE or continue
     */
    private int evaluatePosition () {
        return game.winnerFound (Game.PLAYER2)? PLAYER2WIN :
               game.winnerFound (Game.PLAYER1)? PLAYER1WIN :
               game.tableFull()               ? TIE        : GOAHEAD; 
    }

    /**
     * Recursive function to go through all possible moves evaluating them and selecting best option
     * @param game
     * @param player
     * @return instance of BestMove class
     */
    public BestMove bMove(Game game, String player) {
        this.game = game;
        String[] textBoard = game.requestBoard();

        BestMove move;
        int best = 0;
        int eval = evaluatePosition(); 
 
        if (eval != GOAHEAD){
            return new BestMove(eval); // if can't continue then start to unwind recursion
        }

        String opponent = (player.equals(Game.PLAYER1))? Game.PLAYER2 : Game.PLAYER1;

        int value = (player.equals(Game.PLAYER1))? PLAYER2WIN : PLAYER1WIN; // give the worst possible value that can only get better

        for (int i = 0; i < 9; i++){
            if (textBoard[i].equals(Game.EMPTY)){
                
                game.virtualSet(player, i);		// make a move
                move = bMove(game, opponent);		// opponent move, going on recurcively until solution is reached
                game.virtualSet(player, i);		// cancel the move

                // if move value is better, then keep it
                if (player == Game.PLAYER2 && move.value > value || player == Game.PLAYER1 && move.value < value) {
                        value = move.value;
                        best = i;
                        
                }
            }
        }
        return new BestMove (value, best); 

    }
}
