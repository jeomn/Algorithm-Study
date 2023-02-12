import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, ballOne, ballTwo, ballThree;
	static int[][] grid;
	static Deque<Integer> ballList;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] order = {2, 1, 3, 0};
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
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
        
        ballList = new LinkedList<>();
        for(int m=0; m<M; m++) {
        	st = new StringTokenizer(br.readLine());
        	int d = Integer.parseInt(st.nextToken())-1;
        	int s = Integer.parseInt(st.nextToken());
        	
        	setIceFragmentAttack(d, s);
        	moveBall();
        	while(explosionBall()) {
        		moveBall();
        	}
        	setChangeBall();
        }
        int result = 1*ballOne+2*ballTwo+3*ballThree;
        System.out.println(result);
	}

	private static void setIceFragmentAttack(int d, int s) {
		int r = N/2, c = N/2;
		for(int i=0; i<s; i++) {
			r+=dx[d];
			c+=dy[d];
			grid[r][c] = 0;
		}
	}

	private static void moveBall() {
		ballList.clear();
		int r = N/2, c = N/2;
		int n=2, d=0;
		get: for(int i=1; i<=N; i++) {
			if(i==N) n = 3;
			for(int j=0; j<n; j++) {
				for(int k=0; k<i; k++) {
					r+=dx[order[d]];
					c+=dy[order[d]];
					if(r<0 || c<0) break get;
					if(grid[r][c]!=0) ballList.add(grid[r][c]);
				}
				d = (d+1)%4;
			}
		}
		
		int[][] tempGrid = new int[N][N];
		r = N/2;
		c = N/2;
		n=2;
		d=0;
		set: for(int i=1; i<=N; i++) {
			if(i==N) n = 3;
			for(int j=0; j<n; j++) {
				for(int k=0; k<i; k++) {
					r+=dx[order[d]];
					c+=dy[order[d]];
					if(r<0 || c<0 || ballList.isEmpty()) break set;
					tempGrid[r][c] = ballList.poll();
				}
				d = (d+1)%4;
			}
		}
		grid = tempGrid;
	}

	private static boolean explosionBall() {
		int r = N/2, c = N/2;
		int n=2, d=0;
		int cnt = 0, preB = -1;
		boolean flag = false;
		Deque<Node> ballCoordList = new LinkedList<>();
		set: for(int i=1; i<=N; i++) {
			if(i==N) n = 3;
			for(int j=0; j<n; j++) {
				for(int k=0; k<i; k++) {
					r+=dx[order[d]];
					c+=dy[order[d]];
					if(r<0 || c<0) break set;
					int b = grid[r][c];
					if(preB == -1) preB = b;
					if(preB == b) {
						cnt++;
						ballCoordList.add(new Node(r, c));
					}else {
						if(cnt >= 4) {
							while(!ballCoordList.isEmpty()) {
								Node node = ballCoordList.poll();
								grid[node.r][node.c] = 0;
							}
							if(preB==1) {
								ballOne+=cnt;
							}else if(preB==2) {
								ballTwo+=cnt;
							}else {
								ballThree+=cnt;
							}
							flag |= true;
						}
						cnt = 1;
						ballCoordList.clear();
						ballCoordList.add(new Node(r, c));
						preB = b;
					}
				}
				d = (d+1)%4;
			}
		}
		return flag;
	}

	private static void setChangeBall() {
		ballList.clear();
		int r = N/2, c = N/2;
		int n=2, d=0;
		int preB = -1;
		Deque<Node> ballCoordList = new LinkedList<>();
		get: for(int i=1; i<=N; i++) {
			if(i==N) n = 3;
			for(int j=0; j<n; j++) {
				for(int k=0; k<i; k++) {
					r+=dx[order[d]];
					c+=dy[order[d]];
					if(r<0 || c<0) break get;
					
					int b = grid[r][c];
					if(preB == -1) preB = b;
					if(preB == b) {
						ballCoordList.add(new Node(r, c));
					}else {
						ballList.add(ballCoordList.size());
						ballList.add(preB);
						
						ballCoordList.clear();
						ballCoordList.add(new Node(r, c));
						preB = b;
					}
				}
				d = (d+1)%4;
			}
		}
		
		int[][] tempGrid = new int[N][N];
		r = N/2;
		c = N/2;
		n=2;
		d=0;
		set: for(int i=1; i<=N; i++) {
			if(i==N) n = 3;
			for(int j=0; j<n; j++) {
				for(int k=0; k<i; k++) {
					r+=dx[order[d]];
					c+=dy[order[d]];
					if(r<0 || c<0 || ballList.isEmpty()) break set;
					tempGrid[r][c] = ballList.poll();
				}
				d = (d+1)%4;
			}
		}
		grid = tempGrid;
	}
}
