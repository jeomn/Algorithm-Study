//20211224
//2021 카카오 채용연계형 인턴십


class Solution {
    public int solution(String s) {
        
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        String number = "";
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                number+=c;
            }else{
                sb.append(c);
                String newNumber = sb.toString();
                if(newNumber.equals("zero")){
                    number+="0";
                    sb.setLength(0);
                }else if(newNumber.equals("one")){
                    number+="1";
                    sb.setLength(0);
                }else if(newNumber.equals("two")){
                    number+="2";
                    sb.setLength(0);
                }else if(newNumber.equals("three")){
                    number+="3";
                    sb.setLength(0);
                }else if(newNumber.equals("four")){
                    number+="4";
                    sb.setLength(0);
                }else if(newNumber.equals("five")){
                    number+="5";
                    sb.setLength(0);
                }else if(newNumber.equals("six")){
                    number+="6";
                    sb.setLength(0);
                }else if(newNumber.equals("seven")){
                    number+="7";
                    sb.setLength(0);
                }else if(newNumber.equals("eight")){
                    number+="8";
                    sb.setLength(0);
                }else if(newNumber.equals("nine")){
                    number+="9";
                    sb.setLength(0);
                }  
            }  
        }
        
        answer = Integer.parseInt(number);
        return answer;
    }
}
