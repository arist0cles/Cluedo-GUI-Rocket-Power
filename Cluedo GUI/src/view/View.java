package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

	public final Dimension SIDE_PANEL_SIZE = new Dimension(150, 200);
	public final Dimension FRAME_SIZE = new Dimension(800, 800);
	public final Dimension MIDDLE_BOTTOM_PANEL_SIZE = new Dimension(500, 200);
	public final Dimension MIDDLE_TOP_PANEL_SIZE = new Dimension(500, 500);
	
	private GridPanel middleTopPanel;
	private JPanel middleBottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	
	private BorderLayout layout = new BorderLayout(10, 10);
	private List<ImageIcon> icons = new ArrayList<>();
	private Model model;
	private JButton start;
	private JButton endTurnButton;
	private JSpinner color;
	private List<Component> components = new ArrayList<>();
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem showHand;

	public View(Model m) {
		this.model = m;
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
	
	public int moveDiagonal(){
		return JOptionPane.showConfirmDialog(null, "You are in a corner room would you\n"
		    + "like to move to opposite room?", null, 0);
		
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
		menuBar.add(menu);

		// a group of JMenuItems
		quit = new JMenuItem("Quit");
		menu.add(quit);

		menu.addSeparator();
		
		// a group of JMenuItems
		showHand = new JMenuItem("Show Hand");
		menu.add(showHand);
		
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
	 * Loads the image files as ImageIcons. These may eventually end up in the character objects
	 */
	private void setupIcons() {
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_colonel_mustard.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/White/card_miss_scarlett.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_mrs_peacock.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/White/card_mrs_white.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_professor_plum.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(
				new ImageIcon(System.getProperty("user.dir")+"/Chars/White/card_rev_green.jpg").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));

	}

	/**
	 * Sets up all the middle panels
	 */
	public void setupMiddlePanels() {
		middleTopPanel = new GridPanel(model);
		middleTopPanel.setPreferredSize(MIDDLE_TOP_PANEL_SIZE);
		middleTopPanel.setBackground(model.getColorScheme().BACKGROUND);

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
			thumb.setIcon(icons.get(i));
			image.add(thumb);
			image.setPreferredSize(new Dimension(50, 50));
			if (i < 3) {
				// leftside
				image.setBackground(model.getColorScheme().BACKGROUND);
				components.add(image);
				leftPanel.add(image);
			} else {
				// rightside
				image.setBackground(model.getColorScheme().BACKGROUND);
				components.add(image);
				rightPanel.add(image);
			}
		}
	}

	/**
	 * Sets up the start button and color spinner for picking different color schemes
	 */
	public void setupStartButton() {
		start = new JButton("START");
		color = setupColorSpinner();

		middleBottomPanel.add(start, BorderLayout.SOUTH);
		middleBottomPanel.add(color, BorderLayout.NORTH);
	}
	
	public void addEndTurnButton() {
		endTurnButton = new JButton("End Turn");
		middleBottomPanel.add(endTurnButton, BorderLayout.SOUTH);
	}
	
	public void addEndTurnButtonListener(ActionListener l) {
		middleBottomPanel.remove(start);
		endTurnButton.addActionListener(l);
	}

	/**
	 * Sets up the colorspinner with an arraylist of different colorschemes
	 * 
	 * @return the initialised colorspinner
	 */
	public JSpinner setupColorSpinner() {
		List<String> colorScheme = new ArrayList<>();
		colorScheme.add("Kirita");
		colorScheme.add("Pastel");
		colorScheme.add("Emo");
		colorScheme.add("BW");
		SpinnerListModel l = new SpinnerListModel(colorScheme);
		return new JSpinner(l);
	}

	public void addStartButtonListener(ActionListener l) {
		start.addActionListener(l);
	}
	
	public void showHand() {
		//TODO fill this in once hands are drawing
		System.out.println("Write this method!!!");
	}
	
	public void addQuitMenuListener(ActionListener l) {
		quit.addActionListener(l);
	}
	
	public void addGridMouseListener(MouseListener l) {
		middleTopPanel.addMouseListener(l);
	}
	
	public void addShowHandMenuListener(ActionListener l) {
		showHand.addActionListener(l);
	}

	/**
	 * Redraws the view with an updated background once the user has selected their colorscheme
	 */
	public void redraw() {
		for (Component comp : components) {
			//get component corresponding to player's character 
			//set background to that character's color
			//view doesn't know controller, pass in player as a parameter? 
			//black out character components that are not the current player??
			comp.setBackground(model.getColorScheme().BACKGROUND);
		}
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}

	public String getScheme() {
		return (String) color.getValue();
	}

	public void setGridPaneStarted() {
		middleTopPanel.setStarted(true);
	}

	/**
	 * Helper method to help batch process actions to all components
	 */
	public void addComponents() {
		components.add(leftPanel);
		components.add(rightPanel);
		components.add(middleBottomPanel);
		components.add(middleTopPanel);
	}

	public void quit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
