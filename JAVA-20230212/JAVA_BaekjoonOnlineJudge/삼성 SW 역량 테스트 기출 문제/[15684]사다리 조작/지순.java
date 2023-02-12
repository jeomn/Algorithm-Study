import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_사다리조작_15684 {
	static int N, M, H;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H+1][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[a][b+1] = -1;
		}

		for(int i=0;i<=3;i++) {
			dfs(i, 0);
		}
		System.out.println(-1);
	}
	
	private static void dfs(int maxInsert, int cnt) {
		if(cnt==maxInsert) {
			for(int i=1;i<=N;i++) { // 한줄 한줄 봐야함
				int nx = 1;
				int ny = i;
				
				while(nx<=H) {
					if(map[nx][ny] == 1) ny++;
					else if(map[nx][ny]==-1) ny--;
					nx++;
				}
				
				if(ny != i) {
					return;
				}
			}
			System.out.println(cnt);
			System.exit(0);
		}
		for(int i=1;i<=H;i++) {
			for(int j=1;j<N;j++) {
				if(map[i][j]==0 && map[i][j+1]==0) {
					map[i][j]=1;
					map[i][j+1]=-1;
					dfs(maxInsert, cnt+1);
					map[i][j]=0;
					map[i][j+1]=0;					
				}
			}
		}
	}
}
