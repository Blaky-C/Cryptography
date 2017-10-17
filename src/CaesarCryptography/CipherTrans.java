package CaesarCryptography;

import java.util.Scanner;

public class CipherTrans {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CipherTrans ct = new CipherTrans();
		ct.setCipherText();
		//System.out.println(ct.cipherTrans(3));
		ct.traverse();
	}

	String plainText;
	String cipherText;
	String key;
	Scanner sc = new Scanner(System.in);
	
	public CipherTrans() {
		System.out.println("**********Welcome use Caesar Cryptography**********");
		plainText="";
		cipherText="";
		key="";
	}
	
	public boolean setCipherText() {
		System.out.println("Please enter ciphertext: ");
		cipherText = sc.nextLine();
		char[] arr = cipherText.toCharArray();
		
		for(int i=0;i<arr.length;i++) {
			if (!Character.isLetter(arr[i])) {
				System.out.println("ERROR!Enter formation is wrong.");
				return false;
			}
			if (Character.isLowerCase(arr[i])) {
				arr[i] = Character.toUpperCase(arr[i]);
			}
		}
		cipherText = new String(arr);
		System.out.println("CipherText is :");
		System.out.println(cipherText);
		return true;
	}
	
	public String cipherTrans(int key) {
		char[] arr = cipherText.toCharArray();
		int temp;
		
		for(int i=0;i<cipherText.length();i++) {
			temp = arr[i]+32-key;
			
			if(temp<97) {
				temp += 26;
			}
			arr[i] = (char)temp;
		}
		
		plainText = new String(arr);
		return plainText;
	}
	
	public void traverse() {
		for(int i=0;i<26;i++) {
			System.out.println("When key="+i+", plain is: ");
			System.out.println(cipherTrans(i));
		}
	}
}
