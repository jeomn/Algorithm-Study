//20211224

class Solution {
    public String solution(String new_id) {
        String temp = new_id;
        temp = temp.toLowerCase();
        temp = temp.replaceAll("[~!@#$%^&*()=+\\[{\\]}:?,<>/]", "");
        temp = temp.replaceAll("\\.{2,}", ".");
        temp = temp.replaceAll("^\\.|\\.$", "");
        if(temp.length() == 0){
            temp = "a";
        }
        if(temp.length() >= 16){
            temp = temp.substring(0, 15).replaceAll("\\.$", "");
        }
        while(temp.length() <= 2){
            temp+=temp.substring(temp.length()-1);
        }
		
		return temp;
    }
}
