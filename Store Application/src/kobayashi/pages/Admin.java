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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import kobayashi.components.ErrorMsg;
import kobayashi.components.ItemInfo;
import kobayashi.components.LogoIcon;
import kobayashi.main.DatabaseQueries;
import kobayashi.main.Global;
import kobayashi.main.LandingPage;
import kobayashi.main.Main;

public class Admin {
	
	public JPanel container, usersPanel, categoryPanel, productPanel;
	public boolean visibility = true, editorsave = true;
	
	public JPanel setAdmin() {
		
		container = new JPanel(null);
		usersPanel = new JPanel(null);
		categoryPanel = new JPanel(null);
		productPanel = new JPanel(null);
		
		usersPanel.setVisible(false);
		categoryPanel.setVisible(false);
		
		container.setBackground(Global.generalBg);
		container.setBounds(LandingPage.menuWidth, 0, LandingPage.contentWidth, Main.height);
		container.setVisible(false);
		
		container.add(setHeader());
		container.add(setContent());
		
		return container;
	}

	
	public JPanel setHeader() {
		
		JPanel header = new JPanel(null);
		header.setBounds(0, 0, LandingPage.contentWidth, 70);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		JLabel title = new JLabel("Painel do administrador");
		title.setBounds(20, 20, 250, 30);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setForeground(Color.black);
		
		header.add(title);
		
		return header; 
		
	}
	
	public JPanel setContent() {
		
		JPanel content = new JPanel(null);
		content.setBounds(0, 70, LandingPage.contentWidth, Main.height - 155);
		content.setBackground(Color.cyan);

		JPanel contentHeader = new JPanel(new GridLayout(1,3,0,0));
		contentHeader.setBounds(0, 0, LandingPage.contentWidth, 40);
		
		String btnTitles[] = {"Produto", "Categoria / Promoções", "Usuários"};
		for(int i = 0; i < btnTitles.length; i++) {
			JButton headerOption = new JButton(btnTitles[i]);
			headerOption.setFocusPainted(false);
			headerOption.setForeground(Color.black);
			headerOption.setBackground(Global.generalBg);
			headerOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
			headerOption.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
			if( i == 0) {
				headerOption.setBackground(Global.tertiaryBg);
				headerOption.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			}
			
			headerOption.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					productPanel.setVisible(false);
					categoryPanel.setVisible(false);
					usersPanel.setVisible(false);
					
					for(int i = 0; i < contentHeader.getComponentCount(); i++) {
						contentHeader.getComponent(i).setBackground(Global.generalBg);
					}
										
					switch(headerOption.getText()) {
					case "Produto":
						productPanel.setVisible(true);
						headerOption.setBackground(Global.tertiaryBg);
						break;
					case "Categoria / Promoções":
						categoryPanel.setVisible(true);
						headerOption.setBackground(Global.tertiaryBg);
						break;
					case "Usuários":
						usersPanel.setVisible(true);
						headerOption.setBackground(Global.tertiaryBg);
						break;
						
					}
				}
				
			});
				
			contentHeader.add(headerOption);

		}
		
		setProductPanel();
		setCategoryPanel();
		setUsersPanel();
		
		content.add(usersPanel);
		content.add(categoryPanel);
		content.add(productPanel);
		
		content.add(contentHeader);
				
		return content;
		
	}
	
	public void setProductPanel() {
		
		productPanel.setBounds(0, 40, LandingPage.contentWidth, Main.height-115);
		productPanel.setBackground(Global.generalBg);
		
		JPanel productForm = new JPanel(null);
		productForm.setBounds(0, 65, LandingPage.contentWidth, Main.height-180);
		productForm.setBackground(Global.generalBg);
		
		JLabel productImg = new JLabel(new LogoIcon(100, 100, "/icecream.png").setLogo());
		productImg.setBounds(20, 20, 100, 100);
		productImg.setName("productimg");
		productImg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

		JButton newProductImage = new JButton("Nova imagem");
		newProductImage.setBounds(20, 120, 100, 20);
		newProductImage.setName("addimage");
		newProductImage.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
		newProductImage.setBackground(Global.tertiaryBg);
		newProductImage.setFocusPainted(false);
		
		String lxy[][][] = { {{"Produto"}, {"130", "20", "130"}}, {{"Código de barras"}, {"290", "20", "130"}},{{"Categoria"}, {"450", "20", "130"}}, 
		{{"Quantidade em estoque:"}, {"130", "100", "160"}} , {{"Preço de venda: "}, {"390", "100", "120"}},
		{{"Tipo de unidade"}, {"20", "150", "120"}}, {{"Unidades por caixa:"}, {"270", "150", "130"}}, 
		{{"Preço unitário:"}, {"460", "150", "100"}}, {{"Imposto simples:"}, {"10", "210", "120"}}, 
		{{"MVA:"}, {"170", "210", "40"}}, {{"ST:"}, {"250", "210", "30"}}, {{"Preço sem ST:"}, {"320", "210", "110"}}, {{"Preço final:"}, {"480", "210", "80"}},};
				
		for(int i = 0; i < lxy.length; i++ ) {
			JLabel lbl = new JLabel(lxy[i][0][0]);
			lbl.setBounds(Integer.parseInt(lxy[i][1][0]), Integer.parseInt(lxy[i][1][1]), Integer.parseInt(lxy[i][1][2]), 20);
			lbl.setForeground(Color.black);
			lbl.setName("label");
			lbl.setFont(new Font("Arial", Font.PLAIN, 15));
			//lbl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

			if(i == 5) {
				
				String types[] = {"CAIXA", "UNIDADE"};
				JComboBox txt = new JComboBox(types);
				txt.setForeground(Color.black);
				txt.setBackground(Global.generalBg);
				txt.setFont(new Font("Arial", Font.BOLD, 12));
				txt.setBounds(Integer.parseInt(lxy[i][1][0]) + Integer.parseInt(lxy[i][1][2]), Integer.parseInt(lxy[i][1][1]), 120, 20);
				txt.setName("combobox");
				productForm.add(txt);

			}else if(i == 2){
				JComboBox txt = new JComboBox();
				txt.setForeground(Color.black);
				txt.setBackground(Global.generalBg);
				txt.setFont(new Font("Arial", Font.BOLD, 12));
				txt.setBounds(Integer.parseInt(lxy[i][1][0]), Integer.parseInt(lxy[i][1][1])+25, 148, 20);
				ArrayList<String> itens = DatabaseQueries.getCategories();
				for(int ii = 1; ii < itens.size(); ii++) {
					txt.addItem(itens.get(ii));
					
				}
				txt.setName("categories");
				productForm.add(txt);
				
				
			} else {
				JTextField txt = new JTextField();

				//txt.setEditable(false);
				txt.setForeground(Color.black);
				txt.setName("textbox");
				if(i == 1) {
					txt.setName("barcode");
				}
				txt.setBackground(Global.generalBg);
				txt.setFont(new Font("Arial", Font.BOLD, 12));
				txt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
				txt.setEnabled(false);
				txt.setDisabledTextColor(Color.DARK_GRAY);
				txt.setHorizontalAlignment(JTextField.CENTER);
				
				
				if(i < 3) {
					txt.setBounds(Integer.parseInt(lxy[i][1][0]), Integer.parseInt(lxy[i][1][1])+25, Integer.parseInt(lxy[i][1][2]), 20);
				}else {
					txt.setBounds(Integer.parseInt(lxy[i][1][0]) + Integer.parseInt(lxy[i][1][2]), Integer.parseInt(lxy[i][1][1]), 40, 20);
					txt.addKeyListener(new KeyListener() {

						public void keyPressed(KeyEvent e) {}

						public void keyReleased(KeyEvent e) {}

						public void keyTyped(KeyEvent e) {
							char c = e.getKeyChar();
							if(Character.isAlphabetic(c)) {
								e.consume();
							}
						}
						
					});
				}
				
				if(i == 3) {
					lbl.setBounds(Integer.parseInt(lxy[i][1][0]), Integer.parseInt(lxy[i][1][1]), 165, 20);
					txt.setBounds(Integer.parseInt(lxy[i][1][0]) + 170, Integer.parseInt(lxy[i][1][1]), 40, 20);
				}
				if( i == 4) {
					lbl.setBounds(Integer.parseInt(lxy[i][1][0]), Integer.parseInt(lxy[i][1][1]), 140, 20);
					txt.setBounds(Integer.parseInt(lxy[i][1][0]) + Integer.parseInt(lxy[i][1][2]), Integer.parseInt(lxy[i][1][1]), 40, 20);
				}
				if(i >= 6) {
					txt.setBounds(Integer.parseInt(lxy[i][1][0])+Integer.parseInt(lxy[i][1][2]), Integer.parseInt(lxy[i][1][1]), 30, 20);
				}
				productForm.add(txt);
			}
			
			
			
			productForm.add(lbl);
		}
		
		productForm.add(newProductImage);
		
		
		productForm.add(productImg);
		
		
		
		
		
		
		ImageIcon editImage = new LogoIcon(40,40,"/edit.png").setLogo();
		ImageIcon saveImage = new LogoIcon(40,40,"/save.png").setLogo();
		
		JLabel searchBoxLabel = new JLabel("Insira o código do produto: ");
		searchBoxLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		searchBoxLabel.setForeground(Color.black);
		searchBoxLabel.setBounds(20, 15, 260, 18);
				
		JTextField searchBox = new JTextField();
		searchBox.setBounds(20, 40, 200, 20);
		searchBox.setForeground(Color.DARK_GRAY);
		searchBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		searchBox.setBackground(Global.generalBg);
		searchBox.setFont(new Font("Arial", Font.ITALIC, 15));
		searchBox.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			
		});
		
		JButton editSaveBtn = new JButton(editImage); // mesmo pra salvar
		editSaveBtn.setBounds(LandingPage.contentWidth - 140, 20, 40, 40);
		editSaveBtn.setBackground(Global.generalBg);
		editSaveBtn.setEnabled(true);
		editSaveBtn.setBorder(BorderFactory.createEmptyBorder());
		editSaveBtn.setFocusPainted(false);
		editSaveBtn.setToolTipText("Editar produto");
		editSaveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editSaveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean deleted = false;
				if(editorsave) {
					//edit
					boolean onlyOnce = true;
					for(int i = 0; i < productForm.getComponentCount(); i++) {
						if(productForm.getComponent(i) instanceof JTextField) {
							if(!((JTextField)productForm.getComponent(i)).getText().equals("")) {
								((JTextField)productForm.getComponent(i)).setEnabled(true);
								if(onlyOnce) {
									onlyOnce = false;
									editSaveBtn.setToolTipText("Salvar produto");
									editSaveBtn.setIcon(saveImage);
									editorsave = false;
								}
							}else {
								ErrorMsg.setErrorDialog("Não há nenhum produto que possa ser editado no momento!");
								break;
							}
						}
					}
					updateContent();
				}else {
					//save
					ArrayList<String> data = new ArrayList<String>();
					for(int i = 0; i < productForm.getComponentCount(); i++) {
						if(productForm.getComponent(i) instanceof JTextField || productForm.getComponent(i) instanceof JComboBox) {
							if(productForm.getComponent(i) instanceof JComboBox) {
								data.add((String)((JComboBox)productForm.getComponent(i)).getSelectedItem());
							}else {
								data.add(((JTextField)productForm.getComponent(i)).getText());
								((JTextField)productForm.getComponent(i)).setText("");
								productForm.getComponent(i).setEnabled(false);
							}
						}
					}
					if(DatabaseQueries.verifyProductExistance(data.get(1))) {
						if(DatabaseQueries.updateProduct(data)) {
							ErrorMsg.setErrorDialog("Produto atualizado com sucesso!");
						}
					}else {
						if(DatabaseQueries.registerProduct(data)) {
							ErrorMsg.setErrorDialog("Produto cadastrado com sucesso!");
						}
					}
					
					editSaveBtn.setToolTipText("Editar produto");
					editSaveBtn.setIcon(editImage);
					editorsave = true;
				}
				updateContent();
			}
			
		});
		
		
		
		JButton searchButton = new JButton(new LogoIcon(30,30,"/lupa.png").setLogo());
		searchButton.setBounds(230, 30, 30, 30);
		searchButton.setBorder(BorderFactory.createEmptyBorder());
		searchButton.setFocusPainted(false);
		searchButton.setBackground(Global.generalBg);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.setToolTipText("Buscar produto");
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(!searchBox.getText().isEmpty()) {
					boolean verifyInput = true;
					char cc[] = searchBox.getText().toCharArray();
					for(char c : cc ) {
						if(!Character.isDigit(c)) {
							verifyInput = false;
							break;
						}
					}
					if(verifyInput) {
						if(DatabaseQueries.verifyProductExistance(searchBox.getText())) {
							ArrayList<String> infoToReplace = DatabaseQueries.getItemInfoByBarcode(searchBox.getText());
							int ii = 0;
							for(int i = 0; i < productForm.getComponentCount(); i++) {
								int dataIndex[] = {12 , 11, 17, 14, 13, 1, 2, 3, 5, 6 ,7 , 8 ,9};
								if(productForm.getComponent(i) instanceof JTextField) {
									((JTextField)productForm.getComponent(i)).setText(infoToReplace.get(dataIndex[ii]));
									((JTextField)productForm.getComponent(i)).setToolTipText(infoToReplace.get(dataIndex[ii]));
									ii++;
								}else if(productForm.getComponent(i) instanceof JComboBox) {
									
									((JComboBox)productForm.getComponent(i)).setSelectedItem(infoToReplace.get(dataIndex[ii]));;
									ii++;
								}
							}
						}else {
							ErrorMsg.setErrorDialog("O produto não foi encontrado!");
							searchBox.setText("");
						}
						
						
					}	
				}else {
					ErrorMsg.setErrorDialog("Insira o código do produto a ser pesquisado!");
				}
			}
			
		});
		
		JButton newProductBtn = new JButton(new LogoIcon(40,40,"/addproduct.png").setLogo());
		newProductBtn.setBounds(LandingPage.contentWidth - 220, 20, 40, 40);
		newProductBtn.setBackground(Global.generalBg);
		newProductBtn.setBorder(BorderFactory.createEmptyBorder());
		newProductBtn.setFocusPainted(false);
		newProductBtn.setToolTipText("Adicionar novo produto");
		newProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		newProductBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editSaveBtn.setIcon(saveImage);
				editorsave = false;
				editSaveBtn.setToolTipText("Salvar produto");
				for(int i = 0; i < productForm.getComponentCount(); i++) {
					if(productForm.getComponent(i) instanceof JTextField) {
						((JTextField)productForm.getComponent(i)).setText("");
						((JTextField)productForm.getComponent(i)).setToolTipText("");
						productForm.getComponent(i).setEnabled(true);
					}		
				}	
				updateContent();
			}
			
		});
		
		JButton deleteProductBtn = new JButton(new LogoIcon(40,40,"/delete.png").setLogo());
		deleteProductBtn.setBounds(LandingPage.contentWidth - 60, 20, 40, 40);
		deleteProductBtn.setBorder(BorderFactory.createEmptyBorder());
		deleteProductBtn.setFocusPainted(false);
		deleteProductBtn.setBackground(Global.generalBg);
		deleteProductBtn.setToolTipText("Deletar produto");
		deleteProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteProductBtn.setEnabled(false);
		deleteProductBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				for(int i = 0; i < productForm.getComponentCount(); i++) {
					if(productForm.getComponent(i) instanceof JTextField) {
						if(productForm.getComponent(i).getName().equals("barcode")) {
							 String barcode = ((JTextField)productForm.getComponent(i)).getText();
							 if(!barcode.equals("")) {
									if(DatabaseQueries.deleteProduct(barcode)) {
										ErrorMsg.setErrorDialog("Produto excluído com sucesso!");
									}
							 }else {
								 ErrorMsg.setErrorDialog("Não há nenhum produto para ser excluído!");
							 }
							
						}
						((JTextField)productForm.getComponent(i)).setText("");
						((JTextField)productForm.getComponent(i)).setToolTipText("");
						productForm.getComponent(i).setEnabled(true);
					}
				}
				
				Home.refreshContent();
				updateContent();
			}
			
			
		});
		
		productPanel.add(editSaveBtn);
		productPanel.add(newProductBtn);
		productPanel.add(deleteProductBtn);
		
		productPanel.add(searchBoxLabel);
		productPanel.add(searchBox);
		productPanel.add(searchButton);
		
		productPanel.add(productForm);
		
	}
	
	public void setCategoryPanel() {
		categoryPanel.setBounds(0, 40, LandingPage.contentWidth, Main.height-115);
		categoryPanel.setBackground(Global.generalBg);
				
		JLabel title = new JLabel("Adicione uma categoria");
		title.setBounds(10, 10, 300, 30);
		title.setForeground(Color.black);
		title.setFont(new Font("Arial", Font.BOLD, 18));
		
		JTextField categoryField = new JTextField();
		categoryField.setBounds(10, 50, 200, 30);
		categoryField.setForeground(Color.black);
		categoryField.setFont(new Font("Arial", Font.PLAIN, 15));
		categoryField.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.black));
		categoryField.setHorizontalAlignment(JTextField.CENTER);

		JButton addCat = new JButton("Adicionar");
		addCat.setBounds(220, 50, 100, 30);
		addCat.setBorder(BorderFactory.createEmptyBorder());
		addCat.setForeground(Color.black);
		addCat.setFont(new Font("Arial", Font.PLAIN, 15));
		addCat.setBackground(Global.tertiaryBg);
		addCat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addCat.setFocusPainted(false);
		addCat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println(categoryField.getText());
				if(!categoryField.getText().equals("")) {
					if(!(DatabaseQueries.getCategoryIdByName(categoryField.getText()) > 0)) {
						if(DatabaseQueries.addNewCategory(categoryField.getText())) {
							ErrorMsg.setErrorDialog("A categoria '" +categoryField.getText() +"' foi cadastrada com sucesso!");
						}else {
							ErrorMsg.setErrorDialog("Não foi poss�vel cadastrar a categoria '" + categoryField.getText()+ "'");
						}
					}else {
						ErrorMsg.setErrorDialog("A categoria '" +categoryField.getText() +"' já existe!");
					}
				}else {
					ErrorMsg.setErrorDialog("Preencha todos os campos!");; 
				}
			}
			
		});
		
		categoryPanel.add(title);
		categoryPanel.add(categoryField);
		categoryPanel.add(addCat);
		
		JLabel title2 = new JLabel("Novo desconto");
		title2.setBounds(10, 120, 300, 30);
		title2.setForeground(Color.black);
		title2.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel discountLabel = new JLabel("Insira o valor a ser descontado: ");
		discountLabel.setBounds(10, 160, 300, 30);
		discountLabel.setForeground(Color.black);
		discountLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JTextField discountValue = new JTextField();
		discountValue.setBounds(10, 190, 150, 30);
		discountValue.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		discountValue.setForeground(Color.black);
		discountValue.setFont(new Font("Arial", Font.PLAIN, 14));
		discountValue.setHorizontalAlignment(JTextField.CENTER);
		discountValue.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {}

			public void keyReleased(KeyEvent arg0) {}

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isAlphabetic(c) && !(c == e.VK_PERIOD)) {
					e.consume();
				}
			}
			
		});
		
		JLabel categoriesLabel = new JLabel("Selecione uma categoria: ");
		categoriesLabel.setToolTipText("Selecione uma categoria para a aplicação do desconto!");
		categoriesLabel.setBounds(280, 160, 300, 30);
		categoriesLabel.setForeground(Color.black);
		categoriesLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JComboBox categ = new JComboBox();
		categ.setBounds(280, 185, 150, 30);
		categ.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
		categ.setFont(new Font("Arial", Font.PLAIN, 14));
		categ.setBackground(Color.white);
		ArrayList<String> cats = DatabaseQueries.getCategories();
		for(String cat : cats) {
			if(!cat.equals("Todos")) {
				categ.addItem(cat);
			}
		}
		
		JLabel fromThatPoint  = new JLabel("O desconto se aplicará a partir de: ");
		fromThatPoint.setBounds(10, 230, 300, 30);
		fromThatPoint.setForeground(Color.black);
		fromThatPoint.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JTextField fromThatPointField = new JTextField();
		fromThatPointField.setBounds(10, 260, 150, 30);
		fromThatPointField.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
		fromThatPointField.setForeground(Color.black);
		fromThatPointField.setFont(new Font("Arial", Font.PLAIN, 14));
		fromThatPointField.setHorizontalAlignment(JTextField.CENTER);
		fromThatPointField.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {}

			public void keyReleased(KeyEvent arg0) {}

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isAlphabetic(c)) {
					e.consume();
				}
			}
			
		});
		
		JLabel statusLabel = new JLabel("Status do desconto:");
		statusLabel.setBounds(280, 230, 150, 30);
		statusLabel.setForeground(Color.black);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		String statusCb[] = {"Ativo", "Inativo"};
		JComboBox status = new JComboBox(statusCb);
		status.setBounds(280, 260, 100, 30);
		status.setForeground(Color.black);
		status.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
		status.setFont(new Font("Arial", Font.PLAIN, 14));
		status.setBackground(Color.white);
		
		JButton searchDiscount = new JButton(new LogoIcon(30,30, "/lupa.png").setLogo());
		searchDiscount.setBounds(320, 120, 30, 30);
		searchDiscount.setBorder(BorderFactory.createEmptyBorder());
		searchDiscount.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchDiscount.setBackground(Global.generalBg);
		searchDiscount.setFocusPainted(false);
		searchDiscount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(DatabaseQueries.verifyDiscountExistance(DatabaseQueries.getCategoryIdByName((String)categ.getSelectedItem()))) {
					ArrayList<String> discountData = DatabaseQueries.getDiscountInfoById(DatabaseQueries.getCategoryIdByName((String) categ.getSelectedItem()));
					discountValue.setText(discountData.get(0));
					fromThatPointField.setText(discountData.get(1));
					if(Boolean.parseBoolean(discountData.get(2))) {
						status.setSelectedIndex(0);
					}else {
						status.setSelectedIndex(1);
					}
					categ.setSelectedItem(discountData.get(3));
					
				}else {
					ErrorMsg.setErrorDialog("Nenhum desconto para essa categoria foi encontrado");
				}
	
			}
			
		});
		
		JButton addDiscount = new JButton("Adicionar desconto");
		addDiscount.setBounds(450, 260, 120, 30);
		addDiscount.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addDiscount.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1,Color.black));
		addDiscount.setBackground(Global.tertiaryBg);
		addDiscount.setForeground(Color.black);
		addDiscount.setFocusPainted(false);
		addDiscount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(!fromThatPointField.getText().equals("") && !discountValue.getText().equals("")) {
					boolean stats = true;
					if(status.getSelectedItem().equals("Ativo")) {
						stats = true;
					}else {
						stats = false;
					}
					if(DatabaseQueries.verifyDiscountExistance(DatabaseQueries.getCategoryIdByName((String)categ.getSelectedItem()))) {
						if(DatabaseQueries.updateDiscount(Float.parseFloat(discountValue.getText()) , Integer.parseInt(fromThatPointField.getText()), stats, DatabaseQueries.getCategoryIdByName((String)categ.getSelectedItem()))) {
							ErrorMsg.setErrorDialog("Desconto atualizado com sucesso!");
						}
					}else {
						if(DatabaseQueries.addDiscount(Float.parseFloat(discountValue.getText()) , Integer.parseInt(fromThatPointField.getText()), stats, DatabaseQueries.getCategoryIdByName("" + categ.getSelectedItem()))) {
							ErrorMsg.setErrorDialog("Desconto cadastrado com sucesso!");
							discountValue.setText("");
							fromThatPointField.setText("");
							categ.setSelectedIndex(0);
							status.setSelectedIndex(0);
						}
					}
				}else {
					ErrorMsg.setErrorDialog("Preencha todos os campos!");
				}
			}
			
		});
		
		categoryPanel.add(title2);
		categoryPanel.add(searchDiscount);
		categoryPanel.add(discountLabel);
		categoryPanel.add(discountValue);
		categoryPanel.add(categoriesLabel);
		categoryPanel.add(categ);
		categoryPanel.add(fromThatPoint);
		categoryPanel.add(fromThatPointField);
		categoryPanel.add(statusLabel);
		categoryPanel.add(status);
		categoryPanel.add(addDiscount);
	}
	public void setUsersPanel() {
		
	}
	
	
	public JButton setMissingProducts() {
		
		JButton missingProductsBtn = new JButton("Clique para visualizar os produtos em falta!!!");
		missingProductsBtn.setBounds(0, 515, LandingPage.contentWidth, 60);
		missingProductsBtn.setBackground(new Color(255,0,0));
		missingProductsBtn.setFont(new Font("Arial", Font.BOLD, 20));
		missingProductsBtn.setForeground(Color.white);
		missingProductsBtn.setFocusPainted(false);
		missingProductsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		missingProductsBtn.setBorder(BorderFactory.createEmptyBorder());
		missingProductsBtn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				JDialog missingProductsPanel = new JDialog(Main.frame, "Produtos em falta", false);
				missingProductsPanel.setLayout(new FlowLayout());				
				missingProductsPanel.setSize(new Dimension(520, 400));
				missingProductsPanel.setResizable(false);
				missingProductsPanel.setLocationRelativeTo(null);
				
				JPanel missingList = new JPanel(new GridLayout(0,1,3,3));
				
				JScrollPane missingScroll = new JScrollPane(missingList,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				missingScroll.setPreferredSize(new Dimension(500,400));
				missingScroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				missingScroll.getVerticalScrollBar().setUnitIncrement(15);
				
				ArrayList<ItemInfo> missingItens = getMissingItensInfo();
				
				for(int i = 0; i < missingItens.size(); i++) {
					
					JPanel missingItem = new JPanel(new GridBagLayout());
					missingItem.setBorder(new TitledBorder("Em falta"));
					
					GridBagConstraints gbc = new GridBagConstraints();
					
					gbc.insets = new Insets(5,5,5,5);
					gbc.anchor = GridBagConstraints.WEST;
					gbc.gridwidth = 2;
					gbc.gridheight = 2;
					
					missingItem.add(new JLabel(new LogoIcon(50, 50, "/icecream.png").setLogo()),gbc);
					
					gbc.gridwidth = 2;
					gbc.gridheight = 1;
					gbc.gridx = 2;
					
					missingItem.add(new JLabel(missingItens.get(i).productName),gbc);
					
					gbc.gridx = 4;
					missingItem.add(new JLabel(missingItens.get(i).categoryName),gbc);
					
					gbc.gridy = 1;
					gbc.gridx = 2;
					
					missingItem.add(new JLabel("Código de barras: " + missingItens.get(i).barcode),gbc);
					
					gbc.gridx = 4;
					
					missingItem.add(new JLabel("Quantidade em estoque: " + missingItens.get(i).quantityStock),gbc);
					
					missingList.add(missingItem);

				}
				
				missingProductsPanel.add(missingScroll);

				missingProductsPanel.setVisible(true);


			}
			
		});
		
		return missingProductsBtn;
	}
	
	public void updateContent() {
		this.container.revalidate();
		this.container.repaint();
	}
	
	public ArrayList getMissingItensInfo() {
		ArrayList<ItemInfo> missingItens = DatabaseQueries.getMissingItensInfo();
		return missingItens;
	}
	
	
	public void setVisibility(boolean visibility) {
		if(getMissingItensInfo().isEmpty()) {
			container.remove(setMissingProducts());
		}else {
			container.add(setMissingProducts());
		}
		updateContent();
		this.visibility = visibility;
		container.setVisible(visibility);
	}
}
