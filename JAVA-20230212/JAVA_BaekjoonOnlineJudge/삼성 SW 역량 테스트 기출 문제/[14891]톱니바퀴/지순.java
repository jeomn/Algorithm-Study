import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_톱니바퀴_14891 {
	static int[][] gear;
	static int[] gearDir;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = ch[j] - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			String[] st = br.readLine().split(" ");
			int num = Integer.parseInt(st[0]);
			int dir = Integer.parseInt(st[1]);
//			System.out.println(dir);
			rotation(num - 1, dir);
			rotate();
		}
		
		int sum = 0;
		for(int i=0;i<4;i++) {
			if(gear[i][0] == 1) {
				sum += Math.pow(2, i);
			}
		}
		System.out.println(sum);
	}

	private static void rotation(int num, int dir) {
		gearDir = new int[4];
		int gearLeft = gear[num][6];
		int gearRight = gear[num][2];
		int gDir = dir;
		if (num > 0) {
			for (int i = num - 1; i >= 0; i--) {
//				System.out.println(i);
				if (gearLeft != gear[i][2]) {
					gearLeft = gear[i][6];
					gDir *= -1;
					gearDir[i] = gDir;
				} else {
					break;
				}
			}
		}
		
		gearLeft = gear[num][6];
		gearRight = gear[num][2];
		gDir = dir;
		if(num<3) {
			for(int i=num+1;i<=3;i++) {
				if(gearRight != gear[i][6]) {
					gearRight = gear[i][2];
					gDir *= -1;
					gearDir[i] = gDir;
				} else {
					break;
				}
			}
		}
		gearDir[num] = dir;
//		System.out.println(Arrays.toString(gearDir));
	}
	
	private static void rotate() {
		for(int i=0;i<4;i++) {
			if(gearDir[i]!=0) {
//				System.out.println(i);
				int[] temp = new int[8];
				if(gearDir[i] == 1) {
					temp[0] = gear[i][7];
					for(int j=1;j<8;j++) {
						temp[j] = gear[i][j-1];
					}
				} else if(gearDir[i]==-1) {
					for(int j=0;j<7;j++) {
						temp[j] = gear[i][j+1];
					}
					temp[7] = gear[i][0];
				}
				gear[i] = temp.clone();
			}
//			System.out.println(Arrays.toString(gear[i]));
		}
	}
}
