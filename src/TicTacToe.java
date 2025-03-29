import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650;	//50px fortext panel
	
	JFrame frame = new JFrame();
	
	//Add panel
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel();
	
	TicTacToe(){
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		textLabel.setBackground(Color.darkGray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial{", Font.BOLD, 50));
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText("Virender");
		textLabel.setOpaque(true);
		
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textLabel);
		frame.add(textPanel);
	}
}
