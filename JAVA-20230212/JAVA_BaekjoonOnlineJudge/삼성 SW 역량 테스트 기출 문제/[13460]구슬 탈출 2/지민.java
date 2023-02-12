import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
	static char[][] board;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static class Node{
		int rx;
		int ry;
		int bx;
		int by;
		int moveCnt;
		Node(int rx, int ry, int bx, int by, int moveCnt) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.moveCnt = moveCnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		int rx = -1, ry = -1, bx = -1, by = -1;
		for(int r=0; r<N; r++) {
			board[r] = br.readLine().toCharArray();
			for(int c=0; c<M; c++) {
				if(board[r][c] == 'R') {
					board[r][c] = '.';
					rx = r;
					ry = c;
				}else if(board[r][c] == 'B') {
					board[r][c] = '.';
					bx = r;
					by = c;
				}
			}
		}
		
		int result = moveBall(rx, ry, bx, by);
		System.out.println(result);
	}
	
	
	private static int moveBall(int rx, int ry, int bx, int by) {
		Queue<Node> route = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		route.add(new Node(rx, ry, bx, by, 1));
		visited[rx][ry][bx][by] = true;
		
		while(!route.isEmpty()) {
			Node node = route.poll();
			
			if(node.moveCnt > 10) return -1;
			for(int i=0; i<4; i++) {
				Node nr = findBallDirection(node.rx, node.ry, i, true);
				Node nb = findBallDirection(node.bx, node.by, i, false);
				
				if(board[nb.bx][nb.by] == 'O') continue;
				else if(board[nr.rx][nr.ry] == 'O') return node.moveCnt;
				else {
					if(nr.rx == nb.bx && nr.ry == nb.by) {
						if(nr.moveCnt < nb.moveCnt) {
							nb.bx -= dx[i];
							nb.by -= dy[i];
						}else {
							nr.rx -= dx[i];
							nr.ry -= dy[i];
						}
					}
					
					if(visited[nr.rx][nr.ry][nb.bx][nb.by]) continue;
					visited[nr.rx][nr.ry][nb.bx][nb.by] = true;
					route.add(new Node(nr.rx, nr.ry, nb.bx, nb.by, node.moveCnt+1));
				}
			}
		}

		return -1;
	}
	
	
	private static Node findBallDirection(int x, int y, int dirc, boolean isRed) {
		int cnt = 1;
		int nx = x+dx[dirc];
		int ny = y+dy[dirc];
		while(board[nx][ny] == '.') {
			nx+=dx[dirc];
			ny+=dy[dirc];
			cnt++;
		}
		
		if(board[nx][ny] != 'O') {
			nx-=dx[dirc];
			ny-=dy[dirc];
		}
		if(isRed) return new Node(nx, ny, -1, -1, cnt);
		return new Node(-1, -1, nx, ny, cnt);
	}
}
