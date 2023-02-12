import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static int N, size = 2;
	static int[][] map;
	static PriorityQueue<Fish> fishList;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Fish implements Comparable<Fish>{
		int x, y, d;
		Fish(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public int compareTo(Fish o) {
			int x = this.d - o.d;
			if(x == 0) {
				int y = this.x - o.x;
				if(y == 0) return this.y - o.y;
				return y;
			}
			return x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		Fish shark = null;
		for(int r=0; r<N; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					shark = new Fish(r, c, 0);
					map[r][c] = 0;
				}
			}
		}
		
		fishList = new PriorityQueue<>();
		int ateFish = 0, time = 0;
		while(true) {
			findFish(shark);
			if(fishList.size() == 0) break;
			else {
				shark = fishList.poll();
				time += shark.d;
				shark.d = 0;
				map[shark.x][shark.y] = 0;
				
				ateFish++;
				if(ateFish == size) {
					ateFish = 0;
					size++;
				}
				
				fishList.clear();
			}
		}
		
		System.out.println(time);
	}

	private static void findFish(Fish start) {
		Deque<Fish> route = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		route.add(start);
		visited[start.x][start.y] = true;
		
		while(!route.isEmpty()) {
			Fish n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;
				
				int fish = map[nx][ny];
				
				if(fish > size) continue;
				else if(fish == 0 || fish == size) {
					visited[nx][ny] = true;
					route.add(new Fish(nx, ny, n.d+1));
				}else {
					visited[nx][ny] = true;
					fishList.add(new Fish(nx, ny, n.d+1));
				}
			}
		}
	}
}
