import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Words Concatenation (hard) #
Given a string and a list of words, find all the starting indices of substrings in the given string that are a concatenation of all the given words exactly once without any overlapping of words. It is given that all words are of the same length.

Example 1:

Input: String="catfoxcat", Words=["cat", "fox"]
Output: [0, 3]
Explanation: The two substring containing both the words are "catfox" & "foxcat".
Example 2:

Input: String="catcatfoxfox", Words=["cat", "fox"]
Output: [3]
Explanation: The only substring containing both the words is "catfox".
 */

public class WordConcat {

    public static List<Integer> findWordConcat(String str, String[] words){
        List<Integer> resultIndices = new ArrayList<>();

        int windowStart = 0;
        int startSubstr = 0;

        // freq of every word from list in the string
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words)
            map.put(s, map.getOrDefault(s, 0)+1);

        int wordCount = words.length;
        int wordLength = words[0].length();

        for (int i = 0; i <= str.length() - wordLength * wordCount; i++){
            HashMap<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordCount; j++){
                int wordIndex = i + j*wordLength;
                String word = str.substring(wordIndex, wordIndex+wordLength);

                if (!map.containsKey(word)){
                    break;
                }

                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0)+1);

                if (wordsSeen.get(word) > map.getOrDefault(word, 0)){
                    break;
                }

                if (j + 1 == wordCount)
                    resultIndices.add(i);
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
//        List<Integer> res = WordConcat.findWordConcat("catfoxcat", new String[] {"cat", "fox"});
//        System.out.println(res);
//
//        res = WordConcat.findWordConcat("catcatfoxfox", new String[] {"cat", "fox"});
//        System.out.println(res);

        int[] allCards = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = new ArrayList<>();
        int[][] allHands = new int[21][5];
        int cardsSelected = 0;
        int hand = 0;

        // select first card not to be in the hand
        for(int firstCard = 0; firstCard < 7; firstCard++){
            // select first card not to be in the hand
            for(int secondCard = firstCard + 1; secondCard < 7; secondCard++){
                // every card that is not the first or second will added to the hand
                for(int i = 0; i < 7; i++){
                    if(i != firstCard && i != secondCard){
                        allHands[hand][cardsSelected++] = allCards[i];
                        list.add(allCards[i]);
                    }
                }
                // next hand
                cardsSelected = 0;
                hand++;
                System.out.println(list);
                list = new ArrayList<>();
            }
        }

        //System.out.println(Arrays.deepToString(allHands));


    }

}

