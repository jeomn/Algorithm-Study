package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_25757_임스와함께하는미니게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = mapping(st.nextToken());

        HashSet<String> hs = new HashSet<>();
        int cnt = 0;
        for (int i=0;i<N;i++) {
            String str = br.readLine();
            cnt += hs.contains(str)? 0 : 1;
            hs.add(str);
        }

        System.out.println(cnt/P);
    }

    public static int mapping(String s) {
        if (s.equals("Y")) {
            return 1;
        } else if (s.equals("F")) {
            return 2;
        } else {
            return 3;
        }
    }
}
