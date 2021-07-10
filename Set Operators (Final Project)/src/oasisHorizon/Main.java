package oasisHorizon;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//https://www.youtube.com/watch?v=rJAcMxkjCTs
public class Main {
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				GUI gui = new GUI();
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
