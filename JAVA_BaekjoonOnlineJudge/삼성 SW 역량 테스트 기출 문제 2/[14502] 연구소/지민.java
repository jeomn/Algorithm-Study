import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, maxSafeAreaCnt;
	static ArrayList<Node> initVirus;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] lab, labBlock, labBlockVirus;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][M];
		initVirus = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				int v = Integer.parseInt(st.nextToken());
				lab[r][c] = v;
				if(v==2) initVirus.add(new Node(r, c));
			}
		}
		
		labBlock = new int[N][M];
		for(int r=0; r<N; r++) {
			labBlock[r] = lab[r].clone();
		}
		labBlockVirus = new int[N][M];
		maxSafeAreaCnt = 0;
		makeBlock(3, 0, 0, 0);
		
		System.out.println(maxSafeAreaCnt);
	}


	private static void init() {
		for(int r=0; r<N; r++) {
			labBlockVirus[r] = labBlock[r].clone();
		}
	}


	private static void makeBlock(int blockCnt, int curCnt, int pr, int pc) {
		if(curCnt == blockCnt) {
			init();
			checkSafeArea();
			return;
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				int v = labBlock[r][c];
				if(v == 1 || v == 2) continue;
				labBlock[r][c] = 1;
				if(c==N-1) makeBlock(blockCnt, curCnt+1, r+1, 0);
				else makeBlock(blockCnt, curCnt+1, r, c+1);
				labBlock[r][c] = 0;
			}
		}		
	}


	private static void checkSafeArea() {
		Deque<Node> route = new LinkedList<>(initVirus);
		
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nr = n.r+dx[d];
				int nc = n.c+dy[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(labBlockVirus[nr][nc]!=0) continue;
				
				route.add(new Node(nr, nc));
				labBlockVirus[nr][nc] = 2;
			}
		}
		
		int cnt = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(labBlockVirus[r][c] != 0) continue;
				cnt++;
			}
		}
		maxSafeAreaCnt = Math.max(maxSafeAreaCnt, cnt);
	}
}
