import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, F;
	static int[][] map;
	static HashMap<Integer, Node> customerMap;
	static PriorityQueue<Node> customerPriority;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Node implements Comparable<Node>{
		int n, x, y, d;
		Node(int n, int x, int y, int d){
			this.n = n;
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public int compareTo(Node o) {
			int x = this.d - o.d;
			if(x==0) {
				int y = this.x - o.x;
				if(y==0) return this.y - o.y;
				return y;
			}
			return x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int taxiX = Integer.parseInt(st.nextToken())-1;
		int taxiY = Integer.parseInt(st.nextToken())-1;
		
		customerMap = new HashMap<>();
		for(int i=2; i<M+2; i++) {
			st = new StringTokenizer(br.readLine());
			int fromX = Integer.parseInt(st.nextToken())-1;
			int fromY = Integer.parseInt(st.nextToken())-1;
			int toX = Integer.parseInt(st.nextToken())-1;
			int toY = Integer.parseInt(st.nextToken())-1;
			
			customerMap.put(i, new Node(i, toX, toY, -1));
			map[fromX][fromY] = i;
		}

		Node start = new Node(map[taxiX][taxiY], taxiX, taxiY, 0);
		while(F>=0) {
			if(customerMap.size() <= 0) break;
			
			customerPriority = new PriorityQueue<>();
			if(map[start.x][start.y] >= 2) customerPriority.add(start);
			findCustomer(start);
			Node customer = customerPriority.poll();
			if(customer==null || customer.d > F) {
				F = -1;
				break;
			}else {
				F-=customer.d;
			}
			
			customer.d = 0;
			map[customer.x][customer.y] = 0;
			Node arrival = moveTaxi(customer);
			if(arrival==null || arrival.d > F) {
				F = -1;
				break;
			}else {
				F+=arrival.d;
				customerMap.remove(customer.n);
			}
			
			start = arrival;
			start.d = 0;
		}
		
		System.out.println(F);
	}

	private static Node moveTaxi(Node customer) {
		Node end = customerMap.get(customer.n);
		
		Deque<Node> route = new LinkedList<>();
		route.add(customer);
		boolean[][] visited = new boolean[N][N];
		visited[customer.x][customer.y] = true;
		
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			if(n.x==end.x && n.y==end.y) {
				return n;
			}
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny] || map[nx][ny] == 1) continue;
				
				route.add(new Node(map[nx][ny], nx, ny, n.d+1));
				visited[nx][ny] = true;
			}
		}
		return null;
	}
	
	private static void findCustomer(Node start) {
		Deque<Node> route = new LinkedList<>();
		route.add(start);
		boolean[][] visited = new boolean[N][N];
		visited[start.x][start.y] = true;
		
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny] || map[nx][ny] == 1) continue;
				
				Node next = new Node(map[nx][ny], nx, ny, n.d+1);
				if(next.n != 0) customerPriority.add(next);
				route.add(next);
				visited[nx][ny] = true;
			}
		}
	}
}
