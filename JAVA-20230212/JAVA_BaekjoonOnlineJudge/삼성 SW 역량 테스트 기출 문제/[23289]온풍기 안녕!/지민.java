import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, K;
	static int[][] room;
	static boolean[][][] isWall;
	static ArrayList<Node> checkSection;
	static ArrayList<HotAir> airList;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[] dOrder = {-1, 0, 1};
	static class Node{
		int r, c, t;
		Node(int r, int c, int t){
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	static class HotAir extends Node{
		int d;
		HotAir(int r, int c, int t, int d) {
			super(r, c, t);
			this.d = d;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        checkSection = new ArrayList<>();
        airList = new ArrayList<>();
        for(int r=0; r<R; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c=0; c<C; c++) {
        		int n = Integer.parseInt(st.nextToken());
        		if(n==0) continue;
        		if(n==5) checkSection.add(new Node(r, c, -1));
        		else {
        			airList.add(new HotAir(r, c, -1, n-1));
        		}
        	}
        }
        
        int W = Integer.parseInt(br.readLine());
        
        isWall = new boolean[R][C][4];
        for(int w=0; w<W; w++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken())-1;
        	int c = Integer.parseInt(st.nextToken())-1;
        	int t = Integer.parseInt(st.nextToken());
        	
        	if(t==0) {
        		isWall[r][c][2] = true;
        		if(r!=0) isWall[r-1][c][3] = true;
        	}else {
        		isWall[r][c][0] = true;
        		if(c!=(C-1)) isWall[r][c+1][1] = true;
        	}
        }
        
        int chocolate = 0;
        while(!checkTemperature()) {
        	setHotAir();
        	moveTemperature();
        	setCoolTemperature();
        	chocolate++;
        	if(chocolate > 100) break;
        }
        System.out.println(chocolate);
        
	}

	private static void setHotAir() {
		Deque<Node> route = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		for(HotAir a : airList) {
			route.clear();
			visited = new boolean[R][C];

			int airD = a.d;
			int startR = a.r+dx[airD], startC = a.c+dy[airD];
			Node start = new Node(startR, startC, 5);
			
			route.add(start);
			visited[startR][startC] = true;
			room[startR][startC] += start.t;
			
			while(!route.isEmpty()) {
				Node n = route.poll();
				
				for(int d=0; d<3; d++) {
					int nr=n.r, nc=n.c;
					if(airD==0 || airD==1) {
						nr+=dOrder[d];
						nc+=dy[airD];
					}else {
						nr+=dx[airD];
						nc+=dOrder[d];
					}
					
					if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc]) continue;
					int[] compareD = getCompareDirection(airD, d);
					if(isWall[n.r][n.c][compareD[0]] || isWall[nr][nc][compareD[1]]) continue;
					
					Node nn = new Node(nr, nc, n.t-1);
					visited[nr][nc] = true;
					room[nr][nc] += nn.t;
					
					if(nn.t == 1) continue;
					route.add(nn);
				}
			}
		}
	}

	private static int[] getCompareDirection(int airD, int d) {
		//0은 r, c, 1은 nr, nc
		int[] dirc = new int[2];
		if(airD==0) {
			if(d==0) {
				dirc[0] = 2;
				dirc[1] = 1;
			}else if(d==1) {
				dirc[0] = 0;
				dirc[1] = 1;
			}else {
				dirc[0] = 3;
				dirc[1] = 1;
			}
		}
		else if(airD==1) {
			if(d==0) {
				dirc[0] = 2;
				dirc[1] = 0;
			}else if(d==1) {
				dirc[0] = 1;
				dirc[1] = 0;
			}else {
				dirc[0] = 3;
				dirc[1] = 0;
			}
		}
		else if(airD==2) {
			if(d==0) {
				dirc[0] = 1;
				dirc[1] = 3;
			}else if(d==1) {
				dirc[0] = 2;
				dirc[1] = 3;
			}else {
				dirc[0] = 0;
				dirc[1] = 3;
			}
		}
		else {
			if(d==0) {
				dirc[0] = 1;
				dirc[1] = 2;
			}else if(d==1) {
				dirc[0] = 3;
				dirc[1] = 2;
			}else {
				dirc[0] = 0;
				dirc[1] = 2;
			}
		}
		return dirc;
	}

	private static void moveTemperature() {
		int[][] newRoom = new int[R][C];
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				int t = room[r][c];
				if(t==0) continue;
				
				newRoom[r][c] += t;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if(isWall[r][c][d] || room[nr][nc] >= t) continue;
					
					int setT = (t-room[nr][nc])/4;
					newRoom[r][c] -= setT;
					newRoom[nr][nc] += setT;
				}
			}
		}
		room = newRoom;
	}

	private static void setCoolTemperature() {
		for(int r=0; r<R; r+=(R-1)) {
			for(int c=0; c<C; c++) {
				if(room[r][c]==0) continue;
				room[r][c]-=1;
			}
		}
		
		for(int c=0; c<C; c+=(C-1)) {
			for(int r=1; r<R-1; r++) {
				if(room[r][c]==0) continue;
				room[r][c]-=1;
			}
		}
		
	}

	private static boolean checkTemperature() {
		for(Node n: checkSection) {
			if(room[n.r][n.c] < K) return false;
		}
		return true;
	}
}
