package boj.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10431 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] line = new int[20];

        int testcaseCnt = Integer.parseInt(st.nextToken());
        for(int i=0; i<testcaseCnt; i++){
            st = new StringTokenizer(br.readLine());
            int testcase = Integer.parseInt(st.nextToken());

            int backStepCnt = 0;
            int student = Integer.parseInt(st.nextToken());
            line[0] = student;
            for(int j=1; j<20; j++){
                student = Integer.parseInt(st.nextToken());
                for(int k=0; k<j; k++){
                    if(line[k] <= student) continue;
                    backStepCnt+=1;
                }
                line[j] = student;

            }
            Arrays.fill(line, 0);
            System.out.println(testcase+" "+backStepCnt);
        }

    }
}