import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] parents;
	static ArrayList<Integer>[] graph;
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        
        for(int i=0; i<N; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken())-1;
        	int b = Integer.parseInt(st.nextToken())-1;
        	
        	graph[a].add(b);
        	graph[b].add(a);
        }
        
        parents = new int[N];
        dfs(0, -1);
        
        for(int i=1; i<N; i++) {
        	System.out.println(parents[i]);
        }
	}

	private static void dfs(int node, int parent) {
		for(int adj : graph[node]) {
			if(adj == parent) continue;
			parents[adj] = node+1;
			dfs(adj, node);
		}
		
	}
}
