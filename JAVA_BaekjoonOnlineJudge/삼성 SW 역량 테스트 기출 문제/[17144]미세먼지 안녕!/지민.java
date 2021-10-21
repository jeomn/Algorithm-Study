import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int R, C, T;
	static Node top, bottom;
	static int[][] room;
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
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		for(int r=0; r<R; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if(room[r][c] == -1) {
					if(top == null) top = new Node(r, c);
					else bottom = new Node(r, c);
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			diffuseDust();
			runAirConditioner();
		}
		System.out.println(getDustAmount());

	}

	private static void diffuseDust() {
		int[][] newRoom = new int[R][C];
		newRoom[top.x][top.y] = -1;
		newRoom[bottom.x][bottom.y] = -1;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(room[r][c] == -1 || room[r][c] == 0)continue;
				int dust = room[r][c]/5;
				
				int count = 0;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if(room[nr][nc] == -1) continue;
					newRoom[nr][nc] += dust;
					count++;					
				}
				newRoom[r][c] += room[r][c]-(dust*count);
			}
		}
		room = newRoom;
	}

	private static void runAirConditioner() {
		int[] td = new int[] {3, 0, 2, 1};
		int[] bd = new int[] {3, 1, 2, 0};
		
		int[][] newRoom = new int[R][C];
		for(int r=0; r<R; r++) {
			System.arraycopy(room[r], 0, newRoom[r], 0, C);
		}
		
		int x = top.x+dx[td[0]];
		int y = top.y+dy[td[0]];
		newRoom[x][y] = 0;
		int i=0;
		while(true) {
			int nx = x+dx[td[i]];
			int ny = y+dy[td[i]];
			if(nx<0 || nx>=R || ny<0 || ny>=C) {
				i++;
				continue;
			}
			if(room[nx][ny] == -1) break;
			
			newRoom[nx][ny] = room[x][y]; 
			
			x = nx;
			y = ny;
		}
		
		i=0;
		x = bottom.x+dx[bd[0]];
		y = bottom.y+dy[bd[0]];
		newRoom[x][y] = 0;
		while(true) {
			int nx = x+dx[bd[i]];
			int ny = y+dy[bd[i]];
			if(nx<0 || nx>=R || ny<0 || ny>=C) {
				i++;
				continue;
			}
			if(room[nx][ny] == -1) break;
			
			newRoom[nx][ny] = room[x][y]; 
			
			x = nx;
			y = ny;
		}
		room = newRoom;
	}
	
	private static int getDustAmount() {
		int cntDust = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(room[r][c] == 0 || room[r][c] == -1) continue;
				cntDust += room[r][c];
			}
		}
		return cntDust;
	}
}
