
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Phuong
 */
public class Config extends JFrame {
	public static int size = 0;
	public static int connection = 0;
	public Config (int s, int c){
		size=s;
		connection=c;
	}
	public Config(){
		createConfig2();
	}
	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void createConfig() {               
		if(connection < 3 || !(connection <= size - 2)){
			System.out.println("Connection Input Invalid\nConnection size must be at least Board size - 2.");
		}
		else if(size<6 || size > 10){
			System.out.println("Board Size Input Invalid\nProgram supports board size of 6 to 25.");
		}
		else{
			C4 gameOn = new C4();
		}
		System.out.println("Connection: " + connection);
		System.out.println("Board Size: " + size +" by "+size);               
	}
	public void createConfig2() {
		final JFrame frame1 = new JFrame("Config");
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("Enter Board Size: ");
		final JTextField text1 = new JTextField(6);
		JLabel label2 = new JLabel("Enter Connection: ");
		final JTextField text2 = new JTextField(16);
		frame1.add(panel);
		frame1.setSize(300, 150);
		frame1.setVisible(true);

		panel.add(label1);
		panel.add(text1);
		panel.add(label2);
		panel.add(text2);

		JButton play = new JButton("PLAY");
		panel.add(play);
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String boardSize = text1.getText();
				String connect = text2.getText();
				if (isInteger(boardSize) == false || isInteger(connect) == false) {
					JOptionPane.showMessageDialog(frame1, "Please Enter Number!");
				}                

				else if(Integer.parseInt(connect)< 3|| !(Integer.parseInt(connect) <= Integer.parseInt(boardSize)-2)){
					JOptionPane.showMessageDialog(frame1, "Connection input must be from 3 - (Size-2)");
				}
				else if (Integer.parseInt(boardSize)<6||Integer.parseInt(boardSize)>25)
					JOptionPane.showMessageDialog(frame1, "Program supports board size 6-25");
				else{
					connection = Integer.parseInt(connect);
					size = Integer.parseInt(boardSize);
					C4 gameOn = new C4();
					frame1.dispose();
				}

				System.out.println("Connection: " + connection);
				System.out.println("Board Size: " + size + " by "+size);               

			}
		});
	}   

}
