import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int operCnt = Integer.parseInt(st.nextToken());

        boolean[] S = new boolean[21];

        String cmd = "";
        int num = -1;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<operCnt; i++){
            st = new StringTokenizer(br.readLine());

            cmd = st.nextToken();

            switch(cmd){
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    S[num] = true;
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    S[num] = false;
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    int result = S[num]? 1:0;
                    sb.append(result).append("\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    S[num] = !S[num];
                    break;
                case "all":
                    Arrays.fill(S, true);
                    break;
                case "empty":
                    S = new boolean[21];
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}