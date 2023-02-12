import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, K;
	static int[][] map, A;
	static Deque<Tree> treeList;
	static Queue<Tree> dieTree;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static class Tree implements Comparable<Tree>{
		int x, y, o;
		Tree(int x, int y, int o){
			this.x = x;
			this.y = y;
			this.o = o;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.o - o.o;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		A = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = 5;
			}
		}
		
		treeList = new LinkedList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			treeList.add(new Tree(x, y, z));
		}
		
		dieTree = new LinkedList<>();
		Collections.sort((List<Tree>)treeList);
		for(int k=0; k<K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		System.out.println(treeList.size());
	}

	private static void spring() {
		Deque<Tree> liveTree = new LinkedList<>();
		while(!treeList.isEmpty()) {
			Tree t = treeList.poll();
			if(map[t.x][t.y] < t.o) {
				dieTree.add(t);
			}else {
				map[t.x][t.y]-=t.o;
				t.o++;
				liveTree.add(t);
			}
		}
		treeList = liveTree;
	}

	private static void summer() {
		while(!dieTree.isEmpty()) {
			Tree dt = dieTree.poll();
			map[dt.x][dt.y] += (dt.o/2);
		}
	}

	private static void autumn() {
		Deque<Tree> newTree = new LinkedList<>();
		while(!treeList.isEmpty()) {
			Tree t = treeList.poll();
			newTree.offerLast(t);
			if(t.o%5 != 0) continue;
			for(int d=0; d<8; d++) {
				int nx = t.x+dx[d];
				int ny = t.y+dy[d];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				newTree.addFirst(new Tree(nx, ny, 1));
			}
		}
		treeList = newTree;
	}

	private static void winter() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				map[r][c] += A[r][c];
			}
		}
	}
}
