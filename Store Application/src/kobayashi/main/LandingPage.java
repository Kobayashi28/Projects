package kobayashi.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import kobayashi.components.Button;
import kobayashi.components.ErrorMsg;
import kobayashi.components.Item;
import kobayashi.components.LogoIcon;
import kobayashi.pages.Admin;
import kobayashi.pages.History;
import kobayashi.pages.Home;
import kobayashi.pages.Promotions;

public class LandingPage {
public static JPanel container, menu, orderSection;
	
	public static Home home;
	public static History history;
	public static Promotions promotions;
	public static Admin admin;
	private LogoIcon logo;
	
	public static int menuWidth = 120;
	public static int contentWidth = 600;
	private int tstC = 1, scrollPanelHeight = 50;
	private GridLayout a = new GridLayout(tstC, 2 ,10,10);
	private int optBtnId;
	public PrinterService ps = new PrinterService();
	
	public JPanel itemList = new JPanel(new BorderLayout(4,4));
	public static JPanel scrollPanelItemList = new JPanel(new GridLayout(0,1,3,3));
	
	public static JCheckBox activeFastSell = new JCheckBox("<html><p style='text-align:center'>Entrada automática" );
	
	public static JTextField inputValueReceived = new JTextField();
	public static JComboBox selectPayment = new JComboBox();
	public static JLabel cashback = new JLabel("Troco: R$0.0", SwingConstants.RIGHT);

	public static float finalValue = 0; 
	public static JLabel totalValueLabel = new JLabel("Preço final", SwingConstants.LEFT),
			totalValue = new JLabel("R$ " + finalValue, SwingConstants.RIGHT);
	
	public static ArrayList<Item> itemArrayList = new ArrayList<Item>();
	
	private int buttonId;
	
	public LandingPage() {
		container = new JPanel(null);
		menu = new JPanel(null);
		orderSection = new JPanel(null);
		home = new Home();
		history = new History();
		promotions = new Promotions(); 
		admin = new Admin();
		logo = new LogoIcon(80,80,"/profile.png");
		
		
		setContainer();
		
	}
	
	public void setMenu() {		
						
		menu.setBounds(0, 0, 120, Main.height);
		menu.setBackground(Global.secondaryBg);
		
		JLabel logoPanel = new JLabel(logo.setLogo());
		logoPanel.setBounds(20, 20, 80, 80);		
		menu.add(logoPanel);		
		
		String[] options = {"Cardápio", "Promoções", "Histórico", "Admin"};
		String[] paths = {"/menu.png","/promo.png", "/history.png","/admin.png"} ;
		
		for(int i = 0; i < paths.length; i++) {
			JLabel optLabel = new JLabel(options[i], SwingConstants.CENTER);
			optLabel.setFont(new Font("Arial", Font.BOLD, 12));
			if(i == 3) {
				if(Main.isAdmin) {
					optLabel.setBounds(0,  60+(120*(i+1)),120, 20);
				}else {
					continue;
				}
			}else {
				optLabel.setBounds(0,  60+(105*(i+1)),120, 20);
			}
			
			
			menu.add(new Button().newButton( i, paths[i]));
			menu.add(optLabel); 
		}	
	}
	
	public void setOrderSection() {
		
		JPanel  groupLabels = new JPanel(null), 
				orderSectionHeader = new JPanel(null), 
				headerTitleBox = new JPanel(null), 
				orderSectionFooter = new JPanel(null),
				profileContainer = new JPanel(null);
		
		orderSection.setBounds(contentWidth+menuWidth, 0, 280, Main.height);
		orderSection.setBackground(Global.secondaryBg);
		orderSection.setForeground(Color.black);

		orderSectionHeader.setBackground(Global.secondaryBg);
		orderSectionHeader.setBounds(0, 0, 280, 100);
		
		profileContainer.setBounds(0, 0, 280, 60);
		profileContainer.setBackground(Global.secondaryBg);
		
		JLabel profileImg = new JLabel(new LogoIcon(50,50,"/profile.png").setLogo());
		profileImg.setBounds(5, 5, 50, 50);
		
		JLabel profileTitle = new JLabel(Main.username, SwingConstants.LEFT);
		profileTitle.setBackground(Color.blue);
		profileTitle.setBounds(60, 10, 150, 20);
		profileTitle.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel profileFunction = new JLabel(Main.isAdmin ? "-> Administrador" : "-> Vendedor");
		profileFunction.setBounds(60, 25, 150, 30);
		profileFunction.setFont(new Font("Arial", Font.ITALIC, 12));
		
		JButton profileLogout = new JButton(new LogoIcon(25,25,"/logout.png").setLogo());
		profileLogout.setBackground(Global.secondaryBg);
		profileLogout.setBounds(235, 30, 25, 25);
		profileLogout.setFocusPainted(false);
		profileLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		profileLogout.setToolTipText("Encerrar sessão");
		profileLogout.setBorder(BorderFactory.createEmptyBorder());
		profileLogout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Navigation.setLogin();
			}
			
		});
		
		headerTitleBox.setBounds(0, 60, 280, 40);
		headerTitleBox.setBackground(Global.secondaryBg);
		headerTitleBox.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));

		JButton resetItemList = new JButton("Nova venda");
		resetItemList.setBounds(0,63,160,34);
		resetItemList.setFocusPainted(false);
		resetItemList.setBorder(BorderFactory.createEmptyBorder());
		resetItemList.setBackground(Global.secondaryBg);
		resetItemList.setFont(new Font("Arial", Font.BOLD, 20));
		resetItemList.setForeground(Color.black);
		resetItemList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resetItemList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				resetItemList();
			}
			
		});
		
		activeFastSell.setBounds(170,63, 100,34);
		activeFastSell.setBackground(Global.secondaryBg);
		activeFastSell.setFocusPainted(false);
		activeFastSell.setForeground(Color.black);
		
		setItemList();
		
		orderSectionFooter.setBounds(0,400,280,200);
		orderSectionFooter.setBackground(Global.secondaryBg);
		
		groupLabels.setBounds(0,0,280,65);
		groupLabels.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
		groupLabels.setBackground(Global.secondaryBg);
		
		cashback.setBounds(160, 35, 100, 20);
		cashback.setFont(new Font("Arial", Font.BOLD, 10));
		
		JLabel valueReceived = new JLabel("Dinheiro recebido: R$ ", SwingConstants.LEFT);
		valueReceived.setBounds(10, 35, 120, 20);
		valueReceived.setFont(new Font("Arial", Font.ITALIC, 10));
		
		
		inputValueReceived.setBounds(120, 35, 40, 20);
		inputValueReceived.setBorder(BorderFactory.createEmptyBorder());
		inputValueReceived.setBackground(Global.secondaryBg);
		inputValueReceived.setFont(new Font("Arial", Font.PLAIN, 10));
		
		
		
		
		inputValueReceived.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isAlphabetic(c) && c != e.VK_PERIOD) {
					e.consume();
				}
			}
			
		});
		inputValueReceived.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {}

			public void insertUpdate(DocumentEvent e) {
				if(finalValue != 0) {				
					cashback.setText( "Troco: R$ " + Math.round((Float.parseFloat(inputValueReceived.getText()) - finalValue)*100.0)/100.0);
				}
				 
			}

			public void removeUpdate(DocumentEvent e) {
				if(!inputValueReceived.getText().equals("")) {
					if(finalValue != 0) {
						cashback.setText( "Troco: R$ " +Math.round((Float.parseFloat(inputValueReceived.getText()) - finalValue)*100.0)/100.0);
					}
				}
				
			}
			
		});
		
		ArrayList<String> methods =DatabaseQueries.getPaymentMethods();
		for(int i = 0; i < methods.size();i++) {
			selectPayment.addItem(methods.get(i));
		}
		selectPayment.setBounds(30, 70, 205, 20);
		selectPayment.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectPayment.setToolTipText("Selecione uma forma de pagamento");
		selectPayment.setBorder(BorderFactory.createEmptyBorder());
		selectPayment.setBackground(Global.tertiaryBg);
		
		String firstOption[] = {"Não imprimir cupom"};
		JComboBox selectPrinter = new JComboBox(firstOption);
		selectPrinter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectPrinter.setBounds(30, 95, 205, 20);
		selectPrinter.setBorder(BorderFactory.createEmptyBorder());
		selectPrinter.setBackground(Global.tertiaryBg);
		selectPrinter.setToolTipText("Selecione uma impressora");
		for(String printer : ps.getPrinters()) {
			selectPrinter.addItem(printer);
		}


		JButton confirmBtn = new JButton("Confirmar venda");
		confirmBtn.setBounds(30, 120,  205, 40);
		confirmBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		confirmBtn.setBackground(Global.tertiaryBg);
		confirmBtn.setForeground(Color.black);
		confirmBtn.setFont(new Font("Arial", Font.BOLD, 15));
		confirmBtn.setFocusPainted(false);
		confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		confirmBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(!inputValueReceived.getText().equals("")) {
					if(itemArrayList.size() > 0 && (Float.parseFloat(inputValueReceived.getText()) - finalValue) >= 0) {
						ArrayList<String> productBarcode = new ArrayList<String>();
						ArrayList<Integer> quantityFromEachProduct = new ArrayList<Integer>();
						for(int i = 0; i < itemArrayList.size(); i++) {
							productBarcode.add(itemArrayList.get(i).barcode);
							quantityFromEachProduct.add(itemArrayList.get(i).quantity);
						}
						
						float tmpValue =0;
						
						try {
							tmpValue = Float.parseFloat(inputValueReceived.getText());
						}catch(NumberFormatException ex) {}
						
						if(tmpValue >= finalValue) {
	
							if(DatabaseQueries.verifyProductsQuantities(productBarcode, quantityFromEachProduct)) {
								int paymentId = 0;
								for(int i = 0; i < methods.size(); i++) {
									if(methods.get(i).equals(selectPayment.getSelectedItem())){
										paymentId = i+1;
									}
								}
								String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
								DatabaseQueries.registerSale(productBarcode, quantityFromEachProduct, finalValue, Float.parseFloat(inputValueReceived.getText()), Float.parseFloat(inputValueReceived.getText()) - finalValue, paymentId, timestamp );
								if(!selectPrinter.getSelectedItem().equals(firstOption[0])) {
									
									SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
								    Date date = new Date();
								    
									String receiptFormat = 
											   "\n--------------------------------\n"
											+ "      Fantástica sorvetes"
											+ "\n\n          Cupom fiscal"
											+ "\n--------------------------------\n"
											+ "Endereço: Estrada Ana Procópio  de Moraes, 28 - Vila Anoral,\n Santana de Parnaíba - SP.\n"
											+ "CNPJ: 46.371.524/0001-90\n"
											+ "Data: "	 + formatter.format(date)			
											+ "\n--------------------------------\n"
											+ "ID   Nome            QTD  Preço"
											+ "\n--------------------------------"
											+ getReceiptInfo()
											+ "\n--------------------------------\n"
											+ "Valor total:         " + totalValue.getText()
											+ "\nValor recebido:      R$ "+ inputValueReceived.getText()
											+ "\nValor do troco:      R$ " + Math.round((Float.parseFloat(inputValueReceived.getText()) -  finalValue)* 100.0)/100.0
											+ "\nMétodo de pagamento: " + selectPayment.getSelectedItem()
											+ "\n--------------------------------\n\n\n\n";
									ps.printString((String) selectPrinter.getSelectedItem(), receiptFormat);
									ErrorMsg.setErrorDialog("Venda realizada com sucesso! <br><br> Imprimindo cupom fiscal...");
								}	else {
									ErrorMsg.setErrorDialog("Venda realizada com sucesso!");
								}
								history.setHistoryList();
								history.updateContent();
								inputValueReceived.setText("");
								cashback.setText("Troco: R$ ");
								resetItemList();
							}else {
								ErrorMsg.setErrorDialog("Não há produtos suficientes para que a venda seja efetuada!");
							}
						}else {
							ErrorMsg.setErrorDialog("Valor insuficiente");
						}
					}else {
						ErrorMsg.setErrorDialog("Selecione um produto para que a venda seja efetuada!");
					}
				}else {
					ErrorMsg.setErrorDialog("Preencha o campo de dinheiro recebido!");
				}
			}
			
		});
		
		
		
		totalValueLabel.setBounds(10,0,130,40);
		totalValueLabel.setFont(new Font("Arial", Font.BOLD, 20));
		totalValueLabel.setForeground(Color.black);

		totalValue.setBounds(130,0,130,40);
		totalValue.setFont(new Font("Arial", Font.BOLD, 20));
		totalValue.setForeground(Color.black);

		profileContainer.add(profileImg);
		profileContainer.add(profileTitle);
		profileContainer.add(profileFunction);
		profileContainer.add(profileLogout);
		
		orderSectionHeader.add(activeFastSell);
		orderSectionHeader.add(resetItemList);
		orderSectionHeader.add(headerTitleBox);
		orderSectionHeader.add(profileContainer);
		
		groupLabels.add(inputValueReceived);
		groupLabels.add(cashback);
		groupLabels.add(valueReceived);
		groupLabels.add(totalValueLabel);
		groupLabels.add(totalValue);
		orderSectionFooter.add(confirmBtn);
		orderSectionFooter.add(selectPrinter);
		orderSectionFooter.add(selectPayment);
		orderSectionFooter.add(groupLabels);
		
		orderSection.add(orderSectionHeader);
		orderSection.add(itemList);
		orderSection.add(orderSectionFooter);
		
		
	}
	
	 private String getReceiptInfo(){
	    	String txt = "";
	    	
	    	for(int i = 0; i < itemArrayList.size(); i++) {
	    		txt += "\n" + (i+1) +"  " + DatabaseQueries.getItemInfo(itemArrayList.get(i).barcode)[0] +"  " + itemArrayList.get(i).quantity + "  R$ " + itemArrayList.get(i).price + "\n";
	    	}
	    	
	    	return txt;
	    }
	
	public void resetItemList() {
		int staticComponentCount = scrollPanelItemList.getComponentCount();
		for(int i = 0; i < staticComponentCount ; i++) {
			scrollPanelItemList.remove(scrollPanelItemList.getComponent(0));
			itemArrayList.remove(0);
		}
		
	    updateFinalValue();
	}
	
	public static void updateFinalValue() {
		finalValue = 0;
		for(int i = 0; i < itemArrayList.size(); i++) {
			String info[] = DatabaseQueries.getDiscountForCalc(itemArrayList.get(i).categoryName);
			if(!info[0].equals("") && itemArrayList.get(i).quantity >= Integer.parseInt(info[1])) {
				finalValue += (itemArrayList.get(i).quantity+1)*(itemArrayList.get(i).price - Float.parseFloat(info[0]));
			}else {
				finalValue+= itemArrayList.get(i).quantity*itemArrayList.get(i).price;
			}
			
			 if(itemArrayList.get(i).quantity == 0) {
				 itemArrayList.remove(i);
			 }
		}
		if(!selectPayment.getSelectedItem().equals("Dinheiro")) {
			try {
				inputValueReceived.setText(Float.toString(finalValue));
			}catch(NumberFormatException e) {
				
			}
			cashback.setText("Troco: R$0.0");
		}
		
	    totalValue.setText("R$ "+ Math.round( finalValue * 100.0)/100.0);
		orderSection.revalidate();
		orderSection.repaint();
	}
	
	
	private void setItemList() {
		itemList.setBounds(0, 100, 290, 300);

		   scrollPanelItemList.setBackground(Global.secondaryBg);

		   JScrollPane scrollPanel = new JScrollPane(scrollPanelItemList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		   scrollPanel.getVerticalScrollBar().setUnitIncrement(15);
		   scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		   
		   itemList.add(scrollPanel);
		   
	}
	
	
	public void setContainer() {
		
		setMenu();
		setOrderSection();
		
		container.add(home.setHome());
		container.add(promotions.setPromo());
		container.add(history.setHistory());
		container.add(admin.setAdmin());
		container.add(menu);
		container.add(orderSection);
	}

}