import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		
		StringTokenizer st = null;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		System.out.println(answer);
	}
	
	private static void comb(int idx, int cnt) {
		if(cnt == N/2) {
			int start = 0;
			int link = 0;
			for(int i=0;i<N;i++) {
				for(int j=i;j<N;j++) {
					if(visited[i] && visited[j]) {
						start += (map[i][j]+map[j][i]);
					}
					if(!visited[i] && !visited[j]) {
						link += (map[i][j]+map[j][i]);
					}
				}
			}
			
			answer = Math.min(answer, Math.abs(start - link));
			
			return;
		}
		for(int i=idx;i<N;i++) {
			visited[i] = true;
			comb(i+1, cnt+1);
			visited[i] = false;
		}
	}

}
