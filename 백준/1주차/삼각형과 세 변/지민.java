import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Number{
    int min, mid, max;
    public Number(int a, int b, int c){
        if (a<b){
            if(b<c) {
                this.min = a;
                this.mid = b;
                this.max = c;
            }else if(a<c){
                this.min = a;
                this.mid = c;
                this.max = b;
            }else{
                this.min = c;
                this.mid = a;
                this.max = b;
            }
        }else{
            if(a<c) {
                this.min = b;
                this.mid = a;
                this.max = c;
            }else if(b<c){
                this.min = b;
                this.mid = c;
                this.max = a;
            }else{
                this.min = c;
                this.mid = b;
                this.max = a;
            }
        }
    }
}
public class Main_5073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a==b && b==c) {
                if(a==0) break;
                else{
                    System.out.println("Equilateral");
                    continue;
                }
            }

            Number num = new Number(a, b, c);

            if(num.min+num.mid <= num.max){
                System.out.println("Invalid");
                continue;
            }

            if(num.min == num.mid || num.mid == num.max){
                System.out.println("Isosceles");
            }else{
                System.out.println("Scalene ");
            }
        }
    }
}