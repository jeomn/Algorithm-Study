import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_경사로_14890 {
	static int N, L;
	static int[][] map;
	static int[][] map2;
	static int result;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		map2 = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				map2[j][i] = num;
			}
		}
		result = 0;
		solve(map);
		solve(map2);
		System.out.println(result);
	}
	
	private static void solve(int[][] temp) {
		for(int i=0;i<N;i++) {
			int height = temp[i][0];
			int cnt = 1;
			boolean check = false;
			for(int j=1;j<N;j++) {
				if(Math.abs(temp[i][j]-height)>1) {
					check = true;
					continue;
				}
				if(temp[i][j] == height) {
					cnt++;
				} else {
					if(temp[i][j] < height) { // 내리막
						if(j+L>N || cnt+L>N) {
							check = true;
							break;
						} else {
							int count = 1;
							for(int k=j+1;k<j+L;k++) {
								if(temp[i][j] == temp[i][k]) 
									count++;
							}
							
							if(count>=L) {
								cnt = 0;
								height = temp[i][j];
								j += count-1;
							} else {
								check = true;
								break;
							}
						}
					} else { // 오르막
						if(cnt >= L) {
							cnt = 1;
							height = temp[i][j];
						} else {
							check = true;
							break;
						}
					}
				}
			}
			if(!check) {
				result++;
			}
		}
	}
}
