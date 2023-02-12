import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] score = new int[10];
		int minDiffer = Integer.MAX_VALUE, value = 0;
		score[0] = Integer.parseInt(br.readLine());
		for(int i=1; i<10; i++) {
			score[i] = score[i-1]+Integer.parseInt(br.readLine());
			if(Math.abs(100-score[i-1]) < score[i]-100) {
				value = score[i-1];
				break;
			}else {
				value = score[i];
			}
		}
		System.out.println(value);
	}
}
