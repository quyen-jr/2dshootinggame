package PlayerState;

import main.GamePanel;

public class PlayerGroundedState extends PlayerState{
	public PlayerGroundedState(Player _player, PlayerStateMachine _stateMachine, String _animBoolName, GamePanel _gp) {
		super(_player, _stateMachine, _animBoolName, _gp);
	}
	@Override
	public void enter() {
		super.enter();
	}

	@Override
	public void exit() {
		super.exit();
	}

	@Override
	public void update() {
		super.update();

		if (player.keyH.enterPressed) {
			stateMachine.changeState(player.shootState);
			return;
		}
		if(player.keyH.spacePressed&&player.collision.getySpeed()==0&&player.collision.isGrounded) {
			player.collision.isGrounded=false;
			stateMachine.changeState(player.jumpState);
		}
		if(gp.keyH.xInput!=0&&!player.preventMove&&player.collision.getySpeed()==0) {
			stateMachine.changeState(player.runState);
			return;
		}
		if(player.collision.getySpeed()>0) {
			stateMachine.changeState(player.fallState);
		}
		
	}

	

}
