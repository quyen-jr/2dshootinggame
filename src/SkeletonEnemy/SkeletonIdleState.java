package SkeletonEnemy;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import main.GamePanel;

public class SkeletonIdleState extends EnemyState {
	SkeletonEnemy enemy;
	public SkeletonIdleState(Enemy _enemyBase, EnemyStateMachine _stateMachine, String _ainmBoolName, GamePanel _gp,
			SkeletonEnemy enemy) {
		super(_enemyBase, _stateMachine, _ainmBoolName, _gp);
		this.enemy=enemy;
		getIdleImage();
	}
	@Override
	public void enter() {
		super.enter();
		stateTimer=0.5f;
		enemy.Xvelocity=0;
		enemy.spriteNum=0;
	}
	@Override
	public void exit() {
		super.exit();
	}
	@Override
	public void update() {
		super.update();
		if(stateTimer<0&&!enemy.battleIsOn) {
			stateMachine.changeState(enemy.runState);
		}else if (enemy.battleIsOn) {
			stateMachine.changeState(enemy.runState);
		}
	}
	private void getIdleImage() {
		try {
			enemy.idle= new BufferedImage[4];
			enemy.idle[0]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonIdle/39_Skeleton enemy.png"));
			enemy.idle[1]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonIdle/40_Skeleton enemy.png"));
			enemy.idle[2]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonIdle/41_Skeleton enemy.png"));
			enemy.idle[3]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonIdle/42_Skeleton enemy.png"));
	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
