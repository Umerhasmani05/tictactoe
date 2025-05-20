/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PCS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI1 extends JFrame implements ActionListener {
    private final JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameActive = true;
    private final JButton resetButton;
    private final char[][] sourceBoard = new char[3][3]; 

    // Scoreboard variables
    private int playerXScore = 0;
    private int playerOScore = 0;
    private JLabel scoreLabel;

    public TicTacToeGUI1() {
        setTitle("Tic Tac Toe");
        setSize(350, 400); // Added space for reset button and scoreboard
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }

        // Add reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);

        // Scoreboard label
        scoreLabel = new JLabel("X: " + playerXScore + " | O: " + playerOScore);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add panels to frame
        add(buttonPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);
        add(scoreLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
            return; 
        }

        if (!gameActive) return;

        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) return;

        clickedButton.setText(String.valueOf(currentPlayer));
        if (checkForWin()) {
            if (currentPlayer == 'X') {                playerXScore++;
            } else {
                playerOScore++;
            }
            scoreLabel.setText("X: " + playerXScore + " | O: " + playerOScore);
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            gameActive = false;
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameActive = false;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void resetGame() {
        gameActive = true;
        currentPlayer = 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                sourceBoard[i][j] = ' '; // Reset source board
            }
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
      
             return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
               (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }
      private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI1());
    }
}
