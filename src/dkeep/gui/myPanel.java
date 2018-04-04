package dkeep.gui;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import dkeep.logic.Map;
///import dkeep.gui.Images;
import dkeep.logic.Symbol;
import javafx.scene.input.KeyEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import dkeep.logic.Cmd;
import dkeep.logic.Coord;

import dkeep.logic.Game;
import dkeep.logic.GuardType;
import dkeep.cli.Start;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class myPanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game gamex; 
	Images images = new Images();
	
	

	public myPanel() {
		addKeyListener(this);
	}
	

	public void setGame(Game game) {
		this.gamex = game;
	}
	
	
	private BufferedImage wall = images.getWall();
	private BufferedImage hero = images.getHero();
	private BufferedImage armedH = images.getHero(); //preciso mudar isto!!!
	private BufferedImage guard = images.getGuard();
	private BufferedImage guardAsleep = images.getGuard();//isto tambem!!
	private BufferedImage ogre = images.getOgre();
	private BufferedImage ground = images.getGround();//isto tambem
	private BufferedImage weapon = images.getWeapon();
	private BufferedImage door = images.getDoor();
	private BufferedImage lever = images.getLever();
	private BufferedImage key = images.getKey();
	

	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;}
	public static char symbolToChar(Symbol s) {
		switch(s) {
		case HERO: return 'H';
		case HERO_WITH_KEY:return 'K';	case HERO_WITH_CLUB:return 'A';
		case GUARD:return 'G';			case GUARD_SLEEP:return 'g';
		case WALL:return 'X';			case CLUB_ON_KEY:return '$';
		case OGRE:return 'o';			case OGRE_ON_KEY:return '$';
		case OGRE_WEAPON:return '*';		case OGRE_STUNED:return '8';
		case LEVER:return 'k';			case KEY:return 'k';
		case DOOR_CLOSED:return 'i'; 	case DOOR_OPEN:return 'S';
		case CLEAR_SPACE:
		default:return ' ';
		}
	}

	public void drawGame(Graphics g, Symbol[][] map, int elementWidth, int elementHeight, BufferedImage wall,
			BufferedImage hero, BufferedImage armedH,  BufferedImage guard, BufferedImage guardAsleep, BufferedImage key,
			BufferedImage ogre, BufferedImage stunedOgre, BufferedImage weapon, BufferedImage door, BufferedImage lever, BufferedImage ground) {
		
	


		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int x = j * elementWidth;
				int y = i * elementHeight;
				char sym = symbolToChar(gamex.getSymbolMap()[i][j]);
				switch(sym) {
				case 'X': g.drawImage(wall, x, y, this); break;
				case 'i': g.drawImage(door, x, y, this); break;
				case 'k': 
					if(gamex.getCurrentLevel() == 1)
						g.drawImage(lever, x, y, this); 
					if(gamex.getCurrentLevel() ==2)
						g.drawImage(key,x,y,this);
						break;
				case 'H': g.drawImage(hero, x, y, this); break;
				case ' ': g.drawImage(ground, x, y, this); break;
				case 'h': g.drawImage(hero, x, y, this); break;
				case 'K': g.drawImage(armedH, x, y, this); break; //with key
				case 'G': g.drawImage(guardAsleep, x, y, this); break;
				case 'g': g.drawImage(guard, x, y, this); break; //asleep
				case 'o': g.drawImage(ogre, x, y, this); break;
				case '8': g.drawImage(stunedOgre, x, y, this); break; //stunned
				case '*': g.drawImage(weapon, x, y, this); break;
				case '$': g.drawImage(key, x, y, this); break;
				case 'A': g.drawImage(hero, x, y, this); break;
				default: break;
				}
			} 
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (this.gamex == null)
			return;
		
		int elementWidth =this.getWidth() / gamex.getSymbolMap().length;
		int elementHeight =this.getHeight() /gamex.getSymbolMap()[0].length;
		BufferedImage Wall = scale(wall, elementWidth, elementHeight);
		BufferedImage Door = scale(door, elementWidth, elementHeight);
		BufferedImage Lever = scale(lever, elementWidth, elementHeight);
		BufferedImage Hero = scale(hero, elementWidth, elementHeight);
		BufferedImage ArmedH = scale(armedH, elementWidth, elementHeight);
		BufferedImage Guard = scale(guard, elementWidth, elementHeight);
		BufferedImage GuardAsleep = scale(guardAsleep, elementWidth, elementHeight);
		BufferedImage Ogre = scale(ogre, elementWidth, elementHeight);
		BufferedImage StunedOgre = scale(ogre, elementWidth, elementHeight); //atenção!!
		BufferedImage Weapon = scale(weapon, elementWidth, elementHeight);
		BufferedImage Key = scale(key, elementWidth, elementHeight);
		BufferedImage Ground = scale(ground, elementWidth, elementHeight);
		
		
		
		drawGame(g,gamex.getSymbolMap(),elementWidth,elementHeight, Wall,Hero,ArmedH,Guard, GuardAsleep,Key,
				 Ogre,StunedOgre,Weapon,Door,Lever, Ground);
	
	
	}
	

//	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch(keyCode) {
				case java.awt.event.KeyEvent.VK_LEFT: gamex.moveHero(Cmd.LEFT);   repaint(); break;
				case java.awt.event.KeyEvent.VK_RIGHT: gamex.moveHero(Cmd.RIGHT); repaint(); break;
				case java.awt.event.KeyEvent.VK_UP: gamex.moveHero(Cmd.UP);       repaint(); break;
				case java.awt.event.KeyEvent.VK_DOWN: gamex.moveHero(Cmd.DOWN);   repaint(); break;
				}
	}


	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		
	}


	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		
	}
	
}