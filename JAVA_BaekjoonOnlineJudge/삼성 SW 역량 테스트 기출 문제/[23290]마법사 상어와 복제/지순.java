import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_마법사상어와복제_23290 {
	static int M, S;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] mx = {-1, 0, 1, 0};
	static int[] my = {0, -1, 0, 1};
	static ArrayList<Point>[][] map;
	static ArrayList<Point>[][] copyMap;
	static int sx, sy;
	static int[][] fishSmell;
	static int[] tmp, arr;
	static int maxVal;
	static class Point{
		int x, y, d;
		Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[4][4];
		copyMap = new ArrayList[4][4];
		fishSmell = new int[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				map[i][j] = new ArrayList<>();
				copyMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken())-1;
			int fy = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			map[fx][fy].add(new Point(fx, fy, d));
		}
		
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken())-1;
		sy = Integer.parseInt(st.nextToken())-1;
		
		for(int i=0;i<S;i++) {
			copyMagic();
			moveFish();
			smell();
//			System.out.println("물고기 이동");
			print();
			removeSmall(i+1);
			moveShark(i+1);
//			System.out.println("상어 이동");
//			print();
			copyMagitLoad();
//			System.out.println("결과");
//			print();
		}
		
		int result = 0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				result += map[i][j].size();
			}
		}
		System.out.println(result);
	}

	private static void smell() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(fishSmell[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static void copyMagitLoad() {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<copyMap[i][j].size();k++) {
					map[i][j].add(copyMap[i][j].get(k));
				}
			}
		}
	}

	private static void print() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<map[i][j].size();k++) {
					System.out.println("좌표" + i+" "+j +" "+map[i][j].get(k).d);
				}
//				if(map[i][j].size() == 0) {
//					System.out.print("-1" +" ");
//				}else {
//					System.out.print(map[i][j].get(0).d+" ");
//				}
			}
//			System.out.println();
		}
	}
	
	private static void print2() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<copyMap[i][j].size();k++) {
					System.out.println("좌표" + i+" "+j +" "+copyMap[i][j].get(k).d);
				}
//				if(map[i][j].size() == 0) {
//					System.out.print("-1" +" ");
//				}else {
//					System.out.print(map[i][j].get(0).d+" ");
//				}
			}
//			System.out.println();
		}
	}
	
	private static void copyMagic() {
		for(int i = 0;i<4;i++) {
			copyMap[i] = map[i].clone();
		}
	}
	
	private static void moveFish() {
		ArrayList<Point>[][] temp = new ArrayList[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				temp[i][j] = new ArrayList<>();
			}
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<map[i][j].size();k++) {
					int x = map[i][j].get(k).x;
					int y = map[i][j].get(k).y;
					int d = map[i][j].get(k).d;
					
					boolean check = false;
					for(int l=0;l<8;l++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
//						System.out.println(nx+" "+ny);
						if(nx>=0 && nx<4 && ny>=0 && ny<4) {
							if(!(nx == sx && ny == sy) && fishSmell[nx][ny] == 0) {
								temp[nx][ny].add(new Point(nx, ny, d));
								check = true;
								break;
							}
						}
						
						d = (8+d-1)%8;
					}
					
					if(!check) {
						temp[x][y].add(new Point(x, y, map[i][j].get(k).d));
					}
				}
			}
		}
		map = temp;
	}
	
	private static void removeSmall(int time) {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(fishSmell[i][j] == 0) continue;
				if(time-fishSmell[i][j] == 2)
					fishSmell[i][j] = 0;
			}
		}
	}
	
	private static void moveShark(int time) {
		arr = new int[3];
		tmp = new int[3];
		maxVal = -1;
		dfs(0);
		
		for(int i=0;i<3;i++) {
			int nx = sx + mx[arr[i]];
			int ny = sy + my[arr[i]];
			
			if(map[nx][ny].size() != 0) {
//				System.out.println(nx+" "+ny);
				fishSmell[nx][ny] = time;
				map[nx][ny].clear();
			}
			sx = nx;
			sy = ny;
//			System.out.println("상어 이동: "+nx+" "+ny);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println("상어 위치: "+sx+" "+sy);
	}
	
	private static void dfs(int idx) {
		if(idx == 3) {
			
			boolean[][] visited = new boolean[4][4];
			
			int cnt = 0;
			int x = sx, y = sy;
			for(int i=0;i<3;i++) {
				int dir = tmp[i];
				int nx = x + mx[dir];
				int ny = y + my[dir];
				
				if(nx<0 || nx>=4||ny<0||ny>=4 || visited[nx][ny]) {
					cnt = -1;
					break;
				}
				visited[nx][ny] = true;
				cnt += map[nx][ny].size();
				x = nx;
				y = ny;
			}
			
			if(cnt > maxVal) {
				arr = tmp.clone();
				maxVal = cnt;
			}
//			System.out.println(Arrays.toString(tmp));
//			System.out.println(cnt);
			return;
		}
		
		for(int i=0;i<4;i++) {
			tmp[idx] = i;
			dfs(idx+1);
		}
	}
}
