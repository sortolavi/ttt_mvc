package model.ai;

/**
 * To pass AI algorithm best move and value
 * 
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class BestMove {
    public int value;
    public int index;

    public BestMove (int value, int index){
        this.value = value;
        this.index = index;
    }
    public BestMove (int value) {
        this.value = value;
        this.index = 0;
    }
}
