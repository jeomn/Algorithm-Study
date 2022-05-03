import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {
	static int N, K;
	static int[] A;
	static boolean[] robot;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N*2];
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<A.length;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		while(check()) {
			 move();
			 cnt++;
		}
		
		System.out.println(cnt);
		
	}
	
	private static boolean check() {
		int count = 0;
		
		for(int i=0;i<A.length;i++) {
			if(A[i] == 0)
				count++;
			if(count>=K)
				return false;
		}
		return true;
	}
	
	private static void move() {
		int tmp = A[A.length-1];
		
		for(int i=A.length-1;i>0;i--)
			A[i] = A[i-1];
		
		A[0] = tmp;
		
		for(int i=robot.length-1;i>0;i--) {
			robot[i] = robot[i-1];
		}
		
		robot[0] = false;
		robot[N-1] = false;
		
		for(int i=N-1;i>0;i--) {
			if(robot[i-1] && !robot[i] && A[i] > 0) {
				robot[i] = true;
				robot[i-1] = false;
				A[i]--;
			}
		}
		
		if(A[0] > 0) {
			robot[0] = true;
			A[0]--;
		}
	}

}
