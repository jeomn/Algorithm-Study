import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_스타트와링크_14889 {
	static int N;
	static int[][] S;
	static boolean[] visit;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		visit = new boolean[N];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		System.out.println(result);
	}

	private static void comb(int idx, int cnt) {
		if (cnt == N / 2) {
			int true_sum = 0;
			int false_sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i; j < N; j++) {
					if (visit[i] && visit[j]) {
						true_sum += S[i][j];
						true_sum += S[j][i];
					}
					if (!visit[i] && !visit[j]) {
						false_sum += S[i][j];
						false_sum += S[j][i];
					}
				}
			}
			result = Math.min(result, Math.abs(true_sum - false_sum));
			return;
		}
		for (int i = idx; i < N; i++) {
			visit[i] = true;
			comb(i + 1, cnt + 1);
			visit[i] = false;
		}
	}
}
