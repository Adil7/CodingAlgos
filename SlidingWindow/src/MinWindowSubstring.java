import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    public static String findMinSubstring(String str, String pattern){

        int windowStart = 0;
        int matched = 0;
        int minLength = str.length()+1;
        int subStrStart = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : pattern.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            char rightChar = str.charAt(windowEnd);

            if (map.containsKey(rightChar)){
                map.put(rightChar, map.get(rightChar)-1);
                if (map.get(rightChar) >= 0)
                    matched++;
            }

            while (matched == pattern.length()) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }
                char leftChar = str.charAt(windowStart++);
                if (map.containsKey(leftChar)) {
                    if (map.get(leftChar) == 0)
                        matched--;

                    map.put(leftChar, map.get(leftChar) + 1);
                }
            }
        }

        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart+minLength);
    }

    public static void main(String[] args) {
        System.out.println(findMinSubstring("aabdec", "abc"));
        System.out.println(findMinSubstring("abdabca", "abc"));
        System.out.println(findMinSubstring("adcad", "abc"));
        System.out.println(findMinSubstring("aaacb", "abc"));

    }
}
