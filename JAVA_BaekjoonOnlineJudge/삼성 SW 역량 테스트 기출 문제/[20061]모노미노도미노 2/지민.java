import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, score;
	static boolean[][] blue, green; 
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        blue = new boolean[4][6];
        green = new boolean[6][4];
        score = 0;
        for(int n=0; n<N; n++) {
        	st = new StringTokenizer(br.readLine());
        	int t = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	setBlockBlue(t, x, y);
        	setBlockGreen(t, x, y);
        }
        
        System.out.println(score);
        System.out.println(countBlock());
    }

	private static void setBlockBlue(int t, int x, int y) {
		int top = 0;
		boolean flag = false;
		for(int i=0; i<6; i++) {
			if(t==1) {
				if(!blue[x][i]) continue;
				blue[x][i-1] = true;
				top = i-1;
				flag = true;
			}else if(t==2) {
				if(i <= 1 || !blue[x][i]) continue;
				blue[x][i-2] = true;
				blue[x][i-1] = true;
				top = i-2;
				flag = true;
			}else {
				if(!blue[x][i] && !blue[x+1][i]) continue;
				blue[x][i-1] = true;
				blue[x+1][i-1] = true;
				top = i-1;
				flag = true;
			}
			if(flag) break;
		}
		
		if(!flag) {
			if(t==1) {
				blue[x][5] = true;
				top = 5;
			}else if(t==2) {
				blue[x][4] = true;
				blue[x][5] = true;
				top = 4;
			}else {
				blue[x][5] = true;
				blue[x+1][5] = true;
				top = 5;
			}
		}
		
		//점수 계산
		int col = 5;
		blue: while(col>=0) {
			for(int i=0; i<4; i++) {
				if(!blue[i][col]) {
					col--;
					continue blue;
				}
			}
			
			score++;
			top+=1;
			for(int i=col; i>0; i--) {
				for(int j=0; j<4; j++) {
					blue[j][i] = blue[j][i-1];
					blue[j][i-1] = false;
				}
			}
		}
		
		//특별한 칸
		if(top <= 1) {
			if(top == 1) {
				for(int i=5; i>0; i--) {
					for(int j=0; j<4; j++) {
						blue[j][i] = blue[j][i-1];
						blue[j][i-1] = false;
					}
				}
			}else {
				for(int k=0; k<2; k++) {
					for(int i=5; i>0; i--) {
						for(int j=0; j<4; j++) {
							blue[j][i] = blue[j][i-1];
							blue[j][i-1] = false;
						}
					}
				}
			}
		}
		
	}

	private static void setBlockGreen(int t, int x, int y) {
		int top = 0;
		boolean flag = false;
		for(int i=1; i<6; i++) {
			if(t==1) {
				if(!green[i][y]) continue;
				green[i-1][y] = true;
				top = i-1;
				flag = true;
			}else if(t==2) {
				if(!green[i][y] && !green[i][y+1]) continue;
				green[i-1][y] = true;
				green[i-1][y+1] = true;
				top = i-1;
				flag = true;
			}else {
				if(i <= 1 || !green[i][y]) continue;
				green[i-2][y] = true;
				green[i-1][y] = true;
				top = i-2;
				flag = true;
			}
			if(flag) break;
		}
		
		if(!flag) {
			if(t==1) {
				green[5][y] = true;
				top = 5;
			}else if(t==2) {
				green[5][y] = true;
				green[5][y+1] = true;
				top = 5;
			}else {
				green[4][y] = true;
				green[5][y] = true;
				top = 4;
			}
		}
		
		//점수 계산
		int row = 5;
		green: while(row>=0) {
			for(int i=0; i<4; i++) {
				if(!green[row][i]) {
					row--;
					continue green;
				}
			}
			
			score++;
			top+=1;
			for(int i=row; i>0; i--) {
				for(int j=0; j<4; j++) {
					green[i][j] = green[i-1][j];
					green[i-1][j] = false;
				}
			}
		}
		
		//특별한 칸
		if(top <= 1) {
			if(top == 1) {
				for(int i=5; i>0; i--) {
					for(int j=0; j<4; j++) {
						green[i][j] = green[i-1][j];
						green[i-1][j] = false;
					}
				}
			}else {
				for(int k=0; k<2; k++) {
					for(int i=5; i>0; i--) {
						for(int j=0; j<4; j++) {
							green[i][j] = green[i-1][j];
							green[i-1][j] = false;
						}
					}
				}
			}
		}
		
	}
	
	private static int countBlock() {
		int count = 0;
		for(int r=0; r<6; r++) {
			for(int c=0; c<4; c++) {
				if(!green[r][c] && !blue[c][r]) continue;
				if(green[r][c]) count++;
				if(blue[c][r]) count++;
			}
		}
		return count;
	}
}
