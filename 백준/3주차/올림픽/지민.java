package boj.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class OlympicMedals implements Comparable<OlympicMedals> {
    int country, gold, silver, bronze, rank = 0;
    OlympicMedals(int country, int gold, int silver, int bronze){
        this.country = country;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    @Override
    public int compareTo(OlympicMedals obj) {
        int x = obj.gold - this.gold;
        if(x == 0){
            int y = obj.silver - this.silver;
            if(y == 0){
                return obj.bronze - this.bronze;
            }
            return y;
        }
        return x;
    }
}
public class Main_8979 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int reqCountry = Integer.parseInt(st.nextToken());


        ArrayList<OlympicMedals> ranking = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int countryNum = Integer.parseInt(st.nextToken());
            int goldCnt = Integer.parseInt(st.nextToken());
            int silverCnt = Integer.parseInt(st.nextToken());
            int bronzeCnt = Integer.parseInt(st.nextToken());

            OlympicMedals countryScore = new OlympicMedals(countryNum, goldCnt, silverCnt, bronzeCnt);
            ranking.add(countryScore);
        }
        Collections.sort(ranking);

        ranking.get(0).rank = 1;
        int reqCountryRank = 1;
        OlympicMedals preRank = null;
        for(int i=1; i<ranking.size(); i++){
            preRank = ranking.get(i-1);
            OlympicMedals worldRank = ranking.get(i);
            if(preRank.gold==worldRank.gold && preRank.silver==worldRank.silver && preRank.bronze==worldRank.bronze) worldRank.rank = preRank.rank;
            else worldRank.rank = i+1;

            if(worldRank.country == reqCountry){
                reqCountryRank = worldRank.rank;
                break;
            }
        }
        System.out.println(reqCountryRank);
    }
}