package tictactoe;

import model.Game;
import view.Board;
import model.ai.*;
import controller.Broker;
import java.util.Arrays;

import javax.swing.*;
/**
 *
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Tictactoe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int whoStarts = JOptionPane.showConfirmDialog(null, "Opponent moves first?", "Start the game",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        boolean oppStart = (whoStarts == JOptionPane.YES_OPTION)? true : false;
        
        Game game = new Game();
        Board board = new Board();
        MoveStrategy ms = new Minimax();
        
        if (args.length != 0) {
            switch (args[0]){
            case "Casual":
                ms = new Casual();
            }
        }

        Broker broker = new Broker(board, game, ms);
        board.addEventListener(broker);
        
        broker.initGame(oppStart);
    }
    
}
