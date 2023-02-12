import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
	
	static int N, K;
	static int[][] board;
	static ArrayList<Integer>[][] markerList;
	static HashMap<Integer, Marker> markerMap;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static class Marker{
		int n, r, c, d;
		Marker(int n, int r, int c, int d){
			this.n = n;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		markerList = new ArrayList[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				markerList[r][c] = new ArrayList<>();
			}
		}
		
		markerMap = new HashMap<>();
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			Marker m = new Marker(i, r, c, d);
			markerList[r][c].add(i);
			markerMap.put(i, m);
		}
		
		int nr = -1, nc = -1, nColor = -1, game = 1;
		ArrayList<Integer> moveList;
		game: while(true) {
			for(int i=1; i<=K; i++) {
				Marker m = markerMap.get(i);
				ArrayList<Integer> mList = markerList[m.r][m.c];
				
				if(mList.size() >= 4) break game;
				
				nr = m.r+dx[m.d];
				nc = m.c+dy[m.d];
				if(nr<0 || nr>=N || nc<0 || nc>=N)	nColor = 2;
				else nColor = board[nr][nc];
				
				int idx = mList.indexOf(i);
				moveList = new ArrayList<>();
				int cnt = mList.size() - idx;
				while(cnt-- > 0) {
					moveList.add(mList.remove(idx));
				}
				if(nColor == 0) {
					markerList[nr][nc].addAll(moveList);
					for(int n : moveList) {
						Marker mm = markerMap.get(n);
						mm.r = nr;
						mm.c = nc;
					}
				}else if(nColor == 1) {
					Collections.reverse(moveList);
					markerList[nr][nc].addAll(moveList);
					for(int n : moveList) {
						Marker mm = markerMap.get(n);
						mm.r = nr;
						mm.c = nc;
					}
				}else {
					m.d = changeDirection(m.d);
					nr = m.r+dx[m.d];
					nc = m.c+dy[m.d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N)	nColor = 2;
					else nColor = board[nr][nc];
					
					if(nColor == 2) {
						mList.addAll(moveList);
						nr = m.r;
						nc = m.c;
					}else if(nColor == 0){
						markerList[nr][nc].addAll(moveList);
						for(int n : moveList) {
							Marker mm = markerMap.get(n);
							mm.r = nr;
							mm.c = nc;
						}
					}else if(nColor == 1) {
						Collections.reverse(moveList);
						markerList[nr][nc].addAll(moveList);
						for(int n : moveList) {
							Marker mm = markerMap.get(n);
							mm.r = nr;
							mm.c = nc;
						}
					}
					
				}
				
				if(markerList[nr][nc].size() >= 4) break game;
			}
			game++;
			if(game >= 1001) {
				game = -1;
				break;
			}
		}
		System.out.println(game);
	}

	private static int changeDirection(int d) {
		if(d==0) return 1;
		else if(d==1) return 0;
		else if(d==2) return 3;
		else return 2;
	}
}
