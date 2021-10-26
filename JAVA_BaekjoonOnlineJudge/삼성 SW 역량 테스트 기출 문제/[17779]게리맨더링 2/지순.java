import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_게리맨더링2_17779 {
	static int N;
	static int[][] map;
	static int total;
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		StringTokenizer st;
		total = 0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total+=map[i][j];
			}
		}
		
		result = Integer.MAX_VALUE;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				for(int d1 = 1;d1<=N;d1++) {
					for(int d2 = 1;d2<=N;d2++) {
						if(i+d1+d2>N) continue;
						if(j-d1<1 || j-d1>=j || j+d2>N || j+d2<=j)
							continue;
						
						solve(i, j, d1, d2);
					}
				}
			}
		}
		System.out.println(result);
	}
	private static void solve(int x, int y, int d1, int d2) {
		boolean[][] check = new boolean[N+1][N+1];
		
		for(int i=0;i<=d1;i++) {
			check[x+i][y-i] = true;
			check[x+d2+i][y+d2-i] = true;
		}
		for(int i=0;i<=d2;i++) {
			check[x+i][y+i] = true;
			check[x+d1+i][y-d1+i] = true;
		}
		
		int[] cnt = new int[5];
		// 1번 선거구
		for(int i=1;i<x+d1;i++) {
			for(int j=1;j<=y;j++) {
				if(check[i][j]) break;
				cnt[0] += map[i][j];
			}
		}
		
		// 2번 선거구
		for(int i=1;i<=x+d2;i++) {
			for(int j=N;j>y;j--) {
				if(check[i][j]) break;
				cnt[1] += map[i][j];
			}
		}
		
		// 3번 선거구
		for(int i=x+d1;i<=N;i++) {
			for(int j=1;j<y-d1+d2;j++) {
				if(check[i][j]) break;
				cnt[2] += map[i][j];
			}
		}
		
		// 4번 선거구
		for(int i=x+d2+1;i<=N;i++) {
			for(int j=N;j>=y-d1+d2;j--) {
				if(check[i][j]) break;
				cnt[3] += map[i][j];
			}
		}
		
		cnt[4] = total;
		for(int i=0;i<4;i++) {
			cnt[4] -= cnt[i];
		}
		Arrays.sort(cnt);
		int cntMin = cnt[4]-cnt[0];
		
		result = Math.min(result, cntMin);
	}
}
