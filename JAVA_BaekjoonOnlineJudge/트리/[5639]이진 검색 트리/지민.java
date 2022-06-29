import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int[] tree = new int[10_001];
	static StringBuilder sb;
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int idx = 0;
        while(true) {
        	String input = br.readLine();
        	if(input == null || input.equals("")) break;
        	tree[idx++] = Integer.parseInt(input);
        }
        
        sb = new StringBuilder();
        postOrder(0, idx-1);
        System.out.println(sb.toString());
	}

	private static void postOrder(int idx, int end) {
		if(idx > end) return;
		
		int mid = idx+1;
		while((mid<=end) && tree[mid]<tree[idx]) mid++;
		
		postOrder(idx+1, mid-1);
		postOrder(mid, end);
		sb.append(tree[idx]+"\n");
	}
}
