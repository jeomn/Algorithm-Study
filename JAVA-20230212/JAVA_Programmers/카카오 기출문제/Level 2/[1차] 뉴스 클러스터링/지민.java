//20220114
//2018 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    public int solution(String str1, String str2) {
        int answer = 65536;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        String str="";
        ArrayList<String> strSet1 = new ArrayList<>();
        for(int i=0; i<str1.length()-1; i++){
            str = "";
            if(!Character.isAlphabetic(str1.charAt(i)) || !Character.isAlphabetic(str1.charAt(i+1))) continue;
            str+=str1.charAt(i);
            str+=str1.charAt(i+1);
            strSet1.add(str);
        }
        
        ArrayList<String> strSet2 = new ArrayList<>();
        for(int i=0; i<str2.length()-1; i++){
            str = "";
            if(!Character.isAlphabetic(str2.charAt(i)) || !Character.isAlphabetic(str2.charAt(i+1))) continue;
            str+=str2.charAt(i);
            str+=str2.charAt(i+1);
            strSet2.add(str);
        }
        
        int inter = 0;
        boolean[] strSelected2 = new boolean[strSet2.size()];
        for(int i=0; i<strSet1.size(); i++){
            for(int j=0; j<strSet2.size(); j++){
                if(strSelected2[j]) continue;
                if(strSet1.get(i).equals(strSet2.get(j))){
                    strSelected2[j] = true;
                    inter++;
                    break;
                }
            }
        }
        
        double union = strSet1.size()+strSet2.size()-inter;
        
        if(union!=0) answer = (int)(((double)inter/union)*65536);
        return answer;
    }
}
