import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11723_집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        boolean[] x = new boolean[21];

        StringTokenizer st = null;
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if (st.hasMoreTokens()) {
                int idx = Integer.parseInt(st.nextToken());

                if ("add".equals(str)) {
                    x[idx] = true;
                } else if ("remove".equals(str)) {
                    x[idx] = false;
                } else if ("check".equals(str)) {
                    sb.append(x[idx] ? 1+"\n" : 0+"\n");
                } else if ("toggle".equals(str)) {
                    x[idx] ^= true;
                }
            } else {
                if ("all".equals(str)) Arrays.fill(x, true);
                else x = new boolean[21];
            }
        }
        System.out.println(sb.toString());
    }
}
