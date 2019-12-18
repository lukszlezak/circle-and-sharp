import java.awt.event.*;   
import javax.swing.*;

public class MyMain extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = -7761600428137923387L;

	JTextField field_player1, field_player2;
	JButton button_nicknames, button_chars[][];
	JLabel info;
	String player1, player2, board[][] = new String[3][3], current_char, current_player;
	int number_of_moves = 0;
	
	boolean check_board() {
		for ( int x = 0 ; x < 3 ; x++ ) {
			if ( (board[x][0] == current_char) && (board[x][1] == current_char) && (board[x][2] == current_char) ) return true;
		}
		for ( int y = 0 ; y < 3 ; y++ ) {
			if ( (board[0][y] == current_char) && (board[1][y] == current_char) && (board[2][y] == current_char) ) return true;
		}
		if ( (board[0][0] == current_char) && (board[1][1] == current_char) && (board[2][2] == current_char) ) return true;
		if ( (board[0][2] == current_char) && (board[1][1] == current_char) && (board[2][0] == current_char) ) return true;		
		return false;
	}
	
	void reset() {
		for ( int y = 0 ; y < 3 ; y++ ) {
			for ( int x = 0 ; x < 3 ; x++ ) {
				board[x][y] = "";
				button_chars[x][y].setText ("");
			}
		}
		number_of_moves = 0;
	}
	
	public void actionPerformed (ActionEvent ae) {
		Object source = ae.getSource();
		if (source == button_nicknames) {
			player1 = field_player1.getText();
			player2 = field_player2.getText();
			current_char = "X";
			current_player = player1;
			info.setText ("Listen! " + current_player + " - click where you want to put this char: X");
		}
		for ( int y = 0 ; y < 3 ; y++ ) {
			for ( int x = 0 ; x < 3 ; x++ ) {
				if ( (source == button_chars[x][y]) && ((board[x][y] == "X") || (board[x][y] == "O")) ) {
					JOptionPane.showMessageDialog (null, "You can not put this char there!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else if ( source == button_chars[x][y] ) {
					button_chars[x][y].setText (current_char);
					board[x][y] = current_char;
					number_of_moves++;
					if ( check_board() ) {
						JOptionPane.showMessageDialog (null, "Winner! Congratulations for " + current_player + "!", "End of this match", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					else if ( number_of_moves == 9 ) {
						JOptionPane.showMessageDialog (null, "Draw! Try again.", "End of this match", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					if ( current_char == "X" )	current_char = "O";
					else 						current_char = "X";
					if ( current_player == player1 )	current_player = player2;
					else								current_player = player1;
					info.setText ("Now " + current_player + "! - click where you want to put this char: " + current_char);
				}
			}
		}
	}

	public void keyPressed(KeyEvent arg0) {
		
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	
	MyMain() {
		super ("Circle and Sharp");
		setBounds (200, 100, 400, 400);
    	setLayout (null);
    	
    	JLabel title = new JLabel ("version 1.0", JLabel.CENTER);
    	title.setBounds (5, 5, 385, 20);
    	add (title);
    	
    	JLabel label_player1 = new JLabel ("Player 1");
    	label_player1.setBounds (75, 25, 50, 20);
    	add (label_player1);
    	
    	JLabel label_player2 = new JLabel ("Player 2");
    	label_player2.setBounds (275, 25, 50, 20);
    	add (label_player2);
    	
    	field_player1 = new JTextField();
    	field_player1.setBounds (5, 50, 190, 25);
    	add (field_player1);
    	
    	field_player2 = new JTextField();
    	field_player2.setBounds (200, 50, 190, 25);
    	add (field_player2);
    	
    	button_nicknames = new JButton ("Accept those nicknames");
    	button_nicknames.setBounds (5, 80, 385, 25);
    	button_nicknames.addActionListener (this);
    	add (button_nicknames);
    	
    	info = new JLabel ("Here will be instructions! First setup your nicknames", JLabel.CENTER);
    	info.setBounds (5, 110, 385, 25);
    	add (info);
    	
    	button_chars = new JButton[3][3];
    	for ( int y = 0, pos_y = 145 ; y < 3 ; y++, pos_y += 65 ) {
    		for ( int x = 0, pos_x = 105 ; x < 3 ; x++, pos_x += 65 ) {
    			button_chars[x][y] = new JButton();
    			button_chars[x][y].setBounds (pos_x, pos_y, 60, 60);
    			button_chars[x][y].addActionListener (this);
    			add (button_chars[x][y]);
    		}
    	}
    	
    	setVisible (true);
    	setResizable (false);
	}
	
	public static void main (String args[]) {
		new MyMain();
	}
}