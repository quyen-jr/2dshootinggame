package main;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;


public class Main {
	public static void main(String[] args) {

		JFrame window= new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("WARRIOR GO");
	  
	    JLayeredPane layeredPane = new JLayeredPane();
	    // jtextfield
	    Font font = new Font("Consolas", Font.BOLD, 20);
	    JTextField passTextField = new JTextField();
	    	passTextField.setFont(font);
	    	passTextField.setBounds(50, 90, 150, 30);
	    	passTextField.setVisible(false);
	    	layeredPane.add(passTextField, JLayeredPane.PALETTE_LAYER);
	    JTextField inputIDTextField = new JTextField();
	    	inputIDTextField.setFont(font);
	    	inputIDTextField.setVisible(false);
	    	layeredPane.add(inputIDTextField, JLayeredPane.PALETTE_LAYER);
	    GamePanel gamePanel = new GamePanel(inputIDTextField,passTextField);
        	gamePanel.setBounds(0, 0,gamePanel.screenWidth ,gamePanel.screenHeight);
        	layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(new Dimension(gamePanel.screenWidth, gamePanel.screenHeight));
        
        // database
		String url="jdbc:mysql://localhost:3306/accounts";
		String user="root";
		String password="1234";
		String driverName="com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driverName);
			try {
				Connection myConnection=DriverManager.getConnection(url, user, password);
				Statement stmt=myConnection.createStatement();
				gamePanel.stmt=stmt;
//				String sql="SELECT * FROM accounts.player";
//				ResultSet resultSet= stmt.executeQuery(sql);
//				//System.out.println(resultSet);
//				while (resultSet.next()) {
//					gamePanel.idPlayer.add(resultSet.getString(1).trim());
//					gamePanel.password.add(resultSet.getString(2).trim());
//					gamePanel.level.add(resultSet.getString(3).trim());
//				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// add game in

        window.add(layeredPane);	
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);	
		gamePanel.startGameThread();
//		while (true) {
//			System.out.println(1);
//		}
	}
}
