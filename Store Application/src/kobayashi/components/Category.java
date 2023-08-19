package kobayashi.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kobayashi.main.Global;
import kobayashi.pages.Home;

public class Category {

	public int x,y, width = 80, height = 100 ;
	public String imagePath, name;
	
	public Category(int x, int y, String imagePath, String name) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		this.name = name;
	}
	
	public JPanel setCategory() {
		
		JPanel cat = new JPanel(null);
		
		cat.setBounds(x,y, width, height);
		cat.setBackground(Global.secondaryBg);
		
		JLabel image = new JLabel(new LogoIcon (40,40, imagePath).setLogo());
		image.setBounds(20, 10, 40, 40);
		
		JButton btn = new JButton("<html><p style='text-align:center'>" + name);
		btn.setBounds(5, 55, 70, 40);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setBackground(Global.secondaryBg);
		btn.setFont(new Font("Arial", Font.BOLD, 10 ));
		btn.setToolTipText(name);
		btn.setForeground(Color.black);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setFocusPainted(false);
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Home.resetProductList();
				for(int i = 0; i < Home.products.size(); i++) {
					if(name.equals("Todos")) {
						Home.productList.add(Home.products.get(i).productPanel);
					}else if(Home.products.get(i).category.equals(name)) {
						Home.productList.add(Home.products.get(i).productPanel);
					}
				}
				Home.updateContent();
			}

		});
		
		cat.add(image);
		cat.add(btn);
		
		return cat;
	}
	
}
