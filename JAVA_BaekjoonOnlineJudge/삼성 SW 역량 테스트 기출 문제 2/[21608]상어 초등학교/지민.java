import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] classroom;
	static HashSet<Integer> favorite;
	static HashSet<Integer>[] studentInfo;
	static PriorityQueue<Seat> candidates;
	static class Seat implements Comparable<Seat>{
		int favorite, empty, r, c;
		Seat(int favorite, int empty, int r, int c){
			this.favorite = favorite;
			this.empty = empty;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Seat o) {
			int x = o.favorite-this.favorite;
			if(x==0) {
				int y = o.empty-this.empty;
				if(y==0) {
					int z = this.r - o.r;
					if(z==0) return this.c-o.c;
					return z;
				}
				return y;
			}
			return x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		classroom = new int[N][N];
		favorite = new HashSet<Integer>();
		studentInfo = new HashSet[N*N];
		candidates = new PriorityQueue<Seat>();
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int student = Integer.parseInt(st.nextToken());
			favorite.clear();
			for(int j=0; j<4; j++) {
				favorite.add(Integer.parseInt(st.nextToken()));
			}
			studentInfo[student-1] = (HashSet<Integer>) favorite.clone();
			
			candidates.clear();
			findSeat(student);
		}
		
		System.out.println(getStudentSatisfaction());
	}

	private static void findSeat(int student) {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(classroom[r][c] != 0) continue;
				
				int fCnt = 0, eCnt = 0;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					int seat = classroom[nr][nc];
					if(seat == 0) eCnt++;
					if(favorite.contains(seat)) fCnt++;
					candidates.add(new Seat(fCnt, eCnt, r, c));
				}
			}
		}
		Seat best = candidates.peek();
		classroom[best.r][best.c] = student;
	}
	
	private static int getStudentSatisfaction() {
		int score = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int stu = classroom[r][c];
				
				int fCnt=0;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					int seat = classroom[nr][nc];
					if(studentInfo[stu-1].contains(seat)) fCnt++;
				}
				if(fCnt==0) continue;
				score+=Math.pow(10.0, fCnt-1);
			}
		}
		return score;
	}
}


/**********************************************************************************************************************************************/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] classroom;
	static HashSet<Integer> favorite;
	static HashSet<Integer>[] studentInfo;
	static class Seat implements Comparable<Seat>{
		int favorite, empty, r, c;
		Seat(int favorite, int empty, int r, int c){
			this.favorite = favorite;
			this.empty = empty;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Seat o) {
			int x = o.favorite-this.favorite;
			if(x==0) {
				int y = o.empty-this.empty;
				if(y==0) {
					int z = this.r - o.r;
					if(z==0) return this.c-o.c;
					return z;
				}
				return y;
			}
			return x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		classroom = new int[N][N];
		favorite = new HashSet<Integer>();
		studentInfo = new HashSet[N*N];
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int student = Integer.parseInt(st.nextToken());
			favorite.clear();
			for(int j=0; j<4; j++) {
				favorite.add(Integer.parseInt(st.nextToken()));
			}
			studentInfo[student-1] = (HashSet<Integer>) favorite.clone();
			
			findSeat(student);
		}
		
		System.out.println(getStudentSatisfaction());
	}

	private static void findSeat(int student) {
		Seat best = new Seat(-1, -1, -1, -1);
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(classroom[r][c] != 0) continue;
				
				int fCnt = 0, eCnt = 0;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					int seat = classroom[nr][nc];
					if(seat == 0) eCnt++;
					if(favorite.contains(seat)) fCnt++;
					Seat newSeat = new Seat(fCnt, eCnt, r, c);
					if(best.compareTo(newSeat) > 0) best = newSeat;
				}
			}
		}
		classroom[best.r][best.c] = student;
	}
	
	private static int getStudentSatisfaction() {
		int score = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int stu = classroom[r][c];
				
				int fCnt=0;
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					int seat = classroom[nr][nc];
					if(studentInfo[stu-1].contains(seat)) fCnt++;
				}
				if(fCnt==0) continue;
				score+=Math.pow(10.0, fCnt-1);
			}
		}
		return score;
	}
}
