package boj.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        HashSet<String> players = new HashSet<>();
        for(int i=0; i<N; i++){
            String player = br.readLine();
            players.add(player);
        }

        int playersCnt = players.size(), gameCnt = 0;
        switch(game) {
            case "Y":
                gameCnt = playersCnt;
                break;
            case "F":
                gameCnt = playersCnt/2;
                break;
            case "O":
                gameCnt = playersCnt/3;
                break;
            default:
                break;
        }
        System.out.println(gameCnt);

    }
}
