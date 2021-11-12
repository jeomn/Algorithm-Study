import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_새로운게임2_17837 {
	private static int N, K;
	private static int[][] colorMap;
	private static ArrayList<Integer>[][] map;
	private static Horse[] horse;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { 1, -1, 0, 0 };

	private static class Horse {
		int x, y, d;

		Horse(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		colorMap = new int[N + 1][N + 1];
		horse = new Horse[K + 1];
		map = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				colorMap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			horse[i] = new Horse(x, y, d);
			map[x][y].add(i);
		}

		System.out.println(solve());
	}

	private static int solve() {
		int cnt = 0;

		while (true) {
			if (cnt > 1000)
				return -1;
			cnt++;

			for (int i = 1; i <= K; i++) {
				Horse m = horse[i];

				int nx = m.x + dx[m.d];
				int ny = m.y + dy[m.d];

				if (nx < 1 || nx > N || ny < 1 || ny > N || colorMap[nx][ny] == 2) {
					int d = changeDir(m.d);
					nx = m.x + dx[d];
					ny = m.y + dy[d];

					m.d = d;
				}

				if (1 <= nx && nx <= N && 1 <= ny && ny <= N) {
					if (colorMap[nx][ny] == 0) {
						List<Integer> tmp = new ArrayList<>();
						boolean flag = false;

						for (int n : map[m.x][m.y]) {
							if (n == i)
								flag = true;

							if (flag) {
								map[nx][ny].add(n);
								tmp.add(n);
							}
						}

						for (int n : tmp) {
							map[horse[n].x][horse[n].y].remove((Integer) n);
							horse[n].x = nx;
							horse[n].y = ny;
						}
					} else if (colorMap[nx][ny] == 1) {
						List<Integer> tmp = new ArrayList<>();

						for (int j = map[m.x][m.y].size() - 1; j >= 0; j--) {
							int n = map[m.x][m.y].get(j);

							map[nx][ny].add(n);
							tmp.add(n);

							if (n == i)
								break;
						}

						for (int n : tmp) {
							map[horse[n].x][horse[n].y].remove((Integer) n);
							horse[n].x = nx;
							horse[n].y = ny;
						}
					}
				}

				for (int k = 1; k <= K; k++) {
					if (map[horse[k].x][horse[k].y].size() >= 4)
						return cnt;
				}
			}
		}

	}

	private static int changeDir(int d) {
		switch (d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		default:
			return 2;
		}
	}
}
