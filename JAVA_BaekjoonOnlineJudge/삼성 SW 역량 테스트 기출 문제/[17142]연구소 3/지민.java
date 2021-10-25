import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, minTime = Integer.MAX_VALUE;
	static int[][] lab, labTemp;
	static boolean[][] visited;
	static Node[] myVirus;
	static ArrayList<Node> virus;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
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
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][N];
		virus = new ArrayList<>();
		for(int x=0; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y=0; y<N; y++) {
				lab[x][y] = Integer.parseInt(st.nextToken());
				if(lab[x][y] == 2) {
					virus.add(new Node(x, y));
				}
			}
		}
		
		myVirus = new Node[M];
		labTemp = new int[N][N];
		setVirus(0, 0);
		
		int result = (minTime==Integer.MAX_VALUE) ? -1:minTime;
		System.out.println(result);		
	}
	
	private static void init() {
		for(int r=0; r<N; r++) {
			System.arraycopy(lab[r], 0, labTemp[r], 0, N);
		}
		
		visited = new boolean[N][N];
		for(Node n: myVirus) {
			labTemp[n.x][n.y] = 3;
			visited[n.x][n.y] = true;
		}
	}
	
	private static void setVirus(int cnt, int idx) {
		if(cnt == M) {
			init();
			minTime = Math.min(minTime, spreadVirus());
			return;
		}
		
		for(int i=idx; i<virus.size(); i++) {
			myVirus[cnt] = virus.get(i);
			setVirus(cnt+1, i+1);
		}
	}

	private static int spreadVirus() {
		Deque<Node> route = new LinkedList<>();
		route.addAll(Arrays.asList(myVirus));
		
		int time = 3;
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny] || labTemp[nx][ny]==1) continue;
				
				if(labTemp[nx][ny] == 0) time = Math.max(time, labTemp[n.x][n.y]+1);
				labTemp[nx][ny] = labTemp[n.x][n.y]+1;
				route.add(new Node(nx, ny));
				visited[nx][ny] = true;
			}
		}
		
		if(checkLab()) return time-3;
		else return Integer.MAX_VALUE;
	}

	private static boolean checkLab() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(labTemp[r][c] == 0) return false; 
			}
		}
		return true;
	}
}
