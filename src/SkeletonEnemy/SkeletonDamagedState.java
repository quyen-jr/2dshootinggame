package SkeletonEnemy;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import main.GamePanel;

public class SkeletonDamagedState extends EnemyState{
	SkeletonEnemy enemy;
	public SkeletonDamagedState(Enemy _enemyBase, EnemyStateMachine _stateMachine, String _ainmBoolName,
			GamePanel _gp, SkeletonEnemy _enemy) {
		super(_enemyBase, _stateMachine, _ainmBoolName, _gp);
		this.enemy=_enemy;
		getDamagedImage();
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		super.enter();
		enemy.setVelocitỵ̣̣̣̣(0,2);
		stateTimer=0.1f;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		super.exit();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if(triggerCalled&&stateTimer<0) {
			stateMachine.changeState(enemy.runState);
		}
		
	}
	private void getDamagedImage() {
		try {
			enemy.damaged= new BufferedImage[3];
			enemy.damaged[0]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDamaged/52_Skeleton enemy.png"));
			enemy.damaged[1]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDamaged/53_Skeleton enemy.png"));
			enemy.damaged[2]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonDamaged/54_Skeleton enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
