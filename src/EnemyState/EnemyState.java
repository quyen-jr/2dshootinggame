package EnemyState;

import main.GamePanel;

public class EnemyState {
	protected EnemyStateMachine stateMachine;
	private Enemy enemy;
	protected GamePanel gp;
	public String animBoolName;
	public boolean triggerCalled;
	public float stateTimer=0;
	public EnemyState(Enemy _enemyBase,EnemyStateMachine _stateMachine, String _ainmBoolName,GamePanel _gp) {
		this.enemy=_enemyBase;
		this.stateMachine=_stateMachine;
		this.animBoolName=_ainmBoolName;
		this.gp=_gp;
	
	}
	public void enter() {
		triggerCalled=false;
	}
	public void exit() {
		
	}
	public void update() {
		stateTimer-=0.01;
	}
}
