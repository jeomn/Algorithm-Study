import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, k;
	static Smell[][] smellSpace;
	static Shark[][] newSpace;
	static HashMap<Integer, Shark> sharkMap, newSharkMap;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Shark{
		int n, r, c, d;
		int[][] priorityD;
		Shark(int n, int r, int c){
			this.n = n;
			this.r = r;
			this.c = c;
			priorityD = new int[4][4];
		}
	}
	static class Smell{
		int n, t;
		Smell(int n, int t){
			this.n = n;
			this.t = t;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			smellSpace = new Smell[N][N];
			sharkMap = new HashMap<>();
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					int num = Integer.parseInt(st.nextToken());
					if(num==0) continue;
					Shark shark = new Shark(num, r, c);
					sharkMap.put(num, shark);
					Smell smell = new Smell(num, k);
					smellSpace[r][c] = smell;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<M+1; i++) {
				int d = Integer.parseInt(st.nextToken());
				sharkMap.get(i).d = d-1;
			}
			
			for(int i=1; i<M+1; i++) {
				for(int j=0; j<4; j++) {
					st = new StringTokenizer(br.readLine());
					Shark s = sharkMap.get(i);
					for(int l=0; l<4; l++) {
						int d = Integer.parseInt(st.nextToken())-1;
						s.priorityD[j][l] = d;
					}
				}
			}
			
			int time = 0, cnt = 0, nr = 0, nc = 0;
			boolean emptyFlag, smellFlag;
			while(time<1001) {
				
				newSharkMap = new HashMap<>();
				newSpace = new Shark[N][N];
				
				for(int n=1; n<=M; n++){
					Shark shark = sharkMap.getOrDefault(n, null);
                    if(shark == null) continue;
					
					cnt=0;
					emptyFlag = false;
					//빈 칸 찾기
					int bestD = -1;
					for(int d : shark.priorityD[shark.d]) {
						nr = shark.r+dx[d];
						nc = shark.c+dy[d];
						if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
						if(smellSpace[nr][nc] != null) continue;
						emptyFlag = true;
						bestD = d;
						break;
					}
					
					if(emptyFlag) {
						nr = shark.r+dx[bestD];
						nc = shark.c+dy[bestD];
						if(newSpace[nr][nc] != null) continue;
						
						shark.r = nr;
						shark.c = nc;
						shark.d = bestD;
						newSharkMap.put(n, shark);
						newSpace[nr][nc] = shark;
						continue;
					}
					
					cnt=0;
					smellFlag = false;
					//내 냄새 찾기
					bestD = -1;
					for(int d : shark.priorityD[shark.d]) {
						nr = shark.r+dx[d];
						nc = shark.c+dy[d];
						if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
						if(smellSpace[nr][nc] == null) continue;
						if(smellSpace[nr][nc].n != shark.n) continue;
						smellFlag = true;
						bestD = d;
						break;
					}
					
					if(smellFlag) {
						nr = shark.r+dx[bestD];
						nc = shark.c+dy[bestD];
						if(newSpace[nr][nc] != null) continue;
						shark.r = nr;
						shark.c = nc;
						shark.d = bestD;
						newSharkMap.put(n, shark);
						newSpace[shark.r][shark.c] = shark;
					}
				}
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						Smell smell = smellSpace[r][c];
						if(smell == null) continue;
						smell.t-=1;
						if(smell.t==0) smellSpace[r][c] = null;
					}
				}
				
				sharkMap = newSharkMap;
				
				for(int n : sharkMap.keySet()) {
					Shark s = sharkMap.get(n);
					smellSpace[s.r][s.c] = new Smell(s.n, k);
				}
				time++;
				if(sharkMap.size()==1) break;
			}
			
			time = (time>1000)? -1:time;
			System.out.println(time);
	}
}
