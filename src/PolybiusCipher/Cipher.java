package PolybiusCipher;

import java.util.Scanner;

public class Cipher {

	public static void main(String[] args) {
		Cipher cipher = new Cipher();
		cipher.encryption();
		cipher.decryption();
	}
	
	public String plaintext;
	public String ciphertext;
	public String key;
	public Scanner sc;
	
	public Cipher(){
		plaintext = "";
		ciphertext = "";
		key = "";
		sc = new Scanner(System.in);
	}
	
	public void encryption() {
		System.out.println("**********PloybiusCipher Encryption**********");
		System.out.println("Please enter plaintext: ");
		plaintext = sc.nextLine();
		System.out.println("设置密钥："+setKey());
		trans();
	}
	
	public boolean setKey() {
		System.out.println("Please enter key: ");
		key = sc.nextLine();
		
		//密钥输入检验
		//1.输入密钥的长度为5
		if (key.length()!=5) {
			System.out.println("ERROR!Length of key is wrong.");
			return false;
		}
		
		//2.密钥的每个元素不能相同
		for (int i=0;i<key.length()-1;i++) {
			for(int j=i+1;j<key.length();j++) {
				if (key.charAt(i)==key.charAt(j)) {
					System.out.println("ERROR!Element of key is not allowed to be same.");
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void trans() {
		//密钥的升序排序处理
		char[] sortKey = key.toCharArray();
		for(int i=0;i<sortKey.length-1;i++) {					
			for(int j=0;j<sortKey.length-i-1;j++) {
				if (sortKey[j]>sortKey[j+1]) {
					char temp = sortKey[j];
					sortKey[j] = sortKey[j+1];
					sortKey[j+1] = temp;
				}
			}
		}
		
		//按密钥解读出矩阵列数的排列顺序
		int[] sort = new int[sortKey.length];
		for (int i=0;i<sort.length;i++) {
			sort[i] = key.indexOf(sortKey[i]);
		}
		
		//更新密码表
		Checker.setChecker(sort);
		
		//通过明文进行密文的转化
		char[] plainArray = plaintext.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c: plainArray) {
			if (c==' ') {
				//用S表示明文中的空格
				sb.append("S");
			}else if (c=='j'){
				sb.append(Checker.match('i'));
			}else {
				sb.append(Checker.match(c));
			}
		}
		ciphertext = sb.toString();
		System.out.println("密文为："+ciphertext);
	}
	
	public void decryption() {
		System.out.println("**********PloybiusCipher Decryption**********");
		System.out.println("Please enter ciphertext: ");
		ciphertext = sc.nextLine();
		System.out.println("设置密钥："+setKey());
		inTrans();
	}
	
	public void inTrans() {
		//密钥的升序排序处理
		char[] sortKey = key.toCharArray();
		for(int i=0;i<sortKey.length-1;i++) {					
			for(int j=0;j<sortKey.length-i-1;j++) {
				if (sortKey[j]>sortKey[j+1]) {
					char temp = sortKey[j];
					sortKey[j] = sortKey[j+1];
					sortKey[j+1] = temp;
				}
			}
		}
			
		//按密钥解读出矩阵列数的排列顺序
		int[] sort = new int[sortKey.length];
		for (int i=0;i<sort.length;i++) {
			sort[i] = key.indexOf(sortKey[i]);
		}
				
		//更新密码表
		Checker.setChecker(sort);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<ciphertext.length();i++) {
			if (ciphertext.charAt(i)=='S') {
				sb.append(" ");
				continue;
			}
			char temp = Checker.inMatch(ciphertext.substring(i, i+2));
			if (temp == 'i') {
				sb.append("i(j)");
			}else {
				sb.append(temp);
			}
			i++;
		}
		plaintext = sb.toString();
		System.out.println("明文为："+plaintext);
	}
}
