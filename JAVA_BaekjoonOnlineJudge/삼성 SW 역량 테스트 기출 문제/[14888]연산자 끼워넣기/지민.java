import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
	static int[] A;
	static int[] operations;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		operations = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operations[i] = Integer.parseInt(st.nextToken());
		}
		
		setOperation(0, "", A[0]);
		System.out.println(maxValue);
		System.out.println(minValue);
	}


	private static void setOperation(int cnt, String oper, int value) {
		if(cnt == N-1) {
			minValue = Math.min(minValue, value);
			maxValue = Math.max(maxValue, value);
			return;
		}
		
		int new_value = value;
		int number = A[cnt+1];
		for(int i=0; i<4; i++) {
			if(operations[i] == 0) continue;
			operations[i]-=1;
			if(i == 0) {
				new_value += number;
			}else if(i == 1) {
				new_value -= number;
			}else if(i == 2) {
				new_value *= number;
			}else if(i == 3) {
				if(new_value < 0) {
					new_value = -(Math.abs(new_value)/number); 
				}else
					new_value /= number;
			}
			setOperation(cnt+1, oper+String.valueOf(i), new_value);
			
			operations[i] += 1;
			new_value = value;
		}
	}
}
