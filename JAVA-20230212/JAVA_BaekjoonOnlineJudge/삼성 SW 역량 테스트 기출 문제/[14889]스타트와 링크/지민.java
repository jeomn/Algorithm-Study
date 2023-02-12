import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, minDiffer = Integer.MAX_VALUE;
	static int[][] S;
	static boolean[] teamA;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				S[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		teamA = new boolean[N];
		setTeam(0, 0);
		
		System.out.println(minDiffer);
	}
	
	
	private static void setTeam(int cnt, int idx) {
		if(cnt == N/2) {
			getAbilityDiffer();
			return;
		}
		
		for(int i=idx; i<N; i++) {
			teamA[i] = true;
			setTeam(cnt+1, i+1);
			teamA[i] = false;
		}
	}
	
	
	private static void getAbilityDiffer() {
		int a = 0, b = 0;
		for(int i=0; i<N; i++) {
			if(teamA[i]) {
				for(int j=i+1; j<N; j++) {
					if(teamA[j]) a += (S[i][j] + S[j][i]);
				}
			}else {
				for(int j=i+1; j<N; j++) {
					if(!teamA[j]) b += (S[i][j] + S[j][i]);
				}
			}
		}
		
		minDiffer = Math.min(minDiffer, Math.abs(a-b));
	}
}
