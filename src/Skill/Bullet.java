package Skill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sql.rowset.JoinRowSet;

import EnemyState.Enemy;
import Entity.Entity;
import PlayerState.Player;
import SkeletonEnemy.SkeletonEnemy;
import main.GamePanel;
import main.KeyHandler;

public class Bullet   {
	private BufferedImage shootImage[];
	private BufferedImage boomImage[];
	public boolean isTurnToBoom=false;
	public boolean isActive=true;
	private GamePanel gp;
	private BufferedImage image;
	private Player player;
	List<Enemy> enemies;
	//    position
	private int worldX,worldY;
	private int defaultworldX,defaultworldY;
	private int width=50;
	private int height=50;
	private boolean isCollision=false;
	// move info
	private float speed;
	// spriteSheet load info 
	private int spriteCounter=0;
	private int spriteNum=0;
	private int faceDirection;
	public Rectangle solidArea;
	private Clip shootClip;
	// booom
	private Clip boomClip;
	private URL soundUrl;
	public Bullet(GamePanel _gp,Player _player,List<Enemy> _enemies,int _worldX, int _worldY, int _faceDirection) {
		this.gp=_gp;
		this.player=_player;
		this.enemies=_enemies;
		this.worldX=_worldX;
		this.worldY=_worldY;
		this.defaultworldX=_worldX;
		this.defaultworldY=_worldY;
		this.faceDirection=_faceDirection;
		getShootImage();
		setUpSound();
		setUpBullet();
		playSound("shoot");
		// TODO Auto-generated constructor stub
		//System.out.println(gp);
	}
	private void setUpBullet() {
		speed=10;
		solidArea= new Rectangle();
		solidArea.x=0;
		solidArea.y=width/4;
		solidArea.height=width/2;
		solidArea.width=width-5;
	}
	private void move() {
		worldX+=speed*faceDirection;
	}
	public void update() {
		move();
		checkCollisionWithEnemy();
		if(!isTurnToBoom) {
			RunAnimation(shootImage, 1,false);		
		}else {
			RunAnimation(boomImage, 1, true);
		}
	}
	private void CheckCollision() {
		
	}
	private void getShootImage() {
		try {
			shootImage= new BufferedImage[4];
			shootImage[0]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot1.png"));
			shootImage[1]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot2.png"));
			shootImage[2]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot3.png"));
			shootImage[3]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot4.png"));
			boomImage= new BufferedImage[4];
			boomImage[0]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot-hit1.png"));
			boomImage[1]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot-hit2.png"));
			boomImage[2]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot-hit3.png"));
			boomImage[3]=ImageIO.read(getClass().getResourceAsStream("/playerBulletImage/player-shoot-hit4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int screenX=worldX-player.worldX+player.screenX;
		int screenY=worldY-player.worldY+player.screenY;
		if(worldX+width>=player.worldX-player.screenX&&
		   worldX-width<= player.worldX+player.screenX&&
		   worldY+height>=player.worldY-player.screenY&&
		   worldY-height<=player.worldY+player.screenY
		   ) {
			if(faceDirection==1) {
			//	delta
			}
			g2.drawImage(image,(int)(screenX),screenY,width*faceDirection,height,null);
		}else {
			isActive=false;
		}
	}
	private void checkCollisionWithEnemy() {
		for (int i = 0; i < enemies.size(); i++) {
		    Enemy enemy = enemies.get(i);
		    int rect2X = enemy.worldX + enemy.solidArea.x;
		    int rect2Y = enemy.worldY + enemy.solidArea.y;
		    int rect2Width = enemy.solidArea.width;
		    int rect2Height = enemy.solidArea.height;

		    if (
		        worldX + solidArea.x <= rect2X + rect2Width &&
		        worldX + solidArea.x + solidArea.width >= rect2X &&
		        worldY + solidArea.y <= rect2Y + rect2Height &&
		        worldY + solidArea.y + solidArea.height >= rect2Y
		    ) {
		    	// xoa khi ra khoi the gioi
		       // isActive = false;
		    	speed=0;
		    	if(!isCollision) {
		    		isCollision=true;
		    		if(enemy.canAttacked)
		    			enemy.getDamaged();
		    		playSound("boom");
		    		
		    	}
		    	//System.out.println(2);
		    	isTurnToBoom=true;
		    }
		}

	}
	private void RunAnimation(BufferedImage[] stage,int framerate,boolean isEnd) {
		if(spriteCounter<framerate) {
			spriteCounter++;
		}else {
			if(spriteNum<stage.length) {						
				image=stage[spriteNum];
				//System.out.println(spriteNum);
				spriteNum++;
				if(spriteNum==stage.length&&isEnd) {
					isActive=false;
					return;
				}
			}else {
				spriteNum=0;
			//	finishedAnimation();
			}
			spriteCounter=0;
		}
	}

	private void setUpSound() {
		try {
			//AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(getClass().getResource(null))
			AudioInputStream ais= AudioSystem.getAudioInputStream(getClass().getResource("/music/laser-gun-81720 (mp3cut.net) (1).wav"));
			shootClip=AudioSystem.getClip();
			shootClip.open(ais);
			AudioInputStream ais2= AudioSystem.getAudioInputStream(getClass().getResource("/music/GSCC5QS-futuristic-gun (mp3cut.net).wav"));
			boomClip=AudioSystem.getClip();
			boomClip.open(ais2);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void playSound(String name) {
		if(name=="shoot")
			shootClip.start();
		if(name=="boom") {
			boomClip.start();
		}
	}
	private void stopSound() {
		shootClip.stop();
	}
}
