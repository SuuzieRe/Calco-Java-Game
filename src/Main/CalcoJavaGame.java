package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import GameObjects.Wall;
import GameObjects.Bush;
import Player.Player;

public class CalcoJavaGame extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	public Menu mainFrame;
	private Handler handler;
	private Camera camera;
	int PlayerHp = 5;
	
	private BufferedImage level = null;
	private BufferedImage floor_sprite_sheet = null;
	private BufferedImage floor = null;
	private BufferedImage ObjectSpriteSheet = null;
	private BufferedImage wall = null;
	
	private SpriteSheet wallss;
	private SpriteSheet floorss;
	
	public CalcoJavaGame(Menu mainFrame) {
		//start();
		this.mainFrame = mainFrame;
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));


		this.setBackground(Color.CYAN);
				
		BufferedImageLoader loader = new BufferedImageLoader();
		
		level = loader.LoadImage("/Pngs/level_1.png");
		floor_sprite_sheet = loader.LoadImage("/Pngs/Sprite_Sheet.png");
		ObjectSpriteSheet = loader.LoadImage("/Pngs/Sprite_Sheet_Objects.png");
		
		floorss = new SpriteSheet(floor_sprite_sheet);
		floor = floorss.grabImage(4, 2, 32, 32);

		wallss = new SpriteSheet(ObjectSpriteSheet);
		wall = wallss.grabImage(2, 8, 32, 32);
		//ObjectSpriteSheet = new SpriteSheet(sprite_sheet);
		
		loadLevel(level);
	}
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;	
		///////////////////////////////            
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx = 0; xx < 30*72; xx+=32) {
			for(int yy = 0; yy < 30*72; yy+=32) {
				g.drawImage(floor, xx, yy, null);
			}
		}	
    
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
		g.setColor(Color.black);
	    g.fillRect(19, 19, 120, 22);
	    g.setColor(Color.red);
	    if(PlayerHp >= 1) {
	    g.fillRect(20, 20, 20, 20);
	    }
	    if(PlayerHp >= 2) {
		g.fillRect(45, 20, 20, 20);
		}
	    if(PlayerHp >= 3) {
			g.fillRect(70, 20, 20, 20);
			}
	    if(PlayerHp >= 4) {
			g.fillRect(95, 20, 20, 20);
			}
	    if(PlayerHp >= 5) {
			g.fillRect(120, 20, 20, 20);
			}
	   
		
		///////////////////////////////
		g.dispose();
		
		
		
		bs.show();
	}
	//loading the level
	private void loadLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
			
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0;yy<h; yy++) {
				int pixel = image.getRGB (xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) &0xff;
				
				if(red == 255 && green == 0 && blue == 0)
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, this));
					
        else if(red == 0 && green == 255 && blue == 0)
						handler.addObject(new Wall(xx*32, yy*32, ID.Wall, this.wall));
				
        else if(red == 0 && green == 0 && blue == 255)
			handler.addObject(new Bush(xx*32, yy*32, ID.Bush));
					
					
				}
			}
		}
	

	public void setIsRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
}