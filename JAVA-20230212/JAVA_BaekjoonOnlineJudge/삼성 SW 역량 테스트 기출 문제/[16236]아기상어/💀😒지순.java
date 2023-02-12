import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_아기상어_16236 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int sharkSize;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int result;

	static class Point implements Comparable<Point> {
		int x, y, time;

		Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if (this.time == o.time) {
				if (this.x == o.x)
					return this.y - o.y;
				else
					return Integer.compare(this.x, o.x);
			} else
				return Integer.compare(this.time, o.time);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st = null;
		Point shark = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Point(i, j, 0);
					map[i][j] = 0;
				}
			}
		}
		result = 0;
		sharkSize = 2;
		solve(shark);
		System.out.println(result);
	}

	private static void solve(Point shark) {
		PriorityQueue<Point> fish = new PriorityQueue<>();
		int cnt = 0;
		while (true) {
			PriorityQueue<Point> queue = new PriorityQueue<>();
			queue.add(shark);
			visited = new boolean[N][N];
			visited[shark.x][shark.y] = true;

			while (!queue.isEmpty()) {
				Point p = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (visited[nx][ny])
						continue;

					if (map[nx][ny] < sharkSize && map[nx][ny] > 0) {
						visited[nx][ny] = true;
						fish.add(new Point(nx, ny, p.time + 1));
					} else if(map[nx][ny]==0 || map[nx][ny]==sharkSize){
						visited[nx][ny] = true;
						queue.add(new Point(nx, ny, p.time + 1));
					}
				}
			}

			if (fish.size() == 0)
				break;
			else {
				shark = fish.poll();
				shark.time = 0;
				map[shark.x][shark.y] = 0;
				result += shark.time;

				cnt++;
				if (cnt == sharkSize) {
					sharkSize++;
					cnt = 0;
				}

				fish.clear();
			}
		}
	}
}
