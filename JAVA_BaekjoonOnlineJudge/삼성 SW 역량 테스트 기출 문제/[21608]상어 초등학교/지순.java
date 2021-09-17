import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_상어초등학교_21608 {
	static Map<Integer, Object> map;
	static int[][] arr;
	static int N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		map = new HashMap<>();
		
		StringTokenizer st;
		for(int i=0;i<N*N;i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ArrayList<Integer> list = new ArrayList<>();
			list.add(a);
			list.add(b);
			list.add(c);
			list.add(d);
			map.put(student, list);
			solve(student);
		}
		int result = 0;
		int[] satisfaction = new int[] {0,1,10,100,1000};
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int cnt = 0;
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx<0 || nx >= N || ny<0 || ny>=N) continue;
					ArrayList<Integer> nums = (ArrayList<Integer>) map.get(arr[i][j]);
					
					if(nums.contains(arr[nx][ny]))
						cnt++;
				}
				result+=satisfaction[cnt];
			}
		}
		System.out.println(result);
	}

	private static void solve(int student) {
		int like = -1;
		int zero = -1;
		int x = -1;
		int y = -1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j] != 0) {
					continue;
				}
				int likecnt = 0;
				int zerocnt = 0;
				for(int k = 0;k<4;k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					if(nx<0 || nx >= N || ny<0 || ny>=N) continue;
					ArrayList<Integer> nums = (ArrayList<Integer>) map.get(student);
					
					if(nums.contains(arr[nx][ny]))
						likecnt++;
					else if(arr[nx][ny] == 0)
						zerocnt++;
				}
				
				if(like<likecnt) {
					like = likecnt;
					x = i;
					y = j;
					zero = zerocnt;
				} else if(like == likecnt) {
					if(zero < zerocnt) {
						zero = zerocnt;
						x=i;
						y=j;
					} else if(zero == zerocnt) {
						if(i<x) {
							x = i;
							y = j;
						}
						else if(i == x) {
							if(j<y) {
								y = j;
								x = i;
							}
						}
					}
				}
			}
		}
		arr[x][y] = student;
	}
}
