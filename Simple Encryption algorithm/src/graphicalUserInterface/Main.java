package graphicalUserInterface;

import java.awt.Color;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static int Wwidth = 600, Wheight = 400 ;
	private JFrame frame;
	private Content content;
	
	public Main() {
		frame = new JFrame();
		content = new Content();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.Gui();
	}
	
	public void Gui() {
		
		frame.setTitle("Criptografia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Wwidth, Wheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
                
        System.out.println(content.container);
        frame.add(content.container);
        
        frame.setVisible(true);
        
        
	}
	
}
