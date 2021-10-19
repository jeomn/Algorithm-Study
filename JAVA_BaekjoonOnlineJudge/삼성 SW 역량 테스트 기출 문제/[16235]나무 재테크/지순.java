import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_나무재테크_16235 {
	static int[][] A;
	static int[][] L;
	static ArrayList<Point> trees;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Point implements Comparable<Point> {
		int x, y, z;

		Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return Integer.compare(o.z, this.z);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		L = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				L[i][j] = 5;
			}
		}

		trees = new ArrayList<Point>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			trees.add(new Point(x, y, z));
		}
		Collections.sort(trees);
		treeInvestment(N, K);
		System.out.println(trees.size());
	}

	private static void treeInvestment(int N, int K) {
		for (int i = 0; i < K; i++) {
			ArrayList<Point> dTree = new ArrayList<>();
			int size = trees.size();
			for (int j = size-1; j >= 0; j--) {
				Point p = trees.remove(j);
				if (L[p.x][p.y] < p.z) {
					dTree.add(new Point(p.x, p.y, p.z));
					continue;
				}

				L[p.x][p.y] -= p.z;
				trees.add(new Point(p.x, p.y, p.z + 1));
			}

			for(Point p : dTree) {
				L[p.x][p.y] += p.z / 2;
			}

			size = trees.size();
			for (int j = 0; j < size; j++) {
				Point p = trees.get(j);

				if (p.z % 5 == 0) {
					for (int k = 0; k < 8; k++) {
						int nx = p.x + dx[k];
						int ny = p.y + dy[k];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;
						trees.add(new Point(nx, ny, 1));
					}
				}
			}
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					L[j][k] += A[j][k];
				}
			}
			Collections.sort(trees);
		}
	}
}


------------------------------------------------------------------------------------------------------------------------------------------------------------
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_나무재테크_16235 {
	static int N;
	static int M;
	static int K;
	static int[][] A;
	static int[][] L;
	static PriorityQueue<Point> trees;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static class Point implements Comparable<Point>{
		int x, y, z;
		Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.z, o.z);
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		L = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				L[i][j] = 5;
			}
		}
		
		trees = new PriorityQueue<Point>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			trees.add(new Point(x, y, z));
		}
		treeInvestment();
		System.out.println(trees.size());
	}
	
	private static void treeInvestment() {
		for(int i=0;i<K;i++) {
			ArrayList<Point> dTree = new ArrayList<>();
			ArrayList<Point> bTree = new ArrayList<>();
			PriorityQueue<Point> newTrees = new PriorityQueue<>();

			while(!trees.isEmpty()) {
				Point p = trees.poll();
				if(L[p.x][p.y] < p.z) {
					dTree.add(new Point(p.x,p.y, p.z));
					continue;
				}
				
				L[p.x][p.y]-= p.z; 
				newTrees.add(new Point(p.x, p.y, p.z+1));
				
				if((p.z+1)%5==0) {
					bTree.add(new Point(p.x, p.y, p.z+1));
				}
			}
			
			for(Point p:dTree) {
				L[p.x][p.y]+= p.z/2; 
			}
			
			for(Point p:bTree) {
				for(int j=0;j<8;j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					newTrees.add(new Point(nx, ny, 1));
				}
			}
			
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					L[j][k] += A[j][k];
				}
			}
			trees.addAll(newTrees);
		}
	}
}
