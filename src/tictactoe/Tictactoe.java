package tictactoe;

import model.Game;
import view.Board;
import model.ai.*;
import controller.Controller;

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
        
        int whoStarts = JOptionPane.showConfirmDialog(null, "AI moves first?", "Start the game",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        boolean aiStart = (whoStarts == JOptionPane.YES_OPTION)? true : false;
        
        Game game = new Game();
        Board board = new Board();
        MoveStrategy ms = new Minimax();
//        MoveStrategy ms = new Casual();

        Controller controller = new Controller(board, game, ms);
        board.addEventListener(controller);
        
        controller.initGame(aiStart);
    }
    
}
