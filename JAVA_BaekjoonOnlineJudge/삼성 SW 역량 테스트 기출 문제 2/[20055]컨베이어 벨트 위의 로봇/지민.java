import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, R;
	static int[] A;
	static Robot[] belt;
	static class Robot{
		int start, now;
		Robot(int start, int now){
			this.start = start;
			this.now = now;
		};
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		A = new int[N*2];
		for(int i=0; i<N*2; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int startIdx = 0;
		belt = new Robot[N];
		int step = 1;
		while(true) {
			startIdx = (startIdx+(2*N-1))%(2*N);
			goRound();
			moveRobot();
			setNewRobot(startIdx);
			if(checkBelt()) break;
			step++;
		}
		System.out.println(step);
	}
	


	private static void goRound() {
		for(int i=N-1; i>0; i--) {
			belt[i] = belt[i-1];
			belt[i-1] = null; 
		}
		belt[0] = null;
		belt[N-1] = null;
	}


	private static void moveRobot() {
		for(int i=N-2; i>=0; i--) {
			if(belt[i]==null) continue;
			Robot r = belt[i];
			int newIdx = (r.now+1)%(2*N);
			if(belt[i+1]!=null || A[newIdx]==0) continue;
			r.now = newIdx;
			belt[i+1] = belt[i];
			belt[i] = null;
			A[newIdx]--;
			if(i+1==N-1)belt[N-1] = null;
		}
	}


	private static void setNewRobot(int startIdx) {
		if(A[startIdx]==0) return;
		belt[0] = new Robot(startIdx, startIdx);
		A[startIdx]--;
	}


	private static boolean checkBelt() {
		int cnt = 0;
		for(int i=0; i<2*N; i++) {
			if(A[i]!=0) continue;
			cnt++;
			if(cnt>=K) return true;
		}
		return false;
	}

}
