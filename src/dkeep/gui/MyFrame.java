package dkeep.gui;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import dkeep.logic.CustomLevel;
import dkeep.logic.Symbol;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


public class MyFrame extends JFrame { 
	private JTextField textField;
	private JTextField textField_1;
	private ImageIcon HeroIcon;
	private MyCustomPanel customPanel;
	public MyFrame() {
		HeroIcon = new ImageIcon("Images/hero.png");

		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.setBounds(722, 35, 11, 21);
		getContentPane().add(lblX);

		textField = new JTextField();
		textField.setBounds(745, 30, 23, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		String x =textField.getText();

		JLabel lblY = new JLabel("Y");
		lblY.setBounds(722, 64, 33, 21);
		getContentPane().add(lblY);

		textField_1 = new JTextField();
		textField_1.setBounds(745, 61, 23, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setColumns(10);
		String y =textField_1.getText();

		customPanel = new MyCustomPanel();		
		customPanel.setBounds(31, 62, 474, 399);
		getContentPane().add(customPanel);
		setBounds(100, 100, 800, 600);
		setVisible(true); 


		JLabel lblNewLabel = new JLabel("Create you own level!");
		lblNewLabel.setBounds(31, 473, 474, 16);
		getContentPane().add(lblNewLabel);


		JButton btnCreateMap = new JButton("CREATE MAP");
		btnCreateMap.setBounds(679, 97, 102, 21);
		getContentPane().add(btnCreateMap);
		btnCreateMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				customPanel.setx(textField.getText());
				customPanel.setY(textField_1.getText());
				if(!customPanel.setNewGame())
					lblNewLabel.setText("Invalid Map! You can try again");
				else customPanel.setNewGame();
			}
		});

		customPanel.setSymbol(Symbol.WALL);
		JButton btnWall = new JButton("WALL");
		btnWall.setBounds(677, 153, 117, 29);
		getContentPane().add(btnWall);
		btnWall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				customPanel.setSymbol(Symbol.WALL);
			}});


		JButton btnGuard = new JButton("KEY");
		btnGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customPanel.setSymbol(Symbol.KEY);
			}
		});
		btnGuard.setBounds(677, 207, 117, 29);
		getContentPane().add(btnGuard);

		JButton btnHero = new JButton("HERO");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customPanel.setSymbol(Symbol.HERO);
			}
		});
		btnHero.setBounds(677, 266, 117, 29);
		getContentPane().add(btnHero);

		JButton btnDoor = new JButton("DOOR");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customPanel.setSymbol(Symbol.DOOR_CLOSED);
			}
		});
		btnDoor.setBounds(677, 319, 117, 29);
		getContentPane().add(btnDoor);


		JButton btnOgre = new JButton("OGRE");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customPanel.setSymbol(Symbol.OGRE);
			}
		});
		btnOgre.setBounds(677, 380, 117, 29);
		getContentPane().add(btnOgre);

		JButton btnSavelevel = new JButton("SAVE LEVEL");
		btnSavelevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!customPanel.getMap().isValid()) {
					JOptionPane.showMessageDialog(customPanel, "O seu mapa nao e valido");
					return;
					}
				else {
					
					
				}
				
			}
		});
		btnSavelevel.setBounds(568, 468, 117, 29);
		getContentPane().add(btnSavelevel);


	}
}
