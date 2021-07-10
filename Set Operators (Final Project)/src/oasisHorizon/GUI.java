package oasisHorizon;

import oasisHorizon.Logic;
import oasisHorizon.GameLogic.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;


public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Action action;
	private Boolean alreadySubmitted;
	private Container cp;
	private Color colorCorrect;
	private GameLogic gameLogic;
	private HelpGUI helpGui;
	private JButton jbUnion, jbIntersection, jbDifference, jbCartesian;
	private JButton jbUnion2, jbIntersection2, jbDifference2, jbCartesian2;
	private JButton jbSubmit, jbNextQuestion, jbMainMenu, jbStartGame, jbOptions, jbQuitGame, jbSaveOptions, jbNewGame;
	private JComboBox<Object> jcUnion, jcIntersection, jcDifference, jcCartesian;
	private JComboBox<Object> jcUnion2, jcIntersection2, jcDifference2, jcCartesian2;
	private JLabel lbSetA, lbSetB, lbSetC, lbSetUniversal, lbSetFinal;
	private JLabel lbGameQuestion, lbGamePoints, lbGameTime, lbGameDifficulty, lbGameCheck, lbGameCheckImage, lbGameCheckCorrectAnswer;
	private JLabel lbGameTrophy, lbGameTrophyName, lbGameTrophyComment;
	private JLabel lbGameResultsPoints, lbGameResultsTime, lbGameResultsSkip, lbGameResultsCorrect, lbGameResultsWrong, lbGameResultsStatLabel;
	private JMenuBar menuBar;
	private JMenu menu, helpMenu;
	private JMenuItem menuItemReset, menuItemOptions, menuItemQuit, menuItemHelp, menuItemGame;
	private JPanel panelMain, panelMainAdvanced, panelCard, panelGameMenu, panelGameMain, panelGameOptions, panelGameResult;
	private JRadioButton jrEasy, jrNormal, jrHard, jr5, jr10, jr20;
	private JTextField tfSetA, tfSetB, tfSetFinal;
	private JTextField tfSetA2, tfSetB2, tfSetC2, tfSetUniversal2, tfSetFinal2;
	private JTextField tfGameSetA, tfGameSetB, tfGameAnswer, tfGameQuestion;
	private Logic logic;
	private Time time;
	private Timer timer;
	private String[] optionDifference = {"A-B", "B-A"};
	private String[] optionUnion = {"AuB"};
	private String[] optionIntersection = {"AnB"};
	private String[] optionCartesian = {"AxB", "BxA"};
	private String[] optionUnion2 = {"AuB", "AuC", "BuC", "AuBuC"};
	private String[] optionIntersection2 = {"AnB", "AnC", "BnC", "AnBnC"};
	private String[] optionDifference2 = {"A-B", "A-C", "B-A", "B-C", "C-A", "C-B", "U-A", "U-B", "U-C"};
	private String[] optionCartesian2 = {"AxB", "AxC", "BxA","BxC", "CxA", "CxB"};
	
	public GUI() {
		logic = new Logic();
		action = new Action();
		gameLogic = new GameLogic();
		timer = new Timer();
		time = new Time();
		
		panelMain = setInput();
		panelMainAdvanced = setInputAdvanced();
		panelGameMenu = setGameMenu();
		panelGameMain = setGameMain();
		panelGameOptions = setGameOptions();
		panelGameResult = setGameResult();
		
		// Sets up cards for easy switching of panels
		panelCard = new JPanel(new CardLayout());
		panelCard.add(panelMain, "Main");
		panelCard.add(panelMainAdvanced, "Advanced");
		panelCard.add(panelGameMenu, "GameMenu");
		panelCard.add(panelGameMain, "GameMain");
		panelCard.add(panelGameOptions, "GameOptions");
		panelCard.add(panelGameResult, "GameResult");
		
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.setPreferredSize(new Dimension(525,350));
		setMinimumSize(new Dimension(500, 340));
		
		// Menu Bars
		setJMenuBar(menuBarSetUp());
		
		// Centers all the panels
		cp.add(panelCard, BorderLayout.CENTER);
		
		// Frame configurations
		pack();
		ImageIcon imageIcon = new ImageIcon("src/assets/Icon.png");
		Image image = imageIcon.getImage();
		setIconImage(image);
		setTitle("Set Operators (Final Project)");
		setLocation(setWindowCenter());
		setSize(getPreferredSize());
		setVisible(true);
		
	}
	
	//Sets up Normal Mode
	public JPanel setInput() {
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(7, 7, 7 ,7);
		
		// JLABELS
		inputGrid.ipady = 5;
		lbSetA = new JLabel("Set A"); 
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		panelMain.add(lbSetA, inputGrid);
		
		lbSetB = new JLabel("Set B");
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		panelMain.add(lbSetB, inputGrid);
		
		lbSetFinal = new JLabel("Answer");
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelMain.add(lbSetFinal, inputGrid);
		
		// JTEXTFIELDS
		tfSetA = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMain.add(tfSetA, inputGrid);	
		
		tfSetB = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 1;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMain.add(tfSetB, inputGrid);
		
		tfSetFinal = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 2;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMain.add(tfSetFinal, inputGrid);
		
		// JBUTTONS
		inputGrid.ipady = 0;
		inputGrid.gridwidth = 1;
		jbUnion = new JButton("Union");
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelMain.add(jbUnion, inputGrid);
		jbUnion.addActionListener(action);
		
		jbIntersection = new JButton("Intersection");
		inputGrid.gridx = 1;
		inputGrid.gridy = 3;
		panelMain.add(jbIntersection, inputGrid);
		jbIntersection.addActionListener(action);
		
		jbDifference = new JButton("Difference");
		inputGrid.gridx = 2;
		inputGrid.gridy = 3;
		panelMain.add(jbDifference, inputGrid);
		jbDifference.addActionListener(action);
		
		jbCartesian = new JButton("Cartesian Product");
		inputGrid.gridx = 3;
		inputGrid.gridy = 3;
		panelMain.add(jbCartesian, inputGrid);
		jbCartesian.addActionListener(action);
		
		// JCOMBOBOXES
		jcUnion = new JComboBox<Object>(optionUnion);
		jcUnion.setSelectedIndex(0);
		inputGrid.gridx = 0;
		inputGrid.gridy = 4;
		panelMain.add(jcUnion, inputGrid);
		
		jcIntersection = new JComboBox<Object>(optionIntersection);
		jcIntersection.setSelectedIndex(0);
		inputGrid.gridx = 1;
		inputGrid.gridy = 4;
		panelMain.add(jcIntersection, inputGrid);
		
		jcDifference = new JComboBox<Object>(optionDifference);
		jcDifference.setSelectedIndex(0);
		inputGrid.gridx = 2;
		inputGrid.gridy = 4;
		panelMain.add(jcDifference, inputGrid);
		
		jcCartesian = new JComboBox<Object>(optionCartesian);
		jcCartesian.setSelectedIndex(0);
		inputGrid.gridx = 3;
		inputGrid.gridy = 4;
		panelMain.add(jcCartesian, inputGrid);
		return panelMain;
	}
	
	
	
	// Sets up Advanced mode
	public JPanel setInputAdvanced() {
		panelMainAdvanced = new JPanel();
		panelMainAdvanced.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(7, 7, 7 ,7);
		
		// JLABELS
		inputGrid.ipady = 5;
		lbSetA = new JLabel("Set A"); 
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		panelMainAdvanced.add(lbSetA, inputGrid);
		
		lbSetB = new JLabel("Set B");
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		panelMainAdvanced.add(lbSetB, inputGrid);
		
		lbSetC = new JLabel("Set C");
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelMainAdvanced.add(lbSetC, inputGrid);
		
		lbSetUniversal = new JLabel("Universal Set");
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelMainAdvanced.add(lbSetUniversal, inputGrid);
		
		lbSetFinal = new JLabel("Answer");
		inputGrid.gridx = 0;
		inputGrid.gridy = 4;
		panelMainAdvanced.add(lbSetFinal, inputGrid);
		
		// JTEXTFIELDS
		tfSetA2= new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMainAdvanced.add(tfSetA2, inputGrid);	
		
		tfSetB2 = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 1;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMainAdvanced.add(tfSetB2, inputGrid);
		
		tfSetC2 = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 2;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMainAdvanced.add(tfSetC2, inputGrid);
		
		tfSetUniversal2 = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 3;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMainAdvanced.add(tfSetUniversal2, inputGrid);
		
		tfSetFinal2 = new JTextField(10);
		inputGrid.gridx = 1;
		inputGrid.gridy = 4;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelMainAdvanced.add(tfSetFinal2, inputGrid);
		
		// JBUTTONS
		inputGrid.ipady = 0;
		inputGrid.gridwidth = 1;
		jbUnion2 = new JButton("Union");
		inputGrid.gridx = 0;
		inputGrid.gridy = 5;
		panelMainAdvanced.add(jbUnion2, inputGrid);
		jbUnion2.addActionListener(action);
		
	
		jbIntersection2 = new JButton("Intersection");
		inputGrid.gridx = 1;
		inputGrid.gridy = 5;
		panelMainAdvanced.add(jbIntersection2, inputGrid);
		jbIntersection2.addActionListener(action);
		
		jbDifference2 = new JButton("Difference");
		inputGrid.gridx = 2;
		inputGrid.gridy = 5;
		panelMainAdvanced.add(jbDifference2, inputGrid);
		jbDifference2.addActionListener(action);
		
		jbCartesian2 = new JButton("Cartesian Product");
		inputGrid.gridx = 3;
		inputGrid.gridy = 5;
		panelMainAdvanced.add(jbCartesian2, inputGrid);
		jbCartesian2.addActionListener(action);
		
		// JCOMBOBOXES
		jcUnion2 = new JComboBox<Object>(optionUnion2);
		jcUnion2.setSelectedIndex(0);
		inputGrid.gridx = 0;
		inputGrid.gridy = 6;
		panelMainAdvanced.add(jcUnion2, inputGrid);
		
		jcIntersection2 = new JComboBox<Object>(optionIntersection2);
		jcIntersection2.setSelectedIndex(0);
		inputGrid.gridx = 1;
		inputGrid.gridy = 6;
		panelMainAdvanced.add(jcIntersection2, inputGrid);
		
		jcDifference2 = new JComboBox<Object>(optionDifference2);
		jcDifference2.setSelectedIndex(0);
		inputGrid.gridx = 2;
		inputGrid.gridy = 6;
		panelMainAdvanced.add(jcDifference2, inputGrid);
		
		jcCartesian2 = new JComboBox<Object>(optionCartesian2);
		jcCartesian2.setSelectedIndex(0);
		inputGrid.gridx = 3;
		inputGrid.gridy = 6;
		panelMainAdvanced.add(jcCartesian2, inputGrid);
		return panelMainAdvanced;
	}
	
	// Sets up the Game Main Menu
	public JPanel setGameMenu() {
		
		
		JPanel panelGameMenu = new JPanel();
		panelGameMenu.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(4, 4, 4 ,4);
		
		// JLABEL (TITLE)
		JLabel title = new JLabel(new ImageIcon("src/assets/Title.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		panelGameMenu.add(title, inputGrid);
		
		// JBUTTONS
		jbStartGame = new JButton(new ImageIcon("src/assets/Start.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		jbStartGame.addActionListener(action);
		jbStartGame.setRolloverEnabled(true);
		jbStartGame.setRolloverIcon(new ImageIcon("src/assets/StartHover.png"));
		jbStartGame.setBackground(null);
		jbStartGame.setBorder(null);
		panelGameMenu.add(jbStartGame, inputGrid);
		
		jbOptions = new JButton(new ImageIcon("src/assets/Options.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		jbOptions.addActionListener(action);
		jbOptions.setRolloverEnabled(true);
		jbOptions.setRolloverIcon(new ImageIcon("src/assets/OptionsHover.png"));
		jbOptions.setBackground(null);
		jbOptions.setBorder(null);
		panelGameMenu.add(jbOptions, inputGrid);
		
		jbQuitGame = new JButton(new ImageIcon("src/assets/Quit.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		jbQuitGame.addActionListener(action);
		jbQuitGame.addActionListener(action);
		jbQuitGame.setRolloverEnabled(true);
		jbQuitGame.setRolloverIcon(new ImageIcon("src/assets/QuitHover.png"));
		jbQuitGame.setBackground(null);
		jbQuitGame.setBorder(null);
		panelGameMenu.add(jbQuitGame, inputGrid);
		
		return panelGameMenu;
	}
	
	// Sets up the Game Main screen
	public JPanel setGameMain() {
		
		Font font = new Font("Calibri", Font.BOLD, 18);
		Font fontPlain = new Font("Calibri", Font.PLAIN, 18);
		Font fontSmall = new Font("Calibri", Font.BOLD, 17);
		Font fontSmall2 = new Font("Calibri", Font.BOLD, 16);
		colorCorrect = new Color(0, 100, 0);

		JPanel panelGameMain = new JPanel();
		panelGameMain.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(4, 4, 4 ,4);
		
		// JLABELS
		lbGameQuestion = new JLabel("Question #" + gameLogic.getQuestionNumber(), SwingConstants.CENTER);
		lbGameQuestion.setFont(font);
		inputGrid.gridx = 2;
		inputGrid.gridy = 0;
		panelGameMain.add(lbGameQuestion, inputGrid);
			
		JLabel lbGameSetA = new JLabel("Set A", SwingConstants.CENTER);
		lbGameSetA.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelGameMain.add(lbGameSetA, inputGrid);
		
		JLabel lbGameSetB = new JLabel("Set B", SwingConstants.CENTER);
		lbGameSetB.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelGameMain.add(lbGameSetB, inputGrid);
		
		JLabel lbGameAnswer = new JLabel("Answer", SwingConstants.CENTER);
		lbGameAnswer.setFont(font);
		inputGrid.gridx = 2;
		inputGrid.gridy = 4;
		panelGameMain.add(lbGameAnswer, inputGrid);
		
		lbGameCheck = new JLabel();
		lbGameCheck.setFont(fontSmall);
		inputGrid.gridx = 6;
		inputGrid.gridy = 5;
		panelGameMain.add(lbGameCheck, inputGrid);
		
		lbGameCheckCorrectAnswer = new JLabel();
		lbGameCheckCorrectAnswer.setForeground(Color.red);
		lbGameCheckCorrectAnswer.setFont(fontSmall2);
		inputGrid.gridx = 6;
		inputGrid.gridy = 6;
		panelGameMain.add(lbGameCheckCorrectAnswer, inputGrid);
		
		lbGameCheckImage = new JLabel();
		inputGrid.gridx = 5;
		inputGrid.gridy = 5;
		inputGrid.fill = GridBagConstraints.VERTICAL;
		inputGrid.gridheight = 2;
		panelGameMain.add(lbGameCheckImage, inputGrid);
			
		// JTEXTFIELDS
		inputGrid.gridheight = 1;
		tfGameQuestion = new JTextField(10);
		tfGameQuestion.setFont(font);
		tfGameQuestion.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		tfGameQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		tfGameQuestion.setEditable(false);
		tfGameQuestion.setBackground(Color.white);
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		panelGameMain.add(tfGameQuestion, inputGrid);
		
		tfGameSetA = new JTextField(10);
		tfGameSetA.setFont(fontPlain);
		tfGameSetA.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		tfGameSetA.setEditable(false);
		tfGameSetA.setBackground(Color.white);
		inputGrid.gridx = 1;
		inputGrid.gridy = 2;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 4;
		panelGameMain.add(tfGameSetA, inputGrid);
		
		tfGameSetB = new JTextField(10);
		tfGameSetB.setFont(fontPlain);
		tfGameSetB.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		tfGameSetB.setEditable(false);
		tfGameSetB.setBackground(Color.white);
		inputGrid.gridx = 1;
		inputGrid.gridy = 3;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 4;
		panelGameMain.add(tfGameSetB, inputGrid);
		
		tfGameAnswer = new JTextField(10);
		tfGameAnswer.setFont(fontPlain);
		tfGameAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		tfGameAnswer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		inputGrid.gridx = 0;
		inputGrid.gridy = 5;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		panelGameMain.add(tfGameAnswer, inputGrid);
		
		// JBUTTONS
		Font font2 = new Font("Calibri", Font.BOLD, 16);
		inputGrid.gridwidth = 1;
		jbMainMenu = new JButton("Main Menu");
		jbMainMenu.setBackground(Color.black);
		jbMainMenu.setFont(font2);
		jbMainMenu.setForeground(Color.white);
		jbMainMenu.addActionListener(action);
		inputGrid.gridx = 0;
		inputGrid.gridy = 6;
		panelGameMain.add(jbMainMenu, inputGrid);
		
		jbNextQuestion = new JButton("New Question");
		jbNextQuestion.setBackground(Color.black);
		jbNextQuestion.setFont(font2);
		jbNextQuestion.setForeground(Color.white);
		jbNextQuestion.addActionListener(action);
		inputGrid.gridx = 4;
		inputGrid.gridy = 6;
		panelGameMain.add(jbNextQuestion, inputGrid);
		
		jbSubmit = new JButton("Submit");
		jbSubmit.setBackground(Color.black);
		jbSubmit.setFont(font2);
		jbSubmit.setForeground(Color.white);
		jbSubmit.addActionListener(action);
		inputGrid.gridx = 2;
		inputGrid.gridy = 6;
		panelGameMain.add(jbSubmit, inputGrid);
		
		// INVISIBLE TEMP BUTTONS FOR LAYOUT
	
		inputGrid.gridwidth = 1;
		JButton tempButton1 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButton1.setBackground(null);
		tempButton1.setBorder(null);
		inputGrid.gridx = 0;
		inputGrid.gridy = 7;
		panelGameMain.add(tempButton1, inputGrid);
		
		JButton tempButton2 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButton2.setBackground(null);
		tempButton2.setBorder(null);
		inputGrid.gridx = 2;
		inputGrid.gridy = 7;
		panelGameMain.add(tempButton2, inputGrid);

		JButton tempButton3 = new JButton(new ImageIcon("src/assets/BlankSpace3.jpg"));
		tempButton3.setBackground(null);
		tempButton3.setBorder(null);
		inputGrid.gridx = 4;
		inputGrid.gridy = 7;
		panelGameMain.add(tempButton3, inputGrid);
		
		JButton tempButton6 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButton6.setBackground(null);
		tempButton6.setBorder(null);
		inputGrid.gridx = 5;
		inputGrid.gridy = 7;
		panelGameMain.add(tempButton6, inputGrid);
		
		JButton tempButton7 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButton7.setBackground(null);
		tempButton7.setBorder(null);
		inputGrid.gridx = 6;
		inputGrid.gridy = 7;
		panelGameMain.add(tempButton7, inputGrid);
		
		// JPANEL RIGHT SIDE
		JPanel panelGameMain2 = new JPanel();
		panelGameMain2.setLayout(new GridBagLayout());
		panelGameMain2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		inputGrid.insets = new Insets(4, 4, 4 ,4);
		
		JLabel lbStats = new JLabel("Game Stats");
		lbStats.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		panelGameMain2.add(lbStats, inputGrid);
		
		JButton tempButton4 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButton4.setBackground(null);
		tempButton4.setBorder(null);
		inputGrid.gridx = 0;
		inputGrid.gridy = 4;
		panelGameMain2.add(tempButton4, inputGrid);
		
		JButton tempButton5 = new JButton(new ImageIcon(""));
		tempButton5.setBackground(null);
		tempButton5.setBorder(null);
		inputGrid.gridx = 1;
		inputGrid.gridy = 4;
		panelGameMain2.add(tempButton5, inputGrid);
		
		JLabel lbGamePointsLabel = new JLabel("Points: ");
		lbGamePointsLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		panelGameMain2.add(lbGamePointsLabel, inputGrid);
		
		
		JLabel lbGameTimeLabel = new JLabel("Time: ");
		lbGameTimeLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelGameMain2.add(lbGameTimeLabel, inputGrid);
		
		JLabel lbGameDifficultyLabel = new JLabel("Difficulty: ");
		lbGameDifficultyLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelGameMain2.add(lbGameDifficultyLabel, inputGrid);
		
		lbGamePoints = new JLabel("");
		lbGamePoints.setFont(font2);
		inputGrid.gridx = 1;
		inputGrid.gridy = 1;
		panelGameMain2.add(lbGamePoints, inputGrid);
		
		lbGameTime = new JLabel("");
		lbGameTime.setFont(font2);
		inputGrid.gridx = 1;
		inputGrid.gridy = 2;
		panelGameMain2.add(lbGameTime, inputGrid);
		
		
		lbGameDifficulty = new JLabel("");
		lbGameDifficulty.setFont(font2);
		inputGrid.gridx = 1;
		inputGrid.gridy = 3;
		panelGameMain2.add(lbGameDifficulty, inputGrid);
		

		inputGrid.gridx = 5;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.VERTICAL;
		inputGrid.gridheight = 5;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 2;
		panelGameMain.add(panelGameMain2, inputGrid);
		
		
		return panelGameMain;
	}
	
	// Sets up the Game Options
	public JPanel setGameOptions() {
		
		JPanel panelGameOptions = new JPanel();
		panelGameOptions.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(4, 4, 4 ,4);
		
		JLabel lbOptionsDifficulty = new JLabel(new ImageIcon("src/assets/Difficulty.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		panelGameOptions.add(lbOptionsDifficulty, inputGrid);
		
		
		ButtonGroup groupDifficulty = new ButtonGroup();
		jrEasy = new JRadioButton("Easy");
		jrEasy.setSelected(true);
		jrNormal = new JRadioButton("Normal");
		jrHard = new JRadioButton("Hard");
		groupDifficulty.add(jrEasy);
		groupDifficulty.add(jrNormal);
		groupDifficulty.add(jrHard);
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		panelGameOptions.add(jrEasy, inputGrid);
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelGameOptions.add(jrNormal, inputGrid);
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelGameOptions.add(jrHard, inputGrid);
		
		JLabel lbOptionsQuestionNo = new JLabel(new ImageIcon("src/assets/NumberQuestions.png"));
		inputGrid.gridx = 0;
		inputGrid.gridy = 4;
		panelGameOptions.add(lbOptionsQuestionNo, inputGrid);
		
		ButtonGroup groupQuestionNo = new ButtonGroup();
		jr5 = new JRadioButton("5 Questions");
		jr5.setSelected(true);
		jr10 = new JRadioButton("10 Questions");
		jr20 = new JRadioButton("20 Questions");
		groupQuestionNo.add(jr5);
		groupQuestionNo.add(jr10);
		groupQuestionNo.add(jr20);
		inputGrid.gridx = 0;
		inputGrid.gridy = 5;
		panelGameOptions.add(jr5, inputGrid);
		inputGrid.gridx = 0;
		inputGrid.gridy = 6;
		panelGameOptions.add(jr10, inputGrid);
		inputGrid.gridx = 0;
		inputGrid.gridy = 7;
		panelGameOptions.add(jr20, inputGrid);
		
		jbSaveOptions = new JButton(new ImageIcon("src/assets/Save.png"));
		jbSaveOptions.setRolloverIcon(new ImageIcon("src/assets/SaveHover.png"));
		jbSaveOptions.setBackground(Color.black);
		jbSaveOptions.setForeground(Color.white);
		jbSaveOptions.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		jbSaveOptions.addActionListener(action);
		inputGrid.gridx = 0;
		inputGrid.gridy = 8;
		panelGameOptions.add(jbSaveOptions, inputGrid);
		
		return panelGameOptions;
	}
	
	// Sets up the Game Results
	public JPanel setGameResult() {
		
		Font font = new Font("Calibri", Font.BOLD, 18);
		Font fontLarge = new Font("Calibri", Font.BOLD, 20);
		Font fontPlain = new Font("Calibri", Font.PLAIN, 16);
		
		panelGameResult = new JPanel();
		panelGameResult.setLayout(new GridBagLayout());
		GridBagConstraints inputGrid = new GridBagConstraints();
		inputGrid.insets = new Insets(4, 4, 4 ,4);
		
		JLabel lbResult = new JLabel("Your Trophy is");
		lbResult.setFont(fontLarge);
		lbResult.setHorizontalAlignment(SwingConstants.CENTER);
		inputGrid.gridx = 3;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		panelGameResult.add(lbResult, inputGrid);
		
		lbGameTrophy = new JLabel("");
		lbResult.setHorizontalAlignment(SwingConstants.CENTER);
		inputGrid.gridx = 3;
		inputGrid.gridy = 1;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		inputGrid.fill = GridBagConstraints.VERTICAL;
		inputGrid.gridheight = 4;
		panelGameResult.add(lbGameTrophy, inputGrid);
		
		inputGrid.gridheight = 1;
		lbGameTrophyName = new JLabel("null"); 
		lbGameTrophyName.setFont(font);
		lbGameTrophyName.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		inputGrid.gridx = 3;
		inputGrid.gridy = 5;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		panelGameResult.add(lbGameTrophyName, inputGrid);
		
		lbGameTrophyComment = new JLabel("null");
		lbGameTrophyComment.setFont(fontPlain);
		lbGameTrophyComment.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		inputGrid.gridx = 3;
		inputGrid.gridy = 6;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 5;
		panelGameResult.add(lbGameTrophyComment, inputGrid);
		
		jbNewGame = new JButton("Start New Game");
		jbNewGame.setBackground(Color.black);
		jbNewGame.setForeground(Color.white);
		jbNewGame.setFont(font);
		jbNewGame.addActionListener(action);
		inputGrid.gridx = 0;
		inputGrid.gridy = 6;
		inputGrid.gridwidth = 3;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		panelGameResult.add(jbNewGame, inputGrid);
		
		// JPANEL LEFT SIDE
		inputGrid.gridheight = 1;
		inputGrid.gridwidth = 1;
		JPanel panelGameResult2 = new JPanel();
		panelGameResult2.setLayout(new GridBagLayout());
		panelGameResult2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		lbGameResultsStatLabel = new JLabel("Your Results (Easy)");
		lbGameResultsStatLabel.setFont(fontLarge);
		lbGameResultsStatLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 2;
		panelGameResult2.add(lbGameResultsStatLabel, inputGrid);
		
		inputGrid.gridwidth = 1;
		JLabel lbGameResultsTimeLabel = new JLabel("Time Taken: ");
		lbGameResultsTimeLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 1;
		panelGameResult2.add(lbGameResultsTimeLabel, inputGrid);
		
		JLabel lbGameResultsCorrectLabel = new JLabel("Correct: ");
		lbGameResultsCorrectLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 2;
		panelGameResult2.add(lbGameResultsCorrectLabel, inputGrid);

		JLabel lbGameResultsSkippedLabel = new JLabel("Skipped: ");
		lbGameResultsSkippedLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 3;
		panelGameResult2.add(lbGameResultsSkippedLabel, inputGrid);
		
		JLabel lbGameResultsWrongLabel = new JLabel("Wrong: ");
		lbGameResultsWrongLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 4;
		panelGameResult2.add(lbGameResultsWrongLabel, inputGrid);
		
		JLabel lbGameResultsPointsLabel = new JLabel("Points Earned:        ");
		lbGameResultsPointsLabel.setFont(font);
		inputGrid.gridx = 0;
		inputGrid.gridy = 5;
		panelGameResult2.add(lbGameResultsPointsLabel, inputGrid);
		
		lbGameResultsTime = new JLabel("null");
		lbGameResultsTime.setFont(font);
		inputGrid.gridx = 1;
		inputGrid.gridy = 1;
		panelGameResult2.add(lbGameResultsTime, inputGrid);
		
		lbGameResultsCorrect = new JLabel("null");
		lbGameResultsCorrect.setFont(font);
		inputGrid.gridx = 1;
		inputGrid.gridy = 2;
		panelGameResult2.add(lbGameResultsCorrect, inputGrid);
		
		lbGameResultsSkip = new JLabel("null");
		lbGameResultsSkip.setFont(font);
		inputGrid.gridx = 1;
		inputGrid.gridy = 3;
		panelGameResult2.add(lbGameResultsSkip, inputGrid);
		
		lbGameResultsWrong = new JLabel("null");
		lbGameResultsWrong.setFont(font);
		inputGrid.gridx = 1;
		inputGrid.gridy = 4;
		panelGameResult2.add(lbGameResultsWrong, inputGrid);
		
		lbGameResultsPoints = new JLabel("null");
		lbGameResultsPoints.setFont(font);
		inputGrid.gridx = 1;
		inputGrid.gridy = 5;
		panelGameResult2.add(lbGameResultsPoints, inputGrid);
		
		inputGrid.gridx = 0;
		inputGrid.gridy = 0;
		inputGrid.fill = GridBagConstraints.VERTICAL;
		inputGrid.gridheight = 6;
		inputGrid.fill = GridBagConstraints.HORIZONTAL;
		inputGrid.gridwidth = 3;
		panelGameResult.add(panelGameResult2, inputGrid);
		
		// INVISIBLE TEMP BUTTONS FOR LAYOUT
		inputGrid.gridwidth = 1;
		inputGrid.gridheight = 1;
		inputGrid.gridx = 0;
		inputGrid.gridy = 7;
		JButton tempButtonResult1 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult1.setBackground(null);
		tempButtonResult1.setBorder(null);
		panelGameResult.add(tempButtonResult1, inputGrid);
		
		JButton tempButtonResult2 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult2.setBackground(null);
		tempButtonResult2.setBorder(null);
		inputGrid.gridx = 1;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult2, inputGrid);
		
		JButton tempButtonResult3 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult3.setBackground(null);
		tempButtonResult3.setBorder(null);
		inputGrid.gridx = 2;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult3, inputGrid);
		
		JButton tempButtonResult4 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult4.setBackground(null);
		tempButtonResult4.setBorder(null);
		inputGrid.gridx = 3;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult4, inputGrid);
		
		JButton tempButtonResult5 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult5.setBackground(null);
		tempButtonResult5.setBorder(null);
		inputGrid.gridx = 4;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult5, inputGrid);
		
		JButton tempButtonResult6 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult6.setBackground(null);
		tempButtonResult6.setBorder(null);
		inputGrid.gridx = 5;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult6, inputGrid);
		
		JButton tempButtonResult7 = new JButton(new ImageIcon("src/assets/BlankSpace.jpg"));
		tempButtonResult7.setBackground(null);
		tempButtonResult7.setBorder(null);
		inputGrid.gridx = 6;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult7, inputGrid);
		
		JButton tempButtonResult8 = new JButton(new ImageIcon("src/assets/BlankSpace2.jpg"));
		tempButtonResult8.setBackground(null);
		tempButtonResult8.setBorder(null);
		inputGrid.gridx = 7;
		inputGrid.gridy = 7;
		panelGameResult.add(tempButtonResult8, inputGrid);
			
		return panelGameResult;
	}
	
	
	// Sets up the Menu and MenuItems
	public JMenuBar menuBarSetUp() {
		menuBar = new JMenuBar();
		
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menu.getAccessibleContext().setAccessibleDescription(
				"Main menu for configuration.");
		menuBar.add(menu);
		
		menuItemReset = new JMenuItem("Reset", KeyEvent.VK_R);
		menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menuItemReset.getAccessibleContext().setAccessibleDescription(
				"Resets the Program");
		menuItemReset.addActionListener(action);
		menu.add(menuItemReset);
		
		menuItemOptions = new JCheckBoxMenuItem("Advanced Mode");
		menuItemOptions.addActionListener(action);
		menu.add(menuItemOptions);
		
		menuItemGame = new JCheckBoxMenuItem("Game Mode");
		menuItemGame.addActionListener(action);
		menu.add(menuItemGame);
		
		menu.addSeparator();
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(action);
		menu.add(menuItemQuit);
		
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription(
				"Help and tutorial for program.");
		menuBar.add(helpMenu);
		
		menuItemHelp = new JMenuItem("Help");
		menuItemHelp.addActionListener(action);
		helpMenu.add(menuItemHelp);
	
		return menuBar;
	}
	
	// Sets the window size to be in the center of the screen.
	public Point setWindowCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (getWidth()/2),
									  middle.y - (getWidth()/2));
		return newLocation;
	}

	public void resetPanel() {
		tfSetA.setText("");
		tfSetB.setText("");
		tfSetFinal.setText("");
		jcDifference.setSelectedIndex(0);
		jcCartesian.setSelectedIndex(0);
		tfSetA2.setText("");
		tfSetB2.setText("");
		tfSetC2.setText("");
		tfSetUniversal2.setText("");
		tfSetFinal2.setText("");
		jcUnion2.setSelectedIndex(0);
		jcIntersection2.setSelectedIndex(0);
		jcDifference2.setSelectedIndex(0);
		jcCartesian2.setSelectedIndex(0);
	}

	public String isEmpty(String answer) {
		if(answer.isEmpty()) {
			String msg = "{Set is Empty}";
			return msg;
		}
		else {
			return answer;
		}
	}
	
	public void getNewQuestion() {
		if(!alreadySubmitted && gameLogic.getQuestionNumber() > 0) {
			gameLogic.skipQuestion();
		}
		gameLogic.nextQuestion();
		lbGameQuestion.setText("Question #"+ gameLogic.getQuestionNumber());
		lbGameCheckImage.setIcon(null);
		lbGameCheck.setForeground(Color.RED);
		lbGameCheck.setText("Unanswered");
		lbGameCheckCorrectAnswer.setText("");
		jbNextQuestion.setBackground(Color.RED);
		tfGameQuestion.setText(gameLogic.getQuestion());
		tfGameSetA.setText(gameLogic.getSetA());
		tfGameSetB.setText(gameLogic.getSetB());
		tfGameAnswer.setText("");
		alreadySubmitted = false;
		
	}
	
	public void submitAnswer() {
		if(!alreadySubmitted) {
			jbNextQuestion.setBackground(colorCorrect);
			gameLogic.setAnswer(tfGameAnswer.getText());
			gameLogic.checkAnswer();
			lbGamePoints.setText(gameLogic.getPoints() + " | " + gameLogic.getTotalPoints());
			if(gameLogic.isCorrect()) {
				lbGameCheckImage.setIcon(new ImageIcon("src/assets/Correct.png"));
				lbGameCheck.setForeground(colorCorrect);
				lbGameCheck.setText("Answer is Correct");
			}
			else {
				lbGameCheckImage.setIcon(new ImageIcon("src/assets/Wrong.png"));
				lbGameCheck.setForeground(Color.RED);
				lbGameCheck.setText("Answer is Wrong");
				lbGameCheckCorrectAnswer.setText(gameLogic.getCorrectSet());
			}
			alreadySubmitted = true;
		}
	}
	
	public void finishGame() {
		if(!alreadySubmitted) {
			gameLogic.skipQuestion();
		}
		lbGameResultsTime.setText(time.getTime());
		lbGameResultsCorrect.setText(gameLogic.getPoints() + "");
		lbGameResultsSkip.setText(gameLogic.getSkip() + "");
		lbGameResultsWrong.setText(gameLogic.getWrong() + "");
		lbGameResultsPoints.setText(gameLogic.getPoints() + " | " + gameLogic.getTotalPoints());
		if(gameLogic.getTotalPoints() == 5) {
			if(gameLogic.getPoints() == gameLogic.getTotalPoints()) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Gold.png"));
				lbGameTrophyComment.setText("[Excellent! You'll definitely get a 4.0 in Discrete Math.]");
				lbGameTrophyName.setText("A Gold Trophy!");
			}
			else if(gameLogic.getPoints() == gameLogic.getTotalPoints()-1) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Silver.png"));
				lbGameTrophyComment.setText("[Very Good! Keep practicing and maybe you can get Gold.]");
				lbGameTrophyName.setText("A Silver Trophy!");
			}
			else if(gameLogic.getPoints() == gameLogic.getTotalPoints()-2) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Bronze.png"));
				lbGameTrophyComment.setText("[Not Bad! If you're having trouble practice on the calculator!]");
				lbGameTrophyName.setText("A Bronze Trophy.");
			}
			else {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Medal.png"));
				lbGameTrophyComment.setText("[Even though you failed, don't give up and try again.]");
				lbGameTrophyName.setText("A Sad Medal...");
			}
		}
		else if(gameLogic.getTotalPoints() == 10) {
			if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-1) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Gold.png"));
				lbGameTrophyComment.setText("[Excellent! You'll definitely get a 4.0 in Discrete Math.]");
				lbGameTrophyName.setText("A Gold Trophy!");
			}
			else if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-3) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Silver.png"));
				lbGameTrophyComment.setText("[Very Good! Keep practicing and maybe you can get Gold.]");
				lbGameTrophyName.setText("A Silver Trophy!");
			}
			else if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-5) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Bronze.png"));
				lbGameTrophyComment.setText("[Not Bad! If you're having trouble practice on the calculator!]");
				lbGameTrophyName.setText("A Bronze Trophy.");
			}
			else {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Medal.png"));
				lbGameTrophyComment.setText("[Even though you failed, don't give up and try again.]");
				lbGameTrophyName.setText("A Sad Medal...");
			}
		}
		else if(gameLogic.getTotalPoints() == 20) {
			if(gameLogic.getPoints() == gameLogic.getTotalPoints() && gameLogic.getDifficulty().toString().equals("Hard")) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/EasterEgg2.jpg"));
				lbGameTrophyName.setText("Eh di ikaw na matalino");
				lbGameTrophyComment.setText("[Kahit nga yung mga programmer hindi maka-perfect sa hard.]");
			}
			else if(gameLogic.getPoints() == 0 && gameLogic.getDifficulty().toString().equals("Easy")) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/EasterEgg.jpg"));
				lbGameTrophyName.setText("May suggestion ako sa iyo ha");
				lbGameTrophyComment.setText("[Huwag mong araw-arawin ang katangahan mo. Bumili ka ng utak.]");
			}
			else if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-3) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Gold.png"));
				lbGameTrophyComment.setText("[Excellent! You'll definitely get a 4.0 in Discrete Math.]");
				lbGameTrophyName.setText("A Gold Trophy!");
			}
			else if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-5) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Silver.png"));
				lbGameTrophyComment.setText("[Very Good! Keep practicing and maybe you can get Gold.]");
				lbGameTrophyName.setText("A Silver Trophy!");
			}
			else if(gameLogic.getPoints() >= gameLogic.getTotalPoints()-10) {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Bronze.png"));
				lbGameTrophyComment.setText("[Not Bad! If you're having trouble practice on the calculator!]");
				lbGameTrophyName.setText("A Bronze Trophy.");
			}

			else {
				lbGameTrophy.setIcon(new ImageIcon("src/assets/Medal.png"));
				lbGameTrophyComment.setText("[Even though you failed, don't give up and try again.]");
				lbGameTrophyName.setText("A Sad Medal...");
			}
		}
	}
	
	
	class Action implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			
			// Adds Universal Set
			if(evt.getSource() == jbUnion2 || evt.getSource() == jbIntersection2 || evt.getSource() == jbDifference2 || evt.getSource() == jbCartesian2) {
				if(tfSetUniversal2.getText() == "" || tfSetUniversal2.getText().equals("{Set is Empty}")) {
					tfSetUniversal2.setText(isEmpty(logic.computeUnion(tfSetA2.getText(), tfSetB2.getText(), tfSetC2.getText())));
				}
				else {
					tfSetUniversal2.setText(isEmpty(logic.computeUnion(logic.computeUnion(tfSetA2.getText(), tfSetB2.getText(), tfSetC2.getText()), tfSetUniversal2.getText())));
				}
			}
			
			// Saves Options
			if(evt.getSource() == jbSaveOptions) {
				if(jrEasy.isSelected()) {
					gameLogic.setDifficulty(Difficulty.Easy);
					lbGameResultsStatLabel.setText("Your Result (Easy)");
				}
				else if(jrNormal.isSelected()) {
					gameLogic.setDifficulty(Difficulty.Normal);
					lbGameResultsStatLabel.setText("Your Result (Normal)");
				}
				else if(jrHard.isSelected()) {
					gameLogic.setDifficulty(Difficulty.Hard);
					lbGameResultsStatLabel.setText("Your Result (Hard)");
				}
				if(jr5.isSelected()) {
					gameLogic.setTotalPoints(5);
				}
				else if(jr10.isSelected()) {
					gameLogic.setTotalPoints(10);
				}
				else if(jr20.isSelected()) {
					gameLogic.setTotalPoints(20);
				}
			}
			
			// Close the Program
			if(evt.getSource() == menuItemQuit || evt.getSource() == jbQuitGame) {
				System.exit(0);
			}
			
			// Reset all TextFields and ComboBoxes
			else if(evt.getSource() == menuItemReset) {
				resetPanel();
			}
			
			// Display Help (Dynamic for Game and Normal)
			else if(evt.getSource() == menuItemHelp) {
				if(menuItemGame.isSelected()) {
					helpGui = new HelpGUI();
					helpGui.getGameHelp();
					helpGui.setVisible(true);
					helpGui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				}
				else {
					helpGui = new HelpGUI();
					helpGui.getHelp();
					helpGui.setVisible(true);
					helpGui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				}
			}
			
			// Switch to Advanced Mode
			else if(evt.getSource() == menuItemOptions && menuItemOptions.isSelected()) {
				menuItemGame.setSelected(false);
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "Advanced");
				resetPanel();
				setSize(getPreferredSize());
			}
			
			// Switch to Normal Mode
			else if((evt.getSource() == menuItemOptions && !menuItemOptions.isSelected()) 
					|| (evt.getSource() == menuItemGame && !menuItemGame.isSelected())) {
				menuItemGame.setSelected(false);
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "Main");;
				resetPanel();
				setSize(getPreferredSize());
			}
			
			// Switch to Game Mode
			else if((evt.getSource() == menuItemGame && menuItemGame.isSelected()) 
					|| evt.getSource() == jbMainMenu
					|| evt.getSource() == jbSaveOptions
					|| evt.getSource() == jbNewGame) {
				menuItemOptions.setSelected(false);
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "GameMenu");
				timer.cancel();
				resetPanel();
				setSize(750,400);
			}
			
			// Starts Game
			else if(evt.getSource() == jbStartGame) {
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "GameMain");

				// Resets the values of the Game
				gameLogic.Reset();
				alreadySubmitted = false;
				lbGamePoints.setText(gameLogic.getPoints() + " | " + gameLogic.getTotalPoints());
				lbGameDifficulty.setText(gameLogic.getDifficulty().toString());
				
				getNewQuestion();
				// Starts the Timer
				time.setTime(0, 0, 0);
				timer = new Timer();
				TimerTask task = new TimerTask() {
					    @Override
					    public void run() {
					        lbGameTime.setText(time.nextSecond().toString());
					   }
				};
				timer.schedule(task, 0, 1000);
			}
			
			// Submits answer and checks it
			else if(evt.getSource() == jbSubmit) {
				jbSubmit.setFocusPainted(false);
				submitAnswer();
			}
			
			// Gets Next Question
			else if(evt.getSource() == jbNextQuestion && !(gameLogic.getQuestionNumber() == gameLogic.getTotalPoints())) {
				jbNextQuestion.setFocusPainted(false);
				getNewQuestion();
			}
			
			// Finish Game
			else if(evt.getSource() == jbNextQuestion) {
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "GameResult");
				finishGame();
				
				timer.cancel();
				gameLogic.Reset();
			}
			
			// Switch to Game Options
			else if(evt.getSource() == jbOptions) {
				CardLayout c1 = (CardLayout)(panelCard.getLayout());
				c1.show(panelCard, "GameOptions");
			}

			// Normal Mode Listeners
			// AuB
			else if(evt.getSource() == jbUnion) {
				tfSetFinal.setText(isEmpty(logic.computeUnion(tfSetA.getText(), tfSetB.getText())));
			}
			// AnB
			else if(evt.getSource() == jbIntersection) {
				tfSetFinal.setText(isEmpty(logic.computeIntersection(tfSetA.getText(), tfSetB.getText())));
			}
			// A-B
			else if(evt.getSource() == jbDifference && jcDifference.getSelectedIndex() == 0) {
				tfSetFinal.setText(isEmpty(logic.computeDifference(tfSetA.getText(), tfSetB.getText())));
			}
			// B-A
			else if(evt.getSource() == jbDifference && jcDifference.getSelectedIndex() == 1) {
				tfSetFinal.setText(isEmpty(logic.computeDifference(tfSetB.getText(), tfSetA.getText())));
			}
			// AxB
			else if(evt.getSource() == jbCartesian && jcCartesian.getSelectedIndex() == 0) {
				tfSetFinal.setText(isEmpty(logic.computeCartesian(tfSetA.getText(), tfSetB.getText())));
			}
			// BxA
			else if(evt.getSource() == jbCartesian && jcCartesian.getSelectedIndex() == 1) {
				tfSetFinal.setText(isEmpty(logic.computeCartesian(tfSetB.getText(), tfSetA.getText())));
			}
			
			// Advanced Mode Listeners
			// UNION
			// AuB
			else if(evt.getSource() == jbUnion2 && jcUnion2.getSelectedIndex() == 0) {
				tfSetFinal2.setText(isEmpty(logic.computeUnion(tfSetA2.getText(), tfSetB2.getText())));
			}
			
			// AuC
			else if(evt.getSource() == jbUnion2 && jcUnion2.getSelectedIndex() == 1) {
				tfSetFinal2.setText(isEmpty(logic.computeUnion(tfSetA2.getText(), tfSetC2.getText())));
			}
			
			// BuC
			else if(evt.getSource() == jbUnion2 && jcUnion2.getSelectedIndex() == 2) {
				tfSetFinal2.setText(isEmpty(logic.computeUnion(tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// AuBuC
			else if(evt.getSource() == jbUnion2 && jcUnion2.getSelectedIndex() == 3) {
				tfSetFinal2.setText(isEmpty(logic.computeUnion(tfSetA2.getText(), tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// INTERSECTION
			// AnB
			else if(evt.getSource() == jbIntersection2 && jcIntersection2.getSelectedIndex() == 0) {
				tfSetFinal2.setText(isEmpty(logic.computeIntersection(tfSetA2.getText(), tfSetB2.getText())));
			}
			
			// AnC
			else if(evt.getSource() == jbIntersection2 && jcIntersection2.getSelectedIndex() == 1) {
				tfSetFinal2.setText(isEmpty(logic.computeIntersection(tfSetA2.getText(), tfSetC2.getText())));
			}
			
			// BnC
			else if(evt.getSource() == jbIntersection2 && jcIntersection2.getSelectedIndex() == 2) {
				tfSetFinal2.setText(isEmpty(logic.computeIntersection(tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// AnBnC
			else if(evt.getSource() == jbIntersection2 && jcIntersection2.getSelectedIndex() == 3) {
				tfSetFinal2.setText(isEmpty(logic.computeIntersection(tfSetA2.getText(), tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// DIFFERENCE
			// A-B
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 0) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetA2.getText(), tfSetB2.getText())));
			}
			
			// A-C
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 1) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetA2.getText(), tfSetC2.getText())));
			}
			
			// B-A
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 2) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetB2.getText(), tfSetA2.getText())));
			}
			
			// B-C
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 3) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// C-A
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 4) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetC2.getText(), tfSetA2.getText())));
			}
			
			// C-B
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 5) {
				tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetC2.getText(), tfSetB2.getText())));
			}
			
			// U-A
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 6) {
				if(tfSetUniversal2.getText().equals("{Set is Empty}")){
					tfSetUniversal2.setText("");
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetA2.getText())));
					tfSetUniversal2.setText("{Set is Empty}");
				}
				else {
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetA2.getText())));
				}
			}
			
			// U-B
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 7) {
				if(tfSetUniversal2.getText().equals("{Set is Empty}")){
					tfSetUniversal2.setText("");
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetB2.getText())));
					tfSetUniversal2.setText("{Set is Empty}");
				}
				else {
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetB2.getText())));
				}
			}
			
			// U-C
			else if(evt.getSource() == jbDifference2 && jcDifference2.getSelectedIndex() == 8) {
				if(tfSetUniversal2.getText().equals("{Set is Empty}")){
					tfSetUniversal2.setText("");
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetC2.getText())));
					tfSetUniversal2.setText("{Set is Empty}");
				}
				else {
					tfSetFinal2.setText(isEmpty(logic.computeDifference(tfSetUniversal2.getText(), tfSetC2.getText())));
				}
			}
			
			
			// CARTESIAN
			// AxB
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 0) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetA2.getText(), tfSetB2.getText())));
			}
			
			// AxC
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 1) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetA2.getText(), tfSetC2.getText())));
			}
			
			// BxA
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 2) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetB2.getText(), tfSetA2.getText())));
			}
			
			// BxC
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 3) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetB2.getText(), tfSetC2.getText())));
			}
			
			// CxA
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 4) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetC2.getText(), tfSetA2.getText())));
			}
			
			// CxB
			else if(evt.getSource() == jbCartesian2 && jcCartesian2.getSelectedIndex() == 5) {
				tfSetFinal2.setText(isEmpty(logic.computeCartesian(tfSetC2.getText(), tfSetB2.getText())));
			}
			
		}
	}	
}
