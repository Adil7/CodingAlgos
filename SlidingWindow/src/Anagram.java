import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagram {
    public static List<Integer> findAnagram(String str, String pattern){
        List<Integer> res = new ArrayList<>();
        int windowStart = 0;
        int matched = 0;
        int minLength = str.length()+1;
        int minStart = 0;

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : pattern.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            char rightChar = str.charAt(windowEnd);
            // decrement freq of char matching with pattern
            if (freq.containsKey(rightChar)){
                freq.put(rightChar, freq.get(rightChar)-1);
                if (freq.get(rightChar) == 0)
                    matched++;
            }

            if (matched == freq.size())
                res.add(windowStart);

            if (windowEnd >= pattern.length() - 1){
                char leftChar = str.charAt(windowStart++);
                if (freq.containsKey(leftChar)){
                    if (freq.get(leftChar) == 0){
                        matched--;
                    }
                    freq.put(leftChar, freq.get(leftChar)+1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(findAnagram("ppqp", "pq"));
        System.out.println(findAnagram("abbcabc", "abc"));
        System.out.println(findAnagram("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findAnagram("aaacb", "abc"));

    }
}
