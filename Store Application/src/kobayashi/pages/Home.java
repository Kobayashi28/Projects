package kobayashi.pages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.util.StringUtils;

import kobayashi.components.Category;
import kobayashi.components.ErrorMsg;
import kobayashi.components.Item;
import kobayashi.components.ItemInfo;
import kobayashi.components.LogoIcon;
import kobayashi.components.Product;
import kobayashi.main.DatabaseQueries;
import kobayashi.main.Global;
import kobayashi.main.LandingPage;
import kobayashi.main.Main;

public class Home{
	
	public static JPanel container;
	public boolean visibility = true;
	private JTextField searchBox;
	
	public static ArrayList<ItemInfo> itens =  DatabaseQueries.getAllItemInfo();
	public static ArrayList<Product> products = new ArrayList<Product>();
	public ArrayList<Category> categoriesList = new ArrayList<Category>();
	
	public static JPanel productList;

	public JPanel productListPanel;
	
	public JPanel setHome() {
		
		container = new JPanel(null);
		
		container.setBackground(Global.generalBg);
		container.setBounds(LandingPage.menuWidth, 0, LandingPage.contentWidth, Main.height);
		
		container.add(setHeader());
		setContent();
				
		return container;
	}
	
	private JPanel setHeader() {
		
		JPanel header = new JPanel(null);
		header.setBounds(0, 0, LandingPage.contentWidth, 220);
		
		JLabel title = new JLabel("Selecione uma categoria");
		title.setBounds(20, 30, 300, 40);
		title.setForeground(Color.black);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPanel categories = new JPanel(null);
		categories.setBounds(60, 100, LandingPage.contentWidth-120, 120);
		
		JButton backward = new JButton(new LogoIcon(30,50,"/left-arrow.png").setLogo());
		backward.setBounds(0, 100, 60, 120);
		backward.setBackground(Global.generalBg);
		backward.setBorder(BorderFactory.createEmptyBorder());
		backward.setFocusPainted(false);
		backward.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backward.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(categoriesList.get(0).x < 15) {
					for(int i = 0; i < categoriesList.size(); i++) {
						categoriesList.get(i).x+=50;
						categories.getComponent(i).setBounds(categoriesList.get(i).x, 10, categoriesList.get(i).width, categoriesList.get(i).height);
					}
				}
				
				updateContent();
			}
			
		});
		
		
		
		JButton forward = new JButton(new LogoIcon(30,50,"/right-arrow.png").setLogo());
		forward.setBounds(LandingPage.contentWidth-60, 100, 60, 120);
		forward.setBackground(Global.generalBg);
		forward.setBorder(BorderFactory.createEmptyBorder());
		forward.setFocusPainted(false);
		forward.setCursor(new Cursor(Cursor.HAND_CURSOR));
		forward.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				if(categoriesList.get(categoriesList.size()-1).x >365) {
					for(int i = 0; i < categoriesList.size(); i++) {
						categoriesList.get(i).x-=50;
						categories.getComponent(i).setBounds(categoriesList.get(i).x, 10, categoriesList.get(i).width, categoriesList.get(i).height);
					}
				}
				
				updateContent();
			}
			
		});
		
		ArrayList<String> dbCategories = DatabaseQueries.getCategories();
		for (int i = 0; i <dbCategories.size(); i++) {
			Category category = new Category(15+(100*i), 10, "/icecream.png", dbCategories.get(i));
			categories.add(category.setCategory());
			categoriesList.add(category);
		}
		
		JLabel placeholder = new JLabel("Insira o código ou nome do produto: ");
		placeholder.setBounds(LandingPage.contentWidth-280, 14, 250, 16);
		placeholder.setForeground(new Color(0,0,0,255));
		placeholder.setFont(new Font("Arial", Font.ITALIC, 12));
		
		JButton sendSearch = new JButton(new LogoIcon(40,40,"/lupa.png").setLogo());
		sendSearch.setBounds(LandingPage.contentWidth-50, 30, 42, 42);
		sendSearch.setBackground(Global.generalBg);
		sendSearch.setBorder(BorderFactory.createEmptyBorder());
		sendSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendSearch.setFocusPainted(false);
		sendSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String txt = searchBox.getText();
				ArrayList<String> dbResult = DatabaseQueries.getItensByName(txt);
				if(!dbResult.isEmpty()) {
					resetProductList();
					for(int i = 0; i < dbResult.size(); i++) {
						for(int ii = 0; ii < products.size(); ii++) {
							if(products.get(ii).name.equals(dbResult.get(i))) {
								productList.add(products.get(ii).productPanel);						
							}
						}
					}
				}else {
					ErrorMsg.setErrorDialog("O produto não foi encontrado!");
				}
				updateContent();	
			}
		});
		
		
		searchBox = new JTextField();
		searchBox.setBounds(LandingPage.contentWidth-280, 30, 225, 40);
		searchBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black), BorderFactory.createEmptyBorder(0,10,0,0)));
		searchBox.setFont( new Font("Arial", Font.PLAIN, 20));
		searchBox.setBackground(Global.generalBg);
		searchBox.setForeground(Global.fourth);
		searchBox.getDocument().addDocumentListener(new DocumentListener() {
			
			public void insertUpdate(DocumentEvent e) {
							
				boolean alreadyExist = false;		
				
				if(LandingPage.activeFastSell.isSelected()) {
					if(searchBox.getText().length() == Main.barcodeLength && StringUtils.isStrictlyNumeric(searchBox.getText())) {
						if(DatabaseQueries.verifyProductExistance(searchBox.getText())) {
							for(int i = 0; i < LandingPage.itemArrayList.size(); i++) {
								if(LandingPage.itemArrayList.get(i).barcode.equals(searchBox.getText())) {
									LandingPage.itemArrayList.get(i).setQuantity(LandingPage.itemArrayList.get(i).quantity+1);
									LandingPage.itemArrayList.get(i).setNewValue(LandingPage.itemArrayList.get(i).quantity);
									alreadyExist = true;
								}
							}
							if(!alreadyExist) {
								Item item = new Item(searchBox.getText());
								LandingPage.itemArrayList.add(item);
							}
							
							LandingPage.updateFinalValue();
						}else {
							ErrorMsg.setErrorDialog("O produto não foi encontrado!");
						}
						resetInput();
					}
				}
				
				
			}
			
			public void changedUpdate(DocumentEvent e) {}
			public void removeUpdate(DocumentEvent e) {}
		});;

		header.add(backward);
		header.add(forward);
		header.add(categories);
		header.add(title);
		header.add(searchBox);
		header.add(placeholder);
		header.add(sendSearch);
		return header;
	}
	
	public static void updateContent() {
		container.revalidate();
		container.repaint();		
	}
	
	public static void resetProductList() {
		for(int i = 0; i < products.size(); i++) {
			productList.remove(products.get(i).productPanel);
		}
	}
	public static void refreshContent() {
		itens = DatabaseQueries.getAllItemInfo();
		int productsize = products.size();
	
		for(int i = 0; i < productsize; i++) {
			products.remove(0);
			productList.remove(productList.getComponent(0));
		}
		
		generateNewProductList();
		updateContent();
		System.out.println(itens.size());
	}
	
	private static void generateNewProductList() {
		
		int productSize = products.size();
		for(int i = 0; i < productSize; i++) {
			products.remove(0);
		}
		for(int i = 0; i < DatabaseQueries.countRegister("barcode", "product"); i++) {
			Product product  = new Product();
			products.add(product);
			productList.add(product.setNewProduct(itens.get(i).barcode, itens.get(i).productName,  itens.get(i).categoryName, "/teste.png",  itens.get(i).productPrice ,  itens.get(i).quantityStock));
		}
	}
	
	private void setContent() {
		productListPanel = new JPanel();
		productListPanel.setBounds(0, 220, LandingPage.contentWidth, Main.height-220);
		
		productList =  new JPanel(new GridLayout(0,3,0,0));
		
		JScrollPane scrollPane = new JScrollPane(productList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setPreferredSize(new Dimension(LandingPage.contentWidth, Main.height-255));
		generateNewProductList();
		
		productListPanel.add(scrollPane);
		
		container.add(productListPanel);
	}
		
	private void resetInput() {
		Runnable reset = new Runnable() {
			public void run() {
				searchBox.setText("");
			}
		};
		SwingUtilities.invokeLater(reset);
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		container.setVisible(visibility);
	}
}
