import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
	static int[][] disk, newDisk;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		disk = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				disk[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<k; i++) {
				for(int nx=x; nx-1<N; nx+=x) {
					rotateDisk(nx-1, d);
				}
			}
			
			numberOperation();
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int num = disk[i][j];
				if(num==0) continue;
				
				answer+=num;
			}
		}
		
		System.out.println(answer);
	}

	private static void rotateDisk(int x, int d) {
		if(d==0) {
			int temp = disk[x][M-1];
			for(int i=M-1; i>0; i--) {
				disk[x][i] = disk[x][i-1];
			}
			disk[x][0] = temp;
		}else {
			int temp = disk[x][0];
			for(int i=0; i<M-1; i++) {
				disk[x][i] = disk[x][i+1];
			}
			disk[x][M-1] = temp;
		}
	}
	
	private static void numberOperation() {
		newDisk = new int[N][M];
		
		boolean flag = false;
		double sum = 0, cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int num = disk[i][j];
				if(num==0) continue;
				
				sum+=num;
				cnt++;
				
				boolean subFlag = false;
				for(int d=0; d<4; d++) {
					int nx = i+dx[d];
					int ny = (j+dy[d]+M)%M;
					
					if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
					if(disk[nx][ny] != num) continue;
					
					newDisk[nx][ny] = 0;
					subFlag  = true;
					flag = true;
				}
				if(subFlag) newDisk[i][j] = 0;
				else newDisk[i][j] = disk[i][j];
			}
		}
		
		if(flag) {
			disk = newDisk;
			return;
		}
		
		double avg = sum/cnt;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int num = disk[i][j];
				if(num==0) continue;
				
				if(num > avg) disk[i][j]--;
				else if(num < avg) disk[i][j]++;
			}
		}
	}
}
