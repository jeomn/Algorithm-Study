import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int[][] gear;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gear = new int[4][8];
		for(int i=0; i<4; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j=0; j<8; j++) {
				gear[i][j] = temp[j]-'0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken());
			
			rotateLeft(gearNum, -direction);
			rotateRight(gearNum, -direction);
			rotate(gearNum, direction);
		}
		
		int score = 0;
		for(int i=0; i<4; i++) {
			if(gear[i][0] == 1) score+=Math.pow(2, i);
		}
		
		System.out.println(score);
	}
	
	private static void rotateLeft(int gearNum, int direction) {
		if(gearNum == 0) return;
		if(gear[gearNum-1][2] != gear[gearNum][6]) {
			rotateLeft(gearNum-1, -direction);
			rotate(gearNum-1, direction);
		}else return;
	}

	private static void rotateRight(int gearNum, int direction) {
		if(gearNum == 3) return;
		if(gear[gearNum][2] != gear[gearNum+1][6]) {
			rotateRight(gearNum+1, -direction);
			rotate(gearNum+1, direction);
		}else return;
		
	}
	
	private static void rotate(int gearNum, int direction) {
		if(direction == 1) {
			int temp = gear[gearNum][7];
			for(int i=7; i>0; i--) {
				gear[gearNum][i] = gear[gearNum][i-1];
			}
			gear[gearNum][0] = temp;
		}else {
			int temp = gear[gearNum][0];
			for(int i=0; i<7; i++) {
				gear[gearNum][i] = gear[gearNum][i+1];
			}
			gear[gearNum][7] = temp;
		}
	}
}
