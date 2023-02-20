import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1157_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String word = st.nextToken();
        int[] alphabets = new int[26];

        int maxNum = 0;
        char maxChar = '\0';
        for (int i = 0; i < word.length(); i++) {
            char w = Character.toUpperCase(word.charAt(i));
            int idx = (int)w - 65;
            alphabets[idx]+=1;
            if(maxNum < alphabets[idx]) {
                maxNum = alphabets[idx];
                maxChar = w;
            }
        }

        for(int i=0; i<26; i++){
            int num = alphabets[i];
            if(num != maxNum) continue;
            if ((char)(i+65) != maxChar){
                System.out.println("?");
                return;
            }
        }
        System.out.println(maxChar);
    }
}