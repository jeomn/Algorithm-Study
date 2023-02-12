import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_연산자끼워넣기_14888 {
	static int N;
	static int[] nums;
	static int[] oper;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		oper = new int[4];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(str[i]);
		}
		String[] oper_str = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(oper_str[i]);
		}

		dfs(nums[0], 1);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int num, int cnt) {
		if (cnt == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (oper[i] > 0) {
				oper[i]--;

				if (i == 0) {
					dfs(num + nums[cnt], cnt+1);
				} else if (i == 1) {
					dfs(num - nums[cnt], cnt+1);
				} else if (i == 2) {
					dfs(num * nums[cnt], cnt+1);
				} else if (i == 3) {
					dfs(num / nums[cnt], cnt+1);
				}
				
				oper[i]++;
			}
		}
	}
}
