import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BJ_이차원배열과연산_17140 {
	static int r;
	static int c;
	static int k;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		map = new int[3][3];
		
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(true) {
			if(time>100) {
				time = -1;
				break;
			}
			
			if(r<map.length && c<map[0].length)
				if(map[r][c] == k) break;
			
			int row = map.length; // 행
			int col = map[0].length;//열
			
			int[][] temp = new int[101][101];
			
			if(row>=col) { //R연산
				int idx =0;
				int max = Integer.MIN_VALUE;
				for(int i=0;i<row;i++) {
					idx=0;
					Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
					for(int j=0;j<col;j++) {
						if(map[i][j]==0) continue;
						if(cnt.containsKey(map[i][j]))
							cnt.put(map[i][j], cnt.get(map[i][j])+1);
						else
							cnt.put(map[i][j], 1);
					}
					List<Entry<Integer, Integer>> sort_cnt = new ArrayList<Entry<Integer, Integer>>(cnt.entrySet());
					
					Collections.sort(sort_cnt, new Comparator<Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
							// TODO Auto-generated method stub
							if(o1.getValue() == o2.getValue())
								return o1.getKey()-o2.getKey();
							return o1.getValue()-o2.getValue();
						}
						
					});
					for (Entry<Integer, Integer> entry : sort_cnt) {
						temp[i][idx] = entry.getKey();
						temp[i][idx+1] = entry.getValue();
						idx+=2;
						
						if(idx>=100) break;
					}
					max = Math.max(max, idx);
				}
				map = new int[row][max];
				for(int i=0;i<map.length;i++) {
					for(int j=0;j<map[i].length;j++) {
						map[i][j] = temp[i][j];
					}
				}
			} else {//C연산
				int idx =0;
				int max = Integer.MIN_VALUE;
				for(int i=0;i<col;i++) {
					idx=0;
					Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
					for(int j=0;j<row;j++) {
						if(map[j][i]==0) continue;
						if(cnt.containsKey(map[j][i]))
							cnt.put(map[j][i], cnt.get(map[j][i])+1);
						else
							cnt.put(map[j][i], 1);
					}
					List<Entry<Integer, Integer>> sort_cnt = new ArrayList<Entry<Integer, Integer>>(cnt.entrySet());
					
					Collections.sort(sort_cnt, new Comparator<Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
							// TODO Auto-generated method stub
							if(o1.getValue() == o2.getValue())
								return o1.getKey()-o2.getKey();
							return o1.getValue()-o2.getValue();
						}
						
					});
					
					for (Entry<Integer, Integer> entry : sort_cnt) {
						temp[idx][i] = entry.getKey();
						temp[idx+1][i] = entry.getValue();
						idx+=2;
						if(idx>=100) break;
					}
					max = Math.max(max, idx);
				}
				map = new int[max][col];
				
				for(int i=0;i<map.length;i++) {
					for(int j=0;j<map[i].length;j++) {
						map[i][j] = temp[i][j];
					}
				}
			}
			time++;
		}
		
		System.out.println(time);
	}

}
