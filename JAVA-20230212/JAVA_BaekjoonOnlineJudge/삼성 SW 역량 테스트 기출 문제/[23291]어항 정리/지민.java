import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, minNum;
	static int[][] fishbowl;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        fishbowl = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	fishbowl[N-1][i] = Integer.parseInt(st.nextToken());
        }
        
        int time = 0;
        while(checkFishNum()) {
        	putNewFish();
        	levitateNRotate();
        	controlFish();
        	lineUp();
        	levitate();
        	controlFish();
        	lineUp();
        	
        	time++;
        }
        
        System.out.println(time);
	}

	private static void putNewFish() {
		for(int i=0; i<N; i++) {
			if(fishbowl[N-1][i]==minNum) {
				fishbowl[N-1][i]++;
			}
		}
	}

	private static void levitateNRotate() {
		int step = 0, col = 0;
		int w = 1, nw = 1;
		int h = 1, nh = 2;
		
		while(nw*nh<=N) {
			for(int r=N-1; r>N-1-h; r--) {
				for(int c=col; c<col+w; c++) {
					int nr = (N-1-w)+(c-col);
					int nc = (col+w)+(N-1-r);
					fishbowl[nr][nc] = fishbowl[r][c];
					fishbowl[r][c] = 0;
				}
			}
			if(step%2==0) {
				h++;
				nw++;
			}else {
				w++;
				nh++;
			}
			
			step++;
			col+=((step-1)/2+1);
		}
	}

	private static void controlFish() {
		int[][] newFishbowl = new int[N][N];
		
		for(int r=N-1; r>=0; r--) {
			for(int c=N-1; c>=0; c--) {
				if(fishbowl[r][c]==0) continue;
				newFishbowl[r][c]+=fishbowl[r][c];
				
				for(int d=0; d<4; d++) {
					int nr = r+dx[d];
					int nc = c+dy[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					if(fishbowl[nr][nc]==0) continue;
					if(fishbowl[r][c] <= fishbowl[nr][nc]) continue;
					
					int differ = fishbowl[r][c]-fishbowl[nr][nc];
					int quotient = differ/5;
					if(quotient<=0) continue;
					
					newFishbowl[r][c]-=quotient;
					newFishbowl[nr][nc]+=quotient;
				}
			}
		}
		fishbowl = newFishbowl;
	}

	private static void lineUp() {
		int idx = 0;
		int[] newFishbowl = new int[N];
		
		for(int c=0; c<N; c++) {
			if(fishbowl[N-1][c] == 0) continue;
			for(int r=N-1; r>=0; r--) {
				if(fishbowl[r][c] == 0) continue;
				newFishbowl[idx++] = fishbowl[r][c];
				fishbowl[r][c] = 0;
			}
		}
		fishbowl[N-1] = newFishbowl;
	}

	private static void levitate() {
		int step = N/2;
		for(int c=0; c<step; c++) {
			fishbowl[N-2][N-1-c] = fishbowl[N-1][c];
			fishbowl[N-1][c] = 0;
		}
		
		int startC = step;
		int nStep = step/2;
		for(int c=startC; c<startC+nStep; c++) {
            int idx = N-3;
			for(int r=N-2; r<N; r++) {
				fishbowl[idx--][(N-1)-(c-step)] = fishbowl[r][c];
				fishbowl[r][c] = 0;
			}
		}
	}

	private static boolean checkFishNum() {
		int maxFishNum = 0;
		int minFishNum = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			int fishNum = fishbowl[N-1][i];
			maxFishNum = Math.max(fishNum, maxFishNum);
			minFishNum = Math.min(fishNum, minFishNum);
		}
		
		minNum = minFishNum;
		int fishNumDiffer = maxFishNum-minFishNum;
		if(fishNumDiffer<=K) return false;
		return true;
	}
}
