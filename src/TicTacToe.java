import javax.swing.*; // Import Swing components for GUI
import java.awt.*; // Import AWT classes for layout and colors
import java.awt.event.*; // Import event classes

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650;	// 50px extra height for the text panel
	
	JFrame frame = new JFrame("Tic-Tac-Toe"); // Main window
	JLabel textLabel = new JLabel(); // Label to show messages (like player turn or winner)
	JPanel textPanel = new JPanel(); // Panel to hold the label
	JPanel boardPanel = new JPanel(); // Panel for the game grid
	
	JButton[][] board = new JButton[3][3]; // 3x3 button grid for Tic-Tac-Toe board
	String playerX = "X";
	String playerO = "O";
	String currentPlayer = playerX; // Initially, player X starts
	
	boolean gameOver = false; // Flag to check if the game is over
	int turns = 0; // Count of turns taken
	
	TicTacToe(){
		// Setup main frame
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null); // Center the window
		frame.setResizable(false); // Prevent resizing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app on exit
		frame.setLayout(new BorderLayout()); // Use BorderLayout

		// Setup the text label
		textLabel.setBackground(Color.darkGray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText("Virender"); // Initial message
		textLabel.setOpaque(true); // Make background color visible

		// Add label to text panel, and panel to frame
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);
		frame.add(textPanel, BorderLayout.NORTH); // Add to top

		// Setup board panel
		boardPanel.setLayout(new GridLayout(3, 3)); // 3x3 grid
		boardPanel.setBackground(Color.darkGray);
		frame.add(boardPanel); // Add to center by default

		// Create buttons for each tile
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				JButton tile = new JButton(); // Create new button
				board[r][c] = tile; // Store in board
				boardPanel.add(tile); // Add to panel

				// Style the tile
				tile.setBackground(Color.darkGray);
				tile.setForeground(Color.white);
				tile.setFont(new Font("Arial", Font.BOLD, 120));
				tile.setFocusable(false); // No focus outline

				// Add click functionality
				tile.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(gameOver) return; // Do nothing if game is over
						JButton tile = (JButton) e.getSource();
						if(tile.getText() == "") { // If tile is empty
							tile.setText(currentPlayer); // Set current player's mark
							turns++; // Increment turn count
							checkWinner(); // Check for win or tie
							if(!gameOver) {
								// Switch player
								currentPlayer = currentPlayer == playerX ? playerO : playerX;
								textLabel.setText(currentPlayer + "'s turn"); // Show next player's turn
							}
						}
					}
				});
			}
		}
	}

	// Method to check all win/tie conditions
	void checkWinner() {
		// Check horizontal rows
		for(int r = 0; r < 3; r++) {
			if(board[r][0].getText() == "") continue; // Skip empty rows
			if(board[r][0].getText() == board[r][1].getText() &&
			   board[r][1].getText() == board[r][2].getText()) {
				for(int i = 0; i < 3; i++) {
					setWinner(board[r][i]); // Highlight winner
				}
				gameOver = true;
				return;
			}
		}

		// Check vertical columns
		for(int c = 0; c < 3; c++) {
			if(board[0][c].getText() == "") continue;
			if(board[0][c].getText() == board[1][c].getText() &&
			   board[1][c].getText() == board[2][c].getText()) {
				for(int i = 0; i < 3; i++) {
					setWinner(board[i][c]);
				}
				gameOver = true;
				return;
			}
		}

		// Check main diagonal
		if(board[0][0].getText() != "" &&
		   board[0][0].getText() == board[1][1].getText() &&
		   board[1][1].getText() == board[2][2].getText()) {
			for(int i = 0; i < 3; i++) {
				setWinner(board[i][i]);
			}
			gameOver = true;
			return;
		}

		// Check anti-diagonal
		if(board[0][2].getText() != "" &&
		   board[0][2].getText() == board[1][1].getText() &&
		   board[1][1].getText() == board[2][0].getText()) {
			setWinner(board[0][2]);
			setWinner(board[1][1]);
			setWinner(board[2][0]);
			gameOver = true;
			return;
		}

		// If all 9 turns taken and no winner, it's a tie
		if(turns == 9) {
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					setTie(board[r][c]); // Mark as tie
				}
			}
			gameOver = true;
		}
	}

	// Method to show winner tiles
	void setWinner(JButton tile) {
		tile.setForeground(Color.green); // Change text color
		tile.setBackground(Color.gray); // Change background
		textLabel.setText(currentPlayer + " is the winner!"); // Show winner
	}

	// Method to show tie result
	void setTie(JButton tile) {
		tile.setForeground(Color.orange); // Change text color
		tile.setBackground(Color.gray); // Change background
		textLabel.setText("Tie!"); // Show tie message
	}
}
