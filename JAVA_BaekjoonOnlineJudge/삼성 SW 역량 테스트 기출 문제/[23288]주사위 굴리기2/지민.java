import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
	static int[] dice;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
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
		
		dice = new int[] {1, 3, 6, 4, 2, 5};
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int totalScore = 0, d = 1;
		int r = 0, c = 0;
		for(int k=0; k<K; k++) {
			
			int nr = r+dx[d];
			int nc = c+dy[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=M) {
				nr = r-dx[d];
				nc = c-dy[d];
				d = (d+2)%4;
			}
			
			rotateDice(d);
			
			int num = map[nr][nc];
			totalScore += num*getScore(num, nr, nc);
			
			int bottom = dice[2];

			r = nr;
			c = nc;
			if(bottom == num) continue;
			else if(bottom > num) d = (d+1)%4;
			else d = (d-1+4)%4;
		}
		
		System.out.println(totalScore);
			
	}

	private static void rotateDice(int d) {
		
		if(d==0) {
			int temp = dice[4];
			dice[4] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;
		}else if(d==1) {
			int temp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = temp;
		}else if(d==2) {
			int temp = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[0];
			dice[0] = dice[4];
			dice[4] = temp;
		}else {
			int temp = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[1];
			dice[1] = temp;
		}
	}

	private static int getScore(int num, int x, int y) {
		Deque<Node> route = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		route.add(new Node(x, y));
		visited[x][y] = true;
		
		int count = 1;
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(map[nx][ny] != num || visited[nx][ny]) continue;
				
				count++;
				visited[nx][ny] = true;
				route.add(new Node(nx, ny));
			}
		}
		
		return count;
	}
}
