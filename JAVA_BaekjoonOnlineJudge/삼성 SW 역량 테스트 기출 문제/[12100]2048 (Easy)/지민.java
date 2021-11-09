import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, maxNum;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		playGame(0, board);
		
		System.out.println(maxNum);
	}

	private static void playGame(int cnt, int[][] preGame) {
		if(cnt==5) {
			int num = getMaxNum(preGame);
			maxNum = Math.max(maxNum, num);
			return;
		}
		
		int[][] temp = new int[N][N];
		for(int i=0; i<4; i++) {
			for(int r=0; r<N; r++) {
				System.arraycopy(preGame[r], 0, temp[r], 0, N);
			}
			int[][] postGame = moveBoard(i, temp);
			playGame(cnt+1, postGame);
		}
		
	}

	private static int[][] moveBoard(int d, int[][] game) {		

		if(d==0) {
			for(int c=0; c<N; c++) {
				int idx = 0;
				int preBlock = 0;
				for(int r=0; r<N; r++) {
					if(game[r][c] == 0)continue;
					
					if(game[r][c] == preBlock) {
						game[idx-1][c] = (preBlock*2);
						preBlock = 0;
						game[r][c] = 0;
					}else {
						preBlock = game[r][c];
						game[r][c] = 0;
						game[idx++][c] = preBlock;
					}
				}
			}
			
		}else if(d==1) {
			for(int c=0; c<N; c++) {
				int idx = N-1;
				int preBlock = 0;
				for(int r=N-1; r>=0; r--) {
					if(game[r][c] == 0)continue;
					if(game[r][c] == preBlock) {
						game[idx+1][c] = (preBlock*2);
						preBlock = 0;
						game[r][c] = 0;
					}else {
						preBlock = game[r][c];
						game[r][c] = 0;
						game[idx--][c] = preBlock;
					}
				}
			}
		}else if(d==2) {
			for(int r=0; r<N; r++) {
				int idx = 0;
				int preBlock = 0;
				for(int c=0; c<N; c++) {
					
					if(game[r][c] == 0)continue;
					
					if(game[r][c] == preBlock) {
						game[r][idx-1] = (preBlock*2);
						preBlock = 0;
						game[r][c] = 0;
					}else {
						preBlock = game[r][c];
						game[r][c] = 0;
						game[r][idx++] = preBlock;
					}
				}
			}
		}else {
			for(int r=0; r<N; r++) {
				int idx = N-1;
				int preBlock = 0;
				for(int c=N-1; c>=0; c--) {
					if(game[r][c] == 0)continue;
					if(game[r][c] == preBlock) {
						game[r][idx+1] = (preBlock*2);
						preBlock = 0;
						game[r][c] = 0;
					}else {
						preBlock = game[r][c];
						game[r][c] = 0;
						game[r][idx--] = preBlock;
					}
				}
			}
		}
		return game;
	}

	private static int getMaxNum(int[][] game) {
		int max = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int n = game[r][c];
				if(n==0) continue;
				max = Math.max(max, n);
			}
		}
		
		return max;
	}
}
