import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_드래곤커브_15685 {
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	static boolean[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new boolean[101][101];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dragonCurve(x, y, d, g);
		}
		
		int result = 0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j] && map[i+1][j] && map[i+1][j+1] && map[i][j+1])
					result++;
			}
		}
		
		System.out.println(result);
	}

	private static void dragonCurve(int x, int y, int d, int g) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);
		
		for(int i=0;i<g;i++) {
			for(int j=list.size()-1;j>=0;j--) {
				list.add((list.get(j)+1)%4);
			}
		}
		
		map[x][y] = true;
		for (int dir : list) {
			x += dx[dir];
			y += dy[dir];
			
			if(x<0 || x>100 || y<0 || y>100) continue;
			map[x][y] = true;
		}
	}

}
