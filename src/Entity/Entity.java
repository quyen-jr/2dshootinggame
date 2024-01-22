package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import collisionDetection.CreateCollision;
import main.GamePanel;
import main.KeyHandler;

public class Entity  {
	// vi tri thuc;
	public int worldX;
	public int worldY;
	public int width;
	public int height;
	public boolean isEnemy;
	// move info 
	public  float speed=20;
	public float Xvelocity=0;
	public float Yvelocity=0;
	public boolean isRunning;
	// jump info 
	private float jumpForce=8;
	// image state info
	public BufferedImage idle[];
	public BufferedImage run[];
	public BufferedImage shoot[];
	public BufferedImage jump[];
	public BufferedImage fall[];
	public BufferedImage attack[];
	public BufferedImage damaged[];
	public BufferedImage die[];
	// facing direction info 
	public int faceDirection=1;
	public float deltaWhenFlip=0;
	// spriteSheet load info 
	public int spriteCounter=0;
	public int spriteNum=0;
	// idle info 
	public boolean isIdle;
	//
	protected GamePanel gp;
	// 
	public KeyHandler keyH;
	// collision info 
	public Rectangle solidArea;
	public boolean preventMove=false;
	public CreateCollision collision;
	// danh cho enemy;
	public boolean canMoveLeft=true;
	public boolean canMoveRight=true;
	public boolean isActive=true;
	protected int hp;
	public boolean canAttacked=true;
	public  Entity(GamePanel gp,KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
	}
	public void flip() {
		if(isEnemy) {
			enemyFlip();
			return;
		}
		if(gp.keyH.xInput==-1) {
			faceDirection=-1;
			deltaWhenFlip=width;
		}else if(gp.keyH.xInput==1) {
			deltaWhenFlip=0;
			faceDirection=1;
		}
	}
	public void enemyFlip() {
		if(faceDirection==-1) {
			deltaWhenFlip=width;
		}else if(faceDirection==1) {
			faceDirection=1;
		}
	}
	public  void updateVelocity() {
		if(!preventMove)
			this.worldX+=this.Xvelocity;
		this.worldY+=this.Yvelocity;
		//flip();
	}
	public void setVelocitỵ̣̣̣̣(float xVelocity , float yVelocity) {
		this.Xvelocity=xVelocity;
		this.Yvelocity=yVelocity;
		flip();
	}
	public void setPosition(int x, int y) {
		this.worldX=x;
		this.worldY=y;
	}
	public void jump() {
		collision.setySpeed(-jumpForce);
		this.Yvelocity-=jumpForce;
	}
	public float getJumpForce() {
		return jumpForce;
	}
	public void setJumpForce(float jumpForce) {
		this.jumpForce = jumpForce;
	}
}