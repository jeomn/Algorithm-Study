class Solution {
    public String solution(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        char[] ch = new_id.toCharArray();
        String str = "";
        for(char c: ch) {
            if((c>='0' && c<='9') || (c>='a' && c<='z') || c=='-' || c=='_' || c=='.')
                str+=c;
        }
        
        while(str.contains("..")) {
            str = str.replace("..", ".");
        }
        
        if(str.startsWith("."))
            str = str.substring(1, str.length());
        if(str.endsWith("."))
            str = str.substring(0, str.length()-1);
        
        if(str.length() == 0) {
            str += "a";
        } else if(str.length() >= 16) {
            str = str.substring(0, 15);
        }
        
        if(str.endsWith("."))
            str = str.substring(0, str.length()-1);
        
        while(str.length()<3){
            str += str.charAt(str.length()-1);
        }
        return str;
    }
}
