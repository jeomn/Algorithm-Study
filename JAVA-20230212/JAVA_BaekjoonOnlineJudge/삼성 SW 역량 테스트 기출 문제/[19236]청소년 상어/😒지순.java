import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_청소년상어_19236 {
	static int ans;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static class Fish {
		int x, y, dir;
		public Fish(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[4][4];
		Fish[] fishes = new Fish[17];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;

				map[i][j] = num;
				fishes[num] = new Fish(i, j, d);
			}
		}

		ans = map[0][0];
		Fish shark = new Fish(0, 0, fishes[map[0][0]].dir);
		fishes[map[0][0]] = null;
		map[0][0] = -1;

		solve(map, shark, fishes, ans);
		System.out.println(ans);
	}

	private static void solve(int[][] map, Fish shark, Fish[] fishes, int sum) {
		int[][] tMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			tMap[i] = map[i].clone();
		}

		Fish tShark = new Fish(shark.x, shark.y, shark.dir);
		
		Fish[] tFishes = new Fish[17];
		
		for (int i = 1; i < 17; i++) {
			if (fishes[i] == null)
				continue;
			tFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].dir);
		}

		for (int i = 1; i <17; i++) {
			if(tFishes[i] == null) continue;
			Fish now = tFishes[i];
                        
            for(int j=0;j<8;j++) {
                int nx = now.x + dx[now.dir];
                int ny = now.y + dy[now.dir];
                
                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || tMap[nx][ny] == -1) {
                    now.dir = (now.dir + 1) % 8;
                    continue;
                }
                
                if(tMap[nx][ny]==0) {
                    tFishes[i] = new Fish(nx, ny, now.dir);
                    tMap[nx][ny] = i;
                    tMap[now.x][now.y] = 0;
                }
                else { 
                    tFishes[tMap[nx][ny]] = new Fish(now.x, now.y, tFishes[tMap[nx][ny]].dir);
                    tFishes[i] = new Fish(nx, ny, now.dir);
                    int temp = tMap[nx][ny];
                    tMap[nx][ny] = i;
                    tMap[now.x][now.y] = temp;
                }
                break;
            }
        }

		for (int d = 1; d <= 3; d++) {
			int nx = tShark.x + dx[tShark.dir] * d;
			int ny = tShark.y + dy[tShark.dir] * d;

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
				break;
			if (tMap[nx][ny] == 0)
				continue;

			int eatFish = tMap[nx][ny]; 
			Fish fish = tFishes[tMap[nx][ny]]; 

			tFishes[tMap[nx][ny]] = null;
			tShark = new Fish(fish.x, fish.y, fish.dir);
			tMap[nx][ny] = -1;
			tMap[shark.x][shark.y] = 0;

			solve(tMap, tShark, tFishes, sum + eatFish);

			tMap[shark.x][shark.y] = -1;
			tMap[nx][ny] = eatFish;
			tShark = new Fish(shark.x, shark.y, shark.dir);
			tFishes[tMap[nx][ny]] = new Fish(fish.x, fish.y, fish.dir);
		} 
		ans = Math.max(ans, sum);
	}
}
