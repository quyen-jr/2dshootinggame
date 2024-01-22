package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

import com.mysql.cj.xdevapi.Statement;

import PlayerState.Player;
import Scene.SceneManager;
import Scene.StartMenuScene;
import Scene.level1Scene;
import SkeletonEnemy.SkeletonEnemy;
import collisionDetection.CreateCollision;
//import physic2d.Gravity;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
	// screen setting 
	final int scale=3;
	final int originalTileSize=16;
	public final int tileSize=originalTileSize*scale;
	public int maxScreenCol=18;
	public int maxScreenRow=12;
	public int screenWidth= maxScreenCol*tileSize;
	public int screenHeight= maxScreenRow*tileSize;
	// game frame settings
	final int FPS=60;
	public double drawInterval=1000000000/FPS;
	int x=10;
	public float deltaTime;
	Thread gameThread;
	//world setting;
	public final int maxWorldCol=88;
	public final int maxWorldRow= 54;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight= tileSize*maxWorldRow;
	// component
	public KeyHandler keyH = new KeyHandler();

	// bullet player 
	//public CreateCollision collision =new CreateCollision(player, this);
	// scene
	SceneManager sceneManager;
	StartMenuScene startMenuScene;
	public boolean startMenuIsActive=false;
	public level1Scene level1Scene;
	public boolean level1IsActive=false;
	/// text field input
//	public List<String> idPlayer=new ArrayList<>();
//	public List<String> password=new ArrayList<>();
//	public List<String> level=new ArrayList<>();
//	// EXECUTE SQL
//	public ResultSet resultSet;
	public java.sql.Statement stmt;
	public JTextField inputIDTextField, passTextField;
	
	
	public GamePanel(JTextField _inputIDTextField, JTextField _passTextField){
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setDoubleBuffered(true);
		this.setBackground(Color.red);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.inputIDTextField=_inputIDTextField;
		this.passTextField=_passTextField;
		setUpScene();
	
	}
	private void setUpScene() {
		sceneManager= new SceneManager();
		startMenuScene= new StartMenuScene(this);
	//	level1Scene= new level1Scene(this, keyH);
		sceneManager.initialize(startMenuScene);
	}

	public void startGameThread() {
		gameThread= new Thread(this);
		gameThread.start();
	}
	public void run() {
		double delta = 0;		
		long currentTime;
		long lastTime=System.nanoTime();
		while (gameThread!=null) {
		//	System.out.println(2);
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			deltaTime=(float) ((currentTime-lastTime)/drawInterval);
			lastTime=currentTime;
			if(delta>=1/60) {		
				update();
				repaint();
				delta--;
			}
		}	
	}
	public void update() {
		keyH.update();
		SceneActive();
		sceneManager.currentScene.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		sceneManager.currentScene.draw(g2);
		g2.dispose();
	}
	//  gan scene duoc ve
	public void SceneActive() {
		if(level1IsActive) {
			startMenuScene=null;
			level1Scene= new level1Scene(this, keyH);
			sceneManager.changeScene(level1Scene);
		//	keyH.enterPressed=false;
			level1IsActive=false;
		}
		if(startMenuIsActive) {
			level1Scene=null;
			startMenuScene= new StartMenuScene(this);
		//	keyH.enterPressed=false;
			sceneManager.changeScene(startMenuScene);
			startMenuIsActive=false;
		}
	}
  

}
