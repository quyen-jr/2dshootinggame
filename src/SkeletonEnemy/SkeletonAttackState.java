package SkeletonEnemy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Identity;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import EnemyState.Enemy;
import EnemyState.EnemyState;
import EnemyState.EnemyStateMachine;
import main.GamePanel;

public class SkeletonAttackState extends EnemyState {
	SkeletonEnemy enemy;
	private Clip attackClip;
	public SkeletonAttackState(Enemy _enemyBase, EnemyStateMachine _stateMachine, String _ainmBoolName, GamePanel _gp, SkeletonEnemy _enemy) {
		super(_enemyBase, _stateMachine, _ainmBoolName, _gp);
		this.enemy=_enemy;
		getAttackImage();
		setUpSound();
	}
	@Override
	public void enter() {
		super.enter();
		enemy.setVelocitỵ̣̣̣̣(0, 0);
		playSound();
	}
	@Override
	public void exit() {
		super.exit();
		//System.out.println(attackClip);
		stopSound();
	}
	@Override
	public void update() {
		super.update();
		
		if(triggerCalled) {
			stateMachine.changeState(enemy.idleState);
		}
	}

	private void getAttackImage() {
		try {
			enemy.attack=new BufferedImage[13];
			enemy.attack[0]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/00_Skeleton enemy.png"));
			enemy.attack[1]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/01_Skeleton enemy.png"));
			enemy.attack[2]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/02_Skeleton enemy.png"));
			enemy.attack[3]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/03_Skeleton enemy.png"));
			enemy.attack[4]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/04_Skeleton enemy.png"));
			enemy.attack[5]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/05_Skeleton enemy.png"));
			enemy.attack[6]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/06_Skeleton enemy.png"));
			enemy.attack[7]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/07_Skeleton enemy.png"));
			enemy.attack[8]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/08_Skeleton enemy.png"));
			enemy.attack[9]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/09_Skeleton enemy.png"));
			enemy.attack[10]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/10_Skeleton enemy.png"));
			enemy.attack[11]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/11_Skeleton enemy.png"));
			enemy.attack[12]= ImageIO.read(getClass().getResourceAsStream("/enemySkeletonAttack/12_Skeleton enemy.png"));

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setUpSound() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource("/music/skeletonattack.wav"));
			attackClip=AudioSystem.getClip();
			attackClip.open(ais);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void playSound() {
	//	if(!attackClip.isRunning()) {
			
			attackClip.start();
	//	}
			
	}
	private void stopSound() {
		attackClip.stop();
		attackClip.setFramePosition(0);
		
	}
}
