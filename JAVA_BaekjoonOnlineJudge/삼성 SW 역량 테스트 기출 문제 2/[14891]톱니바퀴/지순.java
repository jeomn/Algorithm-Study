import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {
	static ArrayList<Integer>[] wheel;
	static int[] gearDir;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		wheel = new ArrayList[4];
		
		for(int i=0;i<4;i++) {
			String str = br.readLine();
			wheel[i] = new ArrayList<Integer>();
			for(int j=0;j<8;j++) {
				wheel[i].add(str.charAt(j)-'0');
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0;i<K;i++) {
			gearDir = new int[4];
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			rotation(num, dir);
			rotate();
		}
		
		int answer = 0;
		for(int i=0;i<4;i++) {
			if(wheel[i].get(0) == 1)
				answer += Math.pow(2, i);
		}
		System.out.println(answer);
	}
	
	private static void rotation(int num, int dir) {
		gearDir[num] = dir;
		
		int prev = num - 1;
		int next = num + 1;
		
		if(prev >= 0 && gearDir[prev] == 0) {
			if(wheel[num].get(6) != wheel[prev].get(2))
				rotation(prev, dir * -1);
		}
		
		if(next < 4 && gearDir[next] == 0) {
			if(wheel[num].get(2) != wheel[next].get(6))
				rotation(next, dir * -1);
		}
	}
	
	private static void rotate() {
		for(int i=0;i<4;i++) {
			if(gearDir[i] == 1) { // 시계방향
				wheel[i].add(0, wheel[i].remove(7));
			} else if(gearDir[i] == -1){ //반시계방향
				wheel[i].add(wheel[i].remove(0));
			} else {
				continue;
			}
		}
	}
}
