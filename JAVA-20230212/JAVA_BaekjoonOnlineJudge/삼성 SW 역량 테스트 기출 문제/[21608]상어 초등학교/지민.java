import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static int N;
	static Node[] students;
	static int[][] studentsLikes;
	static int[][] seats;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Node implements Comparable<Node>{
		int n, x, y;
		Node(int n, int x, int y){
			this.n = n;
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Node o) {
			int c = Integer.compare(this.x, o.x);
			if(c==0)
				return Integer.compare(this.y, o.y);
			return c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		students = new Node[N*N+1];
		for(int i=1; i<=N*N; i++) {
			students[i] = new Node(-1, -1, -1);
		}
		
		seats = new int[N][N];
		studentsLikes = new int[N*N+1][4];
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int stuNum = Integer.parseInt(st.nextToken());
			
			ArrayList<Node> stuFavorites = new ArrayList<>();
			for(int j=0; j<4; j++) {
				int favorite = Integer.parseInt(st.nextToken());
				studentsLikes[stuNum][j] = favorite;
			}
			
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(seats[j][k] != 0) continue;
					stuFavorites.add(new Node(stuNum, j, k));
				}
			}
			
			ArrayList<Node> favoriteCandidate = findFavoriteSeat(stuFavorites);
			if(favoriteCandidate.size() == 1) {
				int fx = favoriteCandidate.get(0).x;
				int fy = favoriteCandidate.get(0).y;
				seats[fx][fy] = stuNum;
				students[stuNum].n = stuNum;
				students[stuNum].x = fx;
				students[stuNum].y = fy;
				continue;
			}
			
			PriorityQueue<Node> emptyCandidate = findEmptySeat(favoriteCandidate);
			Node stuSeat = emptyCandidate.poll();
			seats[stuSeat.x][stuSeat.y] = stuNum;
			students[stuNum].n = stuNum;
			students[stuNum].x = stuSeat.x;
			students[stuNum].y = stuSeat.y;
		}
				
		int sum = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int student = seats[r][c];
				
				int cnt = 0;
				boolean[] check = new boolean[4];
				for(int i=0; i<4; i++) {
					int nx = r+dx[i];
					int ny = c+dy[i];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					int friends = seats[nx][ny];
					for(int j=0; j<4; j++) {
						if(check[j]) continue;
						if(friends == studentsLikes[student][j]) {
							check[j] = true;
							cnt++;
						}
					}
				}
				if(cnt == 0) continue;
				else {
					sum+= Math.pow(10, cnt-1);
				}
			}
		}
		System.out.println(sum);
	}


	private static ArrayList<Node> findFavoriteSeat(ArrayList<Node> stuList) {
		ArrayList<Node> candidate = new ArrayList<>();
		
		int maxCnt = 0;
		for(Node node: stuList) {
			int num = node.n;
			int x = node.x;
			int y = node.y;
			
			int cnt = 0;
			boolean[] check = new boolean[4];
			for(int idx=0; idx<4; idx++) {
				int nx = x+dx[idx];
				int ny = y+dy[idx];
				
				if(nx<0 || N<=nx || ny<0 || N<=ny) continue;
				
				
				int friend = seats[nx][ny];
				for(int i=0; i<4; i++) {
					if(check[i]) continue;
					if(friend != studentsLikes[num][i]) continue;
					check[i] = true;
					cnt++;
				}
			}
				
			if(cnt > maxCnt) {
				maxCnt = cnt;
				candidate.clear();
				candidate.add(new Node(num, x, y));
			}else if(cnt == maxCnt) {
				candidate.add(new Node(num, x, y));
			}
		}
		return candidate;
	}
	
	
	private static PriorityQueue<Node> findEmptySeat(ArrayList<Node> stuList) {
		PriorityQueue<Node> candidate = new PriorityQueue<>();
		
		int maxCnt = 0;
		for(Node node: stuList) {
			int num = node.n;
			int x = node.x;
			int y = node.y;
			
			int cnt = 0;
			for(int idx=0; idx<4; idx++) {
				int nx = x+dx[idx];
				int ny = y+dy[idx];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(seats[nx][ny] != 0) continue;
				cnt++;
			}
			if(maxCnt<cnt) {
				maxCnt = cnt;
				candidate.clear();
				candidate.add(new Node(num, x, y));
			}else if(maxCnt == cnt) {
				candidate.add(new Node(num, x, y));
			}
		}
		return candidate;
	}
}
