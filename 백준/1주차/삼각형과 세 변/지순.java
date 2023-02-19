import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_5073_삼각형과세변 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int arr[] = new int[3];

            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
                break;
            }

            Arrays.sort(arr);

            if (arr[2] >= arr[0] + arr[1]) {
                sb.append("Invalid\n");
                continue;
            }

            if (arr[0] == arr[2]) {
                sb.append("Equilateral\n");
                continue;
            }

            if (arr[0] == arr[1] || arr[1] == arr[2]) {
                sb.append("Isosceles\n");
                continue;
            }

            sb.append("Scalene\n");
        }

        System.out.println(sb.toString());
    }
}
