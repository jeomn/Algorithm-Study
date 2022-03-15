import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] pay = new int[N];
		for(int i=N-1; i>=0; i--) {
			int endDay = i+T[i];
			if(i==N-1) {
				if(T[i]==1) pay[i] = P[i];
				continue;
			}
			if(endDay == N) pay[i] = Math.max(pay[i+1], P[i]);
			else if(endDay < N) pay[i] = Math.max(pay[i+1], pay[endDay]+P[i]);
			else pay[i] = pay[i+1];
		}
		System.out.println(Arrays.stream(pay).max().getAsInt());		
	}

}
