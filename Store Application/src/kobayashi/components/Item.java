package kobayashi.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import kobayashi.main.DatabaseQueries;
import kobayashi.main.Global;
import kobayashi.main.LandingPage;

public class Item {

	public int quantity = 1;
		
	public float price;
	
	public String barcode, categoryName;
	
    private JTextField quantityField = new JTextField("1");

	public Item(String barcode) {
		this.barcode = barcode;
		setItem();
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setItem() {
		String itensInfo[] = DatabaseQueries.getItemInfo(barcode);
		this.categoryName = itensInfo[3];
		setPrice(Float.parseFloat(itensInfo[1]));

		JPanel item = new JPanel(new GridBagLayout());
		item.setBackground(Global.secondaryBg);
		item.setPreferredSize(new Dimension(280,50));
		
		 GridBagConstraints gbc = new GridBagConstraints();
	     gbc.insets = new Insets(1, 5,1, 10);
	     gbc.anchor = GridBagConstraints.WEST;
	     
	     gbc.gridwidth = 2;
	     gbc.gridheight = 2;
	     
	     item.add(new JLabel(new LogoIcon(40,40,"/icecream.png").setLogo()), gbc);
	     
	     gbc.gridx = 2;
	     gbc.gridheight =1;
	     item.add(setNewItemLabel(itensInfo[0], 11, Color.black, false), gbc);
	     
	     gbc.gridx = 4;

	     item.add(setNewItemLabel("R$ "+ itensInfo[1], 11, Color.black, true), gbc);
	     
	     gbc.gridy = 1;
	     gbc.gridx = 2;

	     item.add(setNewItemLabel(itensInfo[3], 11, Color.black, false), gbc);
	     
	     gbc.gridwidth =1;
	     gbc.gridx = 4;

	     item.add(setNewItemLabel("Qntd:", 11, Color.black, false), gbc);
	     
	     gbc.gridx = 5;
	     
	     quantityField.setBorder(BorderFactory.createEmptyBorder());
	     quantityField.setPreferredSize(new Dimension(40,12));
	     quantityField.setBackground(Global.secondaryBg);
	     quantityField.setFont(new Font("Arial", Font.PLAIN,11));
	     quantityField.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
			public void keyTyped(KeyEvent e) {
				char c  = e.getKeyChar();
				if( !Character.isDigit(c)) {
					e.consume();

				}
			}
	    	 
	     });
	     quantityField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {}
			
			public void insertUpdate(DocumentEvent e) {			
				int qtd = Integer.parseInt(quantityField.getText()),
						maxQtd = Integer.parseInt(itensInfo[2]);
				if(qtd > maxQtd) {
					setNewValue(maxQtd);
					setQuantity(maxQtd);
				}else {
					if(qtd == 0) {
						LandingPage.scrollPanelItemList.remove(quantityField.getParent());
						setQuantity(0);
					}else {
						setQuantity(Integer.parseInt(quantityField.getText()));
					}
				}
				LandingPage.updateFinalValue();
			}

			public void removeUpdate(DocumentEvent e) {
				if(!quantityField.getText().equals("")) {
					setQuantity(Integer.parseInt(quantityField.getText()));
				}
				LandingPage.updateFinalValue();
			}    	 
	     });
	     
	     quantityField.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {}

			public void focusLost(FocusEvent e) {
				if(quantityField.getText().equals("")){
					LandingPage.scrollPanelItemList.remove(quantityField.getParent());
					setQuantity(0);
				}
				LandingPage.updateFinalValue();
			}
	    	 
	     });
	     item.add(quantityField, gbc);
	     
	     
	     LandingPage.scrollPanelItemList.add(item) ;

	     LandingPage.orderSection.revalidate();
	     LandingPage.orderSection.repaint();
	}
	
	public void setNewValue( int value) {
		Runnable reset = new Runnable() {
			public void run() {
				quantityField.setText(Integer.toString(value));
			}
		};
		SwingUtilities.invokeLater(reset);
	}
	
	private JLabel setNewItemLabel(String name, int fontSize,  Color c, Boolean isBold) {
		JLabel label = new JLabel();
		label.setToolTipText(name);
		if(name.length() > 20) {
			name = name.substring(0, 18) + "...";
		}
		label.setText(name);
		if(isBold) {
			label.setFont(new Font("Arial", Font.BOLD, fontSize));
		}else {
			label.setFont(new Font("Arial", Font.PLAIN, fontSize));
		}
		label.setForeground(c);
		return label;
	}
	
}
