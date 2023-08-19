package criptografia;

import java.util.ArrayList;

public class Encrypt {
	
	public static int tIndex = 3;
	public static String secretKey = "FUDSHF387FNSDU37USHF73902";

	
	public static String encryptMethod1(String textToEncrypt) {
	
		String result = "";
		
		
		String[] str = textToEncrypt.split("");
		
		ArrayList<String> tmp = new ArrayList<String>();
		
		for(int i = 0; i < str.length; i++) {
			if((i+tIndex) < str.length) {
				tmp.add(str[i+tIndex]);
			}else {
				tmp.add(str[(i+tIndex)-str.length]);
			}
			
		}
		
		String output = "";
		
		for(int i = 0; i < tmp.size(); i++) {
			output = output + tmp.get(i);
		}
		
		System.out.println("\n\n\tSaída do primeiro método de encriptação: " + output);
		
		return encryptMethod2(tmp);

	}
	
	public static String encryptMethod2(ArrayList<String> text) {
		String[] splitKey = secretKey.split(""); 
		int skController = 0;
		for(int i = 0; i < text.size(); i++) {
			if(i%2!=0) {
				if(skController >= splitKey.length) 
					skController = 0;
				
				text.add(i, splitKey[skController]);
				skController++;
			}
			
		}

		String result = "";
		
		for(int i = 0; i < text.size(); i++) {
			result = result + text.get(i);
		}
		
		System.out.println("\n\n\tChave secreta para intercalação: " + secretKey);
		
		System.out.println("\n\n\tSaída do segundo método de encriptação: " + result);
		
		return encryptMethod3(text);
	}
	
	public static String encryptMethod3(ArrayList<String> txt) {
		ArrayList<String> blocks = new ArrayList<String>();
		String block = "";
		
		while((txt.size()%8) != 0) {
			txt.add("*");
		}
		for(int i = 0; i <= txt.size(); i++) {
			if((i%8) == 0 && i > 0) {
				block = "";
				for(int ii = (i-8) ; ii < i; ii++) {
					block = block + txt.get(ii);
				}
				blocks.add(block);
			}
		}
		
		String fullblock = "";
		String output = "";
		
		for(int i = 0; i < blocks.size(); i++) {
			output = output + " | " + blocks.get(i); 
		}
		
		System.out.println("\n\n\tSaída da divisão de blocos: " + output);
		
		output = "";
		
		for(int i = (blocks.size()-1) ; i >= 0; i--) {
			fullblock = fullblock + blocks.get(i);
			output = output + " | " + blocks.get(i);
		}
		System.out.println("\n\n\tSaída do bloco inverso: " + output);
		
		System.out.println("\n\n\tSaída do terceiro método de encriptação: " + fullblock );
				
		return fullblock;
		
	}
	
}
