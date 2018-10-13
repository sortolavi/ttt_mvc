
package model;

import java.util.Arrays;
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
    private final int[] winningRow;
    private String winner;
    private int numPlayed;
	
    public Game() {
        this.data = new String[9];
        this.winningRow = new int[3];
        resetData();
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
                    
                    for(int j = 0; j < 3; j++) {
                        int k = WINNINGCOMBINATIONS[i][j];
                        winningRow[j]=k;
                    }
                    
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
    public String[] requestData(){
        return data;
    }
    public int[] requestWinningRow(){
        return winningRow;
    }

    // Testing model change but not changing view
    public void virtualSet(String player, int x){
        if(data[x].equals(EMPTY)) {
            data[x] = player;
        }
        else data[x] = EMPTY;
    }

    // Controller Changes Model State
    public void set(String player, int x) {
        
        if(data[x].equals(EMPTY)) {
            data[x] = player;
            numPlayed++;
            
            if(winnerFound(player)){
                this.winner = player;
            }
            else if(tableFull()) {
                this.winner = "Tie";
            }
        }
    }
    
    public final void resetData() {
        numPlayed = 0;
        for(int x= 0; x<9; x++) {
            data[x] = EMPTY;
        }
        winner = null;
    }
}
