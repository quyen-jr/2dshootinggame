package SkeletonEnemy;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import Entity.Entity;
import main.GamePanel;

public class SkeletonRunState extends EnemyState {
	SkeletonEnemy enemy;
	private int lastFaceDirection;
	public SkeletonRunState(Enemy _enemyBase, EnemyStateMachine _stateMachine, String _ainmBoolName, GamePanel _gp,SkeletonEnemy enemy) {
		super(_enemyBase, _stateMachine, _ainmBoolName, _gp);
		this.enemy=enemy;
		getRunImage();
	}

	@Override
	public void enter() {
		super.enter();
		lastFaceDirection=enemy.faceDirection;
		enemy.spriteNum=0;
	}

	@Override
	public void exit() {
		super.exit();
	}

	@Override
	public void update() {
		super.update();
		// doi huong ==> set ve idle
		if(lastFaceDirection!=enemy.faceDirection) {
			lastFaceDirection=enemy.faceDirection;
			stateMachine.changeState(enemy.idleState);
			return;
			// return ngan thuc hien lenh set velocity ben duoi
		}
		if(!enemy.preventMove) {
				enemy.setVelocitỵ̣̣̣̣(enemy.faceDirection*enemy.speed, enemy.Yvelocity);	
		}
		else {
			enemy.setVelocitỵ̣̣̣̣(0, enemy.Yvelocity);
			stateMachine.changeState(enemy.idleState);
		}
	}
	private void getRunImage() {
		try {
			enemy.run= new BufferedImage[12];
			enemy.run[0]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/26_Skeleton enemy.png"));
			enemy.run[1]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/27_Skeleton enemy.png"));
			enemy.run[2]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/28_Skeleton enemy.png"));
			enemy.run[3]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/29_Skeleton enemy.png"));
			enemy.run[4]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/30_Skeleton enemy.png"));
			enemy.run[5]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/31_Skeleton enemy.png"));
			enemy.run[6]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/32_Skeleton enemy.png"));
			enemy.run[7]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/33_Skeleton enemy.png"));
			enemy.run[8]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/34_Skeleton enemy.png"));
			enemy.run[9]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/35_Skeleton enemy.png"));
			enemy.run[10]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/36_Skeleton enemy.png"));
			enemy.run[11]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonRun/37_Skeleton enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
