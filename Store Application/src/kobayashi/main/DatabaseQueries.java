package kobayashi.main;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kobayashi.components.IndividualItemSold;
import kobayashi.components.ItemInfo;
import kobayashi.components.SalesInfo;

public class DatabaseQueries {

	private static String url = "jdbc:mysql://127.0.0.1:3306/loja", username="root", password="12345678";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	private static void Connect() {

		try {
			conn = DriverManager.getConnection(url,username,password);
			if(conn != null) {
				System.out.println("?");
			}
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	private static void closeConnections() {
		if(rs != null) {
			try { rs.close();} catch (SQLException e) {}
			 rs = null;
		}
		if(stmt != null) {
			try { stmt.close();} catch (SQLException e) {}
			stmt = null;
		}
		if(conn != null) {
			try { conn.close();} catch (SQLException e) {}
			conn = null;
		}
		
		
	}
	
	public static int getIdByUsername(String username) {
		int id = 0;
		
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from users where username = '" +username+"'");
			rs.next();
			id = rs.getInt(1);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		
		return id;
	}
	
	public static boolean updateDiscount(Float value, int fromThatPoint, boolean status, int categoryId) {
		boolean stats = false;
		
		try {
			Connect();
			stmt = conn.createStatement();
			if(stmt.executeUpdate("update discount set discountvalue = " + value+ ", discountstart = " + fromThatPoint + ",discountstatus =" +status + " where categoryId = " + categoryId) != 0) {
				stats = true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		
		
		return stats;
		
	}
	
	public static ArrayList<String> getDiscountInfoById(int catId){
		ArrayList<String> data = new ArrayList<String>();
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select discount.discountvalue, discount.discountstart, discount.discountstatus, category.categoryName  from discount inner join category on category.id = discount.categoryId where discount.categoryId = "+ catId);
			rs.next();
			data.add("" + rs.getFloat(1));
			data.add("" + rs.getInt(2));
			data.add("" + rs.getBoolean(3));
			data.add(rs.getString(4));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return data;
	}
	
	public static boolean verifyDiscountExistance(int catId) {
		boolean exists = false;
		
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from discount where categoryId = "+ catId);
			rs.next();
			if(rs.getInt(1) != 0) {
				exists = true;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return exists;
	}
	
	public static boolean updateProduct(ArrayList<String> data){
		boolean success = false;
		String query = "";
		String fieldsToUpdate[][] = {{"product","productName"}, {"product","categoryId"},{"product","quantityStock"}, {"product","productPrice"},{"fabricInfo","untType"}, {"fabricInfo","untQtd"},{"fabricInfo", "untPrice"}, {"fabricInfo","simpleTax"}, {"fabricInfo", "mva"} , {"fabricInfo","st"},  {"fabricInfo","priceWithoutSt"}, {"fabricInfo","finalPrice" }};
		data.set(2, "" + DatabaseQueries.getCategoryIdByName(data.get(2)));
		int substract = 0;
		try {
			Connect();
			stmt = conn.createStatement();
			for(int i = 0; i < data.size(); i++) {
				System.out.println(data.get(i) + " " + i);
				if(i == 1) {
					substract = 1;
					continue;
				}
				if(fieldsToUpdate[i-substract][0].equals("product")) {
					query = "update " + fieldsToUpdate[i-substract][0] + " set " + fieldsToUpdate[i-substract][1] + " = " + "'"+data.get(i)+"'" + " where " + fieldsToUpdate[i-substract][0] + ".barcode = " + "'"+data.get(1)+"'";
				}else {
					query = "update " + fieldsToUpdate[i-substract][0] + " set " + fieldsToUpdate[i-substract][1] + " = " + "'"+data.get(i)+"'"+ " where " + fieldsToUpdate[i-substract][0] + ".productBarcode = " + "'"+data.get(1)+"'";
				}
				System.out.println(query);
				if(stmt.executeUpdate(query) != 0) {
					success = true;
				}
			}
				
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return success;
	}
	
	public static ArrayList<String> getUserNames(){
		
		ArrayList<String> usernames = new ArrayList<String>();
		
		try {
			
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select username from users");
			while(rs.next()) {
				usernames.add(rs.getString(1));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return usernames;
	}
	
	public static boolean deleteProduct(String barcode) {
		boolean status = false;
		try {
			
			Connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from fabricInfo where productBarcode = '" +barcode + "'");
			if(stmt.executeUpdate("delete from product where barcode ='" + barcode +"'") > 0)
				status = true;	
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		
		
		return status;
	}
	
	public static ArrayList<IndividualItemSold> getIndividualItensSold(int saleId){
		ArrayList<IndividualItemSold> info = new ArrayList<IndividualItemSold>();
		
		String query = "select p.barcode, p.productName, c.categoryName, p.productPrice,"
				+ " i.quantitySold, i.mainSaleId from individualItemSold as i inner join product as"
				+ " p on p.barcode = i.productBarcode inner join category as c on p.categoryId = c.id "
				+ "where i.mainSaleId =" + saleId;

		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				info.add(new IndividualItemSold(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}

		return info;
	}
	
	public static int getUserIdByName(String name) {
		int id = 0;
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from users where username = '" + name+"'");
			rs.next();
			id = rs.getInt(1);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return id;
	}
	
	public static ArrayList<SalesInfo> getAllSalesInfoOnTheDay(String date){
		ArrayList<SalesInfo> info = new ArrayList<SalesInfo>();
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select sales.*, users.username, paymentMethods.method from sales inner join paymentMethods on paymentMethods.id = sales.paymentId inner join users on users.id = sales.seller_id where soldAt like '%" + date+"%' order by sales.id");
			while(rs.next()) {
				info.add(new SalesInfo(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getInt(6), rs.getString(9), rs.getString(8)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return info;
	}
	
	public static ArrayList<SalesInfo> getSalesBySellerId(int sellerId, String dateTime){
		
		ArrayList<SalesInfo> info = new ArrayList<SalesInfo>();
		
		try {
			
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select sales.*, users.username, paymentMethods.method from sales inner join paymentMethods on paymentMethods.id = sales.paymentId inner join users on sales.seller_id = users.id where sales.seller_id = " + sellerId + " and sales.soldAt like '%" +dateTime+ "%' order by sales.id");
			while(rs.next()) {
				info.add(new SalesInfo(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getInt(6), rs.getString(9), rs.getString(8)));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		
		return info;
	}
	
	public static ArrayList<String> getItemInfoByBarcode(String barcode){
		ArrayList<String> info = new ArrayList<String>();
		
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select fabricInfo.*, product.*, category.categoryName from product inner join "
					+ "fabricInfo on product.barcode =  fabricInfo.productBarcode inner join category on category.id = product.categoryId "
					+ "where product.barcode = '" + barcode+ "'");
			rs.next();
			
			for(int i = 1; i <= 18; i++) {
				info.add((String)rs.getString(i));
			}
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return info;
	}
	
	public static boolean verifyProductsQuantities(ArrayList<String> barcodes, ArrayList<Integer> quantities) {
		
		try {
			
			Connect();
			stmt = conn.createStatement();
			for(int i = 0; i < barcodes.size(); i++) {
				String query = "select quantityStock from product where barcode = '"+ barcodes.get(i) + "'"; 
				rs = stmt.executeQuery(query);
				rs.next();
				int qtd = rs.getInt(1);
				if(qtd < quantities.get(i)) {
					return false;
				}
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return true;
	}
	
	public static ArrayList getMissingItensInfo() {
		ArrayList<ItemInfo> missingItens = new ArrayList<ItemInfo>();
		String query = "select product.*, category.categoryName from product inner join category on product.categoryId = category.id where product.quantityStock < 10";

		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				missingItens.add(new ItemInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getFloat(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7)
				));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return missingItens;
	}
	
	public static ArrayList getItensByName(String input) {
		ArrayList <String> names = new ArrayList<String>();
		String query = "select productName from product where productName like '%"+ input +"%'";
		try {
			
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				names.add(rs.getString(1));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			closeConnections();
		}
		
		return names;
	}
	
	public static ArrayList<String> getPaymentMethods() {
		ArrayList<String> methods = new ArrayList<String>();
		
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select method from paymentMethods");
			while(rs.next()) {
				methods.add(rs.getString(1));
			}
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return methods;
	}
	
	public static void registerSale(ArrayList<String>barcodes, ArrayList<Integer> quantitiesFromEach, float totalValue, float valueReceived, float changeGiven, int paymentId, String dateTime) {
		
		try {
			String query = "insert into sales(soldAt, totalValue, valueReceived, changeGiven, seller_id, paymentId) values ('"+ dateTime + "',"+totalValue+ "," +  valueReceived+ ","+ changeGiven+ "," + Main.userId+ "," + paymentId + ")"; 
			Connect();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			rs = stmt.executeQuery("select id from sales order by id desc limit 1");
			rs.next();
			int lastSellId = rs.getInt(1);
			for(int i = 0; i < barcodes.size(); i++) {
				String queries[] = {"insert into individualItemSold(quantitySold, productBarcode, mainSaleId) values("+ quantitiesFromEach.get(i)+ "," + barcodes.get(i)+"," + lastSellId + ")", "update product set product.quantityStock = product.quantityStock -"+quantitiesFromEach.get(i)+" where barcode = '" + barcodes.get(i) +"'" };
				for(int ii = 0; ii < queries.length; ii++) {
					stmt.executeUpdate(queries[ii]);
				}
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnections();
		}
		
	}
	
	public static int countRegister(String field, String table) {
		
		int i = 0;
		String query = "select count("+field+") from "+ table;
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			i = rs.getInt(1) ;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return i;
	}
	
	
	public static ArrayList<String> getCategories() {
		String query = "select categoryName from category";
		ArrayList<String> categories = new ArrayList<String>();
		try {
			
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				categories.add(rs.getString(1));
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		return categories;
	}
	
	
	public static ArrayList getAllItemInfo() {
				
		ArrayList<ItemInfo> itens = new ArrayList<ItemInfo>();
		String query = "select product.*, category.categoryName from product inner join category on product.categoryId = category.id";
				
		try {			
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				itens.add(new ItemInfo(
						rs.getString(1),
						rs.getString(2),
						rs.getFloat(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7)
				));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
			
	
		return itens;
		
	}
	
	public static int getCategoryIdByName(String name) {
		int id = 0;
		try {
			String query = "select id from category where categoryName = '" +name + "'";
			System.out.println(query);

			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			id = rs.getInt(1);
			
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return id;
	}
	
	public static boolean registerProduct(ArrayList<String> data) {
		boolean status = false;
		try {
			
			String query = "insert into product(barcode, productName, productPrice, quantityStock, categoryId) "
			+ "values("+ data.get(1)+ "," + "'" + data.get(0) + "'"+ "," + Float.parseFloat(data.get(4))+ "," +Integer.parseInt(data.get(3))+ "," + 
			getCategoryIdByName(data.get(2)) +")";
			
			Connect();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			query = "insert into fabricInfo(untType, untQtd, untPrice, productPrice, simpleTax, mva, st, priceWithoutSt, finalPrice, productBarcode) values "
					+ "( " +"'" +data.get(5)+"'"+ "," + Integer.parseInt(data.get(6))+ "," + Float.parseFloat(data.get(7))+ "," + Float.parseFloat(data.get(4))
					+ "," + Float.parseFloat(data.get(8))+ "," + Float.parseFloat(data.get(9))+ "," +  Float.parseFloat(data.get(10))+ "," 
					+ Float.parseFloat(data.get(11))+ "," + Float.parseFloat(data.get(12))+ "," + data.get(1) + ")" ;       
			
			if(stmt.executeUpdate(query) > 0) {
				status = true;
			}
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnections();
		}
		return status;
	}
	
	public static boolean verifyProductExistance(String barcode) {
		boolean exist = false;
		
		try {
			String query = "select barcode from product where barcode ="+ barcode;
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query); 
			if(!rs.next()) {
				exist = false;
			}else {
				exist = true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnections();
		}
		
		return exist;
	}
	
	public static String[] getDiscountForCalc(String name) {
		String[] info = {"",""};
		try {
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select discount.discountvalue, discount.discountstart from discount inner join category on category.id = discount.categoryId where category.categoryName = '"+name+"' and discount.discountstatus = true");          
			if(rs.next()) {
				info[0] = "" + rs.getFloat(1);
				info[1] = "" + rs.getInt(2);
			}
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnections();
		}
		
		return info;
	}
	
	public static String[] getItemInfo(String barcode){
		String[] itens = {"","","",""};

		try {
			String query = "select product.productName, product.productPrice, product.quantityStock, category.categoryName "
							+ "from product inner join category on product.categoryId = category.id where "
							+ "product.barcode = "+  barcode ;
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query); 
			rs.next();
			itens[0] = rs.getString(1);
			itens[1] = Float.toString(rs.getFloat(2));
			itens[2] = Integer.toString(rs.getInt(3));
			itens[3] = rs.getString(4);
		}catch(SQLException e) {
			throw new IllegalStateException("Error: ", e);
		} finally {
			closeConnections();

		}
		
		
		return itens;
	}
	
	public static boolean verifyUser(String username, String pw) {
		boolean result = false;
		try {
			String query = "select * from users where username = '"+ username+"'";
			Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query); 
			System.out.println(rs);
			while(rs.next()) {
				if(rs.getString(2)!=null) {
					if(rs.getString(3).equals(pw)) {
						Main.userId = rs.getInt(1);
						Main.username = rs.getString(2);
						Main.isAdmin = rs.getBoolean(4);
						result = true;
					}
				}
			}
		}catch(SQLException e) {
			 throw new IllegalStateException("Error: ", e);
		} finally {
			closeConnections();
			
		}
		return result;
	}
	
	public static boolean addDiscount(float value,int start, boolean status, int categoryId ) {
		boolean worked = false;
		
		try {
			Connect();
			stmt = conn.createStatement();
			if(stmt.executeUpdate("insert into discount(discountvalue, discountstart, discountstatus, categoryId) "
					+ "values ("+ value+"," +start+ "," +status+"," +categoryId+")") > 0) {
				worked = true;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		
		
		return worked;
	}

	public static boolean addNewCategory(String name) {
		boolean inserted = false;
		try {
			
			Connect();
			stmt = conn.createStatement();
			if(stmt.executeUpdate("insert into category (categoryName) values ('" + name+ "')") > 0){
				inserted =true;
			}else {
				inserted = false;
			}
	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			closeConnections();
		}
		return inserted;	
	}
}
