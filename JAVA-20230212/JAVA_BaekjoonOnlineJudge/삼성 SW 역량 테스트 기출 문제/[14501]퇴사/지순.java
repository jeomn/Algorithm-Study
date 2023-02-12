import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_퇴사_14501 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for(int i=0;i<N;i++) {
			String[] str = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(str[0]);
			arr[i][1] = Integer.parseInt(str[1]);
		}
		int[] ans = new int[N+1];
		for(int i=0;i<N;i++) {
			if(i+arr[i][0] <=N) {
				ans[i+arr[i][0]] = Math.max(ans[i+arr[i][0]], ans[i]+arr[i][1]);
			}
			ans[i+1] = Math.max(ans[i], ans[i+1]);
		}
		System.out.println(ans[N]);
	}

}
