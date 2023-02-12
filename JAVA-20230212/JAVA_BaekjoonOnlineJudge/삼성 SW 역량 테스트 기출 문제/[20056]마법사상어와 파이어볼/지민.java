import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
	static ArrayList<Fireball> fList;
	static HashMap<Node, ArrayList<Fireball>> map;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			Node n = (Node)obj;
			return n.r == r && n.c == c;
		}
		@Override
		public int hashCode() {
			return Objects.hash(r, c);
		}
	}
	
	static class Fireball{
		int r, c, m, s, d;
		Fireball(int r, int c, int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Fireball [r=" + r + ", c=" + c + ", m=" + m + ", d=" + d + ", s=" + s + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		fList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Fireball fb = new Fireball(r, c, m, s, d);
			fList.add(fb);
		}
		
		for(int k=0; k<K; k++) {
			map = new HashMap<>();
			moveFireball();
			
			fList.clear();
			for(Node key: map.keySet()) {
				int fbCnt = map.get(key).size();
				if(fbCnt == 1) {
					fList.add(map.get(key).get(0));
					continue;
				}
				
				int mSum = 0, sSum = 0, evenCnt = 0, oddCnt = 0;
				for(Fireball fb : map.get(key)) {
					mSum += fb.m;
					sSum += fb.s;
					if(fb.d%2 == 0) oddCnt++;
					else evenCnt++;
				}
				
				int m = mSum/5, s = sSum/fbCnt;
				int[] d = {0, 2, 4, 6};
				if(evenCnt != fbCnt && oddCnt != fbCnt) {
					d = new int[]{1, 3, 5, 7};
				}
				
				if(m == 0) continue;
				
				for(int i=0; i<4; i++) {
					fList.add(new Fireball(key.r, key.c, m, s, d[i]));
				}
			}
		}
		
		int result = 0;
		for(Fireball fb: fList) {
			result += fb.m;
		}
		System.out.println(result);
	}

	private static void moveFireball() {
		for(Fireball fb : fList) {
			int nr = (fb.r+dx[fb.d]*fb.s)%N;
			int nc = (fb.c+dy[fb.d]*fb.s)%N;
			
			if(nr < 0) nr = (nr+N)%N;
			if(nc < 0) nc = (nc+N)%N;
			
			Node node = new Node(nr, nc);
			if(!map.containsKey(node)) map.put(node, new ArrayList<Fireball>());
			map.get(node).add(new Fireball(nr, nc, fb.m, fb.s, fb.d));
		}
	}
}
