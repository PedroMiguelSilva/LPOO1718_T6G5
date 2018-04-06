package dkeep.gui;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    private BufferedImage armedH;
	private BufferedImage stunnedOgre;
	private BufferedImage guardAsleep;
    	int height, width;
    	JFrame frame;
	
 
    public Images() {
    	  
    
		//this.width = frame.getWidth();
		//this.height = frame.getHeight();
       try {                
          this.wall = ImageIO.read(new File("Images/wall.png"));         
          this.wall2 = ImageIO.read(new File("Images/wall2.png"));       
          this.ground = ImageIO.read(new File("Images/ground.png"));
          this.guard = ImageIO.read(new File("Images/guard.png"));
          this.hero = ImageIO.read(new File("Images/hero.png"));
          this.armedH = ImageIO.read(new File("Images/armedH.png"));
          this.ogre = ImageIO.read(new File("Images/ogre.png"));
          this.stunnedOgre = ImageIO.read(new File("Images/stunnedOgre.png"));
          this.lever = ImageIO.read(new File("Images/lever.png"));
          this.weapon = ImageIO.read(new File("Images/weapon.png"));
          this.key = ImageIO.read(new File("Images/key.png"));
          this.door = ImageIO.read(new File("Images/door.png"));
          this.guardAsleep = ImageIO.read(new File("Images/guardAsleep.png"));
       } catch (IOException ex) {
            System.out.println("Inexisting file");
       }
    } 
    /*
    public void loadhero() {
		ImageIcon imageIcon = new ImageIcon("Images/hero.png");
    	    BufferedImage tmpImage = (BufferedImage) imageIcon.getImage();

    	    BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    	    image.getGraphics().drawImage(tmpImage, 0, 0, null);
    	    tmpImage.flush();

    	   this.hero = image;
    	   }
    */
    public  BufferedImage getWall() {
		return wall;
	}
    
    public  BufferedImage getArmedH() {
 		return armedH;
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

	

	public  BufferedImage getHero() {
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
	public BufferedImage getStunnedOgre() {
		
		return stunnedOgre;
	}

	public BufferedImage getGuardAsleep() {
		
		return guardAsleep;
	}

}