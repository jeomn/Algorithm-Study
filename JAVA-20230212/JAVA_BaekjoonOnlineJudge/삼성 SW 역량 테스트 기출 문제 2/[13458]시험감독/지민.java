import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken());
		
		long n = N;
		for(int i=0; i<N; i++) {
			int s = A[i]-B;
			if(s <= 0) continue;
			n+=(int)s/C;
			if(s%C!=0) n+=1;
		}
		
		System.out.println(n);
	}

}