/**************************
* 20211014
* 재귀 넣어줄 때 변수^^!
* backtracking 조건문^^!!
**************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, H, minCnt = 4;
	static boolean[][] ladder;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new boolean[H][N+1];
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			ladder[r][c] = true;
		}
		
		setLadders(0, 0, 1);
		minCnt = (minCnt < 4)? minCnt:-1;
		System.out.println(minCnt);
		
	}

	private static void setLadders(int cnt, int x, int y) {
		if(minCnt <= cnt || cnt > 3) return;
		if(checkLadder()) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		for(int r=x; r<H; r++) {
			int temp = (r==x)? y : 1;
			for(int c=temp; c<N; c++) {
				if(ladder[r][c-1] || ladder[r][c] || ladder[r][c+1]) continue;
				ladder[r][c] = true;
				setLadders(cnt+1, r, c+2);
				ladder[r][c] = false;
			}
		}
	}

	private static boolean checkLadder() {
		for(int i=1; i<N+1; i++) {
			int c = i;
			for(int r=0; r<H; r++) {
				if(ladder[r][c]) c+=1;
				else if(ladder[r][c-1]) c-=1;
			}
			if(c!=i) return false;
		}
		return true;
	}
}
