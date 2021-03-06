package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.ID;

public abstract class GameObject {

	 protected int x, y; 						/** asdf **/
	 protected float velX = 0, velY = 0;
	 protected ID id;
	 protected boolean solid = false;
	 
	 public GameObject (int x, int y, ID id, boolean solid) {
		 this.x = x;
		 this.y = y;
		 this.id = id;
		 this.solid = solid;
	 }
	 
	 public GameObject (int x, int y, ID id) {
		 this.x = x;
		 this.y = y;
		 this.id = id;
	 }
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public boolean getSolid() {
		return solid;
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	 
}
