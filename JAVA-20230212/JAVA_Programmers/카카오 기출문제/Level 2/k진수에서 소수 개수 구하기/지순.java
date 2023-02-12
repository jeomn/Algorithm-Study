class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n%k);
            n /= k;
        }
        sb.reverse();
        System.out.println(sb.toString());
        String[] str = sb.toString().split("0");
        
        for(String s : str) {
            if(s.equals("")) continue;
            if(isPrime(Long.valueOf(s)))
                answer++;
        }
        return answer;
    }
    public boolean isPrime(long num) {
        if(num == 1) return false;
        
        for(int i=2;i<=Math.sqrt(num);i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
