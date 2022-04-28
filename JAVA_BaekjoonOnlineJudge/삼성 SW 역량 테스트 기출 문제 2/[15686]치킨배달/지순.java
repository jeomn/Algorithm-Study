import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15686_치킨배달 {
	static int N, M;
	static ArrayList<Point> chicken;
	static ArrayList<Point> home;
	static Point[] visit;
	static int answer;
	
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
		
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 2)
					chicken.add(new Point(i, j));
				else if(num == 1)
					home.add(new Point(i, j));
			}
		}
		visit = new Point[M];
		answer = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(answer);
	}

	private static void comb(int cnt, int start) {
		if(cnt == M) {
			answer = Math.min(answer, calc());
			return;
		}
		for(int i=start;i<chicken.size();i++) {
			visit[cnt] = chicken.get(i);
			comb(cnt+1, i);
		}
	}
	
	private static int calc() {
		int sum = 0;
		
		for(int i=0;i<home.size();i++) {
			Point h = home.get(i);
			
			int d = Integer.MAX_VALUE;
			for(int j=0;j<M;j++) {
				d = Math.min(d, Math.abs(h.x - visit[j].x)+Math.abs(h.y-visit[j].y));
			}
			sum += d;
		}
		
		return sum;
	}
}
