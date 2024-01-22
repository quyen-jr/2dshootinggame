package SkeletonEnemy;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import main.GamePanel;

public class SkeletonDieState extends EnemyState{
	SkeletonEnemy enemy;
	public SkeletonDieState(Enemy _enemyBase, EnemyStateMachine _stateMachine, String _ainmBoolName, GamePanel _gp,SkeletonEnemy _enemy) {
		super(_enemyBase, _stateMachine, _ainmBoolName, _gp);
		this.enemy=_enemy;
		getDieImage();
	}

	@Override
	public void enter() {
		super.enter();
		enemy.setVelocitỵ̣̣̣̣(0, 0);
		enemy.spriteNum=0;
	}

	@Override
	public void exit() {
		super.exit();
	}

	@Override
	public void update() {
		super.update();
		if(triggerCalled) {
			enemy.isActive=false;
		}
	}
	private void getDieImage(){
		try {
			enemy.die= new BufferedImage[13];
			enemy.die[0]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/13_Skeleton enemy.png"));
			enemy.die[1]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/14_Skeleton enemy.png"));
			enemy.die[2]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/15_Skeleton enemy.png"));
			enemy.die[3]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/16_Skeleton enemy.png"));
			enemy.die[4]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/17_Skeleton enemy.png"));
			enemy.die[5]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/18_Skeleton enemy.png"));
			enemy.die[6]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/19_Skeleton enemy.png"));
			enemy.die[7]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/20_Skeleton enemy.png"));
			enemy.die[8]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/21_Skeleton enemy.png"));
			enemy.die[9]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/22_Skeleton enemy.png"));
			enemy.die[10]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/23_Skeleton enemy.png"));
			enemy.die[11]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/24_Skeleton enemy.png"));
			enemy.die[12]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDie/25_Skeleton enemy.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
