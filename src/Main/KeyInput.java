package Main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import GameObjects.GameObject;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
		
	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObjects.GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
				if(key == KeyEvent.VK_1) handler.setOne(true);
				if(key == KeyEvent.VK_2) handler.setTwo(true);
				if(key == KeyEvent.VK_3) handler.setThree(true);
				if(key == KeyEvent.VK_4) handler.setFour(true);
			}
		}
	}
		

	
	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();
			
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
				if(key == KeyEvent.VK_1) handler.setOne(false);
				if(key == KeyEvent.VK_2) handler.setTwo(false);
				if(key == KeyEvent.VK_3) handler.setThree(false);
				if(key == KeyEvent.VK_4) handler.setFour(false);
				if(key == KeyEvent.VK_I) handler.setInventory(true);
				if(key == KeyEvent.VK_ESCAPE) handler.setEscape(true);
			}
		}
	}
}