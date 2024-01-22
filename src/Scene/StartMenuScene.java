package Scene;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ViewportUI;

import org.w3c.dom.Text;

import main.GamePanel;

public class StartMenuScene  extends Scene {
	private BufferedImage image;
	private boolean isDrawLogInAndQuitGameButton=true;
	private boolean isDrawInputID=false;
	private boolean isDrawSignUpMenu=false;
	// login button
	private int logInX;
	private int logInY;
	private int logInWidth;
	private int logInHeight;
	// submit 
	private int subX;
	private int subY;
	private int subWidth;
	private int subHeight; 
	// dang ki sign up
	private int signupX;
	private int singupY;
	private int singupWidth;
	private int singupHeight; 
	// back button
	private int backX;
	private int backY;
	private int backWidth;
	private int backHeight; 
	// back button
	private int quitX;
	private int quitY;
	private int quitWidth;
	private int quitHeight; 
	// input field
	private String IdText;
	private String passwordText;
	private String id;
	private String pass;
	private String level;
	// music
	private Clip musiClip;
	// 

	//
	boolean canMoveToGame=false;
	//
	public StartMenuScene(GamePanel _gp) {
		super(_gp);
	    gp.addMouseListener(this);
	    gp.addMouseMotionListener(this); 
	    setUpSound();
	    setUpImage();
	     
	}

	@Override
	public void enter() {
		super.enter();
		playSound();
		//System.out.println(gp.level1Scene);
	}

	@Override
	public void exit() {
		super.exit();
		musiClip.stop();
		gp.removeMouseListener(this);
		gp.removeMouseMotionListener(this);
	}

	@Override
	public void update() {
		super.update();
	}
	public void draw(Graphics2D g2) {
		// vẽ khung
		if(!canMoveToGame) {			
			drawStartImageBackground(g2);
			drawStartMenuFrame(g2);
			if(isDrawLogInAndQuitGameButton)
				drawStartMenu(g2);
			if(isDrawInputID) 
				drawInputMenu(g2,true);		
			if(isDrawSignUpMenu)
				drawSignUpMenu(g2);
			
		}
	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(mouseX<signupX+singupWidth&&mouseX>signupX&&mouseY<singupY+singupHeight&&mouseY>singupY&&isDrawLogInAndQuitGameButton) {
			isDrawLogInAndQuitGameButton=false;
			isDrawSignUpMenu=true;
		}
		if(mouseX<logInX+logInWidth&&mouseX>logInX&&mouseY<logInY+logInHeight&&mouseY>logInY&&isDrawLogInAndQuitGameButton) {
			isDrawLogInAndQuitGameButton=false;
			isDrawInputID=true;
		}

			// check tai khoan co dung k
		if(isDrawInputID) {
			checkInfoOfPlayerInputed();
		}
		// dang ki 
		if(isDrawSignUpMenu) {
			createID();
		}		
		if(!isDrawLogInAndQuitGameButton) {
			checkPressBackButton();	
			return;
		}
		//System.out.println(isDrawLogInAndQuitGameButton);
		if(mouseX<quitX+quitWidth&&mouseX>quitX&&mouseY<quitY+quitHeight&&mouseY>quitY) {
			if(isDrawLogInAndQuitGameButton)
				System.exit(0);
		}
		///System.out.println(1);
		e.consume();
	}
	private void checkPressBackButton() {
		if(mouseX<backX+backWidth&&mouseX>backX&&mouseY<backY+backHeight&&mouseY>backY) {
			isDrawInputID=false;
			isDrawSignUpMenu=false;
			isDrawLogInAndQuitGameButton=true;
			gp.passTextField.setVisible(false);
			gp.passTextField.setText(null);
			gp.inputIDTextField.setVisible(false);
			gp.inputIDTextField.setText(null);
		}
	}
	private void checkInfoOfPlayerInputed() {
		if(mouseX<subX+subWidth&&mouseX>subX&&mouseY<subY+subHeight&&mouseY>subY) {
			IdText=gp.inputIDTextField.getText();
			passwordText=gp.passTextField.getText();
			if(!IdText.isEmpty()&&!passwordText.isEmpty()){
				String sql="select id,password,level from accounts.player where id='"+IdText+"'";
			//	System.out.println(sql);	
				try {
					ResultSet result=gp.stmt.executeQuery(sql);
					if(!result.next() ){
					//	throw new SQLException("tai khoan khong ton tai : vui long dang ki");
						JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại !");
					}else {
						String executedID=result.getString(1);
						String executedPass=result.getString(2);
						if(IdText.equals(executedID)&&passwordText.equals(executedPass)) {
							level=result.getString(3);
							canMoveToGame=true;
						}	
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(canMoveToGame) {
				gp.inputIDTextField.setVisible(false);
				gp.passTextField.setVisible(false);
				System.out.println("Wellcome to level "+level+" :"+IdText);
				gp.level1IsActive=true;
				//canMoveToGame=false;
			//	gp.SceneActive();
			}else {
				System.out.println(" vui long nhap dung tai khoan mat khau");
			}
		}
	}
	private void createID() {
		if(mouseX<subX+subWidth&&mouseX>subX&&mouseY<subY+subHeight&&mouseY>subY) {
			IdText=gp.inputIDTextField.getText();
			passwordText=gp.passTextField.getText();
		//	System.out.println(IdText);
	// kiem tra tai khoan nhap vao co hop le k		
			if(!IdText.isEmpty()&&!passwordText.isEmpty()) {
				char firstChar = IdText.charAt(0);
				char firstCharPass = passwordText.charAt(0);
				if(Character.isDigit(firstChar)|| IdText.contains(" ")||passwordText.contains(" ")
					||IdText.isEmpty()||passwordText.isEmpty()) {
				//	System.out.println("tai khoan khong hop le : khong duoc bat dau bang chu so va khong chua dau cach ");
					gp.inputIDTextField.setText("invalid");
					gp.passTextField.setText("invalid");
				}else {
			
					// thuc hien  them du lieu
					try {
						String sqlCheckDuplicateAccount="select id from accounts.player where id='"+IdText+"'";
						ResultSet result= gp.stmt.executeQuery(sqlCheckDuplicateAccount);
						if(result.next()) {
							throw new Exception("Tài khoản đã tồn tại");
							//JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại !");
							
						}else {
							String newAccount="INSERT INTO player (id, password, level) VALUES ('"+IdText+"','"+passwordText+"','1');";
							//System.out.println(newAccount);
							gp.stmt.executeUpdate(newAccount);
							JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công !");
						}
						System.out.println(sqlCheckDuplicateAccount);
					} catch (Exception e) {
						System.out.println("StartMenuScene.createID()");
					   //    SwingUtilities.invokeLater(() -> {
				                JOptionPane.showMessageDialog(null, "Tạo tài khoản không thành công : "+e.getMessage());
				                gp.inputIDTextField.setText("");
				                gp.passTextField.setText("");
				      //      });
				       
					}
				
				}
			}else {
				System.out.println(" vui long dien vao cho trong");
			}
		}
	}
	private void drawStartMenu(Graphics2D g2) {
        g2.setColor(Color.white);
        drawLogInButton(g2);
        drawQuitGameButton(g2);
        drawSignUpGameButton(g2);
	}
	private void drawInputMenu(Graphics2D g2 , boolean isDrawSubmitButton) {
		// ve chu id va pass
		  g2.setColor(Color.white);
		  g2.drawString("ID", logInX-gp.tileSize/3, logInY+gp.tileSize/3);
		  g2.drawString("PASS", (int) (logInX-gp.tileSize/1.5), logInY+gp.tileSize/3+gp.tileSize);
		  if(isDrawSubmitButton)drawSubmitButton(g2,"JOIN GAME");
		  drawBackButton(g2);
		  // submit button 
		  // kich hoat text fields
		  gp.inputIDTextField.setBounds(logInX,logInY,logInWidth,logInHeight/2);
		  gp.passTextField.setBounds(logInX,logInY+gp.tileSize/2+gp.tileSize/2,logInWidth,logInHeight/2);
		  if(! gp.inputIDTextField.isVisible()) {
			  gp.inputIDTextField.setVisible(true);
			  gp.inputIDTextField.setText(null);
		  }
		  if(! gp.passTextField.isVisible()) {
			  gp.passTextField.setVisible(true);
			  gp.passTextField.setText(null);
		  }
		
	}
	private void drawSignUpMenu(Graphics2D g2) {
		drawInputMenu(g2, false);
		drawSubmitButton(g2,"SIGN UP");
	}
	private void drawStartMenuFrame(Graphics2D g2) {
		// ve khunng login
		g2.setColor(new Color(0,255,250,200));
		g2.fillRoundRect(logInX-gp.tileSize,logInY-gp.tileSize/2, logInWidth+gp.tileSize*2, (int) (gp.tileSize*4.5), 30, 30);
		g2.setColor(new Color(0,0,0,210));
		g2.fillRoundRect(logInX-gp.tileSize +4,logInY-gp.tileSize/2+4, logInWidth+gp.tileSize*2-8, (int) (gp.tileSize*4.5-8), 30, 30);
	}
	
	private void drawStartImageBackground(Graphics2D g2) {
		g2.drawImage(image,0, 0, gp.screenWidth+gp.tileSize+20, gp.screenHeight,null);
		//255,255,255
	}
	private void drawLogInButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "LOG IN";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2-gp.tileSize/2;
        // areaOfButton;
        logInX=x-gp.tileSize;
        logInY=y-gp.tileSize/2;
        logInWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        logInHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<logInX+logInWidth&&mouseX>logInX&&mouseY<logInY+logInHeight&&mouseY>logInY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(logInX, logInY, logInWidth, logInHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(logInX+2, logInY+2, logInWidth-4, logInHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawQuitGameButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "QUIT GAME";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2+gp.tileSize +gp.tileSize/2; 
        // areaOfButton;
        quitX=x-gp.tileSize;
        quitY=y-gp.tileSize/2;
        quitWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        quitHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<quitX+quitWidth&&mouseX>quitX&&mouseY<quitY+quitHeight&&mouseY>quitY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(quitX, quitY, quitWidth, quitHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(quitX+2, quitY+2, quitWidth-4, quitHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawSignUpGameButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "SIGN UP";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y =gp.screenHeight / 2+gp.tileSize/2;
        // areaOfButton;
        signupX=x-gp.tileSize;
        singupY=y-gp.tileSize/2;
        singupWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        singupHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<signupX+singupWidth&&mouseX>signupX&&mouseY<singupY+singupHeight&&mouseY>singupY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(signupX, singupY, singupWidth, singupHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(signupX+2, singupY+2, singupWidth-4, singupHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawSubmitButton(Graphics2D g2,String _text) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = _text;
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2+gp.tileSize+gp.tileSize/4;
        // areaOfButton;
        subX=x-gp.tileSize;
        subY=y-gp.tileSize/2;
        subWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        subHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<subX+subWidth&&mouseX>subX&&mouseY<subY+subHeight&&mouseY>subY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(subX, subY, subWidth, subHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(subX+2, subY+2, subWidth-4, subHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawBackButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "BACK";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2+gp.tileSize+gp.tileSize;
        // areaOfButton;
        backX=x-gp.tileSize;
        backY=y-gp.tileSize/2;
        backWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        backHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<backX+backWidth&&mouseX>backX&&mouseY<backY+backHeight&&mouseY>backY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(backX, backY, backWidth, backHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(backX+2, backY+2, backWidth-4, backHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}

	private void setUpSound() {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(getClass().getResource("/music/startMenuMusic.wav"));
			musiClip=AudioSystem.getClip();
			musiClip.open(ais);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private  void playSound() {
		musiClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private void setUpImage() {
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/startMenuImage/OIG.Zwc.png"));			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
