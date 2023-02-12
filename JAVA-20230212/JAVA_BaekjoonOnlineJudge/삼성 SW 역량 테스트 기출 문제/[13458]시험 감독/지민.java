import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int A = Integer.parseInt(st.nextToken());
			numbers[i] = A;
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum+=1;
			int num = numbers[i]-B;
			
			if(num <= 0) continue;
			else {
				if(num%C != 0)
					sum += 1;
				sum+= (num/C);
			}
		}
		System.out.print(sum);
	}
}
