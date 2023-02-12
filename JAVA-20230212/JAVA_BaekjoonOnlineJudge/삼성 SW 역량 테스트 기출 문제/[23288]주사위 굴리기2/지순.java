import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_주사위굴리기2_23288 {
	static int[] dice = {0, 1, 2, 3, 4, 5, 6};
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 }; // 동서북남
	static int N, M, K;
	static int[][] map;
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = 0;
		int y = 0;
		int dir = 0;
		int result = 0;
		for(int i=0;i<K;i++) {
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			
			if(nx<0 || nx>=N || ny< 0 || ny>=M) {
				dir = reverseDir(dir);
				nx = x+dx[dir];
				ny = y+dy[dir];
			}
			
			int num = map[nx][ny];
			result += num*bfs(nx, ny);
			
			changeDice(dir);
			dir = setDice(dir, dice[6]-map[nx][ny]);
			x = nx;
			y = ny;
			
		}
		System.out.println(result);
	}
	private static int setDice(int dir, int num) {
		// TODO Auto-generated method stub
		if(num>0) {  // 동서북남 시계 방향으로 회전
			switch(dir) {
			case 0:
				return 3;
			case 1:
				return 2;
			case 2:
				return 0;
			case 3:
				return 1;
			}
		} else if(num<0) {
			switch(dir) {
			case 0:
				return 2;
			case 1:
				return 3;
			case 2:
				return 1;
			case 3:
				return 0;
			}
		}
		return dir;
	}
	static int reverseDir(int d) {
		switch(d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return d;
	}
	static int bfs(int x, int y) {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		int num = map[x][y];
		int cnt = 1;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny< 0 || ny>=M) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] != num) continue;
				
				visited[nx][ny] = true;
				cnt++;
				queue.add(new Point(nx,ny));
			}
		}
		return cnt;
	}
	static void changeDice(int dir) {
		int[] temp = dice.clone();

		switch (dir) {
		case 0:
			dice[1] = temp[4];
			dice[3] = temp[1];
			dice[4] = temp[6];
			dice[6] = temp[3];
			break;
		case 1: // 서
			dice[1] = temp[3];
			dice[3] = temp[6];
			dice[4] = temp[1];
			dice[6] = temp[4];
			break;
		case 2:
			dice[1] = temp[5];
			dice[2] = temp[1];
			dice[5] = temp[6];
			dice[6] = temp[2];
			break;
		case 3:
			dice[1] = temp[2];
			dice[2] = temp[6];
			dice[5] = temp[1];
			dice[6] = temp[5];
			break;
		default:
			break;
		}
	}
}
