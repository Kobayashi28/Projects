package kobayashi.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kobayashi.main.Main;

public class ErrorMsg {
	
	public static JDialog dialog;
	
	public static void setErrorDialog(String msg) {
		dialog = new JDialog(Main.frame, "Atenção!",  true);
		dialog.setLayout(null);				
		dialog.setSize(new Dimension(300, 120));
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel imgLabel = new JLabel(new LogoIcon(80, 80, "/exclamation.jpg").setLogo());
		imgLabel.setBounds(10, 0, 80, 80);
		
		JLabel label = new JLabel( "<html><p style='text-align:center'> "+ msg, SwingConstants.CENTER);
		label.setBounds(100, 0, 190, 80);
		label.setForeground(Color.black);
		label.setFont(new Font("Arial", Font.BOLD, 15));

		dialog.add(label);
		dialog.add(imgLabel);
		
		dialog.setVisible(true);
		
	}
	

	
	
	
	public static JPanel newErrorMsg(int x, int y, int width, int height, int spacing,  String msg) {
		JPanel container = new JPanel(null);
		
		JLabel msgLabel = new JLabel(msg);
				
		container.setBounds(x, y, width, height);
		container.setBackground(new Color(230,230,230));
		msgLabel.setForeground(Color.red);
		msgLabel.setFont(new Font("Arial", Font.BOLD, 15));
		msgLabel.setBounds(spacing/2, 0, width-spacing, height);
		
		container.add(msgLabel);
		
		return container;
	}
}
