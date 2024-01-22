package Scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import EnemyState.Enemy;
import PlayerState.Player;
import SkeletonEnemy.SkeletonEnemy;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class level1Scene extends Scene {

	public Player player;
	public KeyHandler keyH;
	public TileManager tileM;
	private BufferedImage pauseImage;
	// ske enemy
	// pause button
	private int pauseX;
	private int pauseY;
	private int pauseWidth;
	private int pauseHeight; 
	// pause menu
	private boolean isDrawPauseMenu;
	// resume button
	private int resumeX;
	private int resumeY;
	private int resumeWidth;
	private int resumeHeight;
	// main menu button
	private int mainMenuX;
	private int mainMenuY;
	private int mainMenuWidth;
	private int mainMenuHeight;
	// win
	private boolean isWin;
	private int winX;
	private int winY;
	private int winWidth;
	private int winHeight;
	// color
	private Color color;
	private float red;
	private float green;
	private float blue;
	public List<Enemy> enemies=new ArrayList<Enemy>();
	public level1Scene(GamePanel _gp, KeyHandler _keyH) {
		super(_gp);
		this.keyH=_keyH;
		getImage();
		gp.addMouseListener(this);
	    gp.addMouseMotionListener(this); 

	}
	private void setUpLevel1() {
		player= new Player(gp, keyH,enemies);
		tileM= new TileManager("world",gp,player);
	}
	@Override
	public void enter() {
		super.enter();
		setUpLevel1();
		setUpEnemy();
	}

	@Override
	public void exit() {
		super.exit();
		gp.removeMouseListener(this);
		gp.removeMouseMotionListener(this);
	}

	@Override
	public void update() {
		super.update();		
		if(!isDrawPauseMenu) {
			skeletonEnemyUpdate();
			player.update();
		}
		
	}

	@Override
	public void draw(Graphics2D g2) {
		super.draw(g2);
		// resume, win  la  button canh le
		tileM.draw(g2);
		drawSkeletonEnemy(g2);
		player.draw(g2);
		if(!isDrawPauseMenu&&!isWin)drawPauseButton(g2);
		checkForPressPause(g2);
		checkForWin(g2);
	}
	private void checkForPressPause(Graphics2D g2) {
		if(isDrawPauseMenu) {
			drawPauseFrame(g2);
			drawResumeButton(g2);
			drawMainMenuButton(g2);
		}
	}
	private void  checkForWin(Graphics2D g2) {
		if(enemies.isEmpty()) {
			isWin=true;
			drawWinMenu(g2);
		}
	}
	private void drawWinMenu(Graphics2D g2) {
		drawWinFrame(g2);
		drawWinButton(g2);
	}
	private void setUpEnemy() {
		enemies.add(new SkeletonEnemy(gp,player,keyH,47,17,3,5,100,30));
		enemies.add(new SkeletonEnemy(gp,player, keyH,50,13,3,5,100,30));
		enemies.add(new SkeletonEnemy(gp,player, keyH,63,17,3,5,100,30));
		enemies.add(new SkeletonEnemy(gp,player, keyH,45,31,8,8,150,50));
	}
	private void skeletonEnemyUpdate() {
		for (int i = 0; i < enemies.size();) {
			Enemy enemy= enemies.get(i);
			if(enemy.isActive) {
				enemy.update();
				i++;
			}
			else {
				enemies.remove(i);				
			}
		}
	}
	private void drawSkeletonEnemy(Graphics2D g2) {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy= enemies.get(i);
			if (enemy.isActive) {
				enemy.draw(g2);					
			}
		}
	}
	private void drawPauseButton(Graphics2D g2) {
        pauseX=gp.screenWidth-gp.tileSize*2;
        pauseY=gp.tileSize/4;
        pauseWidth=gp.tileSize*2;
        pauseHeight=gp.tileSize*2;
        // check mouse in area
        boolean isInArea=false;
        g2.drawImage(pauseImage,pauseX, pauseY, pauseWidth, pauseHeight-gp.tileSize/4,null);
		if(mouseX<pauseX+pauseWidth&&mouseX>pauseX&&mouseY<pauseY+pauseHeight&&mouseY>pauseY) {
			g2.setColor(new Color(0,0,0,100));
			g2.fillRoundRect(pauseX+gp.tileSize/4, pauseY+gp.tileSize/4, pauseWidth-gp.tileSize/2, (int) (pauseHeight-gp.tileSize/1.5), 100, 100);
			isInArea=true;
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if(mouseX<pauseX+pauseWidth&&mouseX>pauseX&&mouseY<pauseY+pauseHeight&&mouseY>pauseY&&!isDrawPauseMenu&&!isWin) {
			isDrawPauseMenu=true;
		}
		// click resume
		if(mouseX<resumeX+resumeWidth&&mouseX>resumeX&&mouseY<resumeY+resumeHeight&&mouseY>resumeY&&isDrawPauseMenu&&!isWin) {
			isDrawPauseMenu=false;
		}
		// main menu
		if(mouseX<mainMenuX+mainMenuWidth&&mouseX>mainMenuX&&mouseY<mainMenuY+mainMenuHeight&&mouseY>mainMenuY&&isDrawPauseMenu&&!isWin) {
			gp.startMenuIsActive=true;
		}
		// win and back to main menu
		if(mouseX<winX+winWidth&&mouseX>winX&&mouseY<winY+winHeight&&mouseY>winY&&isWin) {
			gp.startMenuIsActive=true;
		}
		e.consume();
	}
	private void drawPauseFrame(Graphics2D g2) {
		// ve khunng login
		g2.setColor(new Color(0,255,250,200));
		g2.fillRoundRect(resumeX-gp.tileSize,resumeY-gp.tileSize/2, resumeWidth+gp.tileSize*2, (int) (gp.tileSize*4.5), 30, 30);
		g2.setColor(new Color(0,0,0,210));
		g2.fillRoundRect(resumeX-gp.tileSize +4,resumeY-gp.tileSize/2+4, resumeWidth+gp.tileSize*2-8, (int) (gp.tileSize*4.5-8), 30, 30);
		//drawNeonLights(g2);
	}
	private void drawResumeButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text ="RESUME";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2-gp.tileSize/2;
        // areaOfButton;
        resumeX=x-gp.tileSize;
        resumeY=y-gp.tileSize/2;
        resumeWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        resumeHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<resumeX+resumeWidth&&mouseX>resumeX&&mouseY<resumeY+resumeHeight&&mouseY>resumeY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(resumeX, resumeY, resumeWidth, resumeHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(resumeX+2, resumeY+2, resumeWidth-4, resumeHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawWinButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "You Win : click to back";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2-gp.tileSize/2;
        // areaOfButton;
        winX=x-gp.tileSize;
        winY=y-gp.tileSize/2;
        winWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        winHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<winX+winWidth&&mouseX>winX&&mouseY<winY+winHeight&&mouseY>winY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(winX, winY, winWidth, winHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(winX+2, winY+2, winWidth-4, winHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void drawWinFrame(Graphics2D g2) {
		// ve khunng win
		red+=0.1;
		if(red>1) {
			red=0;
		}
		color= Color.getHSBColor(red, 1.0f, 1.0f);
		g2.setColor(color);
		g2.fillRoundRect(winX-gp.tileSize,winY-gp.tileSize/2, winWidth+gp.tileSize*2, (int) (gp.tileSize*4.5), 30, 30);
		g2.setColor(new Color(0,0,0,210));
		g2.fillRoundRect(winX-gp.tileSize +4,winY-gp.tileSize/2+4, winWidth+gp.tileSize*2-8, (int) (gp.tileSize*4.5-8), 30, 30);
		//drawNeonLights(g2);
	}
	private Color color(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return null;
	}
	private void drawMainMenuButton(Graphics2D g2) {
		Font font = new Font("Consolas", Font.BOLD, 20);
        g2.setFont(font);
        String text = "MAIN MENU";
        FontMetrics fontMetrics = g2.getFontMetrics();
        // possition of text;
        int x =( gp.screenWidth - fontMetrics.stringWidth(text)) / 2;
        int y = gp.screenHeight / 2+gp.tileSize/2;
        // areaOfButton;
        mainMenuX=x-gp.tileSize;
        mainMenuY=y-gp.tileSize/2;
        mainMenuWidth=fontMetrics.stringWidth(text)+gp.tileSize*2;
        mainMenuHeight=gp.tileSize;
        // check mouse in area
        boolean isInArea=false;
		if(mouseX<mainMenuX+mainMenuWidth&&mouseX>mainMenuX&&mouseY<mainMenuY+mainMenuHeight&&mouseY>mainMenuY) {
			g2.setColor(new Color(255,255,0));
			g2.fillRoundRect(mainMenuX, mainMenuY, mainMenuWidth, mainMenuHeight-gp.tileSize/4, 20, 20);
			g2.setColor(new Color(0,0,0,210));
			g2.fillRoundRect(mainMenuX+2, mainMenuY+2, mainMenuWidth-4, mainMenuHeight-gp.tileSize/4-4, 20, 20);
			isInArea=true;
		}
    	if(!isInArea) g2.setColor(Color.white);
    	else g2.setColor(new Color(255, 255, 0));
        g2.drawString(text, x, y);
	}
	private void getImage() {
		try {
			pauseImage= ImageIO.read(getClass().getResourceAsStream("/buttonImage/OIG.png"));		
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}

}
