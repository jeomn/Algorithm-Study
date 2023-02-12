import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static int R = 3, C = 3;
	static int[][] arr;
	static HashMap<Integer, Integer> countMap;
	static PriorityQueue<Number> sortQueue;
	static class Number implements Comparable<Number>{
		int n, cnt;
		Number(int n, int cnt){
			this.n = n;
			this.cnt = cnt;
		}
		
		public int compareTo(Number o) {
			int x = this.cnt-o.cnt;
			if(x == 0) {
				return this.n-o.n;
			}
			return x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[100][100];
		
		for(int x=0; x<3; x++) {
			st = new StringTokenizer(br.readLine());
			for(int y=0; y<3; y++) {
				arr[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		countMap = new HashMap<>();
		sortQueue = new PriorityQueue<>();
		while(arr[r][c] != k) {
			if(time == 100) {
				time = -1;
				break;
			}
			if(R>=C) calcR();				
			else calcC();
			time++;
		}
		
		System.out.println(time);	
	}

	private static void calcR() {
		int maxColumnCnt = 0;
		int[] newRow;
		for(int r=0; r<R; r++) {
			newRow = new int[100];
			for(int c=0; c<C; c++) {
				if(arr[r][c] == 0) continue;
				countMap.put(arr[r][c], countMap.getOrDefault(arr[r][c], 0)+1);
			}
			for(Map.Entry<Integer, Integer> e : countMap.entrySet()) {
				Number n = new Number(e.getKey(), e.getValue());
				sortQueue.add(n);
			}
			int idx=0;
			int length = sortQueue.size()*2;
			int size = (length>=100)? 100:length;
			while(!sortQueue.isEmpty()) {
				Number n = sortQueue.poll();
				newRow[idx++] = n.n;
				newRow[idx++] = n.cnt;
				if(idx == 100) break;
			}
			arr[r] = newRow;			
			maxColumnCnt = Math.max(maxColumnCnt, size);
			
			countMap.clear();
			sortQueue.clear();
		}
		C = maxColumnCnt;
	
	}

	private static void calcC() {
		int maxRowCnt = 0;
		for(int c=0; c<C; c++) {
			for(int r=0; r<R; r++) {
				if(arr[r][c] == 0) continue;
				countMap.put(arr[r][c], countMap.getOrDefault(arr[r][c], 0)+1);
			}
			for(Map.Entry<Integer, Integer> e : countMap.entrySet()) {
				Number n = new Number(e.getKey(), e.getValue());
				sortQueue.add(n);
			}
			int idx=0;
			int length = sortQueue.size()*2;
			int size = (length>=100)? 100:length;
			while(!sortQueue.isEmpty()) {
				Number n = sortQueue.poll();
				arr[idx++][c] = n.n;
				arr[idx++][c] = n.cnt;
				if(idx == 100) break;
			}
			if(size < C) {
				for(int r=size; r<C; r++) {
					arr[r][c] = 0;
				}
			}
			maxRowCnt = Math.max(maxRowCnt, size);
			countMap.clear();
			sortQueue.clear();
		}
		R = maxRowCnt;
		
	}
}
