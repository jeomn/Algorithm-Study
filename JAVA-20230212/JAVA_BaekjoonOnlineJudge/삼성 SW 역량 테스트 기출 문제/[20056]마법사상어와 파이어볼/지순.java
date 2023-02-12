import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_마법사상어와파이어볼_20056 {
	static int N;
	static int M;
	static int K;
	static ArrayList<FireBall>[][] map;
	static ArrayList<FireBall> list;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	private static class FireBall {
		int r;
		int c;
		int m;
		int s;
		int d;

		FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}

		for (int i = 0; i < K; i++) {
			move();
			check();
		}
		int sum = 0;
		for(FireBall fb : list) {
			sum+=fb.m;
		}
		System.out.println(sum);
	}

	private static void move() {
		for (FireBall fb : list) {
			int nx = (fb.r+N+dx[fb.d]*(fb.s%N))%N;
			int ny = (fb.c+N+dy[fb.d]*(fb.s%N))%N;
			fb.r = nx;
			fb.c = ny;
			map[nx][ny].add(fb);
		}
	}
	private static void check() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int mapSize = map[i][j].size();
				if(mapSize<2) {
					map[i][j].clear();
					continue;
				}
				
				int mSum = 0;
				int sSum = 0;
				int dSum = 0;
				for (FireBall fb : map[i][j]) {
					mSum+=fb.m;
					sSum+=fb.s;
					dSum += fb.d%2;
					list.remove(fb);
				}
        
				mSum/=5;
				sSum/=mapSize;
				map[i][j].clear();
        
				if(mSum == 0)
					continue;
				if(dSum==0 || dSum == mapSize) {
					list.add(new FireBall(i, j, mSum, sSum, 0));
					list.add(new FireBall(i, j, mSum, sSum, 2));
					list.add(new FireBall(i, j, mSum, sSum, 4));
					list.add(new FireBall(i, j, mSum, sSum, 6));
				} else {
					list.add(new FireBall(i, j, mSum, sSum, 1));
					list.add(new FireBall(i, j, mSum, sSum, 3));
					list.add(new FireBall(i, j, mSum, sSum, 5));
					list.add(new FireBall(i, j, mSum, sSum, 7));
				}
			}
		}
	}
}
