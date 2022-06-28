import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static ArrayList<Node>[] graph;
	static StringBuilder sb;
	static class Node{
		int left, right;
		Node(int left, int right){
			this.left = left;
			this.right = right;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        
        for(int i=0; i<N; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int n = st.nextToken().charAt(0)-'A';
        	int l = st.nextToken().charAt(0)-'A';
        	int r = st.nextToken().charAt(0)-'A';
        	
        	graph[n].add(new Node(l, r));
        }
        
        sb = new StringBuilder();
        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        System.out.println(sb.toString());
        
	}

	private static void preorder(int start) {
		for(Node node: graph[start]) {
			sb.append((char)(start+'A'));
			if(node.left != -19) preorder(node.left);
			if(node.right != -19) preorder(node.right);
		}
	}

	private static void inorder(int start) {
		for(Node node: graph[start]) {
			if(node.left != -19) inorder(node.left);
			sb.append((char)(start+'A'));
			if(node.right != -19) inorder(node.right);
		}
	}

	private static void postorder(int start) {
		for(Node node: graph[start]) {
			if(node.left != -19) postorder(node.left);
			if(node.right != -19) postorder(node.right);
			sb.append((char)(start+'A'));
		}
	}
}
