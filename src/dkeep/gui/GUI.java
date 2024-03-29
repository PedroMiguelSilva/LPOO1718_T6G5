package dkeep.gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import dkeep.logic.Cmd;
import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.GuardType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI implements ActionListener, KeyListener {

	private JFrame frame;
	private JTextField textField;
	protected Game game;
	protected int numberOfMoves=0;
	protected MyFrame customFrame;
	protected MyPanel panel;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void getkey(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case java.awt.event.KeyEvent.VK_LEFT: game.moveHero(Cmd.LEFT); panel.repaint(); break;
		
		case java.awt.event.KeyEvent.VK_RIGHT: game.moveHero(Cmd.RIGHT); panel.repaint(); break;
		
		case java.awt.event.KeyEvent.VK_UP: game.moveHero(Cmd.UP);       panel.repaint(); break;
		
		case java.awt.event.KeyEvent.VK_DOWN: game.moveHero(Cmd.DOWN);   panel.repaint(); break;
	}
		}
	
	private void getGuard(JComboBox comboBox, GuardType guard) {
		int chosenIndex = comboBox.getSelectedIndex();
		switch(chosenIndex) {
		case 0: guard = GuardType.ROOKIE; break;
		case 1: guard = GuardType.DRUNKEN; break;
		case 2: guard = GuardType.SUSPICIOUS; break;
	}
		}
		
	
	private void initialize()  {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{73, 115, 0, 0, 0, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{26, 27, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabenterNumber = new JLabel("Number of Ogres");
		GridBagConstraints gbc_lblNewLabenterNumber = new GridBagConstraints();
		gbc_lblNewLabenterNumber.gridwidth = 3;
		gbc_lblNewLabenterNumber.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabenterNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabenterNumber.gridx = 0;
		gbc_lblNewLabenterNumber.gridy = 2;
		frame.getContentPane().add(lblNewLabenterNumber, gbc_lblNewLabenterNumber);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblGuard = new JLabel("Guard personality");
		GridBagConstraints gbc_lblGuard = new GridBagConstraints();
		gbc_lblGuard.gridwidth = 3;
		gbc_lblGuard.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuard.anchor = GridBagConstraints.EAST;
		gbc_lblGuard.gridx = 0;
		gbc_lblGuard.gridy = 3;
		frame.getContentPane().add(lblGuard, gbc_lblGuard);

		JButton btnCustomGame = new JButton("Custom Game");
		btnCustomGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 customFrame = new MyFrame();
				 customFrame.sendGame(game);
				 panel.setGame(game);
				 
			}
		});
		GridBagConstraints gbc_btnCustomGame = new GridBagConstraints();
		gbc_btnCustomGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnCustomGame.gridx = 14;
		gbc_btnCustomGame.gridy = 5;
		frame.getContentPane().add(btnCustomGame, gbc_btnCustomGame);
		
	

	
		//panel.setOpaque(false);
		


		JLabel lblYouCanStart = new JLabel("You can start a new game");
		GridBagConstraints gbc_lblYouCanStart = new GridBagConstraints();
		gbc_lblYouCanStart.gridwidth = 4;
		gbc_lblYouCanStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblYouCanStart.gridx = 1;
		gbc_lblYouCanStart.gridy = 15;
		frame.getContentPane().add(lblYouCanStart, gbc_lblYouCanStart);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(159, 61, 86, 20);
		JButton btnUp = new JButton("Up");
		btnUp.setName("up");
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 14;
		gbc_btnUp.gridy = 9;
		frame.getContentPane().add(btnUp, gbc_btnUp);


		JButton btnLeft = new JButton("Left");
		btnLeft.setName("left");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 13;
		gbc_btnLeft.gridy = 10;
		frame.getContentPane().add(btnLeft, gbc_btnLeft);


		JButton btnRight = new JButton("Right");
		btnRight.setName("right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.gridx = 15;
		gbc_btnRight.gridy = 10;
		frame.getContentPane().add(btnRight, gbc_btnRight);


		JButton btnDown = new JButton("Down");
		btnDown.setName("down");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 14;
		gbc_btnDown.gridy = 11;
		frame.getContentPane().add(btnDown, gbc_btnDown);

		panel = new MyPanel();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(game.gameEnded())
					return;
				getkey(e);
			
				panel.requestFocusInWindow();
				
				lblYouCanStart.setText("Keep going! You are playing level " + game.getCurrentLevel());
				if(game.isGameOver()) {
					
					lblYouCanStart.setText("Too bad, you lost! The Hero has died");
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);

				}
				if(game.getWonGame() == true) {
					lblYouCanStart.setText("You Won!! The Hero has escaped");
					btnDown.setEnabled(false); 
					btnUp.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}	
			}
		});
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 11;
		gbc_panel.gridheight = 9;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		frame.getContentPane().add(panel, gbc_panel);

		

		JButton btnNewButton = new JButton("New game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardType guard = GuardType.ROOKIE;
				Integer numOgres;
				if(textField.getText().isEmpty() || !textField.getText().matches("[0-9]+")){
					lblYouCanStart.setText("Number of Ogres: 1 to 5...");
					return;
				}

				numOgres= Integer.parseInt(textField.getText());

				if(numOgres < 1 || numOgres > 5 )
				{
					lblYouCanStart.setText("Number of Ogres: 1 to 5...");
					return;
				}  

				//Preparing first Level
				

				getGuard(comboBox,guard);


				game = new Game(guard,numOgres,2);
				panel.setGame(game);
				lblYouCanStart.setText("Keep going! You are playing level " + game.getCurrentLevel());
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				panel.requestFocusInWindow();
			}
		});
		JButton btnPlayCustomGame = new JButton("Play Custom Game");
		btnPlayCustomGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = MyFrame.h;
				panel.setGame(game);
				lblYouCanStart.setText("You are playing your custom level");
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				panel.requestFocusInWindow();
				
			}
		});
		GridBagConstraints gbc_btnPlayCustomGame = new GridBagConstraints();
		gbc_btnPlayCustomGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlayCustomGame.gridx = 14;
		gbc_btnPlayCustomGame.gridy = 6;
		frame.getContentPane().add(btnPlayCustomGame, gbc_btnPlayCustomGame);



		frame.getContentPane().add(comboBox);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 14;
		gbc_btnNewButton.gridy = 4;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);


		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 14;
		gbc_btnExit.gridy = 15;
		frame.getContentPane().add(btnExit, gbc_btnExit);
		





		//Action Listener Generalizado para todos os botões
		ActionListener actionListener =( new ActionListener() {
			@Override  
			public void actionPerformed(ActionEvent e) {
				Cmd cmd= null;


				if(e.getSource() == btnUp) 
					cmd = Cmd.UP;

				if(e.getSource() == btnDown) 
					cmd = Cmd.DOWN;

				if(e.getSource() == btnLeft) 
					cmd = Cmd.LEFT;

				if(e.getSource() == btnRight) 	
					cmd = Cmd.RIGHT;

				if(e.getSource() == btnExit) 
					System.exit(0);


				game.moveHero(cmd);
				panel.repaint();
				panel.requestFocusInWindow();
				


				lblYouCanStart.setText("Keep going! You are playing level " + game.getCurrentLevel());
				if(game.isGameOver()) {
					lblYouCanStart.setText("Too bad, you lost! The Hero has died");
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);

				}
				if(game.getWonGame() == true) {
					lblYouCanStart.setText("You Won!! The Hero has escaped");
					btnDown.setEnabled(false);
					btnUp.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}
			}

		});
		
	



		//chamada dos Action Listeners pelos botões
		btnUp.addActionListener(actionListener);

		btnLeft.addActionListener(actionListener);

		btnRight.addActionListener(actionListener);

		btnDown.addActionListener(actionListener);

		btnExit.addActionListener(actionListener);
		
	
	}
	public void getkey() {
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton action = (JButton) ae.getSource();
		switch(action.getName()) {
		case "up":
			game.moveHero(Cmd.UP);
			break;
		case "down":
			game.moveHero(Cmd.DOWN);
			break;
		case "right":
			game.moveHero(Cmd.RIGHT);
			break;
		case "left":
			game.moveHero(Cmd.LEFT);
			break;
		}
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent arg0) {
				
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}



