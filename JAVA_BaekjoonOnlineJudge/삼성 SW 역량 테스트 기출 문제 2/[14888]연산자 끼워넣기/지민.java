import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums;
	static char[] opers;
	static boolean[] isSelected;
	static ArrayList<Character> operList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		operList = new ArrayList<>();
		HashMap<Integer, Character> operMap = new HashMap<>();
		operMap.put(0, '+');
		operMap.put(1, '-');
		operMap.put(2, '*');
		operMap.put(3, '/');
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			int operCnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<operCnt; j++) {
				operList.add(operMap.get(i));
			}
		}
		
		opers = new char[N-1];
		isSelected = new boolean[N-1];
		makeExpression(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void makeExpression(int cnt) {
		if(cnt == N-1) {
			int num = nums[0];
			for(int i=0; i<N-1; i++) {
				if(i==N) break;
				num = calcNum(num, nums[i+1], opers[i]);
			}
			min = Math.min(min, num);
			max = Math.max(max, num);
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			if(isSelected[i]) continue;
			opers[cnt] = operList.get(i);
			isSelected[i] = true;
			makeExpression(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static int calcNum(int num, int num2, char oper) {
		if(oper == '+') {
			return num+=num2;
		}else if(oper == '-') {
			return num-=num2;
		}else if(oper == '*') {
			return num*=num2;
		}else{
			return num/=num2;
		}
	}
}