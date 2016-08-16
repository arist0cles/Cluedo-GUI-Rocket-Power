package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import colorschemes.Emo;
import colorschemes.Pastel;
import core.Board;
import squares.DoorSquare;
import squares.HallwaySquare;
import squares.RoomSquare;
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
	private JPanel middleTopPanel;
	private JPanel middleBottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private BorderLayout layout = new BorderLayout(10, 10);
	private Board board;
	public final Dimension SIDE_PANEL_SIZE = new Dimension(150, 200);
	public final Dimension MIDDLE_BOTTOM_PANEL_SIZE = new Dimension(500, 200);
	public final Dimension MIDDLE_TOP_PANEL_SIZE = new Dimension(500, 500);
	private Color STARTING_COLOR = new Color(0, 0, 0);
	private List<ImageIcon> icons = new ArrayList<>();

	public View(Board board) {
		this.board = board;
		setupFrame();
		setupMenu();
		setupLayout();
		setupPanels();
		setupStartButton();
		this.pack();
	}

	/**
	 * Sets up the JFrame, the main section of the program
	 * 
	 */
	public void setupFrame() {
		this.setPreferredSize(new Dimension(700, 700));
		this.getContentPane().setLayout(layout);
		this.setTitle("Cluedo");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Sets up the Menu, the top menu of the program
	 * 
	 */
	public void setupMenu() {
		// TODO This is the gross java tutorial demonstration of Menus. Update to reflect our program
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("A text-only menu item",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menu.add(menuItem);

		//a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		//Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

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
		setupSidePanels(STARTING_COLOR, leftPanel, BorderLayout.EAST);
		setupSidePanels(STARTING_COLOR, rightPanel, BorderLayout.WEST);
		setupIcons();
		setupRightSidePanelImages();
		setupLeftSidePanelImages();
	}

	private void setupIcons() {
		icons.add(new ImageIcon(new ImageIcon("Chars\\card_colonel_mustard.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon("C:\\Users\\Patrick\\Documents\\GitHub\\Cluedo-GUI-Rocket-Power\\Cluedo GUI\\assets\\Chars\\card_miss_scarlett.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon("C:\\Users\\Patrick\\Documents\\GitHub\\Cluedo-GUI-Rocket-Power\\Cluedo GUI\\assets\\Chars\\card_mrs_peacock.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon("C:\\Users\\Patrick\\Documents\\GitHub\\Cluedo-GUI-Rocket-Power\\Cluedo GUI\\assets\\Chars\\card_mrs_white.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon("C:\\Users\\Patrick\\Documents\\GitHub\\Cluedo-GUI-Rocket-Power\\Cluedo GUI\\assets\\Chars\\card_professor_plum.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		icons.add(new ImageIcon(new ImageIcon("C:\\Users\\Patrick\\Documents\\GitHub\\Cluedo-GUI-Rocket-Power\\Cluedo GUI\\assets\\Chars\\card_rev_green.png").getImage().getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
		
	}

	private void setupLeftSidePanelImages() {
		JPanel image1 = new JPanel();
		JLabel thumb = new JLabel();
		thumb.setIcon(icons.get(0));
		image1.add(thumb);
		JPanel image2 = new JPanel();
		JLabel thumb2 = new JLabel();
		thumb2.setIcon(icons.get(1));
		image2.add(thumb2);
		JPanel image3 = new JPanel();
		JLabel thumb3 = new JLabel();
		thumb3.setIcon(icons.get(2));
		image3.add(thumb3);
		image1.setPreferredSize(new Dimension(50, 50));
		image2.setPreferredSize(new Dimension(50, 50));
		image3.setPreferredSize(new Dimension(50, 50));
		image1.setBackground(new Color(0, 0, 0));
		image2.setBackground(new Color(0, 0, 0));
		image3.setBackground(new Color(0, 0, 0));
		leftPanel.setLayout(new GridLayout(3, 1));
		leftPanel.add(image1);
		leftPanel.add(image2);
		leftPanel.add(image3);
	}

	/**
	 * Sets up all the middle panels
	 */
	public void setupMiddlePanels() {
		middleTopPanel = new GridPanel(board);
		middleTopPanel.setPreferredSize(MIDDLE_TOP_PANEL_SIZE);
		middleTopPanel.setBackground(STARTING_COLOR);

		middleBottomPanel.setPreferredSize(MIDDLE_BOTTOM_PANEL_SIZE);
		middleBottomPanel.setBackground(new Color(50, 0, 100));

		this.getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setBackground(STARTING_COLOR);
		//middlePanel.setLayout(new GridLayout(2, 1));
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
	public void setupSidePanels(Color startingColor, JPanel panel, String layoutPosition) {
		panel.setPreferredSize(SIDE_PANEL_SIZE);
		panel.setVisible(true);
		this.getContentPane().add(panel, layoutPosition);
		panel.setBackground(startingColor);
	}
	
	public void setupRightSidePanelImages(){
		JPanel image1 = new JPanel();
		JLabel thumb = new JLabel();
		thumb.setIcon(icons.get(3));
		image1.add(thumb);
		JPanel image2 = new JPanel();
		JLabel thumb2 = new JLabel();
		thumb2.setIcon(icons.get(4));
		image2.add(thumb2);
		JPanel image3 = new JPanel();
		JLabel thumb3 = new JLabel();
		thumb3.setIcon(icons.get(5));
		image3.add(thumb3);
		image1.setPreferredSize(new Dimension(50, 50));
		image2.setPreferredSize(new Dimension(50, 50));
		image3.setPreferredSize(new Dimension(50, 50));
		image1.setBackground(new Color(0, 0, 0));
		image2.setBackground(new Color(0, 0, 0));
		image3.setBackground(new Color(0, 0, 0));
		rightPanel.setLayout(new GridLayout(3, 1));
		rightPanel.add(image1);
		rightPanel.add(image2);
		rightPanel.add(image3);
	}

	public void setupTextFields() {
		JTextField numberOfPlayers = new JTextField("hello");
		middleBottomPanel.add(numberOfPlayers, BorderLayout.CENTER);
		String players = numberOfPlayers.getText();
	}

	public void setupStartButton() {
		JButton start = new JButton("START");
		JSpinner color = setupColorSpinner();
		addStartButtonListener(start, color);
		
		middleBottomPanel.add(start, BorderLayout.SOUTH);
		middleBottomPanel.add(color, BorderLayout.NORTH);
	}
	
	public JSpinner setupColorSpinner(){
		List<String> colorScheme = new ArrayList<>();
		colorScheme.add("Pastel");
		colorScheme.add("Emo");
		colorScheme.add("BW");
		SpinnerListModel l = new SpinnerListModel(colorScheme);
		return new JSpinner(l);
	}

	private void addStartButtonListener(JButton start, JSpinner color) {
		start.addActionListener(e -> {
			String name = JOptionPane.showInputDialog(this,
                    "How many players?", null);
			System.out.println(name);
			String scheme = (String) color.getValue();
			switch (scheme) {
			case "Emo":
				board.setScheme(new Emo());
				this.redraw(new Emo().BACKGROUND);
				break;
			case "Pastel":
				board.setScheme(new Pastel());
				this.redraw(new Pastel().BACKGROUND);
				break;
			case "BW":
				board.setScheme(new BW());
				this.redraw(new BW().BACKGROUND);
				break;
			}
			board.updateScheme();
			this.start(start, color);
		});
		
	}

	private void start(JButton start, JSpinner color) {
		((GridPanel) middleTopPanel).setStarted(true);
		middleBottomPanel.remove(start);
		middleBottomPanel.remove(color);
		middleTopPanel.repaint();
		middleBottomPanel.repaint();
		setupTextFields();
	}
	
	public void redraw(Color c){
		middleBottomPanel.setBackground(c);
		middleTopPanel.setBackground(c);
		leftPanel.setBackground(c);
		rightPanel.setBackground(c);
	}

}
