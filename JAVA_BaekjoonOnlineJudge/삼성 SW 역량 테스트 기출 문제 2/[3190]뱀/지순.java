import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {
	static int N;
	static int[][] map;
	static HashMap<Integer, String> route;
	static ArrayList<int[]> snake;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int time;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		route = new HashMap<>();
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			route.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		
		snake = new ArrayList<>();
		move();
		System.out.println(time);
	}
	
	private static void move() {
		snake.add(new int[]{0, 0});
		int dir = 0;
		time = 0;
		int nx = 0, ny = 0;
		
		while(true) {
			time++;
			nx += dx[dir];
			ny += dy[dir];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) return;
			
			for(int[] info : snake) {
				if(info[0] == nx && info[1] == ny) return;
			}
			
			if(map[nx][ny] == 1) {
				snake.add(new int[] {nx, ny});
				map[nx][ny] = 0;
			} else {
				snake.add(new int[] {nx, ny});
				snake.remove(0);
			}
			
			if(route.containsKey(time)) {
				if(route.get(time).equals("D")) {
					dir = (dir+1)%4;
				} else {
					dir = (4+dir-1)%4;
				}
			}
		}
	}
}
