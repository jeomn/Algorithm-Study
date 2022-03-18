import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14888_연산자끼워넣기 {
	static int N;
	static int[] A;
	static int[] oper;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		oper = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(A[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void dfs(int a, int idx) {
		if(idx == N) {
			max = Math.max(a, max);
			min = Math.min(a, min);
			return;
		}
		for(int i=0;i<4;i++) {
			if(oper[i]>0) {
				oper[i]--;
				
				if(i==0) {
					dfs(a + A[idx], idx+1);
				} else if(i==1) {
					dfs(a - A[idx], idx+1);
				} else if(i==2) {
					dfs(a * A[idx], idx+1);
				} else {
					dfs(a / A[idx], idx+1);
				}
				
				oper[i]++;
			}
		}
	}

}
