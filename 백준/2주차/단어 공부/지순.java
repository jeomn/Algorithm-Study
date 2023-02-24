import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BJ_1157_단어공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.toUpperCase();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i=0;i<str.length();i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0)+1);
        }

        List<Character> list = new ArrayList<>(map.keySet());
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });

        if (list.size() >= 2 && map.get(list.get(0)) == map.get(list.get(1)))
            System.out.println('?');
        else System.out.println(list.get(0));
    }
}
