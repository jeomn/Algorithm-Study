package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Arrays;

public class BJ_4659_비밀번호발음하기 {
    static List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;

            sb.append("<" + str + ">"+" is "+ (solve(str) ? "acceptable.\n" : "not acceptable.\n"));
        }

        System.out.println(sb.toString());
    }

    public static boolean solve(String str) {
        int vowel = 0;
        int cntVowel = 0; // 자음
        int cntConsonants = 0; // 모음
        for(int i=0;i<str.length();i++) {
            if (list.contains(str.charAt(i))) {
                vowel++;
                cntVowel++;
                cntConsonants = 0;
            } else {
                cntConsonants++;
                cntVowel = 0;
            }

            if (cntVowel == 3 || cntConsonants == 3) return false;
            if (i > 0) {
                if (str.charAt(i-1) == str.charAt(i)) {
                    if (str.charAt(i) == 'o' || str.charAt(i) == 'e') continue;
                    return false;
                }
            }
        }

        if (vowel == 0) return false;
        return true;
    }
}
