import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, min = Integer.MAX_VALUE;
	static boolean[] isTeam;
	static int[][] ability;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		ability = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				ability[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		isTeam = new boolean[N];
		setTeam(0, 0);
		
		System.out.println(min);
	}
	
	public static void setTeam(int cnt, int idx) {
		if(cnt == N/2) {
			min = Math.min(min,  calcTotalAbilityDiffer());
			return;
		}
		
		for(int i=idx; i<N; i++) {
			isTeam[i] = true;
			setTeam(cnt+1, i+1);
			isTeam[i] = false;
		}
	}
	
	public static int calcTotalAbilityDiffer() {
		int teamA = 0, teamB = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(isTeam[i] && isTeam[j]) teamA += ability[i][j];
				if(!isTeam[i] && !isTeam[j])teamB += ability[i][j];
			}
		}
		return Math.abs(teamA-teamB);
	}
}
