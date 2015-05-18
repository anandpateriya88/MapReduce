package testing;

import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;

public class CharArray {

	public static void main(String str[]){
		String line =" tic tac toe is played by cat and cit lap and pal are anagrams of each other.";
		StringTokenizer strTkns = new StringTokenizer(line, " ");
		while(strTkns.hasMoreTokens()){
			String word = strTkns.nextToken();
			char[] anagramKey = word.toCharArray();
			Arrays.sort(anagramKey);
			String st = new String(anagramKey);
			System.out.println(st);	
}
	}
}

