package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * The view the user see. Part of the Model View Controller pattern, displays the state dynamically
 * 
 * The view renders the contents of a model. It specifies exactly how the model data should be presented. 
 * If the model data changes, the view must update its presentation as needed. This can be achieved by 
 * using a push model, in which the view registers itself with the model for change notifications, 
 * or a pull model, in which the view is responsible for calling the model when it needs 
 * to retrieve the most current data.
 * 
 * @author Patrick and Kirita
 *
 */
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;

import colorschemes.BW;
import colorschemes.ColorScheme;
import colorschemes.Emo;
import colorschemes.Pastel;
import controller.Controller;
import core.Board;
import core.Die;
import model.Model;
import squares.DoorSquare;
import squares.HallwaySquare;
import squares.RoomSquare;
import squares.Square;
import squares.WallSquare;

/**
 * View class from MVC architecture. Displays state dynamically. Views are
 * responsible for UI elements and displaying things. Controllers are
 * responsible for connecting Views with Models and providing data for the View
 * to display.
 * 
 * @author Patrick and Kirita
 *
 */
public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	//dimensions
	public final Dimension SIDE_PANEL_SIZE = new Dimension(150, 200);
	public final Dimension FRAME_SIZE = new Dimension(800, 800);
	public final Dimension MIDDLE_BOTTOM_PANEL_SIZE = new Dimension(500, 200);
	public final Dimension MIDDLE_TOP_PANEL_SIZE = new Dimension(500, 500);
	
	//panels
	private JPanel middleTopPanel;
	private JPanel middleBottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	
	//for the dice
	JLabel thmbd1;
	JLabel thmbd2;
	JPanel imgd1;	
	JPanel imgd2;
	//layouts, icons, buttons and menu
	private BorderLayout layout = new BorderLayout(10, 10);
	private List<ImageIcon> icons = new ArrayList<>();
	private Model model;
	private JButton start;
	private JButton endTurnButton;
	private JButton suggestButton;
	private JButton accuseButton;
	private JSpinner color;
	private List<Component> components = new ArrayList<>();
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem showHand;
	private JMenuItem showDiscard;

	public View(Model m) {
		this.model = m;
		middleTopPanel = new GridPanel(m);
		setupFrame();
		setupMenu();
		setupLayout();
		setupPanels();
		setupStartButton();
		this.pack();
		addComponents();
	}

	/**
	 * Sets up the JFrame, the main section of the program
	 * 
	 */
	public void setupFrame() {
		this.setPreferredSize(FRAME_SIZE);
		this.getContentPane().setLayout(layout);
		this.setTitle("Cluedo");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public int moveDiagonal() {
		return JOptionPane.showConfirmDialog(null,
				"You are in a corner room would you\n" + "like to move to opposite room?", null, 0);
	}
	public void wonGame(){
		JOptionPane.showMessageDialog(null, model.getCurrentPlayer().getName()+" has won!");
	}
	public void lostGame() {
		JOptionPane.showMessageDialog(null,model.getCurrentPlayer().getName()+" has lost and will be eliminated!");
	}
	public void invalidMove() {
		JOptionPane.showMessageDialog(null,"That destination is too far away! Try again!");
	}
	public void discardMessage() {
		JOptionPane.showMessageDialog(null,"Check the discarded cards in the menu then end turn!");
	}
	public void endGameMessage() {
		JOptionPane.showMessageDialog(null,"Only one player left! Game is over!");
	}
	public void incorrectPlayersMessage() {
		JOptionPane.showMessageDialog(null,"Must have between 3 and 6 players to start the game!");
	}
	public void mustEnterNumberMessage() {
		JOptionPane.showMessageDialog(null,"Must enter a number value!");
	}
	public void invalidPlayerMessage() {
		JOptionPane.showMessageDialog(null,"Must select a valid selection!");
	}


	/**
	 * Sets up the Menu, the top menu of the program
	 * 
	 */
	public void setupMenu() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Cluedo Menu");
		menu.setToolTipText("Can be used to show the players hand or quit the game");
		menuBar.add(menu);

		// a group of JMenuItems
		quit = new JMenuItem("Quit");
		quit.setToolTipText("If you leave me now, you take away the biggest part of me");
		menu.add(quit);

		menu.addSeparator();

		// a group of JMenuItems
		showHand = new JMenuItem("Show Hand");
		showHand.setToolTipText("Players hand");
		menu.add(showHand);
		
		showDiscard = new JMenuItem("Show Discarded");
		showDiscard.setToolTipText("Cards that have been ruled out");
		menu.add(showDiscard);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Gets rid of any gaps between panels
	 */
	public void setupLayout() {
		layout.setHgap(0);
		layout.setVgap(0);
		this.setLayout(layout);
	}

	/**
	 * Calls methods to setup the panels
	 */
	public void setupPanels() {
		setupMiddlePanels();
		setupSidePanels(leftPanel, BorderLayout.EAST);
		setupSidePanels(rightPanel, BorderLayout.WEST);
		setupIcons();
		setupImagePanels();
	}

	/**
	 * Loads the image files as ImageIcons. These may eventually end up in the
	 * character objects
	 */
	private void setupIcons() {
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/White/card_colonel_mustard.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/Black/card_miss_scarlett.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/White/card_mrs_peacock.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/Black/card_mrs_white.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/White/card_professor_plum.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Chars/Black/card_rev_green.jpg")
				.getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));

	}

	/**
	 * Sets up all the middle panels
	 */
	public void setupMiddlePanels() {
		middleTopPanel = new GridPanel(model);
		middleTopPanel.setPreferredSize(MIDDLE_TOP_PANEL_SIZE);
		//middleTopPanel.setBackground(model.getColorScheme().BACKGROUND);

		middleBottomPanel.setPreferredSize(MIDDLE_BOTTOM_PANEL_SIZE);
		middleBottomPanel.setBackground(model.getColorScheme().BACKGROUND);

		this.add(middlePanel, BorderLayout.CENTER);
		middlePanel.setBackground(model.getColorScheme().BACKGROUND);

		middlePanel.add(middleTopPanel, BorderLayout.NORTH);
		middlePanel.add(middleBottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * Sets up the side panels.
	 * 
	 * @param startingColor
	 *            Basic Bitch Black
	 * @param panel
	 *            The panel to be setup.
	 * 
	 *            * @param layoutPosition Either the left or the right, which
	 *            are called east or west
	 */
	public void setupSidePanels(JPanel panel, String layoutPosition) {
		panel.setPreferredSize(SIDE_PANEL_SIZE);
		panel.setVisible(true);
		this.add(panel, layoutPosition);
		panel.setBackground(model.getColorScheme().BACKGROUND);
	}
	
	

	/**
	 * Populates the side panels with images
	 */
	public void setupImagePanels() {
		JLabel thumb;
		JPanel image;
		leftPanel.setLayout(new GridLayout(3, 1));
		rightPanel.setLayout(new GridLayout(3, 1));
		for (int i = 0; i < icons.size(); i++) {
			thumb = new JLabel();
			image = new JPanel();
			image.setToolTipText("Did I do it?");
			thumb.setIcon(icons.get(i));
			image.add(thumb);
			image.setPreferredSize(new Dimension(50, 50));
			if (i < 3) {
				// leftside
				image.setBackground(model.getColorScheme().BACKGROUND);
				image.setName("" + i);
				components.add(image);
				leftPanel.add(image);
			} else {
				// rightside
				image.setBackground(model.getColorScheme().BACKGROUND);
				image.setName("" + i);
				components.add(image);
				rightPanel.add(image);
			}
		}
	}
	/**
	 * adds the die images to the middle bottom panel 
	 * @param d1, d2
	 * */
	public void addDiceToPane(Die d1, Die d2){
		thmbd1 = new JLabel();
		thmbd2 = new JLabel();
		imgd1 = new JPanel();	
		imgd2 = new JPanel();
		
		thmbd1.setIcon(new ImageIcon(new ImageIcon(d1.getDieFile())
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		thmbd2.setIcon(new ImageIcon(new ImageIcon(d2.getDieFile())
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		
		imgd1.add(thmbd1);
		imgd2.add(thmbd2);
		
		components.add(imgd1);
		components.add(imgd2);
		middleBottomPanel.add(imgd1);
		middleBottomPanel.add(imgd2);
	}
	/**
	 * removes the dice from the panel
	 * */
	public void removeDice(){
		middleBottomPanel.remove(imgd1);
		middleBottomPanel.remove(imgd2);
	}
	/**
	 * returns the jpanel suggest button
	 * @return suggestButton
	 * */
	public JButton getSuggest(){
		return this.suggestButton;
	}
	/**
	 * removes the suggest button
	 * */
	public void removeSuggest(){
		if (suggestButton != null) 
		middleBottomPanel.remove(suggestButton);
	}

	/**
	 * Sets up the start button and color spinner for picking different color
	 * schemes
	 */
	public void setupStartButton() {
		start = new JButton("START");
		start.setToolTipText("A+ please?");
		color = setupColorSpinner();
		color.setToolTipText("Choose your color scheme before starting the game");

		middleBottomPanel.add(start, BorderLayout.SOUTH);
		middleBottomPanel.add(color, BorderLayout.NORTH);
	}
	/**
	 * adds the buttons to the middle bottom panel
	 * that are always present
	 * eg end turn button and accuse button
	 * */
	public void addPlayButtons() {
		endTurnButton = new JButton("End Turn");
		endTurnButton.setToolTipText("Ends this players turn");
		accuseButton = new JButton("Accuse");
		accuseButton.setToolTipText("Accuse IF YOU DARE!");
		middleBottomPanel.add(endTurnButton, BorderLayout.SOUTH);
		middleBottomPanel.add(accuseButton, BorderLayout.WEST);
	}
	/**
	 * add suggest to the middle bottom panel
	 * */
	public void addSuggestButton(){
		suggestButton = new JButton("Suggest");
		suggestButton.setToolTipText("Make a suggestion");
		middleBottomPanel.add(suggestButton, BorderLayout.EAST);
	}
	/**
	 * adds the end button listener 
	 * */
	public void addEndTurnButtonListener(ActionListener l) {
		middleBottomPanel.remove(start);
		middleBottomPanel.remove(color);
		endTurnButton.addActionListener(l);
	}
	/**
	 * adds the suggest button listener 
	 * */
	public void addSuggestButtonListener(ActionListener l) {
		suggestButton.addActionListener(l);
	}
	/**
	 * adds the accuse button listener 
	 * */
	public void addAccuseButtonListener(ActionListener l) {
		accuseButton.addActionListener(l);
	}

	/**
	 * Sets up the colorspinner with an arraylist of different colorschemes
	 * 
	 * @return the initialised colorspinner
	 */
	public JSpinner setupColorSpinner() {
		List<String> colorScheme = new ArrayList<>();
		colorScheme.add("Pastel");
		colorScheme.add("Kirita");
		colorScheme.add("Emo");
		colorScheme.add("BW");
		SpinnerListModel l = new SpinnerListModel(colorScheme);
		return new JSpinner(l);
	}
	/**
	 * adds the start button listener 
	 * */
	public void addStartButtonListener(ActionListener l) {
		start.addActionListener(l);
	}
	/**
	 * adds the quit menu listener 
	 * */
	public void addQuitMenuListener(ActionListener l) {
		quit.addActionListener(l);
	}
	/**
	 * adds the grid mouse listener 
	 * */
	public void addGridMouseListener(MouseListener l) {
		middleTopPanel.addMouseListener(l);
	}
	
	/**
	 * adds the hand menu listener 
	 * */
	public void addShowHandMenuListener(ActionListener l) {
		showHand.addActionListener(l);
	}
	/**
	 * adds the discard menu listener 
	 * */
	public void addShowDiscardMenuListener(ActionListener l) {
		showDiscard.addActionListener(l);
	}

	/**
	 * Redraws the view with an updated background once the user has selected
	 * their colorscheme
	 */
	public void redraw() {
		for (Component comp : components) {
			// get component corresponding to player's character
			// set background to that character's color
			// view doesn't know controller, pass in player as a parameter?
			// black out character components that are not the current player??
			comp.setBackground(model.getColorScheme().BACKGROUND);
		}
		highlight();
		this.getContentPane().validate();
		this.getContentPane().repaint();
		
	}
	/**
	 * returns the color scheme as a string
	 * */
	public String getScheme() {
		return (String)color.getValue();
	}
	/**
	 * set the started boolean in the gridpanel to true
	 * */
	public void setGridPaneStarted() {
		((GridPanel)middleTopPanel).setStarted(true);
	}

	/**
	 * Helper method to help batch process actions to all components
	 */
	public void addComponents() {
		components.add(leftPanel);
		components.add(rightPanel);
		components.add(middleBottomPanel);
		components.add(middleTopPanel);
		components.add(middlePanel);
	}
	/**
	 * quit the game and close all panels
	 * */
	public void quit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	/**
	 * highlights the current players back ground on the screen
	 * if player one chooses mrs peacock then when the game starts
	 * mrs peacocks image background is highlighted her color (blue)
	 * */
	public void highlight() {
		for (Component c : components) {
			if (c.getName() != null && model.getCurrentPlayer().getCharacter() != null) {
				if (c.getName().equals(model.getCurrentPlayer().getCharacter().getID())) {
					c.setBackground(model.getCurrentPlayer().getCharacter().getColor());
				}
			}
		}
	}
	
}
