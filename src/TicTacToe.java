import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {
	int boardWidth = 600;
	int boardHeight = 650;	//50px fortext panel
	
	JFrame frame = new JFrame();
	
	TicTacToe(){
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
	}
}
