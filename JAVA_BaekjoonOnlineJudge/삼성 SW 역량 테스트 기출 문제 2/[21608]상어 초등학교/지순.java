import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_21608_상어초등학교 {
	static int N;
	static int[][] map;
	static int[][] friends;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		friends = new int[N * N + 1][4];

		StringTokenizer st = null;
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			friends[student][0] = Integer.parseInt(st.nextToken());
			friends[student][1] = Integer.parseInt(st.nextToken());
			friends[student][2] = Integer.parseInt(st.nextToken());
			friends[student][3] = Integer.parseInt(st.nextToken());
			findSeat(student);
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int student = map[i][j];
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					for (int l = 0; l < friends[student].length; l++) {
						if (map[nx][ny] == friends[student][l])
							cnt++;
					}
				}
				if(cnt!=0)
					answer += Math.pow(10, cnt - 1);
			}
		}
		System.out.println(answer);
	}

	private static void findSeat(int student) {
		int like = -1;
		int zero = -1;
		int x = -1;
		int y = -1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] != 0) {
					continue;
				}
				int likecnt = 0;
				int zerocnt = 0;
				for(int k = 0;k<4;k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if(nx<0 || nx >= N || ny<0 || ny>=N) continue;
					
					for(int l=0;l<friends[student].length;l++) {
						if(friends[student][l] == map[nx][ny]) 
							likecnt++;
					}
					
					if(map[nx][ny] == 0)
						zerocnt++;
					
				}
				
				if(like<likecnt) {
					like = likecnt;
					x = i;
					y = j;
					zero = zerocnt;
				} else if(like == likecnt) {
					if(zero < zerocnt) {
						zero = zerocnt;
						x=i;
						y=j;
					} else if(zero == zerocnt) {
						if(i<x) {
							x = i;
							y = j;
						}
						else if(i == x) {
							if(j<y) {
								y = j;
								x = i;
							}
						}
					}
				}
			}
		}

		map[x][y] = student;
	}
}
