import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class C4 extends JFrame implements ActionListener {
	JFrame frame;
	JPanel panel;
	static int maxRow;
	static int maxCol;
	static int connection;
	static int [][] board;
	int row, col, rowSelected, colSelected = 0;
	int pTurn = 0;
	boolean win;
	JButton[][] button;
	JLabel winner;
	MenuItem restart, newGame;
	GridLayout myboard;
	final ImageIcon Empty = new ImageIcon(getClass().getResource("white.png"));
	final ImageIcon p1 = new ImageIcon(getClass().getResource("p1.png"));
	final ImageIcon p2 = new ImageIcon(getClass().getResource("p2.jpg"));
	/**
	 * bottom commented lines are used for eclipse testing
	 */
//	final ImageIcon Empty = new ImageIcon("white.png");
//	final ImageIcon p1 = new ImageIcon("p1.png");
//	final ImageIcon p2 = new ImageIcon("p2.jpg");
	public C4() {
		maxRow =Config.size;
		maxCol =Config.size;
		connection = Config.connection;
		board = new int [maxRow][maxCol];
		win = false;
		button = new JButton[maxRow][maxCol];
		myboard = new GridLayout(maxRow+1,maxCol);
		frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuBar bar = new MenuBar();
		Menu file = new Menu("File");
		restart = new MenuItem("Restart");
		newGame = new MenuItem("New Game");
		newGame.addActionListener(this);
		restart.addActionListener(this);
		file.add(restart);
		file.add(newGame);
		bar.add(file);
		frame.setMenuBar(bar);
		panel = new JPanel();
		panel.setLayout(myboard);
		winner = new JLabel("");

		for (int x = maxRow -2; x >=0; x--) {
			for (int y = maxCol - 1; y >= 0; y--) {
				board[x][y] = -1;
			}
		}
		for (row = 0; row < maxRow ; row++) {
			for (col = 0; col <maxCol ; col++) {
				button[row][col] = new JButton(Empty);
				button[row][col].addActionListener(new buttonListener());
				panel.add(button[row][col]);
			}
		}
		panel.add(winner);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public boolean checkWin() {		
		//horizontal
		for (int x = 0; x < maxRow; x++) {
			for (int y = 0; y < maxCol - connection + 1; y++) {
				int count =1; int c2 =1;
				if(board[x][y]!=0 &&board [x][y]!=-1){
					while(c2<connection){
						if(board[x][y]==board[x][y+c2])
							count++;
						if(count==connection){
							win=true;
						}
						c2++;
					}
				}
			}
		}
		//vertical
		for (int x=0; x<maxRow-(connection-1); x++) {
			for (int y=0; y<maxCol; y++) {
				int count =1; int c2 =1;
				if (board[x][y] != 0 && board[x][y] != -1){
					while(c2<connection){
						if(board[x][y]==board[x+c2][y])
							count++;
						if(count==connection){
							win=true;
						}
						c2++;
					}
				}
			}
		}
		// positive slope
		for (int x=maxRow-1; x>maxRow-connection+1; x--) {
			for (int y=0; y<maxCol-connection+1; y++) {
				int count =1; int c2 =1;
				if (board[x][y] != 0 && board[x][y] != -1){
					while(c2<connection){
						if(board[x][y] == board[x-c2][y+c2])
							count++;
						if(count==connection){
							win=true;
						}
						c2++;
					}
				}
			}
		}
		// negative slope
		for (int x=0; x<maxRow-connection+1; x++) {
			for (int y=0; y<maxCol-connection+1; y++) {
				int count =1; int c2 =1;
				if (board[x][y] != 0 && board[x][y] != -1){
					while(c2<connection){
						if(board[x][y] == board[x+c2][y+c2])
							count++;
						if(count==connection){
							win=true;
						}
						c2++;
					}
				}
			}
		}
		return win;
	}
	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for (row = maxRow-1; row >= 0; row--) {
				for (col = maxCol-1; col >= 0; col--) {
					if (button[row][col] == event.getSource()) {
						if (pTurn % 2 == 0 && board[row][col] == 0) {
							button[row][col].setIcon(p1);
							board[row][col] = 1;
							try {
								board[row-1][col] = 0;
							}
							catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("Reached top of column");
							}
							if (checkWin()) {
								System.out.println("Player 1 win");
								winner.setText("Player 1 wins");
								for (int x = maxRow - 1; x >=0; x--) {
									for (int y = maxCol - 1; y >= 0; y--) {
										board[x][y] = -1;
									}
								}
							}
							pTurn = pTurn + 1;
							break;
						}
						if (pTurn % 2 == 1 && board[row][col] == 0) {
							button[row][col].setIcon(p2);
							board[row][col] = 2;
							try {
								board[row-1][col] = 0;
							}
							catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("Reached top of column");
							}
							if (checkWin()) {
								System.out.println("Player 2 win");
								winner.setText("Player 2 wins");
								for (int x = maxRow - 1; x >=0; x--) {
									for (int y = maxCol - 1; y >= 0; y--) {
										board[x][y] = -1;
									}
								}
							}
							pTurn = pTurn + 1;
							break;
						}
						else {
							System.out.println("Space Invalid");
						}
					}
				}
			}
		}
	}
	/** Method is called when "Restart" button is hit from file menu
	 * will erase the current frame and restart the program 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==  restart) {
			frame.dispose();
			new C4();

		}
		else if(e.getSource()== newGame){
			frame.dispose();
			new Config();
		}
	}
}