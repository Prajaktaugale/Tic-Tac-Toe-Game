
package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650; // 50 px for the text panel on top
    
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton[][] board = new JButton[3][3]; // Created 2 D array to keep track of button on the board
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX; // First player will be X
    
    boolean gameOver = false;
    int turns =0;
    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // It will open the window to the center of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white); // Change font color to white
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);
                 
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        
        for(int r = 0;r<3;r++)
        {
            for(int c = 0;c<3;c++)
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                
                tile.setBackground(Color.darkGray); 
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "")
                        {
                            tile.setText(currentPlayer);
                            turns++; 
                            checkWinner();
                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s Turn");
                            }
                            
                        }
                        
                    }
                
                });
            }
        }
    }
    
    public static void main(String[] args) throws Exception 
    {
        TicTacToe tictactoe = new TicTacToe();
        
    }

    
    void checkWinner()
    {
    // Horizontally
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0;i<3;i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        
        // Vertical
        for(int c = 0;c<3;c++)
        {
            if(board[0][c].getText() == "") continue;
            
            if(board[0][c].getText() == board[1][c].getText() &&
               board[1][c].getText() == board[2][c].getText()){
                for(int i =0;i<3;i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        
        // Diagnoally
        if(board[0][0].getText() == board[1][1].getText() &&
              board[1][1].getText() == board[2][2].getText() &&
              board[0][0].getText() != "")
                {
                    for(int i =0;i<3;i++)
                    {
                        setWinner(board[i][i]);
                    }
                    gameOver = true;
                    return;
                }
        
        // antidiagnoally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }
    
        if(turns == 9)
        {
            for(int r =0;r<3;r++)
            {
                for(int c = 0;c<3;c++)
                {
                    setTile(board[r][c]);
                }
            }
            gameOver = true;
        }
    }
    void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    } 
    
    void setTile(JButton tile)
    {
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
