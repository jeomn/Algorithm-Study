import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main_1157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String word = st.nextToken();
        HashMap<Character, Integer> alphabets = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char w = Character.toUpperCase(word.charAt(i));
            alphabets.put(w, alphabets.getOrDefault(w, 0) + 1);
        }

        List<HashMap.Entry<Character, Integer>> sortedAlphabets =
                alphabets.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toList());

        if (sortedAlphabets.size() >= 2) {
            if (Objects.equals(sortedAlphabets.get(0).getValue(), sortedAlphabets.get(1).getValue())) {
                System.out.println("?");
                return;
            }
        }
        System.out.println(sortedAlphabets.get(0).getKey());
    }
}