package dkeep.gui;
import javax.swing.JPanel;
import java.util.Map;
import java.util.HashMap;
import dkeep.logic.Symbol;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import dkeep.logic.Cmd;
import dkeep.logic.Game;


public class MyPanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game gamex; 
	Images images = new Images();
	
	

	public MyPanel() {
		addKeyListener(this);
		//setOpaque(false);
	}

	public void setGame(Game game) {
		this.gamex = game; 
	}
	
	static Map<Symbol,BufferedImage> img = new HashMap<Symbol,BufferedImage>(); 

	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        		}
        return scaledImage;
        }

	public void drawGame(Graphics g, Symbol[][] map, Map<Symbol, BufferedImage> img, int elementWidth, int elementHeight){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j <map.length; j++) {
				int x = j * elementWidth;
				int y = i * elementHeight;
				g.drawImage(img.get(map[i][j]),x,y,this);
			} 
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (this.gamex == null)
			return;
		
		int elementWidth =this.getWidth() / gamex.getSymbolMap().length;
		int elementHeight =this.getHeight() /gamex.getSymbolMap()[0].length;
		
		BufferedImage Wall = scale(images.getWall(), elementWidth, elementHeight);
		img.put(Symbol.WALL, Wall);
		
		BufferedImage Door = scale(images.getDoor(), elementWidth, elementHeight);
		img.put(Symbol.DOOR_CLOSED, Door);
		
		BufferedImage Lever = scale(images.getLever(), elementWidth, elementHeight);
		img.put(Symbol.LEVER, Lever);
		
		BufferedImage Hero = scale(images.getHero(), elementWidth, elementHeight);
		img.put(Symbol.HERO, Hero);
		
		BufferedImage ArmedH = scale(images.getArmedH(), elementWidth, elementHeight);
		img.put(Symbol.HERO_WITH_CLUB, ArmedH);
		
		BufferedImage Guard = scale(images.getGuard(), elementWidth, elementHeight);
		img.put(Symbol.GUARD, Guard);
		
		BufferedImage GuardAsleep = scale(images.getGuard(), elementWidth, elementHeight);
		img.put(Symbol.GUARD_SLEEP, GuardAsleep);
		
		BufferedImage Ogre = scale(images.getOgre(), elementWidth, elementHeight);
		img.put(Symbol.OGRE, Ogre);
		
		BufferedImage StunedOgre = scale(images.getStunnedOgre(), elementWidth, elementHeight); //atenção!!
		img.put(Symbol.OGRE_STUNED, StunedOgre);
		
		BufferedImage Weapon = scale(images.getWeapon(), elementWidth, elementHeight);
		img.put(Symbol.OGRE_WEAPON, Weapon); 
		
		BufferedImage Key = scale(images.getKey(), elementWidth, elementHeight);
		img.put(Symbol.KEY, Key);
		
		BufferedImage Ground = scale(images.getGround(), elementWidth, elementHeight);
		img.put(Symbol.CLEAR_SPACE, Ground);
		
		
		drawGame(g,gamex.getSymbolMap(),img,elementWidth, elementHeight);
	
	
	}
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}