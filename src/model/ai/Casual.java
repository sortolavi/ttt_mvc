package model.ai;

import model.Game;
import java.util.*;

/**
 *  Dumb AI chooses randomly from available free slots
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Casual {
    public BestMove bMove(Game game, String player) {

        String[] textBoard = game.requestBoard();
        ArrayList<Integer> arrList = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            if(textBoard[i].equals(Game.EMPTY)) {

                arrList.add(i); // collect all empty slots
            }
        }
        // all slots are full
        if(arrList.isEmpty()) return null;

        // randomly select one of all available slots
        Random rand = new Random(System.currentTimeMillis());

        int index = rand.nextInt(arrList.size());

        int best = arrList.get(index);
        int value = 0;

        return new BestMove(value, best);

    }
}
