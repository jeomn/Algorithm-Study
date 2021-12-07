import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_마법사상어와블리자드_21611 {
	static int N, M;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] tx = {0, 1, 0, -1};
	static int[] ty = {-1, 0, 1, 0};
	static int cnt=0, result=0;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0) {
					cnt++;
				}
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
	
			magic(d, s);
			move();
			bomb();
			change();
			setMap();
		}
		System.out.println(result);
	}
	
	private static void setMap() {
		for(int i=0;i<N;i++) {
			Arrays.fill(map[i], 0);
		}
		int x = (N)/2;
		int y = (N)/2;
		int dir = 0;
		int t = 1; // 하나하나, 둘둘,
		int idx = 0;
		while(idx != cnt) {
			for(int k=0;k<2;k++) {
				for(int i=0;i<t;i++) {
					int nx = x + tx[dir];
					int ny = y + ty[dir];
					
					map[nx][ny] = list.get(idx++);
					
					x = nx;
					y = ny;
					if(idx == cnt) return;
				}
				dir = (dir+1)%4;
			}
			t++;
		}
	}
	
	private static void change() {
		ArrayList<Integer> temp = new ArrayList<>();
		int count = 0;
		
		for(int i=0;i<list.size();i++) {
			if(temp.size()>=N*N-1) break;
			
			count = i;
			while(count<list.size() && list.get(i) == list.get(count)) {
				count++;
			}
			int nCnt = count-i;
			int num = list.get(i);
			
			temp.add(nCnt);
			temp.add(num);
			i = count-1;
		}
		if(temp.size()>=N*N-1) {
			temp.subList(0, N*N-2);
		}
		list = temp;
		cnt = list.size();
	}
	private static void bomb() {
		while(true) {
			boolean check = false;
			ArrayList<Integer> temp = new ArrayList<>();
			
			int count = 0;
			
			for(int i=0;i<list.size();i++) {
				count = i;
				
				while(count<list.size() && list.get(i) == list.get(count)) {
					count++;
				}
				
				if(count-i < 4) {
					for(int j=i;j<count;j++) {
						temp.add(list.get(j));
					}
				} else { // 크다면
					result += list.get(i)*(count-i);
					check = true;
				}
				i = count-1;
			}
			list = temp;
			if(!check) break;
		}
	}
	private static void move() {
		list = new ArrayList<>();
		
		int x = (N)/2;
		int y = (N)/2;
		int dir = 0;
		int t = 1; // 하나하나, 둘둘,
		int idx = 0;
		while(idx != cnt) {
			for(int k=0;k<2;k++) {
				for(int i=0;i<t;i++) {
					int nx = x + tx[dir];
					int ny = y + ty[dir];
					if(map[nx][ny] > 0) {
						list.add(map[nx][ny]);
						idx++;
					}
					x = nx;
					y = ny;
					if(idx == cnt) return;
				}
				dir = (dir+1)%4;
			}
			t++;
		}
	}
	private static void magic(int d, int s) {
		for(int i=1;i<=s;i++) {
			int nx = (N)/2 + dx[d]*i;
			int ny = (N)/2 + dy[d]*i;
			
			if(map[nx][ny] == 0) continue;
			map[nx][ny] = 0;
			cnt--;
		}
	}
}
