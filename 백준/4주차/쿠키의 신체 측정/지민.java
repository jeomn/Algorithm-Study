package boj.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main_20125 {

    static int[] x = new int[]{0, 0, -1, 1};
    static int[] y = new int[]{-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[] heart = new int[2];
        heartLabel:
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == '_') continue;

                boolean isHeart = true;
                for (int i = 0; i < 4; i++) {
                    int nextR = r + x[i];
                    int nextC = c + y[i];
                    char nextChar = '\0';
                    if(0<=nextR && nextR<N && 0<=nextC && nextC<N) nextChar=board[nextR][nextC];
                    if (nextChar == '_') {
                        isHeart = false;
                        break;
                    }
                }
                if (isHeart) {
                    heart = new int[]{r, c};
                    break heartLabel;
                }
            }
        }

        int waist = 0;
        int cnt=0;
        int r=heart[0]+x[3], c=heart[1]+y[3];
        char coord = board[r][c];

        int[] endWaist = new int[2];
        while (coord=='*') {
            cnt+=1;
            r+=x[3];
            c+=y[3];
            coord=board[r][c];
        }
        waist = cnt;
        endWaist = new int[]{r, c};

        int[] arms=new int[2], legs=new int[2];
        for(int i=0; i<2; i++){
            cnt=0;
            r=heart[0]+x[i];
            c=heart[1]+y[i];
            coord = board[r][c];
            while (coord=='*') {
                cnt+=1;
                r+=x[i];
                c+=y[i];
                if(0<=r && r<N && 0<=c && c<N) coord=board[r][c];
                else break;
            }
            arms[i] = cnt;

            cnt=0;
            r=endWaist[0]+x[i];
            c=endWaist[1]+y[i];
            coord = board[r][c];
            while (coord=='*') {
                cnt+=1;
                r+=x[3];
                c+=y[3];
                if(0<=r && r<N && 0<=c && c<N) coord=board[r][c];
                else break;
            }
            legs[i] = cnt;

        }

        System.out.println((heart[0]+1)+" "+(heart[1]+1));
        System.out.println(arms[0]+" "+arms[1]+" "+waist+" "+legs[0]+" "+legs[1]);
    }
}
