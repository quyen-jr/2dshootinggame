//package Skill;
//
//import main.GamePanel;
//
//public class Skill {
//	protected float coolDown;
//	protected float coolDownTimer;
//	public GamePanel gp;
//	public Skill(GamePanel gp) {
//		this.gp=gp;
//	}
//	public void update() {
//		coolDownTimer-=gp.deltaTime;
//	}
//	public void useSkill() {
//		
//	}
//	public void canUseSkill() {
//		if(coolDownTimer<0) {
//			coolDownTimer=coolDown;
//			useSkill();
//			
//		}
//	}
//}
