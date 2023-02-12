import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			int num = Integer.parseInt(br.readLine());
			if (sum + num < 100)
				sum += num;
			else {
				if (Math.abs(sum + num - 100) <= Math.abs(sum - 100)) {
					sum += num;
					break;
				} else
					break;
			}
		}
		System.out.println(sum);
	}
}
