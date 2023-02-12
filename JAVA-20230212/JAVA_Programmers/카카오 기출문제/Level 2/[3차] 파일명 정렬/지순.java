import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String header1 = o1.split("[0-9]")[0].toLowerCase();
                String header2 = o2.split("[0-9]")[0].toLowerCase();
                
                if(header1.compareTo(header2) == 0) {
                    String subStr1 = o1.substring(header1.length());
                    String subStr2 = o2.substring(header2.length());
                    int num1 = Integer.parseInt(setNum(subStr1));
                    int num2 = Integer.parseInt(setNum(subStr2));
                    
                    return num1-num2;
                } else {
                    return header1.compareTo(header2);
                }
            }
        });
        return files;
    }
    
    private String setNum(String str) {
        String result = "";
        for(char ch: str.toCharArray()) {
            if(Character.isDigit(ch) && result.length() < 6) {
                result += ch;
            } else {
                break;
            }
        }
        return result;
    }
}
