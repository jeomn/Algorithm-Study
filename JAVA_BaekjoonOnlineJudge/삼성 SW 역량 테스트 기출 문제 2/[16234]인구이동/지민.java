import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, R;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] country;
	static boolean[][] visited;
	static class Node{
		int r, c, cnt;
		Node(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		country = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				country[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean isContinue = false;
		int day = 0;
		while(true) {
			isContinue = false;
			
			visited = new boolean[N][N];
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(visited[r][c]) continue;
					visited[r][c] = true;
					isContinue |= movePopulation(r, c, country[r][c]);
				}
			}
			
			if(!isContinue) break;
			day++;
		}
		System.out.println(day);
	}
	

	private static boolean movePopulation(int startR, int startC, int curCnt) {
		Deque<Node> route = new LinkedList<>();
		route.add(new Node(startR, startC, curCnt));
		
		ArrayList<Node> union = new ArrayList<>();
		int sumCnt = 0;
		while(!route.isEmpty()) {
			Node n = route.poll();
			for(int d=0; d<4; d++) {
				int nr = n.r+dx[d];
				int nc = n.c+dy[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(visited[nr][nc]) continue;
				int newCnt = country[nr][nc];
				int differ = Math.abs(n.cnt-newCnt);
				if(differ<L || differ>R) continue;
				
				route.add(new Node(nr, nc, newCnt));
				visited[nr][nc] = true;
				union.add(new Node(nr, nc, newCnt));
				sumCnt+=newCnt;
			}
		}
		
		if(union.size() == 0) return false;
		union.add(new Node(startR, startC, curCnt));
		sumCnt+=curCnt;
		int newUnionCnt = sumCnt/union.size();
		for(Node c:union) {
			country[c.r][c.c] = newUnionCnt;
		}
		
		return true;
	}
}
