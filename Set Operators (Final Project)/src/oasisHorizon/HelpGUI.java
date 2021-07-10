package oasisHorizon;

import javax.swing.*;
import java.awt.*;

public class HelpGUI extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
    private final JScrollPane scrollPane = new JScrollPane();
    JLabel lblContent;
    
    public HelpGUI() { 
        setSize(750,600);
        setLocationRelativeTo(null);
        setTitle("Help");
        lblContent = new JLabel(new ImageIcon("src/assets/Help.png"));
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setViewportView(lblContent);
    }

    public void resetScroll() {
    	scrollPane.getVerticalScrollBar().setValue(0);
    }
	
	public static void displayHelpGame() {

	}
	
	public void getGameHelp() {
		lblContent.setIcon(new ImageIcon("src/assets/GameManual.png"));
	}
	
	public void getHelp() {
		lblContent.setIcon(new ImageIcon("src/assets/Help.png"));
	}
	
}

