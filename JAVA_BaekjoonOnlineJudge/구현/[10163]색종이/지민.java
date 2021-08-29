import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N;
	static int[][] field;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		field = new int[1001][1001];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			for(int r=row; r<row+h; r++) {
				for(int c=col; c<col+w; c++) {
					field[r][c] = i;
				}
			}
		}
		
		int[] cnt = new int[N];
		for(int r=0; r<1001; r++) {
			for(int c=0; c<1001; c++) {
				if(field[r][c] == 0) continue;
				cnt[field[r][c]-1]++;
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.println(cnt[i]);
		}
	}
}
