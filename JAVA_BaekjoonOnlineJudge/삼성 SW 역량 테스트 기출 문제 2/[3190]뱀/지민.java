import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] board;
	static Deque<Node> snake;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static class Command{
		int x;
		char c;
		Command(int x, char c){
			this.x = x;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			board[r][c] = 2;
		}
		
		int L = Integer.parseInt(br.readLine());
		HashMap<Integer, Command> cmdMap = new HashMap<>();
		for(int l=0; l<L; l++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			cmdMap.put(X, new Command(X, C));
		}
		
		snake = new LinkedList<>();
		snake.add(new Node(0, 0));
		board[0][0] = 1;
		int r = 0, c = 0, d = 1, time = 0;
		while(true) {
			time++;
			r += dx[d];
			c += dy[d];
			
			if(r<0 || r>=N || c<0 || c>=N || board[r][c]==1) {
				System.out.println(time);
				return;
			}
			if(board[r][c]!=2) {	//사과가 아닐 때
				Node tail = snake.pollLast();
				board[tail.r][tail.c] = 0;
			}
			board[r][c] = 1;
			snake.offerFirst(new Node(r, c));
			
			if(cmdMap.containsKey(time)) {
				Command cmd = cmdMap.get(time);
				if(cmd.c=='L') d = (d+3)%4;
				else d = (d+1)%4;
			}
		}
	}
}