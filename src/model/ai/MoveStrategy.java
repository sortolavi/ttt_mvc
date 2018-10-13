package model.ai;

import model.Game;

/**
 * Interface to be implemented by all AI solutions
 * 
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public interface MoveStrategy {
    
    public BestMove bMove(Game game, String player);
    
}
