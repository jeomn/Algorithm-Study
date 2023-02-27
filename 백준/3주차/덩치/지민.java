package boj.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_7568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] sizeArr = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            sizeArr[i][0] = Integer.parseInt(st.nextToken());
            sizeArr[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int rank = 1;
            for(int j=0; j<N; j++){
                if(i==j) continue;
                if(sizeArr[i][0]<sizeArr[j][0] && sizeArr[i][1]<sizeArr[j][1]) rank++;
            }
            sb.append(rank).append(" ");
        }
        System.out.println(sb.toString());
    }
}