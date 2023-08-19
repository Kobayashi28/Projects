package criptografia;

import java.util.ArrayList;

public class Decrypt {

	public static int tIndex = 3;
	public static String secretKey = "FUDSHF387FNSDU37USHF73902";
	
	public static String decryptMethod(String textToEncrypt) {
		String[] str = textToEncrypt.split("");

		ArrayList<String> tmp = new ArrayList<String>();
		
		for(int i = (str.length-1); i >= 0; i-- ) {
			if((i-tIndex) >= 0) {
				tmp.add(str[i-tIndex]);				
			}else {
				tmp.add(str[(i-tIndex)+str.length]);
			}
		}
		
		String result = "";
		for(int i = (str.length-1); i >=0; i--) {
			result = result + tmp.get(i);
		}
		System.out.println("\n\n\tSaída do terceiro método de encriptação: " + result);
		return result;
	}
	
	public static String decryptMethod2(String text) {
		String[] str = text.split("");
		
		ArrayList<String> strArray = new ArrayList<String>();
		
		String[] splitKey = secretKey.split(""); 
		
		int skController = 0;
		for(int i = 0; i < str.length; i++) {
			if(i%2==0) {
				
				if(skController >= splitKey.length) 
					skController = 0;
				
				strArray.add(str[i]);
				skController++;
			}
			
		}

		String result = "";
		for(int i = 0; i < strArray.size(); i++ ) {
			result = result + strArray.get(i);
		}
		
		System.out.println("\n\n\tSaída do segundo método de decriptação: " + result);
		
		return decryptMethod(result);
	}
	
	public static String decryptMethod3(String txt) {
		
		String[] txtSplit = txt.split("");
		
		ArrayList<String> blocks = new ArrayList<String>();
		String block = "";
		
		for(int i = 0; i <= txtSplit.length;i++) {

			if((i%8) == 0 && i > 0) {
				block = "";
				for(int ii = (i-8) ; ii < i; ii++) {
					block = block + txtSplit[ii];
				}
				blocks.add(block);
				
			}
		}
		
		
		String fullblock = "";
		String output = "";
		for(int i = (blocks.size()-1) ; i >= 0; i--) {
			fullblock = fullblock + blocks.get(i);
			output = output + " | " + blocks.get(i);
		}
		
		System.out.println("\n\n\tInversão dos blocos: " + output );
		
		System.out.println("\n\n\tSaída do primeiro método de decriptação: " + fullblock );
						
		return decryptMethod2(fullblock.replace("*", ""));
	}
	
	
	
}
