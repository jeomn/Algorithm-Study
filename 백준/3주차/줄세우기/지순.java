package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10431_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<P;i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());

            int[] line = new int[20];
            line[0] = Integer.parseInt(st.nextToken());
            int cnt = 0;

            for (int j=1;j<20;j++) {
                int student = Integer.parseInt(st.nextToken());
                for (int k=0;k<j;k++) {
                    if (student >= line[k]) continue;
                    cnt++;
                }
                line[j] = student;
            }

            sb.append(T+" "+cnt+"\n");
        }

        System.out.println(sb.toString());
    }
}
