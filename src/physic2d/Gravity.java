//package physic2d;
//
//import Entity.Entity;
//import main.GamePanel;
//
//public class Gravity {
//	private  float x,y;
//	private float gravityScale;
//	private float velocityX, velocityY;
//	private Entity entity;
//	private double deltaTime;
//	private double acc=9.8f;
//	
//	GamePanel gp;
//	public Gravity(GamePanel _gp) {
//		this.gp=_gp;
//	}
//	public float getGravityScale() {
//		return gravityScale;
//	}
//	public void setGravityScale(float gravityScale) {
//		this.gravityScale = gravityScale;
//	}
//	public void applyGravity(Entity entity) {
//		entity.worldY-=acc*deltaTime*gravityScale;
//	}
//	public void  caculateVelocity(double delta) {
//		deltaTime=delta;
//		
//	}
//	public void setVelocity(Entity entity, float xSpeed, float ySpeed) {
//		//while(true) {
//		//caculateVelocity();
//		//System.out.println(deltaTime*xSpeed);
//			velocityX=x;
//			velocityY=y;
//			entity.worldX+=(int)xSpeed*getDeltaTime();
//			entity.worldY+=(int)ySpeed*getDeltaTime();
//			
//	//	}
//	}
//	public double getDeltaTime() {
//		return deltaTime;
//	}
//	public void setDeltaTime(double deltaTime) {
//		this.deltaTime = deltaTime;
//	}
//	public float getVelocityX() {
//		return velocityX;
//	}
//	public float getVelocityY() {
//		return velocityY;
//	}
//}
