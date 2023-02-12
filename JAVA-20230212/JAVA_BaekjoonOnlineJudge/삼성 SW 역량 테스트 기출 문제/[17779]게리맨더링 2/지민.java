import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;


public class Main {
	
	static int N, totalCnt, minDiffer = Integer.MAX_VALUE;
	static int[][] city;
	static boolean[][] isBoundary;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, 1, -1};
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			Node n = (Node)obj;
			if(n.hashCode() != this.hashCode()) return false;
			if(n.x != this.x) return false;
			if(n.y != this.y) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		
		city = new int[N][N];
		for(int x=0; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y=0; y<N; y++) {
				city[x][y] = Integer.parseInt(st.nextToken());
				totalCnt+=city[x][y];
			}
		}
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				for(int d1=1; d1<N; d1++) {
					for(int d2=1; d2<N; d2++) {
						if(x+d1+d2 >= N) continue;
						if(y-d1<0 || y+d2>=N) continue;
						
						getWardCnt(x, y, d1, d2);
					}
				}
			}
		}
		
		System.out.println(minDiffer);		
	}


	private static void getWardCnt(int x, int y, int d1, int d2) {
		
		isBoundary = new boolean[N][N];
		for(int i=0; i<=d1; i++) {
			isBoundary[x+i][y-i] = true;
			isBoundary[x+d2+i][y+d2-i] = true;
		}
		for(int i=0; i<=d2; i++) {
			isBoundary[x+i][y+i] = true;
			isBoundary[x+d1+i][y-d1+i] = true;
		}
		
		
		
		int[] ward = new int[5];
		int sumCnt = 0;
		for(int r=0; r<x+d1; r++) {
			for(int c=0; c<=y; c++) {
				if(isBoundary[r][c]) break;
				ward[0]+=city[r][c];
			}
		}
		sumCnt+=ward[0];
		
		for(int r=0; r<=x+d2; r++) {
			for(int c=N-1; c>y; c--) {
				if(isBoundary[r][c]) break;
				ward[1]+=city[r][c];
			}
		}
		sumCnt+=ward[1];
		
		for(int r=x+d1; r<N; r++) {
			for(int c=0; c<y-d1+d2; c++) {
				if(isBoundary[r][c]) break;
				ward[2]+=city[r][c];
			}
		}
		sumCnt+=ward[2];
		
		for(int r=x+d2+1; r<N; r++) {
			for(int c=N-1; c>=y-d1+d2; c--) {
				if(isBoundary[r][c]) break;
				ward[3]+=city[r][c];
			}
		}
		sumCnt+=ward[3];
		
		ward[4] = totalCnt-sumCnt;
				
		Arrays.sort(ward);
		minDiffer = Math.min(minDiffer, ward[4]-ward[0]);
		
	}
}
