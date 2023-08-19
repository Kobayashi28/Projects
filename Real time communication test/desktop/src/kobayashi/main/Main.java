package kobayashi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;


public class Main {

	private String ip = "http://127.0.0.1:7654/";
	
	 public static void main(String[] args) throws MalformedURLException, IOException {
		 Main client = new Main();
		 String a = (String) client.getUser().getJSONObject("a").get("name");
		 System.out.println(client.addUser());
		 System.out.println(a);
		 System.out.println(client.listProducts().getJSONObject("2"));
		 
	 }
	 
	 public boolean addUser() throws IOException {
		 
		 HttpURLConnection conn = (HttpURLConnection) new URL(ip+"users").openConnection();

		 String username = "sadsa", password = "abc123";
		 
		 String json = "{\"username\":\""+username+"\", \"password\":\""+password+"\"}";

		 return postMethod(conn, json);
	 }
	 
	 public JSONObject listProducts() throws MalformedURLException, IOException {
		 HttpURLConnection conn = (HttpURLConnection) new URL(ip + "products").openConnection();
		 
		 return getMethod(conn);
	 }
	 
	 public JSONObject getUser() throws MalformedURLException, IOException {
		 HttpURLConnection conn = (HttpURLConnection) new URL(ip+"users").openConnection();

		 return getMethod(conn);
	 }
	 
	 public boolean postMethod(HttpURLConnection conn, String json) throws IOException {
		 
		 byte[] output = json.getBytes(StandardCharsets.UTF_8);
		 
		 conn.setRequestMethod("POST");
		 conn.setDoOutput(true); 		
		 conn.setFixedLengthStreamingMode(output.length);
		 conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		 conn.connect();
		 
		 try (OutputStream os = conn.getOutputStream()) {
			 os.write(output);
		 }		 
		 
		 return getMethod(conn).getBoolean("success");
	 }
	 
	 public JSONObject getMethod(HttpURLConnection conn) throws IOException {
		 
		 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		 String input;
		 StringBuffer sb = new StringBuffer();

		 while ((input = br.readLine()) != null){
		     sb.append(input);
		 }
		 br.close();
		 

		 JSONObject res = new JSONObject(sb.toString());
		 return res;
	 }
	 
	 
	 
	 
	 
  
}