import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cLength, hLength, minChickenDist;
	static int[] myChicken;
	static int[][] city;
	static ArrayList<Node> house, chicken;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][N];
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				int value = Integer.parseInt(st.nextToken());
				city[r][c] = value;
				if(value==0)continue;
				if(value==1) house.add(new Node(r, c)); 
				else chicken.add(new Node(r, c));
			}
		}
		cLength = chicken.size();
		hLength = house.size();
		
		minChickenDist = Integer.MAX_VALUE;
		myChicken = new int[M];
		chooseChicken(0, 0);
		
		System.out.println(minChickenDist);
	}


	private static void chooseChicken(int cnt, int idx) {
		if(cnt==M) {
			checkChickenDistance();
			return;
		}
		
		for(int i=idx; i<cLength; i++) {
			myChicken[cnt] = i;
			chooseChicken(cnt+1, i+1);
		}
	}


	private static void checkChickenDistance() {
		int chickenDist = 0;
		for(int j=0; j<hLength; j++) {
			Node h = house.get(j);
			int minDist = Integer.MAX_VALUE;
			for(int i=0; i<M; i++) {
				Node c = chicken.get(myChicken[i]);
				int dist = Math.abs(c.r-h.r)+Math.abs(c.c-h.c);
				minDist = Math.min(minDist, dist);
			}
			chickenDist+=minDist;
		}
		minChickenDist = Math.min(minChickenDist, chickenDist);
	}
}
