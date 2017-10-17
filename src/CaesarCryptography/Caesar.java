package CaesarCryptography;

import java.util.Scanner;

public class Caesar {

	public static void main(String[] args) {
		Caesar caesar = new Caesar();
		caesar.setPlaintext();
		caesar.transCipherText();
	}


	int key;
	String plaintext;
	String ciphertext;
	int size;
	Scanner sc = new Scanner(System.in);
	
	Caesar(){
		System.out.println("**********Welcome use Caesar Cryptography**********");
		key=0;
		size=0;
		plaintext = "";
		ciphertext = "";
		this.setKey();
	}
	
	public void setKey() {
		System.out.println("Please enter a key: ");
		key = sc.nextInt()%26;
		sc.nextLine();
	}
	
	public boolean setPlaintext() {
		System.out.println("Please enter your plaintext: ");
		String tempPlaintext = sc.nextLine();
		char[] plaintextArray = tempPlaintext.toCharArray();
		
		for(int i=0;i<plaintextArray.length;i++) {
			if (!Character.isLetter(plaintextArray[i])) {
				System.out.println("ERROR!Please enter plaintext with letter.");
				return false;
			}
			if (Character.isUpperCase(plaintextArray[i])) {
				plaintextArray[i] = Character.toLowerCase(plaintextArray[i]);
			}
		}
		
		plaintext = new String(plaintextArray);
		System.out.println("PlainText is: \n"+plaintext);
		size = plaintext.length();
		return true;
	}
	
	public void transCipherText() {
		char[] text = plaintext.toCharArray();
		int transValue=key-32;
		int temp;
		for(int i=0;i<text.length;i++) {
			temp = text[i]+transValue;
			if (temp>90) {
				temp =temp-90+65-1;
			}
			text[i] = (char)(temp);
		}
		ciphertext = new String(text);
		System.out.println("Ciphertext after transaction is: ");
		System.out.println(ciphertext);
	}
}
