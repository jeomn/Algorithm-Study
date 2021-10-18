import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	
	static int N;
	static boolean[][] map;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> direction = new ArrayList<>();
		map = new boolean[101][101];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			map[r][c] = true;
			int nx = r, ny = c;
			direction.add(d);
			for(int j=0; j<g; j++) {
				for(int k=direction.size()-1; k>=0; k--) {
					direction.add((direction.get(k)+1)%4);
				}
			}
			for(int j=0; j<direction.size(); j++) {
				nx+=dx[direction.get(j)];
				ny+=dy[direction.get(j)];
				map[nx][ny] = true;
			}
			direction.clear();
		}
		
		int cnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(!map[i][j]) continue;
				if(!map[i+1][j]) continue;
				if(!map[i+1][j+1]) continue;
				if(!map[i][j+1]) continue;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
