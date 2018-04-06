package dkeep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import dkeep.logic.CustomLevel;
import dkeep.logic.Symbol;
import dkeep.gui.*;

public class MyCustomPanel extends JPanel implements MouseListener {
	Images images = new Images();
	Symbol s;

	protected CustomLevel map = new CustomLevel();


	protected int x;
	protected int y;
	private Symbol symbol;
	protected int elementWidth;
	protected int elementHeight;

	static Map<Symbol,BufferedImage> img = new HashMap<Symbol,BufferedImage>();



	public MyCustomPanel() {
		this.x = map.getMap().length;
		this.y = map.getMap()[0].length;
		addMouseListener(this);
	}
	public void setx(String x2) {
		if(x2 == null || x2 =="" || x2 == " ")
			this.x = 0;
		else this.x= (Integer.parseInt(x2));

	}

	public void setY(String y2) {
		if(y2 == null|| y2 =="" || y2 == " ")
			this.y=0;
		else this.y= (Integer.parseInt(y2));
	}



	public boolean setNewGame() {
		if(x<4 || y<4 || x>10 || y>10)  {
			map = new CustomLevel();
			repaint();
			return false;
		}
		map = new CustomLevel(x,y);
		repaint();
		return true;
	}

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

	public void drawGame(Graphics g, Symbol[][] map, Map<Symbol, BufferedImage> img,int elementWidth, int elementHeight){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j <map[0].length; j++) {
				int xint = j * elementWidth;
				int yint = i * elementHeight;
				g.drawImage(img.get(map[i][j]),xint,yint,this);
			} 
		}
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.elementWidth =this.getWidth() / map.getMap().length;
		this.elementHeight =this.getHeight() / map.getMap()[0].length;


		BufferedImage Door = scale(images.getDoor(), elementWidth, elementHeight);
		img.put(Symbol.DOOR_CLOSED, Door);
		BufferedImage Wall = scale(images.getWall(), elementWidth, elementHeight);
		img.put(Symbol.WALL, Wall);
		BufferedImage Hero = scale(images.getHero(), elementWidth, elementHeight);
		img.put(Symbol.HERO, Hero);
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

		drawGame(g,map.getMap(),img,elementWidth, elementHeight);

	}

	public void setSymbol(Symbol s) {
		this.symbol = s;
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX()/this.elementWidth;
		int y = arg0.getY()/this.elementHeight;
		map.editMap(symbol, y, x);
		repaint();




	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public CustomLevel getMap(){
		return map;
	}





}