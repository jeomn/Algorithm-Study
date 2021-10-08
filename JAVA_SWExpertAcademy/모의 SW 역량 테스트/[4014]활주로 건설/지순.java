import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_활주로건설_4014 {
	static int N;
	static int X;
	static int[][] map;
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			row();
			column();
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}
	private static void row() {
		for(int i=0;i<N;i++) { // 행 검사
			int height = map[i][0];
			int cnt = 1;
			boolean check = false;
			for(int j=1;j<N;j++) {
				if(height - map[i][j] == 0) {
					cnt++;
				} else if(height - map[i][j] == -1) {
					if(cnt >= X) {
						cnt = 1;
						height = map[i][j];
					} else {
						check = true;
						break;
					}
				} else if(height - map[i][j] == 1) {
					if(cnt+X > N || j+X > N) {
						check = true;
						break;
					} else {
						int forcnt = 1;
						for(int k=j+1;k<j+X;k++) {
							if(map[i][j] == map[i][k])
								forcnt++;
						}
						if(forcnt >= X) {
							cnt = 0;
							height = map[i][j];
							j += forcnt-1;
						} else {
							check = true;
							break;
						}
					}
				} else {
					check = true;
					break;
				}
			}
			if(!check) {
				result++;
			}
		}
	}
	
	private static void column() {
		for(int j=0;j<N;j++) { // 행 검사
			int height = map[0][j];
			int cnt = 1;
			boolean check = false;
			for(int i=1;i<N;i++) {
				if(height - map[i][j] == 0) {
					cnt++;
				} else if(height - map[i][j] == -1) {
					if(cnt >= X) {
						cnt = 1;
						height = map[i][j];
					} else {
						check = true;
						break;
					}
				} else if(height - map[i][j] == 1) {
					if(cnt+X > N || i+X > N) {
						check = true;
						break;
					} else {
						int forcnt = 1;
						for(int k=i+1;k<i+X;k++) {
							if(map[i][j] == map[k][j])
								forcnt++;
						}
						if(forcnt >= X) {
							cnt = 0;
							height = map[i][j];
							i += forcnt-1;
						} else {
							check = true;
							break;
						}
					}
				} else {
					check = true;
					break;
				}
			}
			if(!check) {
				result++;
			}
		}
	}
}
