package dkeep.gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dkeep.logic.Clear;
import dkeep.logic.Cmd;
import dkeep.logic.Coord;
import dkeep.logic.Entity;
import dkeep.logic.Game;
import dkeep.logic.Map;
import dkeep.logic.GuardType;
import dkeep.logic.Symbol;
//import dkeep.cli.Start;

public class GUI implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	protected Game game;
	protected int numberOfMoves=0;

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
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{73, 115, 0, 0, 0, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{26, 27, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 9;
		gbc_textArea.gridwidth = 12;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 5;
		frame.getContentPane().add(textArea, gbc_textArea);


		JLabel lblYouCanStart = new JLabel("You can start a new game");
		GridBagConstraints gbc_lblYouCanStart = new GridBagConstraints();
		gbc_lblYouCanStart.gridwidth = 4;
		gbc_lblYouCanStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblYouCanStart.gridx = 1;
		gbc_lblYouCanStart.gridy = 14;
		frame.getContentPane().add(lblYouCanStart, gbc_lblYouCanStart);


		JButton btnNewButton = new JButton("New game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Game(GuardType.ROOKIE,2,2);
				//Start.printMap(game.getLevel().getMap());
				textArea.append(game.mapString(game.getLevel().getMap()));
				lblYouCanStart.setText("Keep going! You are playing level " + game.getCurrentLevel());
			}
		});

		JComboBox comboBox = new JComboBox();
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


		JButton btnUp = new JButton("Up");
		btnUp.setName("up");
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 14;
		gbc_btnUp.gridy = 8;
		frame.getContentPane().add(btnUp, gbc_btnUp);


		JButton btnLeft = new JButton("Left");
		btnLeft.setName("left");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 13;
		gbc_btnLeft.gridy = 9;
		frame.getContentPane().add(btnLeft, gbc_btnLeft);


		JButton btnRight = new JButton("Right");
		btnRight.setName("right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.gridx = 15;
		gbc_btnRight.gridy = 9;
		frame.getContentPane().add(btnRight, gbc_btnRight);


		JButton btnDown = new JButton("Down");
		btnDown.setName("down");
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 14;
		gbc_btnDown.gridy = 10;
		frame.getContentPane().add(btnDown, gbc_btnDown);

		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 14;
		gbc_btnExit.gridy = 14;
		frame.getContentPane().add(btnExit, gbc_btnExit);





		//Action Listener Generalizado para todos os botões
		ActionListener actionListener =( new ActionListener() {
			@Override  
			public void actionPerformed(ActionEvent e) {
				char var=0;

				if(e.getSource() == btnUp)
					var = 'w';
				if(e.getSource() == btnDown)
					var = 's';
				if(e.getSource() == btnLeft)
					var = 'a';
				if(e.getSource() == btnRight)
					var = 'd';
				if(e.getSource() == btnExit) {
					System.exit(0);
				}
				int status = game.getLevel().update(Start.parseCharToCmd(var));
				game.updateGameVariables(status,Start.parseCharToCmd(var));
				textArea.setText(game.mapString(game.getLevel().getMap()));
				lblYouCanStart.setText("Keep going! You are playing level " + game.getCurrentLevel());
				if(game.isGameOver() ==  true) {
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



	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton action = (JButton) ae.getSource();
		int status = 0;
		switch(action.getName()) {
		case "up":
			status = game.getLevel().update(Start.parseCharToCmd('w'));
			break;
		case "down":
			status = game.getLevel().update(Start.parseCharToCmd('s'));
			break;
		case "right":
			status = game.getLevel().update(Start.parseCharToCmd('d'));
			break;
		case "left":
			status = game.getLevel().update(Start.parseCharToCmd('a'));
			break;
		}
		game.updateGameVariables(status,Start.parseCharToCmd('p'));
	}


}

