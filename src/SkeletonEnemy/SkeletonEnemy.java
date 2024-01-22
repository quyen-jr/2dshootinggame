package SkeletonEnemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import Entity.Entity;
import PlayerState.Player;
import collisionDetection.CreateCollision;
import main.GamePanel;
import main.KeyHandler;

public class SkeletonEnemy extends Enemy {
	int screenX, screenY;
	BufferedImage image;
	public CreateCollision collision;
	public EnemyStateMachine stateMachine;
	public SkeletonIdleState idleState;
	public SkeletonRunState runState;
	public SkeletonAttackState attackState;
	public SkeletonDamagedState damagedState;
	public SkeletonDieState dieState;
	// attack area
	Rectangle attackDistance= new Rectangle();
	public boolean canAttack=false;
	public boolean battleIsOn=false;
	private float defaulSpeed;
	private float coolDownBattle=0.1f;
	private float battleTimer=coolDownBattle;;
	private int scale;
	private int gapCanAttack;
	private Player player;
	//int attackDistance=50;
	public SkeletonEnemy(GamePanel gp,Player _player,KeyHandler keyH, int _worldX, int _worldY,float _speed,int scale, int _gapCanAttack, int _hp ) {
		super(gp, keyH);
		this.worldX=_worldX*gp.tileSize;
		this.worldY=_worldY*gp.tileSize;
		this.speed=_speed;
		this.gapCanAttack=_gapCanAttack;
		this.scale=scale;
		this.player=_player;
		this.hp=_hp;
		setUpValue();
	}
	private void setUpValue() {
		width=gp.tileSize*scale;
		height=gp.tileSize*scale;
		// state
		stateMachine= new EnemyStateMachine();
		idleState= new SkeletonIdleState(this, stateMachine, "Idle", gp, this);
		runState= new SkeletonRunState(this, stateMachine, "Run", gp, this);
		attackState= new SkeletonAttackState(this, stateMachine, "Attack", gp, this);
		damagedState= new SkeletonDamagedState(this, stateMachine, "Damaged", gp, this);
		dieState= new SkeletonDieState(this, stateMachine, "Die", gp, this);
		// rigid body
		solidArea= new Rectangle();
		solidArea.x=width/4+width/6;
		solidArea.y=0;
		solidArea.width=width/3-width/6;
		solidArea.height=height-height/4;
		//
		isEnemy=true;
		collision= new CreateCollision(this, gp);
		stateMachine.initialize(idleState);
		// attack
		attackDistance.x=-width/2;
		attackDistance.y=0;
		attackDistance.width=width+width;
		attackDistance.height=solidArea.height;
		defaulSpeed=speed;
	}
	@Override
	public void  update() {
		super.update();
		stateMachine.currentState.update();
		collision.updateGravity();
		if(stateMachine.currentState.animBoolName!="Die")
			CheckBattleMode();
		manageAnimation();
			
	}
	private void manageAnimation() {
		if(stateMachine.currentState.animBoolName=="Idle")
			RunAnimation(idle,2);
		if(stateMachine.currentState.animBoolName=="Run")
			RunAnimation(run,2);
		if(stateMachine.currentState.animBoolName=="Attack")
			RunAnimation(attack,5);
		if(stateMachine.currentState.animBoolName=="Damaged")
			RunAnimation(damaged,2);
		if(stateMachine.currentState.animBoolName=="Die")
			RunAnimation(die,5);
	}
	private void RunAnimation(BufferedImage[] stage,int framerate) {
		if(spriteCounter<framerate) {
			spriteCounter++;
		}else {
			if(spriteNum<stage.length) {						
				image=stage[spriteNum];
				spriteNum++;
			}else {
				spriteNum=0;
				finishedAnimation();
			}
			spriteCounter=0;
		}
	}
	@Override
	public void draw(Graphics2D g2) {		
		super.draw(g2);
		//System.out.println(2);
		g2.setColor(Color.green);
		screenX=worldX-player.worldX+player.screenX;
		screenY=worldY-player.worldY+player.screenY;
		if(worldX+width>=player.worldX-player.screenX&&
		   worldX-width<= player.worldX+player.screenX&&
		   worldY+height>=player.worldY-player.screenY&&
		   worldY-height<=player.worldY+player.screenY
		   ) {
//		   	g2.setColor(Color.blue);
//			g2.fillRect(screenX+solidArea.x, screenY+solidArea.y,solidArea.width, solidArea.height);
			// ve duong attack
//			if(canAttack)
//			g2.setColor(Color.red);
//			g2.fillRect(screenX+attackDistance.x,screenY+attackDistance.y,attackDistance.width,attackDistance.height);
			//
			int delta=(faceDirection==-1 )?width:0;
			g2.drawImage(image,(int)(screenX+delta),screenY,(int)faceDirection*width,height,null);
		}
	}
	public void CheckBattleMode() {
		if(player.worldX+player.solidArea.x<=worldX+attackDistance.x+attackDistance.width&&
				player.worldX+player.solidArea.x+player.solidArea.width>=worldX+attackDistance.x&&
				player.worldY+player.solidArea.y<=worldY+attackDistance.y+attackDistance.height&&
				player.worldY+player.solidArea.y+player.solidArea.height>=worldY+attackDistance.y)
		{					
				speed=defaulSpeed*3;
				if(!canMoveLeft&&player.worldX+player.solidArea.x+player.width<=worldX+solidArea.x){
					faceDirection=1;
					speed=defaulSpeed;
					updateVelocity();
					return;
				}	
				distanceCanAttack();
		}else {
				  canAttack=false;
				  speed=defaulSpeed;
				  battleTimer=0;	
			 }			
				
	}
	public void finishedAnimation() {
		stateMachine.currentState.triggerCalled=true;
	}
	private void distanceCanAttack() {
		int screenXLine=worldX+solidArea.x;
		int delta=faceDirection==-1?player.solidArea.width:0;
		if(Math.abs((player.worldX+player.solidArea.x+delta)-(screenXLine))<90) {
			canAttack=true;
			if(stateMachine.currentState.animBoolName!="Attack")
				stateMachine.changeState(attackState);
			battleTimer=coolDownBattle;	
			if(spriteNum>=4&& spriteNum<=9 ) {
				player.getDamage(faceDirection);
			}	
			
		}else 
		canAttack=false;
	}
	@Override
	public void getDamaged() {
		this.hp-=10;
		System.out.println(hp);
		if(this.hp>0)
			stateMachine.changeState(damagedState);
		else {
			 if(stateMachine.currentState.animBoolName!="Die")
			 stateMachine.changeState(dieState);		 
		}
	}
}
