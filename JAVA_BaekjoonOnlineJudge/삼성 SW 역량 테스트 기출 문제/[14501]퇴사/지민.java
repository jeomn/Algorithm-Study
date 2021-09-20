import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		for(int i=N-1; i>=0; i--) {
			int day = i+arr[i][0];
			if(i == N-1) {
				if(arr[i][0] == 1) {
					dp[i] = arr[i][1];
				}
			}else if(day == N) {
				dp[i] = Math.max(arr[i][1], dp[i+1]);
			}else if(day<N) {
				dp[i] = Math.max(arr[i][1]+dp[day], dp[i+1]);
			}else {
				dp[i] = dp[i+1];
			}
		}
		
		System.out.println(Arrays.stream(dp).max().getAsInt());

	}
}
