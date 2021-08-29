package IM_BJ_DIFFICULTY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_색종이_10163 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int[][] arr = new int[1001][1001];
		int[] result = new int[TC+1];
		for(int tc = 1;tc<=TC;tc++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			for(int i=x;i<x+w;i++) {
				for(int j=y;j<y+h;j++) {
					arr[i][j] = tc;
				}
			}
		}
		
		for(int i=0;i<1001;i++) {
			for(int j=0;j<1001;j++) {
				result[arr[i][j]]++;
			}
		}
		for(int i=1;i<=TC;i++) {
			sb.append(result[i]+"\n");
		}
		System.out.println(sb.toString());
	}

}
