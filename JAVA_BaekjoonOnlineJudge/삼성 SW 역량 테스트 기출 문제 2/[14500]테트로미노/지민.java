import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, max;
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
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				calcTetromino(r, c);
			}
		}
		System.out.println(max);
	}
	
	public static void calcTetromino(int r, int c) {
		dirc: for(int i=0; i<tetromino.length; i++) {
			int sum = 0;
			for(int j=0; j<4; j++) {
				int nr = r+tetromino[i][j][0];
				int nc = c+tetromino[i][j][1];
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue dirc;
				
				sum+=paper[nr][nc];
			}
			max = Math.max(max, sum);
		}
	}
}
