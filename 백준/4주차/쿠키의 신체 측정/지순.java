package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_20125_쿠키의신체측정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];

        for (int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        }

        int x = 0, y = 0;
        outer : for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (map[i][j] == '*') {
                    x = i+1;
                    y = j;
                    System.out.println((i+2)+" "+(j+1));
                    break outer;
                }
            }
        }

        int leftArm = 0;
        for (int i = y-1; i>=0;i--) {
            if (map[x][i] == '*') leftArm++;
            else break;
        }

        int rightArm = 0;
        for (int i = y+1; i<N;i++) {
            if (map[x][i] == '*') rightArm++;
            else break;
        }

        int height = 0;
        int idx = 0;
        for (int i = x+1;i<N;i++) {
            if (map[i][y] == '*') height++;
            else {
                idx = i;
                break;
            }
        }

        int leftLeg = 0;
        for (int i = idx;i<N;i++) {
            if (map[i][y-1] == '*') leftLeg++;
            else break;
        }

        int rightLeg = 0;
        for (int i = idx;i<N;i++) {
            if (map[i][y+1] == '*') rightLeg++;
            else break;
        }

        System.out.println(leftArm + " " + rightArm + " " + height + " "+leftLeg+" "+rightLeg);
    }
}
