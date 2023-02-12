import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] gears;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		gears = new int[4][8];
		for(int i=0; i<4; i++) {
			String temp = br.readLine();
			for(int j=0; j<8; j++) {
				gears[i][j] = temp.charAt(j)-'0';
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int dirc = Integer.parseInt(st.nextToken());
			
			rotateLeft(idx, -dirc);
			rotateRight(idx, -dirc);
			rotate(idx, dirc);
		}
		
		int score = 0;
		for(int i=0; i<4; i++) {
			if(gears[i][0]==1) score+=Math.pow(2, i);
		}
		System.out.println(score);
	}


	private static void rotateLeft(int idx, int d) {
		if(idx==0) return;
		if(gears[idx][6]!=gears[idx-1][2]) {
			rotateLeft(idx-1, -d);
			rotate(idx-1, d);
		}else return;	
	}


	private static void rotateRight(int idx, int d) {
		if(idx==3) return;
		if(gears[idx][2]!=gears[idx+1][6]) {
			rotateRight(idx+1, -d);
			rotate(idx+1, d);
		}else return;
	}


	private static void rotate(int idx, int d) {
		if(d==-1) {
			int temp = gears[idx][0];
			for(int i=0; i<7; i++) {
				gears[idx][i] = gears[idx][i+1];
			}
			gears[idx][7] = temp;
		}else {
			int temp = gears[idx][7];
			for(int i=7; i>0; i--) {
				gears[idx][i] = gears[idx][i-1];
			}
			gears[idx][0] = temp;
		}
	}
}
