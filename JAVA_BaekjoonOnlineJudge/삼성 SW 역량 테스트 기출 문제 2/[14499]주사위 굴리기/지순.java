import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[] dice = new int[7];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            int dir = Integer.parseInt(st.nextToken())-1;
            x += dx[dir];
            y += dy[dir];

            if(x<0 || x>=N || y<0 || y>=M) {
                x -= dx[dir];
                y -= dy[dir];
                continue;
            }

            int[] temp = dice.clone();

            switch (dir) {
                case 0:
                    dice[1] = temp[4];
                    dice[3] = temp[1];
                    dice[4] = temp[6];
                    dice[6] = temp[3];
                    break;
                case 1:
                    dice[1] = temp[3];
                    dice[3] = temp[6];
                    dice[4] = temp[1];
                    dice[6] = temp[4];
                    break;
                case 2:
                    dice[1] = temp[5];
                    dice[2] = temp[1];
                    dice[5] = temp[6];
                    dice[6] = temp[2];
                    break;
                case 3:
                    dice[1] = temp[2];
                    dice[2] = temp[6];
                    dice[5] = temp[1];
                    dice[6] = temp[5];
                    break;
                default:
                    break;
            }

            if(map[x][y] == 0) {
                map[x][y] = dice[6];
            } else {
                dice[6] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[1]);
        }
    }
}
