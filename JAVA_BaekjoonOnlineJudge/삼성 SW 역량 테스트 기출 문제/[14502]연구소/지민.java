/************************************
* 조합코드 실수하지 맙시다^^777!!
************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, maxCnt;
	static int[][] lab, labTemp;
	static Node[] block;
	static ArrayList<Node> virus, zero;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
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
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];
		virus = new ArrayList<>();
		zero = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());
				if(lab[r][c] == 2) {
					virus.add(new Node(r, c));
				}else if(lab[r][c] == 0) {
					zero.add(new Node(r, c));
				}
			}
		}
		
		block = new Node[3];
		labTemp = new int[N][M];
		setBlock(0, 0);
		
		System.out.println(maxCnt);
	}

	private static void setBlock(int cnt, int idx) {
		if(cnt == 3) {
			init();
			for(int i=0; i<3; i++) {
				Node n = block[i];
				labTemp[n.x][n.y] = 1;
			}
			spreadVirus();
			maxCnt = Math.max(maxCnt, countSafeArea());
			return;
		}
		
		for(int i=idx; i<zero.size(); i++) {
			block[cnt] = zero.get(i);
			setBlock(cnt+1, i+1);
		}
		
	}
	
	private static int countSafeArea() {
		int cnt = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(labTemp[r][c]!=0) continue;
				cnt++;
			}
		}
		return cnt;
	}

	private static void spreadVirus() {
		Queue<Node> route = new LinkedList<>();
		route.addAll(virus);
		
		while(!route.isEmpty()) {
			Node n = route.poll();
			
			for(int d=0; d<4; d++) {
				int nx = n.x+dx[d];
				int ny = n.y+dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(labTemp[nx][ny] != 0) continue;
				labTemp[nx][ny] = 2;
				route.add(new Node(nx, ny));
			}
		}
	}

	private static void init() {
		for(int i=0; i<N; i++) {
			System.arraycopy(lab[i], 0, labTemp[i], 0, M);
		}	
	}
}
