package kobayashi.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kobayashi.main.Global;
import kobayashi.main.LandingPage;

public class Product {

	public String barcode, name, category, imagePath;
	
	public float price;
	
	public int stockQuantity;
		
	public JPanel productPanel = new JPanel(new GridBagLayout());
	
	public JPanel setNewProduct(String barcode, String name, String category, String imagePath, float price, int stockQuantity) {
		
		this.barcode = barcode;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.imagePath = imagePath;
		
		
		productPanel.setPreferredSize(new Dimension(LandingPage.contentWidth/4,300));
		productPanel.setBackground(Global.secondaryBg);
		productPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Global.generalBg));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight =2;
		
		productPanel.add(new JLabel(new LogoIcon(100,100,this.imagePath).setLogo()), gbc);
		gbc.gridheight =1;
		gbc.gridy = 2;
		
		productPanel.add(setItemLabel(this.name, 15, true), gbc);
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridy = 3;
		
		productPanel.add(setItemLabel(this.category, 12, false), gbc);

		gbc.gridy = 4;
		
		productPanel.add(setItemLabel("Quantidade: " + this.stockQuantity, 12, false), gbc);
		
		gbc.gridy = 5;
		
		productPanel.add(setItemLabel("R$ " + this.price, 12, false), gbc);

		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.CENTER;
		
		JButton addToCart = new JButton("Adicionar na lista");
		addToCart.setForeground(Color.black);
		addToCart.setFont(new Font("Arial", Font.BOLD, 12) );
		addToCart.setBackground(Global.generalBg);
		addToCart.setBorder(BorderFactory.createEmptyBorder());
		addToCart.setPreferredSize(new Dimension(150, 30));
		addToCart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addToCart.setFocusPainted(false);
		addToCart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Boolean alreadyExist = false;
				for(int i = 0; i < LandingPage.itemArrayList.size(); i++) {
					if(LandingPage.itemArrayList.get(i).barcode.equals(barcode)) {
						LandingPage.itemArrayList.get(i).setQuantity(LandingPage.itemArrayList.get(i).quantity+1);
						LandingPage.itemArrayList.get(i).setNewValue(LandingPage.itemArrayList.get(i).quantity);
						alreadyExist = true;
					}
				}
				if(!alreadyExist) {
					Item item = new Item(barcode);
					LandingPage.itemArrayList.add(item);
				}
				LandingPage.updateFinalValue();
			}
			
		});
		
		productPanel.add(addToCart, gbc);
		return productPanel;
	}
	
	private JLabel setItemLabel (String name, int fontSize, Boolean isBold) {
		JLabel label = new JLabel();
		label.setToolTipText(name);
		
		if(isBold) {
			if(name.length() > 20) {
				name = name.substring(0, 18) + "...";
			}
			label.setFont(new Font("Arial", Font.BOLD, fontSize));
		}else {
			if(name.length() > 25) {
				name = name.substring(0, 23) + "...";
			}
			label.setFont(new Font("Arial", Font.PLAIN, fontSize));
		}
		
		label.setText(name);
		label.setForeground(Color.black);
		return label;
	}
	
	
}
