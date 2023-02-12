import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, L;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			if(countAvailableRoadRow(i)) answer++;
			if(countAvailableRoadColumn(i)) answer++;
		}
		
		System.out.println(answer);
	}
	
	private static boolean countAvailableRoadRow(int n) {
		boolean[] isRamp = new boolean[N];
		for(int c=0; c<N-1; c++) {
			int cur = map[n][c];
			int next = map[n][c+1];
			if(Math.abs(cur-next) >= 2) return false;
			if(cur == next) continue;
			else if(cur > next) {
				for(int i=1; i<=L; i++) {
					int nc = c+i;
					if(nc>=N || map[n][nc] != next || isRamp[nc]) return false;
					isRamp[nc] = true;
				}
			}else {
				for(int i=0; i<L; i++) {
					int nc = c-i;
					if(nc<0 || map[n][nc]!=cur || isRamp[nc]) return false;
					isRamp[nc] = true;
				}
			}
		}
		return true;
	}
	
	private static boolean countAvailableRoadColumn(int n) {
		boolean[] isRamp = new boolean[N];
		for(int r=0; r<N-1; r++) {
			int cur = map[r][n];
			int next = map[r+1][n];
			if(Math.abs(cur-next) >= 2) return false;
			if(cur == next) continue;
			else if(cur > next) {
				for(int i=1; i<=L; i++) {
					int nr = r+i;
					if(nr>=N || map[nr][n] != next || isRamp[nr]) return false;
					isRamp[nr] = true;
				}
			}else {
				for(int i=0; i<L; i++) {
					int nr = r-i;
					if(nr<0 || map[nr][n]!=cur || isRamp[nr]) return false;
					isRamp[nr] = true;
				}
			}
		}
		return true;
	}
}
