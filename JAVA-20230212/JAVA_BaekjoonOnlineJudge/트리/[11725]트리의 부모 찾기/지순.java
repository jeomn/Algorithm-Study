import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_11725_트리의부모찾기 {
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static int[] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        node = new int[N+1];
        list = new ArrayList<>();
        for(int i=0;i<N+1;i++) {
            list.add(new ArrayList<Integer>());
        }

        StringTokenizer st = null;
        int a, b;
        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        dfs(1, 0);
        for(int i=2;i<=N;i++) {
            System.out.println(node[i]);
        }
    }

    private static void dfs(int start, int parent) {
        node[start] = parent;
        for(int item : list.get(start)) {
            if(item != parent) {
                dfs(item, start);
            }
        }
    }
}
