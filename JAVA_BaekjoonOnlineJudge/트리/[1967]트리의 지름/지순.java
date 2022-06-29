import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1967_트리의지름 {
    static int N;
    static ArrayList<Node>[] list;
    static boolean visited[];
    static int max;
    static class Node {
        int data, weight;
        Node(int data, int weight) {
            this.data = data;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];

        for(int i=0;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[parent].add(new Node(child, weight));
            list[child].add(new Node(parent, weight));
        }
        for(int i=1;i<=N;i++) {
            visited = new boolean[N+1];
            visited[i] = true;
            dfs(i, 0);
        }
        System.out.println(max);
    }

    public static void dfs(int idx, int dis) {
        for(Node n : list[idx]) {
            if(!visited[n.data]) {
                visited[n.data] = true;
                dfs(n.data, dis + n.weight);
            }
        }
        max = Math.max(max, dis);
    }
}
