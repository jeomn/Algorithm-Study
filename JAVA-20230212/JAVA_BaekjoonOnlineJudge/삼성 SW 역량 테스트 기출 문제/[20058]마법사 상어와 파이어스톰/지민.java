import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, Q, L, n, iceTotalCnt, largeIceSize;
	static int[][] grid;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		n = (int) Math.pow(2, N);
		grid = new int[n][n];
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int q=0; q<Q; q++) {
			L = Integer.parseInt(st.nextToken());
			
			int l = (int)Math.pow(2, L);
			for(int r=0; r<n; r+=l) {
				for(int c=0; c<n; c+=l) {
					rotateGrid(r, c, l);
				}
			}
			meltIce();
		}
		
		countTotalIce();
		
		System.out.println(iceTotalCnt);
		System.out.println(largeIceSize);
	}

	private static void rotateGrid(int r, int c, int l) {
		int[][] temp = new int[l][l];
		for(int i=0; i<l; i++) {
			System.arraycopy(grid[r+i], c, temp[i], 0, l);
		}
		
		int tempC = c+l;
		for(int i=0; i<l; i++) {
			tempC--;
			for(int j=0; j<l; j++) {
				grid[r+j][tempC] = temp[i][j];
			}
		}
	}
	
	private static void meltIce() {
		ArrayList<Node> meltedIce = new ArrayList<>();
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(grid[r][c]==0) continue;
				
				int iceCnt = 0;
				for(int i=0; i<4; i++) {
					int nr = r+dx[i];
					int nc = c+dy[i];
					
					if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
					if(grid[nr][nc]==0) continue;
					iceCnt++;
				}
				
				if(iceCnt<3) meltedIce.add(new Node(r, c));
			}
		}
		
		for(Node node:meltedIce) {
			grid[node.x][node.y]--;
		}
	}
	
	private static void countTotalIce() {
		visited = new boolean[n][n];
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				iceTotalCnt+=grid[r][c];
				if(grid[r][c]==0 || visited[r][c]) continue;
				visited[r][c] = true;
				countIceSize(new Node(r, c));
			}
		}
	}

	private static void countIceSize(Node start) {
		Deque<Node> route = new LinkedList<>();
		route.add(start);
		
		int cnt = 1;
		while(!route.isEmpty()) {
			Node node = route.poll();
			
			for(int d=0; d<4; d++) {
				int nr = node.x+dx[d];
				int nc = node.y+dy[d];
				
				if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if(grid[nr][nc]==0 || visited[nr][nc]) continue;
				
				route.add(new Node(nr, nc));
				visited[nr][nc] = true;
				cnt++;
			}
		}
		largeIceSize = Math.max(largeIceSize, cnt);
	}
}
