package Replacement;

import java.util.Scanner;

public class ReplaceTrans {

	public static void main(String[] args) {
		ReplaceTrans rt = new ReplaceTrans();
		//加密方式
		rt.encryption();
		//解密方式
		rt.decryption();
	}
	
	String plainText;
	String cipherText;
	char[][] plainMatrix;
	String key;
	int row;
	int col;
	Scanner sc;
	
	ReplaceTrans(){
		plainText = "";
		cipherText = "";
		key = "";
		row = col = 0;
		sc = new Scanner(System.in);
	}
	
	public boolean setPlainText() {
		//输入明文
		System.out.println("Please enter your plainText: ");
		plainText = sc.nextLine();
		String[] plainArray = plainText.split(" ");
		
		//确定矩阵的行数和列数
		row = plainArray.length;
		col = 0;
		for(int i=0;i<row;i++) {
			if (col < plainArray[i].length()) {
				col = plainArray[i].length();
			}
		}
		
		//以空格数据初始化矩阵
		plainMatrix = new char[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				plainMatrix[i][j] = ' ';
			}
		}
		//将明文内容填入矩阵
		for (int i=0;i<plainArray.length;i++) {
			for(int j=0;j<plainArray[i].length();j++) {
				plainMatrix[i][j] = plainArray[i].charAt(j);
			}
		}
		
		//显示矩阵
		/*for (int i=0;i<plainMatrix.length;i++) {
			for(char c:plainMatrix[i]) {
				System.out.print(c);
			}
			System.out.println();
		}*/
		return true;
	}
	
	public boolean setKey() {
		System.out.println("Please enter your key: ");
		key = sc.nextLine();
		
		//密钥输入检验
		//1.输入密钥的长度必须大于字符数组的最大宽度
		if (key.length()<col) {
			System.out.println("ERROR!Length of key must not less than maximent of column.");
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
		
		System.out.println("Your key is:	"+key);
		return true;
	}
	
	public boolean transText() {
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
		//System.out.println(sortKey);
		
		//按密钥解读出矩阵列数的排列顺序
		int[] sort = new int[sortKey.length];
		for (int i=0;i<sort.length;i++) {
			sort[i] = key.indexOf(sortKey[i])+1;
			//System.out.print(sort[i]);
		}
		
		char[][] afterTrans = new char[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				afterTrans[i][j] = ' ';
			}
		}

		for (int j=0;j<col;j++) {
			int aim = sort[j]-1;
			for (int i=0;i<row;i++) {
				afterTrans[i][j] = plainMatrix[i][aim];
			}
		}

		//显示矩阵
		/*for (int i=0;i<afterTrans.length;i++) {
			for(char c:afterTrans[i]) {
				System.out.print(c);
			}
			System.out.println();
		}*/
		
		//生成密文
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<col;j++) {
			int aim = sort[j]-1;
			for(int i=0;i<row;i++) {
				sb.append(afterTrans[i][aim]);
			}
		}
		cipherText = sb.toString();
		System.out.println("Generate cipherText: \n"+cipherText);
	
		return true;
	}
	
	public void encryption() {
		System.out.println("**********Encryption**********");
		boolean flag1 = setPlainText();
		System.out.println("输入明文："+flag1);
		System.out.println();
		boolean flag2 = setKey();
		System.out.println("设置密钥："+flag2);
		System.out.println();
		if (flag1 && flag2) {
			transText();
		}else {
			System.out.println("ERROR!");
		}
		System.out.println();
	}
	
	public void decryption() {
		System.out.println("**********Decryption**********");
		System.out.println("Please enter your cipherText: ");
		cipherText = sc.nextLine();
		System.out.println();
		
		/*//这里我们使用人为给定的密文：
		cipherText = "abatgftetcnvaiikse";
		System.out.println("Enter cipherText: "+cipherText);
		System.out.println();*/
		
		System.out.println("Please enter your key: ");
		key = sc.nextLine();
		System.out.println();
		inTransText(cipherText, key);
	}
	
	public boolean inTransText(String cipher, String key) {
		System.out.println("CipherText entered is: "+cipher);
		System.out.println("Key is: "+key);
		
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
		//System.out.println(sortKey);
				
	    //按密钥解读出矩阵列数的排列顺序
		int[] sort = new int[sortKey.length];
		for (int i=0;i<sort.length;i++) {
			sort[i] = key.indexOf(sortKey[i])+1;
			//System.out.print(sort[i]);
		}
		
		int col = key.length();
		int row = cipher.length()/col;
		char[][] rawText = new char[row][col];
		for(int i=0;i<cipher.length();i++){
			int aim = sort[i/row]-1;
			rawText[i%row][aim] = cipher.charAt(i);
		}
		
		//显示矩阵
		/*for (int i=0;i<rawText.length;i++) {
			for(char c:rawText[i]) {
				System.out.print(c);
			}
			System.out.println();
		}*/
		
		//生成最原始的明文矩阵
		char[][] originText = new char[row][col];
		for(int j=0;j<col;j++) {
			int aim = sort[j]-1;
			for (int i=0;i<row;i++) {
				originText[i][aim] = rawText[i][j];
			}
		}
		
		//显示矩阵
		/*for (int i=0;i<originText.length;i++) {
			for(char c:originText[i]) {
				System.out.print(c);
			}
			System.out.println();
		}*/
		
		//生成明文
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<originText.length;i++) {
			for(int j=0;j<originText[i].length;j++) {
				if (originText[i][j]!=' ') {
					sb.append(originText[i][j]);
				}
			}
			sb.append(" ");
		}
		System.out.println("Generate plainText: \n"+sb.toString());
		
		return true;
	}

}
