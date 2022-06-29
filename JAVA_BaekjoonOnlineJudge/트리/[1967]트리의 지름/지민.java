import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int max;
	static boolean[] visited;
	static ArrayList<Node>[] tree;
	static class Node{
		int num, weight;
		
		Node(int num, int weight){
			this.num = num;
			this.weight = weight;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        
        for(int i=0; i<N; i++) {
        	tree[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int r = Integer.parseInt(st.nextToken())-1;
        	int c = Integer.parseInt(st.nextToken())-1;
        	int w = Integer.parseInt(st.nextToken());
        	
        	tree[r].add(new Node(c, w));
        	tree[c].add(new Node(r, w));
        }
        
        for(int i=0; i<N; i++) {
        	visited = new boolean[N];
        	visited[i] = true;
        	dfs(i, 0);
        }
        
        System.out.println(max);
	}

	private static void dfs(int idx, int weight) {
		for(Node node: tree[idx]) {
			if(visited[node.num]) continue;
			visited[node.num] = true;
			dfs(node.num, weight+node.weight);
		}
		max = Math.max(max, weight);
	}
}
