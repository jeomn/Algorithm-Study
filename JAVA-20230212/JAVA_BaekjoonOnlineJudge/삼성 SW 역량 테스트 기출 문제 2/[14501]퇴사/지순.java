import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14501_퇴사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] period = new int[N][2];
		
		StringTokenizer st = null;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			period[i][0] = Integer.parseInt(st.nextToken());
			period[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] answer = new int[N+1];
		
		for(int i=0;i<N;i++) {
			if(i+period[i][0]<=N) {
				answer[i+period[i][0]] = Math.max(answer[i+period[i][0]], answer[i]+period[i][1]);
			}
			answer[i+1] = Math.max(answer[i], answer[i+1]);
		}
		System.out.println(answer[N]);
	}
}
