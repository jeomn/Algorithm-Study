import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int maxScore;
	static int[] dice;
	static int[][] marker;
	static int[][] board = {
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{10, 13, 16, 19, 25},
			{20, 22, 24, 25},
			{30, 28, 27, 26, 25},
			{25, 30, 35, 40},
			{40, 0}
	};
	static boolean[][] isMarker;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		marker = new int[4][4];
		dice = new int[10];
		for(int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		isMarker = new boolean[6][];
		for(int i=0; i<6; i++) {
			isMarker[i] = new boolean[board[i].length];
		}
		
		moveMarker(0, 0, marker);
		
		System.out.println(maxScore);
	}

	private static void moveMarker(int time, int score, int[][] preMarker) {
		if(time == 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int r = preMarker[i][0];
			int c = preMarker[i][1];
			if(r==-1 && c==-1) continue;
			
			int nr = r;
			int nc = c+dice[time];
			int lenLine = board[nr].length;
			if(r>=1 && r<=3 && nc>=lenLine) {
				nr = 4;
				nc -= (lenLine-1);
			}
            
			if(nc >= board[nr].length || (nr==5 && nc>=2)) {
				preMarker[i][0] = -1;
				preMarker[i][1] = -1;
				isMarker[r][c] = false;
				moveMarker(time+1, score, preMarker);
				
				preMarker[i][0] = r;
				preMarker[i][1] = c;
				isMarker[r][c] = true;
				continue;
			}else {
				if(nr==0) {
					if(nc==5) {
						nr = 1;
						nc = 0;
					}else if(nc==10) {
						nr = 2;
						nc = 0;
					}else if(nc==15){
						nr = 3;
						nc = 0;
					}
				}
				if(board[nr][nc]==25) {
					nr = 4;
					nc = 0;
				}
				if(board[nr][nc]==40) {
					nr = 5;
					nc = 0;
				}
				
				if(isMarker[nr][nc]) continue;
				
				isMarker[r][c] = false;
				isMarker[nr][nc] = true;
				preMarker[i][0] = nr;
				preMarker[i][1] = nc;
				moveMarker(time+1, score+board[nr][nc], preMarker);
                
				isMarker[nr][nc] = false;
				isMarker[r][c] = true;
				preMarker[i][0] = r;
				preMarker[i][1] = c;
			}
		}
	}
}
