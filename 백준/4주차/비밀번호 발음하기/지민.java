package boj.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_4659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String testcase = br.readLine();
            if(testcase.equals("end")) break;

            String result = "";
            boolean existVowel=false;
            int vowelCnt = 0, consonantCnt = 0;
            char preT = testcase.charAt(0);
            if(preT=='a' || preT=='e' || preT=='i' || preT=='o' || preT=='u') {
                existVowel = true;
                vowelCnt=1;
            }else consonantCnt=1;
            for(int i=1; i<testcase.length(); i++){
                char t = testcase.charAt(i);
                if(t=='a' || t=='e' || t=='i' || t=='o' || t=='u'){
                    existVowel = true;
                    consonantCnt=0;
                    vowelCnt+=1;
                }else{
                    vowelCnt=0;
                    consonantCnt+=1;
                }

                if(consonantCnt==3 || vowelCnt==3){
                    result = "not ";
                    break;
                }

                if(preT == t && (t!='e' && t!='o')){
                    result = "not ";
                    break;
                }
                preT = t;
            }

            if(!existVowel) result = "not ";

            System.out.println("<"+testcase+"> is "+result+"acceptable.");
        }
    }
}
