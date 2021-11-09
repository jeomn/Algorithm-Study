import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2048_12100 {
	static int N;
	static int[][] map;
	static int[] select;
	static int ans;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		select = new int[5];
		ans = 0;
		comb(0);
		System.out.println(ans);
	}

	private static void comb(int cnt) {
		if(cnt == 5) {
			move();
			return;
		}
		
		for(int i=0;i<4;i++) {
			select[cnt] = i;
			comb(cnt+1);
		}
	}
	
	private static void move() {
		int[][] temp = new int[N][N];
		for(int i=0;i<N;i++)
			temp[i] = map[i].clone();
		
		for(int i=0;i<select.length;i++) {
			int dir = select[i]; // 0: 상, 1: 우, 2:하, 3:좌
			
			if(dir == 0) {
				for(int c=0;c<N;c++) {
					int idx = 0;
					int num = 0;
					for(int r = 0;r<N;r++) {
						if(temp[r][c] != 0) {
							if(num == temp[r][c]) {
								temp[idx-1][c] = num*2;
								num = 0;
								temp[r][c] = 0;
							}
							else {
								num = temp[r][c];
								temp[r][c] = 0;
								temp[idx][c] = num;
								idx++;
							}
						}
					}
				}
			} else if(dir ==1) {
				for(int r=0;r<N;r++) {
					int idx = N-1;
					int num = 0;
					for(int c=N-1;c>=0;c--) {
						if(temp[r][c] != 0) {
							if(num == temp[r][c]) {
								temp[r][idx+1] = num * 2;
								num = 0;
								temp[r][c] = 0;
							}
							else {
								num = temp[r][c];
								temp[r][c] = 0;
								temp[r][idx] = num;
								idx--;
							}
						}
					}
				}
			} else if(dir == 2) {
				for(int c=0;c<N;c++) {
					int idx = N-1;
					int num = 0;
					for(int r = N-1;r>=0;r--) {
						if(temp[r][c] != 0) {
							if(num == temp[r][c]) {
								temp[idx+1][c] = num*2;
								num = 0;
								temp[r][c] = 0;
							}
							else {
								num = temp[r][c];
								temp[r][c] = 0;
								temp[idx][c] = num;
								idx--;
							}
						}
					}
				}
			} else {
				for(int r=0;r<N;r++) {
					int idx = 0;
					int num = 0;
					for(int c=0;c<N;c++) {
						if(temp[r][c] != 0) {
							if(num == temp[r][c]) {
								temp[r][idx-1] = num * 2;
								num = 0;
								temp[r][c] = 0;
							}
							else {
								num = temp[r][c];
								temp[r][c] = 0;
								temp[r][idx] = num;
								idx++;
							}
						}
					}
				}
			}
			
			int max = 0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					max = Math.max(max, temp[r][c]);
				}
			}
			ans = Math.max(ans, max);
		}
		
	}
}
