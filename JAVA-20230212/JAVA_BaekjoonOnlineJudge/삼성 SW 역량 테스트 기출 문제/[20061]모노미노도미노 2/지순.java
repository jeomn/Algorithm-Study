import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_모노미노도미노2_20061 {
	static boolean[][] blue = new boolean[4][6];
	static boolean[][] green = new boolean[6][4];
	static int score = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			moveBlue(t, x, y);
			moveGreen(t, x, y);

			removeBlue();
			removeGreen();

			lightBlue();
			lightGreen();
		}

		System.out.println(score);
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (blue[i][j])
					cnt += 1;
				if (green[j][i])
					cnt += 1;
			}
		}
		System.out.println(cnt);
	}

	private static void lightBlue() {
		int cnt = 0;
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				if (blue[i][j]) {
					cnt += 1;
					break;
				}
			}
		}
		if (cnt != 0) {
			for (int k = 5; k >= 2; k--) {
				for (int i = 0; i < 4; i++) {
					blue[i][k] = blue[i][k - cnt];
				}
			}

			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < 4; i++) {
					blue[i][j] = false;
				}
			}
		}
	}

	private static void lightGreen() {
		int cnt = 0;
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				if (green[j][i]) {
					cnt += 1;
					break;
				}
			}
		}
		if (cnt != 0) {
			for (int k = 5; k >= 2; k--) {
				for (int i = 0; i < 4; i++) {
					green[k][i] = green[k - cnt][i];
				}
			}

			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < 4; i++) {
					green[j][i] = false;
				}
			}
		}
	}

	private static void removeBlue() {
		while (true) {
			boolean flag = true;
			for (int i = 5; i >= 2; i--) {
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					if (blue[j][i])
						cnt++;
				}

				if (cnt == 4) {
					score++;
					flag = false;
					for (int j = 0; j < 4; j++) {
						blue[j][i] = false;
					}
					for (int k = i - 1; k >= 0; k--) {
						for (int j = 0; j < 4; j++) {
							blue[j][k + 1] = blue[j][k];
							blue[j][k] = false;
						}
					}
					break;
				}
			}
			if (flag)
				break;
		}
	}

	private static void removeGreen() {
		while (true) {
			boolean flag = true;
			for (int i = 5; i >= 2; i--) {
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					if (green[i][j])
						cnt++;
				}

				if (cnt == 4) {
					score++;
					flag = false;
					for (int j = 0; j < 4; j++) {
						green[i][j] = false;
					}
					for (int k = i - 1; k >= 0; k--) {
						for (int j = 0; j < 4; j++) {
							green[k + 1][j] = green[k][j];
							green[k][j] = false;
						}
					}
					break;
				}
			}

			if (flag)
				break;
		}
	}

	private static void moveBlue(int t, int x, int y) {
		if (t == 1) {
			int max = 0;
			for (int i = 0; i < 6; i++) {
				if (!blue[x][i]) {
					max = i;
				} else {
					break;
				}
			}
			blue[x][max] = true;
		} else if (t == 2) {
			int max = 0;
			for (int i = 0; i < 5; i++) {
				if (!blue[x][i] && !blue[x][i + 1]) {
					max = i;
				} else {
					break;
				}
			}
			blue[x][max] = true;
			blue[x][max + 1] = true;
		} else {
			int max = 0;
			for (int i = 0; i < 6; i++) {
				if (!blue[x][i] && !blue[x + 1][i]) {
					max = i;
				} else {
					break;
				}
			}
			blue[x][max] = true;
			blue[x + 1][max] = true;
		}
	}

	private static void moveGreen(int t, int x, int y) {
		if (t == 1) {
			int max = 0;
			for (int i = 0; i < 6; i++) {
				if (!green[i][y]) {
					max = i;
				} else {
					break;
				}
			}
			green[max][y] = true;
		} else if (t == 2) {
			int max = 0;
			for (int i = 0; i < 6; i++) {
				if (!green[i][y] && !green[i][y + 1]) {
					max = i;
				} else {
					break;
				}
			}
			green[max][y] = true;
			green[max][y + 1] = true;
		} else {
			int max = 0;
			for (int i = 0; i < 5; i++) {
				if (!green[i][y] && !green[i + 1][y]) {
					max = i;
				} else {
					break;
				}
			}
			green[max][y] = true;
			green[max + 1][y] = true;
		}
	}
}
