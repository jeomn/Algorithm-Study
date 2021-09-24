import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_테트로미노_14500 {
	
	static int[][][] tetromino = {
		{{0,0},{0,1},{0,2},{0,3}}, // ㅡ
		{{0,0},{1,0},{2,0},{3,0}},
		{{0,0},{0,1},{1,0},{1,1}}, // ㅁ
		{{0,0},{1,0},{1,1},{1,2}}, //ㄴ
		{{0,0},{0,1},{0,2},{1,2}},
		{{0,0},{0,1},{1,0},{2,0}},
		{{0,0},{0,1},{1,1},{2,1}},
		{{0,0},{0,1},{0,2},{1,0}},
		{{0,0},{1,0},{2,0},{2,1}},
		{{0,0},{1,0},{2,0},{2,-1}},
		{{0,0},{0,1},{0,2},{-1,2}},
		{{0,0},{0,1},{0,2},{1,1}}, //ㅗ
		{{0,0},{0,1},{0,2},{-1,1}},
		{{0,0},{1,0},{2,0},{1,1}},
		{{0,0},{1,0},{2,0},{1,-1}},
		{{0,0},{0,1},{1,1},{1,2}}, //ㄹ 
		{{0,0},{0,1},{-1,1},{-1,2}},
		{{0,0},{1,0},{1,1},{2,1}},
		{{0,0},{1,0},{1,-1},{2,-1}}
	};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = Integer.MIN_VALUE;
		
		int[][] map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				for(int i=0;i<tetromino.length;i++) {
					int sum = 0;
					boolean check = false;
					for(int j=0;j<4;j++) {
						int nx = n+tetromino[i][j][0];
						int ny = m+tetromino[i][j][1];
						
						if(nx<0 || nx>=N || ny<0 || ny>=M) {
							check = true;
							break;
						}
						sum+=map[nx][ny];
					}
					if(!check)
						result = Math.max(result, sum);
				}
			}
		}
		System.out.println(result);
	}
}
