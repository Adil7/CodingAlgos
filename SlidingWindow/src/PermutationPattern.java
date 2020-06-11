import java.util.HashMap;
import java.util.Map;

public class PermutationPattern {
    /*
    Given a string and a pattern, find out if the string contains any permutation of the pattern.

Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:

abc
acb
bac
bca
cab
cba
If a string has ‘n’ distinct characters it will have n!n! permutations.

Example 1:

Input: String="oidbcaf", Pattern="abc"
Output: true
Explanation: The string contains "bca" which is a permutation of the given pattern.
Example 2:

Input: String="odicf", Pattern="dc"
Output: false
Explanation: No permutation of the pattern is present in the given string as a substring.
Example 3:

Input: String="bcdxabcdy", Pattern="bcdyabcdx"
Output: true
Explanation: Both the string and the pattern are a permutation of each other.
Example 4:

Input: String="aaacb", Pattern="abc"
Output: true
Explanation: The string contains "acb" which is a permutation of the given pattern.
     */

    public static boolean findPermutation(String str, String pattern){
        // freq of pattern chars
        HashMap<Character, Integer> map = new HashMap<>();
        int matchedChars = 0;
        for (char c : pattern.toCharArray())
            map.put(c, map.getOrDefault(c, 0)+1);

        int windowStart = 0;

        // goal is to match all chars from freq map with the current window
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++){
            char rightChar = str.charAt(windowEnd);
            if (map.containsKey(rightChar)){
                // decrement freq of matched char
                map.put(rightChar, map.get(rightChar) - 1);
                // if the char exists in the string then decrement
                if (map.get(rightChar) == 0)
                    matchedChars++;
            }

            // if we find that all chars from the pattern exist in the str then return
            if (matchedChars == map.size())
                return true;

            // shrink window for the other chars
            if (windowEnd >= pattern.length()-1){
                char leftChar = str.charAt(windowStart);
                windowStart++;
                if (map.containsKey(leftChar)){
                    if (map.get(leftChar) == 0)
                        matchedChars--;

                    map.put(leftChar, map.get(leftChar)+1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(findPermutation("oidbcaf", "abc"));
        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findPermutation("aaacb", "abc"));

    }
}
