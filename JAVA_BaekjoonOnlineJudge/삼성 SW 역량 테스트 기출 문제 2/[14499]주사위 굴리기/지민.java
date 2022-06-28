import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int[] dice;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		dice = new int[7];
		for(int k=0; k<K; k++) {
			int dirc = Integer.parseInt(st.nextToken())-1;
			
			int nx = x+dx[dirc];
			int ny = y+dy[dirc];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

			rollDice(dirc);
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			}else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}
			System.out.println(dice[1]);
			
			x = nx;
			y = ny;
		}
	}

	private static void rollDice(int dirc) {
		if(dirc == 0) {	//동쪽
			int temp = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = dice[1];
			dice[1] = temp;
		}else if(dirc == 1) {	//서쪽
			int temp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
		}else if(dirc == 2) {	//북쪽
			int temp = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = temp;
		}else {	//남
			int temp = dice[6];
			dice[6] = dice[5];
			dice[5] = dice[1];
			dice[1] = dice[2];
			dice[2] = temp;
		}
	}
}
