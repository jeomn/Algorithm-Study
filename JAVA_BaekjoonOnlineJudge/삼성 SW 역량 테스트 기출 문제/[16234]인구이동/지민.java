/*******************
* 20211012
* Deque가 더 빠른 듯
*******************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, L, R;
	static int[][] country;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		country = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				country[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		int day = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean flag = false;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(visited[r][c]) continue;
					flag |= movePopulation(r, c);
				}
			}
			
			if(!flag) break;
			day++;
		}
		
		System.out.println(day);
	}

	private static boolean movePopulation(int r, int c) {
		Deque<Node> route = new LinkedList<>();
		route.add(new Node(r, c));
		ArrayList<Node> moveCountry = new ArrayList<>();
		moveCountry.add(new Node(r, c));
		visited[r][c] = true;
		
		int sumPopulation = country[r][c];
		while(!route.isEmpty()) {
			Node n = route.pollFirst();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;
				int differ = Math.abs(country[n.x][n.y]-country[nx][ny]);
				if(differ<L || differ>R) continue;
				
				Node next = new Node(nx, ny);
				moveCountry.add(next);
				route.addLast(next);
				visited[nx][ny] = true;
				sumPopulation += country[nx][ny];
			}
		}
		
		if(moveCountry.size() == 1) return false;
		
		int pNum = sumPopulation/moveCountry.size();
		for(Node n : moveCountry) {
			country[n.x][n.y] = pNum;
		}
		return true;
	}
}
