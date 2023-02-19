import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 1;
        int res = 1;

        while(res < N) {
            res = res + (6 * cnt);
            cnt++;
        }

        System.out.println(cnt);
    }
}
