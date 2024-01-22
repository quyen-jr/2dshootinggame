package PlayerState;


import main.GamePanel;

public class PlayerState{
	protected PlayerStateMachine stateMachine;
	protected Player player;
    protected float xInput;
    protected float yInput;
    public String animBoolName;
    protected float stateTimer=0;
    public boolean triggerCalled;
    protected GamePanel gp;
    public static float yVelocity=0;
    private float acc=0.5f;
    public PlayerState(Player _player,PlayerStateMachine _stateMachine,String _animBoolName,GamePanel _gp) {
		this.player=_player;
		this.stateMachine=_stateMachine;
		this.animBoolName=_animBoolName;
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
