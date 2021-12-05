import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_상어중학교_21609 {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<Point> blockGroup;
	static boolean[][] visited;
	static int result = 0;

	static class Point implements Comparable<Point> {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (findBlock()) {
			removeBlock();
			gravity();
			rotate();
			gravity();
		}
		System.out.println(result);
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void rotate() {
		int[][] copy = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[N - j - 1][i] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			map[i] = copy[i].clone();
		}
	}

	private static void gravity() {
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 0) {
					int temp = i + 1;
					while (temp < N && map[temp][j] == -2) {
						temp++;
					}
					map[--temp][j] = map[i][j];
					if (temp != i)
						map[i][j] = -2;
				}
			}
		}
	}

	private static void removeBlock() {
		for (Point p : blockGroup) {
			map[p.x][p.y] = -2;
		}
		result += blockGroup.size() * blockGroup.size();
	}

	private static boolean findBlock() {
		blockGroup = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] <= M && !visited[i][j]) {
					ArrayList<Point> tmpBlock = new ArrayList<>();
					dfs(i, j, map[i][j], tmpBlock);

					if (tmpBlock.size() > blockGroup.size()) {
						blockGroup = tmpBlock;
					} else if (tmpBlock.size() == blockGroup.size()) {
						int r1 = rainbowBlock(blockGroup);
						int r2 = rainbowBlock(tmpBlock);

						if (r1 < r2) {
							blockGroup = tmpBlock;
						} else if (r1 == r2) {
							Collections.sort(blockGroup);
							Collections.sort(tmpBlock);

							Point p1 = null;
							Point p2 = null;
							for (int s = 0; s < blockGroup.size(); s++) {
								if (map[blockGroup.get(s).x][blockGroup.get(s).y] != 0) {
									p1 = blockGroup.get(s);
									break;
								}
							}

							for (int s = 0; s < tmpBlock.size(); s++) {
								if (map[tmpBlock.get(s).x][tmpBlock.get(s).y] != 0) {
									p2 = tmpBlock.get(s);
									break;
								}
							}

							if (p1.x < p2.x) {
								blockGroup = tmpBlock;
							} else if (p1.x == p2.x) {
								if (p1.y < p2.y) {
									blockGroup = tmpBlock;
								}
							}

						}
					}
					visited = new boolean[N][N];
				}
			}
		}
		if (blockGroup.size() > 1) {
			return true;
		} else {
			return false;
		}
	}

	private static int rainbowBlock(ArrayList<Point> block) {
		int cnt = 0;
		for (Point p : block) {
			if (map[p.x][p.y] == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	private static void dfs(int x, int y, int color, ArrayList<Point> tmpBlock) {
		visited[x][y] = true;
		tmpBlock.add(new Point(x, y));

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (visited[nx][ny])
				continue;

			if (map[nx][ny] == 0 || map[nx][ny] == color) {
				dfs(nx, ny, color, tmpBlock);
			}
		}
	}
}
