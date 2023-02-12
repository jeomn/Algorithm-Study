import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, score;
	static int[][] grid;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static PriorityQueue<BlockGroup> candidates;
	static HashMap<Integer, ArrayList<Node>> groups;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static class BlockGroup implements Comparable<BlockGroup>{
		int n, size, rainbows, r, c;
		BlockGroup(int n, int size, int rainbows, int r, int c){
			this.n = n;
			this.size = size;
			this.rainbows = rainbows;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(BlockGroup o) {
			int x = o.size - this.size;
			if(x==0) {
				int y = o.rainbows - this.rainbows;
				if(y==0) {
					int z = o.r - this.r;
					if(z==0) {
						return o.c-this.c;
					}
					return z;
				}
				return y;
			}
			return x;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        grid = new int[N][N];
        for(int r=0; r<N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c=0; c<N; c++) {
        		grid[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        candidates = new PriorityQueue<>();
        groups = new HashMap<>();
        score = 0;
        boolean flag = false;
        while(true) {
        	candidates.clear();
        	groups.clear();
        	
        	flag = findBlockGroup();
        	if(!flag) break;
        	getScore();
        	setGravity();
        	setRotate();
        	setGravity();
        }
        
        System.out.println(score);
	}

	private static boolean findBlockGroup() {
		Deque<Node> route = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		ArrayList<Node> temp;
		int idx = 0;
		boolean flag = false;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int color = grid[r][c];
				if(color==-2 || color==-1) continue;
				
				int cnt = 1;
				int nomalCnt = 1;
				int rainbowsCnt = 0;
				if(color == 0) {
					rainbowsCnt = 1;
					nomalCnt = 0;
				}
				temp = new ArrayList<>();
				
				route.clear();
				Node cn = new Node(r, c);
				route.add(cn);
				visited[r][c] = true;
				temp.add(cn);
				while(!route.isEmpty()) {
					Node n = route.poll();
					
					for(int d=0; d<4; d++) {
						int nr = n.r+dx[d];
						int nc = n.c+dy[d];
						
						if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
						if(visited[nr][nc]) continue;
						if(grid[nr][nc]==0 || grid[nr][nc]==color) {
							cnt++;
							if(grid[nr][nc] == 0) {
								rainbowsCnt++;
							}else {
								nomalCnt++;
							}
						
							visited[nr][nc] = true;
							Node nn = new Node(nr, nc);
							route.add(nn);
							temp.add(nn);
						}
					}
				}
				if(cnt>=2 && nomalCnt>=1) {
					candidates.add(new BlockGroup(idx, cnt, rainbowsCnt, r, c));
					groups.put(idx++, temp);
					flag |= true;
				}
				for(Node n : temp) {
					if(grid[n.r][n.c] != 0) continue;
					visited[n.r][n.c] = false;
				}
			}
		}
		return flag;
	}

	private static void getScore() {
		BlockGroup group = candidates.poll();
		
		ArrayList<Node> removeGroup = groups.get(group.n);
		for(Node n : removeGroup) {
			grid[n.r][n.c] = -2;
		}
		
		score += Math.pow(group.size, 2);
	}

	private static void setRotate() {
		int[][] tempGrid = new int[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				tempGrid[r][c] = grid[c][N-r-1];
			}
		}
		grid = tempGrid;
	}

	private static void setGravity() {
		int[][] tempGrid = new int[N][N];
		
		int row = N-1;
		for(int c=0; c<N; c++) {
			row = N-1;
			for(int r=N-1; r>=0; r--) {
				if(row<0) {
					tempGrid[r][c] = -2;
					continue;
				}
				int b = grid[row][c];
				
				int nr = row;
				if(b==-1) {
					if(nr!=r) {
						row = nr;
						tempGrid[r][c] = -2;
						continue;
					}
				}else if(b==-2) {
					while(nr>0 && grid[nr][c]==-2) {
						nr--;
					}
					if(grid[nr][c] == -1) nr = row;
				}
				tempGrid[r][c] = grid[nr][c];
				row = nr-1;
			}
		}
		grid = tempGrid;
	}
}
