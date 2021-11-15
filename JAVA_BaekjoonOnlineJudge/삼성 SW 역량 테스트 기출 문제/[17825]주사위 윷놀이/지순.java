import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_주사위윷놀이_17825 {
	static int[] nums;
	static int[] horse;
	static int answer;
	static int map[][] = { 
			{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 10, 13, 16, 19, 25, 30, 35, 40 }, 
			{ 20, 22, 24, 25, 30, 35, 40 }, 
			{ 30, 28, 27, 26, 25, 30, 35, 40 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		nums = new int[10];
		horse = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		answer = 0;
		perm(0);
		System.out.println(answer);
	}

	private static void perm(int cnt) {
		if (cnt == 10) {
			int result = solve();
			answer = Math.max(answer, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			horse[cnt] = i;
			perm(cnt + 1);
		}
	}

	private static int solve() {
		int[][] moveInfo = new int[4][2];
		int result = 0;
		for (int i = 0; i < 10; i++) {
			int cur = horse[i];
			int move = nums[i];

			if (moveInfo[cur][1] == -1)
				return 0;

//			System.out.println(moveInfo[cur][0]);
			if (moveInfo[cur][0] == 0) {
				int val = moveInfo[cur][1];
				if (map[0][val] % 10 == 0 && map[0][val] / 10 > 0 && map[0][val] / 10 < 4) {
					moveInfo[cur][0] = map[0][val] / 10;
					moveInfo[cur][1] = 0;
				}
			}

			moveInfo[cur][1] += move;

			if (moveInfo[cur][1] < map[moveInfo[cur][0]].length) {
				for (int j = 0; j < 4; j++) {
					if (cur == j || moveInfo[j][1] == -1)
						continue;

					// 같은 맵, 같은 위치
					if (moveInfo[cur][0] == moveInfo[j][0]) {
						if(moveInfo[cur][1] == moveInfo[j][1])
							return 0;
					} else {
						if(isRange(moveInfo[cur][0], moveInfo[cur][1]) && isRange(moveInfo[j][0], moveInfo[j][1])) {
							if(map[moveInfo[cur][0]][moveInfo[cur][1]] == map[moveInfo[j][0]][moveInfo[j][1]])
								return 0;
						}
					}
				}
				result += map[moveInfo[cur][0]][moveInfo[cur][1]];
			} else {
				moveInfo[cur][1] = -1;
			}
		}
		return result;
	}
	
	private static boolean isRange(int line, int idx) {
		int num = map[line][idx];
		if(num==25||(num==30&&line!=0)||num==35||num==40) 
			return true;
		return false;
	}
}
