import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, tempM, minDistance = Integer.MAX_VALUE;
	static int[][] city;
	static Node[] myChicken;
	static ArrayList<Node> chickenList, houseList;
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][N];
		chickenList = new ArrayList<>();
		houseList = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				city[r][c] = Integer.parseInt(st.nextToken());
				if(city[r][c] == 1) houseList.add(new Node(r, c));
				else if(city[r][c] == 2) chickenList.add(new Node(r, c));
			}
		}
		
		for(int m=1; m<=M; m++) {
			tempM = m;
			myChicken = new Node[m];
			setChicken(0, 0);
		}
		
		System.out.println(minDistance);
	}

	private static void setChicken(int cnt, int idx) {
		if(cnt == tempM) {
			minDistance = Math.min(minDistance, getChickenDistance());
			return;
		}
		
		for(int i=idx; i<chickenList.size(); i++) {
			myChicken[cnt] = chickenList.get(i);
			setChicken(cnt+1, i+1);
		}
	}

	private static int getChickenDistance() {
		int sumD = 0;
		for(Node h : houseList) {
			int d = Integer.MAX_VALUE;
			for(Node c : myChicken) {
				d = Math.min(d, (Math.abs(c.x-h.x) + Math.abs(c.y-h.y)));
			}
			sumD += d;
		}
		return sumD;
	}
}
