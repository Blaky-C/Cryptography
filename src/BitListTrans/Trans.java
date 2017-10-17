package BitListTrans;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Trans {

	public static void main(String[] args) {
		//DecimalFormat df = new DecimalFormat("00000000");
		String s = "midnight";
		char c = 'm';
		//System.out.println(df.format(Integer.toBinaryString(c).toString()));
		//System.out.printf("%08o\n",97);
		
		//假设必须15位长
        int fullLength = 15;
        //假设输入123456
        String input = new String(Integer.toBinaryString(c).toString());
        //预先定义一个15位0
        StringBuilder zero = new StringBuilder("00000000");
         
        String result = zero.substring(0, zero.length() - input.length()) + input;
         
        System.out.println(fixedFormat(Integer.toBinaryString(c).toString()));
			
	}
	
	public static String fixedFormat(String input) {
		StringBuilder inputBuilder = new StringBuilder(input);
		StringBuilder zero = new StringBuilder("00000000");
		
		String result = zero.substring(0, zero.length()-input.length())+input;
		return result;
	}

}
