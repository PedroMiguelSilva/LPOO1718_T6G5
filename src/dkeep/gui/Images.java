package dkeep.gui;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Images extends JFrame{

    protected BufferedImage wall;
    private BufferedImage wall2;
    private BufferedImage ground;
    private BufferedImage guard;
    private BufferedImage hero;
    private BufferedImage ogre;
    private BufferedImage lever;
    private BufferedImage weapon;
    private BufferedImage key;
    private BufferedImage door;
    	int height, width;
    	JFrame frame;

    public Images() {
    
		//this.width = frame.getWidth();
		//this.height = frame.getHeight();
       try {                
          this.wall = ImageIO.read(new File("Images/wall.jpg"));         
          this.wall2 = ImageIO.read(new File("Images/wall2.jpg"));       
          this.ground = ImageIO.read(new File("Images/ground.jpg"));
          this.guard = ImageIO.read(new File("Images/guard.jpg"));
          this.hero = ImageIO.read(new File("Images/hero.jpg"));
          this.ogre = ImageIO.read(new File("Images/ogre.jpg"));
          this.lever = ImageIO.read(new File("Images/lever.jpg"));
          this.weapon = ImageIO.read(new File("Images/weapon.jpg"));
          this.key = ImageIO.read(new File("Images/key.jpg"));
          this.door = ImageIO.read(new File("Images/door.jpg"));
       } catch (IOException ex) {
            System.out.println("Inexisting file");
       }
    }
    
    public  BufferedImage getWall() {
		return wall;
	}
    public BufferedImage getWall2() {
		return wall2;
	}

	public BufferedImage getGround() {
		return ground;
	}

	public BufferedImage getDoor() {
		return door;
	}


	public BufferedImage getGuard() {
		return guard;
	}

	
	public BufferedImage getOgre() {
		return ogre;
	}

	

	public BufferedImage getHero() {
		return hero;
	}


	public BufferedImage getLever() {
		return lever;
	}


	public BufferedImage getKey() {
		return key;
	}
	
	public BufferedImage getWeapon() {
			return weapon;
		}

}