import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int n = 1;
        int i = 0;
        while(true){
            n+=6*i;
            if (N <= n){
                System.out.println(i+1);
                break;
            }
            i+=1;
        }
    }
}
