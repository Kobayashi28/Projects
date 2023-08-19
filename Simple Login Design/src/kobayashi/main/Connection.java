package kobayashi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	private Socket socket = null;

	public void Conn() throws UnknownHostException, IOException, ClassNotFoundException {

		Connection client = new Connection();

	    String ip = "127.0.0.1";
	    int port = 6969;
	    client.socketConnect(ip, port);
	    String message = "mensagem123";

	    System.out.println("Enviando: " + message);
	    String retorno = client.echo(message);
	    System.out.println("Recebendo: " + retorno);
	  }

	  private void socketConnect(String ip, int port) throws UnknownHostException, IOException {
	    System.out.println("[Conectando socket...]");
	    this.socket = new Socket(ip, port);
	  }

	  public String echo(String message) {
	    try {
	      PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
	      BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));

	      out.println(message);
	      String retorno = in.readLine();
	      return retorno;

	      } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return null;
	  }

	  private Socket getSocket() {
	              return socket;
	  }
	
	
}
