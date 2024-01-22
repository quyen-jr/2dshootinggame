package collisionDetection;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.Entity;
import main.GamePanel;
import tile.TileManager;

public class CreateCollision {
	GamePanel gp;
	private float acceleration=0.3f;
	public float getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	public float getySpeed() {
		return ySpeed;
	}
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	private float ySpeed=0;
	Entity entity;
	public boolean isGrounded=false;
	public  CreateCollision(Entity entity , GamePanel gp) {
		this.entity=entity;
		this.gp=gp;
	}
	public void checkCollisionTile() {
		switch (entity.faceDirection) {
		case 1:
			if(canMoveOnRightSide(entity.Xvelocity)) {
				entity.preventMove=false;
			}else {
				if(entity.isEnemy)
					entity.faceDirection=-1;
			}
		break;
		case -1:
			if(canMoveOnLeftSide(entity.Xvelocity)) {
				entity.preventMove=false;
			}else {
				if(entity.isEnemy)
					entity.faceDirection=1;
			}
		break;
			
		default:entity.preventMove=false;
			break;
		}
		// va cham tren dau
		if(!isGrounded) {	
			checkCollisionOnTopOfEntiy();
		}
	}
	private void checkCollisionOnTopOfEntiy() {
		int topColLeft=  ((entity.worldX+entity.solidArea.x+entity.solidArea.width/2)/gp.tileSize);
		int topColRight= (int) ((entity.worldX+entity.solidArea.x+entity.solidArea.width-entity.solidArea.width/4)/gp.tileSize);
		int rowCol= (int) ((entity.worldY+ySpeed)/gp.tileSize);
		int topNumLeft=gp.level1Scene.tileM.mapTileNum[topColLeft][rowCol];
		int topNumRight=gp.level1Scene.tileM.mapTileNum[topColRight][rowCol];
//	
		if(gp.level1Scene.tileM.tile[topNumLeft].collision) {
			ySpeed=0;
			ySpeed+=acceleration;
			entity.setVelocitỵ̣̣̣̣(entity.Xvelocity, +5);
			entity.updateVelocity();
		}
	}
	public boolean canMoveOnRightSide( float xVelocity) {
		// ngan can di chuyen 
		int entityRightWorldX= (int) (entity.worldX+entity.solidArea.x+entity.solidArea.width+xVelocity);
		int entityTopWorldY= entity.worldY+entity.solidArea.y;
		int entityBottomWorldY= entity.worldY+entity.solidArea.y+ entity.solidArea.height;
		int entityTopRow;
		int entityRightCol=(int) ((entityRightWorldX)/gp.tileSize);
		entityTopRow= entityTopWorldY/gp.tileSize;	
		int topRight= gp.level1Scene.tileM.mapTileNum[entityRightCol][entityTopRow];
		int entityBottomRow=(int)(entityBottomWorldY-gp.tileSize/2)/gp.tileSize ;
		int downRight=gp.level1Scene.tileM.mapTileNum[entityRightCol][entityBottomRow];
		// kiem tra ben duoi va ben tren nhan vat o ben phai khi di chuyen co va cham voi ai k
		// neu co ==> stop di chuyen 
		if(gp.level1Scene.tileM.tile[topRight].collision||gp.level1Scene.tileM.tile[downRight].collision ) {
			entity.preventMove=true;
			// neu la enemy moi lat
		//	if(entity.isEnemy)	entity.faceDirection=-1;
			return false;
		}
		return true;
	}
	public boolean canMoveOnLeftSide(float xVelocity) {
		// ngan can di chuyen 
		int entityLeftWorldX= (int) (entity.worldX+entity.solidArea.x-xVelocity);
		int entityTopWorldY= entity.worldY+entity.solidArea.y;
		int entityBottomWorldY= entity.worldY+entity.solidArea.y+ entity.solidArea.height;
		int entityTopRow;
		int entityLeftCol=(int) ((entityLeftWorldX)/gp.tileSize);
		entityTopRow= entityTopWorldY/gp.tileSize;
		int topleft = gp.level1Scene.tileM.mapTileNum[entityLeftCol][entityTopRow];
		int entityBottomRow1=(int)(entityBottomWorldY-gp.tileSize/2)/gp.tileSize;
		int downLeft= gp.level1Scene.tileM.mapTileNum[entityLeftCol][entityBottomRow1];
		if(gp.level1Scene.tileM.tile[topleft].collision||gp.level1Scene.tileM.tile[downLeft].collision) {
			entity.preventMove=true;
			return false;
		}
		return true;
	}
	public void updateGravity() {
		checkCollisionTile();
		LogicGravityGround();
		
		
	}
 	private void LogicGravityGround() {
		//  ap dung trong luc
		ySpeed+=acceleration;
		entity.setVelocitỵ̣̣̣̣(entity.Xvelocity, ySpeed);
		entity.updateVelocity();

		
		int entityBottomWorldY=  (entity.worldY+entity.solidArea.y+ entity.solidArea.height);
		int entityBottomRow=(int) ((entityBottomWorldY+entity.speed)/gp.tileSize);
		//  check collision with ground in middle 
		int entityMiddleWorldX= (int) (entity.worldX+entity.solidArea.x+(entity.solidArea.width/2));
		int entityBottomCol=(int) ((entityMiddleWorldX)/gp.tileSize);
	
		int middle= gp.level1Scene.tileM.mapTileNum[entityBottomCol][entityBottomRow];
		
		//  check collision with ground in left
		int delta=18;// delta de thay doi collision cho chinh xac
		int entityLeftWorldX= (int) (entity.worldX+entity.solidArea.x+delta);
		int entityBottomLeftCol=(int) ((entityLeftWorldX)/gp.tileSize);
		int left= gp.level1Scene.tileM.mapTileNum[entityBottomLeftCol][entityBottomRow];
		////  check collision with ground in right
		int entityRightWorldX= entity.worldX+entity.solidArea.x+entity.solidArea.width-28;
		int entityBottomRightCol= entityRightWorldX/gp.tileSize;
		int right= gp.level1Scene.tileM.mapTileNum[entityBottomRightCol][entityBottomRow];
		// check neu  isground 1 trong 3 ---
		if(gp.level1Scene.tileM.tile[middle].isGround||gp.level1Scene.tileM.tile[left].isGround|| gp.level1Scene.tileM.tile[right].isGround) {
			int rect2Y=entityBottomRow*gp.tileSize;
			
			   int groundY = entityBottomRow * gp.tileSize - entity.solidArea.height;
				entity.worldY=(int) (rect2Y-entity.solidArea.height-entity.solidArea.y);
				ySpeed=0;
			isGrounded=true;
			ySpeed=0;
			// neu la enemy thi doi huong khi truoc k co gi // chi ap dung cho enemy
			flipWhenNotHaveBlockInFront(entityBottomRow, left, right);
			return;
		}
	}
	private void flipWhenNotHaveBlockInFront(int entityBottomRow, int left, int right) {
		int delta;
		int entityLeftWorldX;
		int entityBottomLeftCol;
		int entityRightWorldX;
		int entityBottomRightCol;
		if(entity.isEnemy) {
		
			if(gp.level1Scene.tileM.tile[left].isGround) {
				 delta=8;// delta de thay doi collision cho chinh xac
				 entityLeftWorldX= (int) (entity.worldX+entity.solidArea.x-gp.tileSize);
				 entityBottomLeftCol=(int) ((entityLeftWorldX)/gp.tileSize);
				 left= gp.level1Scene.tileM.mapTileNum[entityBottomLeftCol][entityBottomRow];
				 if(!gp.level1Scene.tileM.tile[left].isGround) {
					 entity.faceDirection=1;
					 entity.canMoveLeft=false;
				 }else {
					 entity.canMoveLeft=true;
				}
				 
			}
			if(gp.level1Scene.tileM.tile[right].isGround) {
			    entityRightWorldX= entity.worldX+entity.solidArea.x+entity.solidArea.width+gp.tileSize;
				entityBottomRightCol= entityRightWorldX/gp.tileSize;
				right= gp.level1Scene.tileM.mapTileNum[entityBottomRightCol][entityBottomRow];
				if(!gp.level1Scene.tileM.tile[right].isGround) {
					entity.faceDirection=-1;
					entity.canMoveRight=false;
				return;
				}else entity.canMoveRight=true;
			}
		}
	}
}
