import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_미세먼지안녕_17144 {
	static int[][] map;
	static Point[] airCleaner;
	static int R;
	static int C;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		airCleaner = new Point[2];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == -1) {
					airCleaner[idx++] = new Point(i, j);
				}
			}
		}

		for (int t = 0; t < T; t++) {
			spreadDust();
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			turnOnAirCleaner();
//			System.out.println();
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
		}
		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

	private static void spreadDust() {
		int[][] temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[i][j] += map[i][j];
				int val = map[i][j] / 5;

				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					if (map[nx][ny] == -1)
						continue;
					temp[i][j] -= val;
					temp[nx][ny] += val;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			map[i] = temp[i].clone();
		}
	}

	private static void turnOnAirCleaner() {
		int[][] temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			temp[i] = map[i].clone();
		}
		int x1 = airCleaner[0].x;
		int y1 = airCleaner[0].y + 1; // 공기청정기 옆칸부터 시작

		int x2 = airCleaner[1].x;
		int y2 = airCleaner[1].y + 1;

		temp[x1][y1] = 0;
		temp[x2][y2] = 0;

		while (y1<C-1 && y2<C-1) { // 왼->오
			temp[x1][y1 + 1] = map[x1][y1];
			temp[x2][y2 + 1] = map[x2][y2];
			y1++;
			y2++;
		}
		while (x1 > 0) { // 아래에서 위로
			temp[x1 - 1][y1] = map[x1][y1];
			x1--;
		}
		while (x2 < R - 1) { // 위에서 아래로
			temp[x2 + 1][y2] = map[x2][y2];
			x2++;
		}
		while (y1>0 && y2>0) { // 오 -> 왼
			temp[x1][y1 - 1] = map[x1][y1];
			temp[x2][y2 - 1] = map[x2][y2];
			y1--;
			y2--;
		}
		while (x1 < airCleaner[0].x - 1) { // 위-> 아래
			temp[x1 + 1][y1] = map[x1][y1];
			x1++;
		}
		while (x2 > airCleaner[1].x + 1) { // 아래-> 위
			temp[x2 - 1][y2] = map[x2][y2];
			x2--;
		}
		for (int i = 0; i < R; i++) {
			map[i] = temp[i].clone();
		}
	}
}
