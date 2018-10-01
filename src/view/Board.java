/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Game;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Timo Sorakivi <timo.sorakivi@gmail.com>
 */
public class Board extends JFrame implements ActionListener {
    private JButton[] buttons;
    
    private ImageIcon player1Icon = new ImageIcon("images/cross.gif");
    private ImageIcon player2Icon = new ImageIcon("images/naught.gif");
    private ImageIcon curIcon;

    private Game game;
    private Controller controller;

    public Board(Game game) {
        this.game = game;
        initialize();
    }

    public void addEventListener(Controller controller) {
        this.controller = controller;
    }
    public void resetView(){
        for(int i= 0; i<9; i++) {

            buttons[i].setText("");
            buttons[i].setIcon(null);
            buttons[i].setBackground(Color.white);
            buttons[i].setEnabled(true);

        }
    }
    public void initialize() {
        
        buttons = new JButton[9];
        
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 3));
        
        for(int i= 0; i<9; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.white);
            buttons[i].setName(Integer.toString(i));
            buttons[i].addActionListener(this);
            
            this.add(buttons[i]);
        }
        this.setVisible(true);

        this.setTitle("Tic-Tac-Toe");
    }

    public void notifyChanges(String player, int x) {
        curIcon = player.equals(Game.PLAYER1)? player1Icon : player2Icon;
//        buttons[x].setText(player);
        buttons[x].setEnabled(false);
        buttons[x].setIcon(curIcon);
    }
    public void displayMessage(String message){
//        messageLabel.setText(message);
        int answer = JOptionPane.showConfirmDialog(null, message + "\nPlay again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
	if(answer == JOptionPane.YES_OPTION)controller.newGame(); else System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        String buttonName = pressed.getName();

        controller.notifyButtonPressed(buttonName);

    }
}
