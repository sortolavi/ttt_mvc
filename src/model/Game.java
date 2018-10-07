
package model;

import view.Board;
/**
 *
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Game {
    // values at game board
    public static final String EMPTY    = "";
    public static final String PLAYER1  = "X";
    public static final String PLAYER2 	= "O";
    
    private static final int[][] WINNINGCOMBINATIONS = new int[][] {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal rows
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical rows
        {0, 4, 8}, {2, 4, 6}             // diagonal rows
    };
    
    private final String[] data;
    private Board board;
    public boolean aiStart;
    private String winner;
    private boolean gameOver;
    private int numPlayed;
	
    public Game() {
        this.data = new String[9];
        resetBoard();
    }
    public String getWinner(){
        return this.winner;
    }
//    public static boolean winnerFound(String id, String[] data){
    public boolean winnerFound(String id){
        for(int i = 0; i < 8 ; i++){
            if(data[WINNINGCOMBINATIONS[i][0]] == id &&  
               data[WINNINGCOMBINATIONS[i][1]] == id && 
               data[WINNINGCOMBINATIONS[i][2]] == id) { 
                    return true;
            }
        }
        return false;
    }
  
//    public boolean tableFull(String[] data){
    public boolean tableFull(){
        for (int i = 0; i < 9; i++) if (data[i].equals(EMPTY)) return false;
        return true;
    }
    
    //State Query used by ai classes
    public String[] requestBoard(){
        return data;
    }

    public void registerViewObserver(Board board) {
        this.board = board;
    }

    // Testing model change but not changing view
    public void virtualSet(String player, int x){
        if(data[x].equals(EMPTY)) {
            data[x] = player;
        }
        else data[x] = EMPTY;
    }

    //Controller Changes Model State
    public String set(String player, int x) {
        
        if(gameOver) {
            return "game over";
        }
        
        if(data[x].equals(EMPTY)) {
            data[x] = player;
            numPlayed++;
            
            if(winnerFound(player)){
                this.winner = player;
                gameOver = true;
            }
            else if(tableFull()) {
                this.winner = "Draw";
                gameOver = true;
            }

            return "model updated";
            
        }
        return "illegal move";
    }
    
    public final void resetBoard() {
        gameOver = false;
//        numPlayed = 0;
        for(int x= 0; x<9; x++) {
            data[x] = EMPTY;
        }
        winner = "none";
    }
}
