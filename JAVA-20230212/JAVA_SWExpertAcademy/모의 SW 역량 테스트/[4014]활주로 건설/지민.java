import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	
	static int N, X;
	static int[][] map;
	static boolean[][] isRamp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt=0;
			for(int i=0; i<N; i++) {
				if(countWay(i, -1)) cnt++;
				if(countWay(-1, i)) cnt++;
			}
			
			System.out.println("#"+test+" "+cnt);
		}
	}

	private static boolean countWay(int r, int c) {
		isRamp = new boolean[N][N];
		if(r==-1) {
			for(int x=0; x<N-1; x++) {
				int n = map[x][c];
				int next = map[x+1][c];
				if(n==next) continue;
				if(Math.abs(n-next) >= 2) return false;
				if(n > next) {
					for(int i=1; i<=X; i++) {
						int nx = x+i;
						if(nx >= N || map[nx][c] != next || isRamp[nx][c]) return false;
						isRamp[nx][c] = true;
					}
				}else if(n < next) {
					for(int i=1; i<=X; i++) {
						int nx = x+1-i;
						if(nx < 0 || map[nx][c] != n || isRamp[nx][c]) return false;
						isRamp[nx][c] = true;
					}
				}				
			}			
		}else if(c==-1){
			for(int y=0; y<N-1; y++) {
				int n = map[r][y];
				int next = map[r][y+1];
				if(n==next) continue;
				if(Math.abs(n-next) >= 2) return false;
				if(n > next) {
					for(int i=1; i<=X; i++) {
						int ny = y+i;
						if(ny >= N || map[r][ny] != next || isRamp[r][ny]) return false;
						isRamp[r][ny] = true;;
					}
				}else if(n < next) {
					for(int i=1; i<=X; i++) {
						int ny = y+1-i;
						if(ny < 0 || map[r][ny] != n || isRamp[r][ny]) return false;
						isRamp[r][ny] = true;
					}
				}		
			}
		}
		return true;
	}
}
