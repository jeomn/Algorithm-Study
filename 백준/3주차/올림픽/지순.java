package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_8979_올림픽 {
    static class Country implements Comparable<Country>{
        private int c;
        private int g;
        private int s;
        private int b;

        public Country(int c, int g, int s, int b) {
            this.c = c;
            this.g = g;
            this.s = s;
            this.b = b;
        }

        @Override
        public int compareTo(Country o) {
            if (g == o.g) {
                if (s == o.s) {
                    return o.b - b;
                }
                return o.s - s;
            }
            return o.g - g;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Country(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int ranking = 1;

        Country country = pq.poll();

        if (country.c == K){
            System.out.println(ranking);
            System.exit(0);
        }

        int cnt = 0;
        while (!pq.isEmpty()) {
            Country next = pq.poll();

            if (country.g == next.g && country.s == next.s && country.b == next.b)
                cnt++;
            else {
                country = next;
                ranking += (cnt+1);
                cnt = 0;
            }

            if (next.c == K) {
                break;
            }
        }

        System.out.println(ranking);
    }
}
