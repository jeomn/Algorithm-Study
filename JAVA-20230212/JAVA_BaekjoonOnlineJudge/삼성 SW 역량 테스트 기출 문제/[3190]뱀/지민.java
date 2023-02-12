//HashMap 안쓰고 풀어봄.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] board;
	static Deque<int[]> snake;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			board[r][c] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		Queue<int[]> order = new LinkedList<>();
		for(int l=0; l<L; l++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String C = st.nextToken();
			int c = (C.equals("D"))? 1:-1;
			order.add(new int[] {X, c});
		}
		
		board[0][0] = 1;
		snake = new LinkedList<>();
		snake.add(new int[] {0, 0});
		int time = 0, r = 0, c = 0, d = 1, cTime = 0;
		boolean command = true;
		int[] o = new int[2];
		while(true) {
			if(command && !order.isEmpty()) {
				o = order.poll();
				cTime = o[0];
				command = false;
			}
			
			time++;
			r += dx[d];
			c += dy[d];
			
			if(r<0 || r>=N || c<0 || c>=N || board[r][c] == 1) break;
			if(board[r][c] != 2) {
				int[] tail = snake.poll();
				board[tail[0]][tail[1]] = 0;
			}
			board[r][c] = 1;
			snake.addLast(new int[] {r, c});
			
			if(time == cTime) {
				command = true;
				d = ((d+o[1])+4)%4;
			}
		}

		System.out.println(time);
	}

}
