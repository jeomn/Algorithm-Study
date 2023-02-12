import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, maxSum = Integer.MIN_VALUE;
	static int[][] paper;
	static int[][][] tetromino = {{{0, 0}, {0, 1}, {1, 0}, {1, 1}},	//ㅁ
			{{0, 0}, {0, 1}, {0, 2}, {0, 3}},	//-
			{{0, 0}, {1, 0}, {2, 0}, {3, 0}},
			{{0, 0}, {1, 0}, {2, 0}, {2, 1}},	//L
			{{0, 0}, {1, 0}, {2, 0}, {2, -1}},
			{{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
			{{0, 0}, {1, 0}, {1, 1}, {1, 2}},
			{{0, 0}, {0, 1}, {1, 1}, {2, 1}},
			{{0, 0}, {0, 1}, {1, 0}, {2, 0}},
			{{0, 0}, {1, 0}, {0, 1}, {0, 2}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 2}},
			{{0, 0}, {1, 0}, {1, 1}, {2, 1}},	//z
			{{0, 0}, {1, 0}, {1, -1}, {2, -1}},
			{{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
			{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
			{{0, 0}, {0, 1}, {1, 1}, {0, 2}},	//ㅜ
			{{0, 0}, {0, 1}, {-1, 1}, {0, 2}},
			{{0, 0}, {1, 0}, {1, 1}, {2, 0}},
			{{0, 0}, {-1, 1}, {0, 1}, {1, 1}}};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				calcTetromino(r, c);
			}
		}
		
		System.out.println(maxSum);
	}


	private static void calcTetromino(int r, int c) {
		for(int i=0; i<tetromino.length; i++) {
			int sum = 0;
			for(int j=0; j<4; j++) {
				int nr = r+tetromino[i][j][0];
				int nc = c+tetromino[i][j][1];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				sum+=paper[nr][nc];
			}
			maxSum = Math.max(maxSum, sum);
		}
	}
}
