import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cleanCnt;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] area;	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int rr = Integer.parseInt(st.nextToken());
		int rc = Integer.parseInt(st.nextToken());
		int rd = Integer.parseInt(st.nextToken());
		
		area = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				area[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		cleanCnt = 0;
		boolean[][] visited = new boolean[N][M];
		next: while(true) {
			if(!visited[rr][rc]) cleanCnt++;
			visited[rr][rc] = true;
			
			for(int d=1; d<=4; d++) {
				int nrd = (4+(rd-d))%4;
				
				int nrr = rr+dx[nrd];
				int nrc = rc+dy[nrd];
				if(nrr<0 || nrr>=N || nrc<0 || nrc>=M) continue;
				if(area[nrr][nrc]==1 || visited[nrr][nrc]) continue;
				
				rr = nrr;
				rc = nrc;
				rd = nrd;
				continue next;
			}
			
			int brr = rr-dx[rd];
			int brc = rc-dy[rd];
			if(brr<0 || brr>=N || brc<0 || brc>=M) continue;
			if(area[brr][brc]==1) break;
			rr = brr;
			rc = brc;
		}
		
		System.out.println(cleanCnt);
	}

}
