import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_시험감독_13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] people = new int[N];
		for(int n=0;n<N;n++) {
			people[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long result = N;
		for(int i=0;i<N;i++) {
			people[i] -= B;
			if(people[i]<=0)
				continue;
			result += people[i]/C;
			if(people[i]%C > 0) 
				result++;
		}
		
		System.out.println(result);
	}

}
