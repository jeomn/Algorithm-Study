import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_치킨배달_15686 {
	static int N, M;
	static ArrayList<Point> house, chicken;
	static int[] visited;
	static int ans;
	
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
		house = new ArrayList<Point>();
		chicken = new ArrayList<Point>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num == 2) {
					chicken.add(new Point(i, j));
				} else if(num==1) {
					house.add(new Point(i, j));
				}
			}
		}
		ans = Integer.MAX_VALUE;
		visited = new int[M];
		solve(0, 0);
		System.out.println(ans);
	}
	
	private static void solve(int cnt, int start) {
		if(cnt == M) {
			int sumCheck = distance();
			ans = Math.min(sumCheck, ans);
			return;
		}
		
		for(int i=start;i<chicken.size();i++) {
			visited[cnt] = i;
			solve(cnt+1, i+1);
		}
	}
	
	private static int distance() {
		int sum = 0;
		for(int i=0;i<house.size();i++) {
			int dis = Integer.MAX_VALUE;
			Point hs = house.get(i);
			for(int j=0;j<visited.length;j++) {
				Point chi = chicken.get(visited[j]);
				dis = Math.min(dis, Math.abs(hs.x-chi.x)+Math.abs(hs.y-chi.y));
			}
			sum+=dis;
		}
		return sum;
	}
}
