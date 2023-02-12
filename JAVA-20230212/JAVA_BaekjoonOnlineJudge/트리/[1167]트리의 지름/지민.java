import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int max, node;
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
        
        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V];
        
        for(int i=0; i<V; i++) {
        	tree[i] = new ArrayList<>();
        }
        
        for(int i=0; i<V; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken())-1;
        	while(true) {
        		int v = Integer.parseInt(st.nextToken())-1;
        		if(v==-2) break;
        		
        		int w = Integer.parseInt(st.nextToken());
        		tree[n].add(new Node(v, w));
        	}
        }
        
        visited = new boolean[V];
        dfs(0, 0);
        
        visited = new boolean[V];
        dfs(node, 0);
        
       System.out.println(max); 
	}

	private static void dfs(int n, int weight) {
		if(weight > max) {
			max = weight;
			node = n;
		}
		visited[n] = true;
		
		for(Node node: tree[n]) {
			if(visited[node.num]) continue;
			dfs(node.num, weight+node.weight);
			visited[node.num] = true;
		}
		
	}
}
