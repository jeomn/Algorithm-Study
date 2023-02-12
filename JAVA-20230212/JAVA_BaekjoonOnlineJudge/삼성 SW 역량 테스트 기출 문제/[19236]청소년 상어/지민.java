import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static int maxEatNum;
	static int[][] space;
	static HashMap<Integer, Fish> fishMap;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static class Fish{
		int n, r, c, d;
		boolean alive;
		Fish(int n, int r, int c, int d, boolean alive){
			this.n = n;
			this.r = r;
			this.c = c;
			this.d = d;
			this.alive = alive;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        
        space = new int[4][4];
        fishMap = new HashMap<Integer, Fish>();
        for(int r=0; r<4; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c=0; c<4; c++) {
        		int n = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken())-1;
        		
        		Fish fish = new Fish(n, r, c, d, true);
        		space[r][c] = n;
        		fishMap.put(n, fish);
        	}
        }
        
        int eatNum = space[0][0];
        fishMap.put(100, new Fish(100, 0, 0, -1, true));
        eatFish(0, 0);

        EatYouthShark(eatNum);
        
        System.out.println(maxEatNum);
	}

	private static void eatFish(int r, int c) {
		int eatFish = space[r][c];
		Fish eat = fishMap.get(eatFish);
		eat.alive = false;
		
		Fish shark = fishMap.get(100);
		space[shark.r][shark.c] = 0;
		shark.r = r;
		shark.c = c;
		shark.d = eat.d;
	    space[r][c] = 100;
	}

	private static void EatYouthShark(int eatNum) {
		if(eatNum > maxEatNum) {
			maxEatNum = eatNum;
		}
		
		HashMap<Integer, Fish> tempFishMap = new HashMap<>();
		for(int i=1; i<17; i++) {
			Fish old = fishMap.get(i);
			tempFishMap.put(i, new Fish(old.n, old.r, old.c, old.d, old.alive));
		}
		Fish oldShark = fishMap.get(100);
		tempFishMap.put(100, new Fish(oldShark.n, oldShark.r, oldShark.c, oldShark.d, oldShark.alive));
		int[][] tempSpace = new int[4][4];
		for(int i=0; i<4; i++) {
			System.arraycopy(space[i], 0, tempSpace[i], 0, 4);
		}
		
		moveFish();
		
		Fish shark = fishMap.get(100);
		for(int i=1; i<4; i++) {
			int nr = shark.r+(dx[shark.d])*i;
			int nc = shark.c+(dy[shark.d])*i;
			
			if(nr<0 || nr>=4 || nc<0 || nc>=4) break;
			if(space[nr][nc]==0) continue;
			
			int n = space[nr][nc];
			eatFish(nr, nc);
			EatYouthShark(eatNum+n);
			
			
			oldShark = tempFishMap.get(100);
			fishMap.put(100, new Fish(oldShark.n, oldShark.r, oldShark.c, oldShark.d, oldShark.alive));
			shark = fishMap.get(100);
			Fish pf = fishMap.get(n);
			pf.alive = true;
			space[nr][nc] = pf.n;
			space[shark.r][shark.c] = 100;
		}
		
		for(int i=1; i<17; i++) {
			Fish old = tempFishMap.get(i);
			fishMap.put(i, new Fish(old.n, old.r, old.c, old.d, old.alive));
		}
		oldShark = tempFishMap.get(100);
		fishMap.put(100, new Fish(oldShark.n, oldShark.r, oldShark.c, oldShark.d, oldShark.alive));
		for(int i=0; i<4; i++) {
			System.arraycopy(tempSpace[i], 0, space[i], 0, 4);
		}
	}

	private static void moveFish() {
		for(int n=1; n<17; n++) {
			Fish f = fishMap.get(n);
			if(!f.alive) continue;
			
			for(int i=0; i<8; i++) {
				int d = (f.d+i)%8;
				int nr = f.r+dx[d];
				int nc = f.c+dy[d];
				
				if(nr<0 || nr>=4 || nc<0 || nc>=4) continue;
				if(space[nr][nc]!=0 && space[nr][nc]==100) continue;
				
				int nfNum = space[nr][nc];
				Fish nf = fishMap.getOrDefault(nfNum, null);
				if(nf != null) {
					space[f.r][f.c] = nf.n;
					Fish mf = fishMap.get(nf.n);
					mf.r = f.r;
					mf.c = f.c;
				}else {
					space[f.r][f.c] = 0;
				}
				space[nr][nc] = f.n;
				f.r = nr;
				f.c = nc;
				f.d = d;
				
				break;
			}
		}
	}
}
