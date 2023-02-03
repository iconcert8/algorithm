import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
class Solution {
    public int solution(String[] babbling) {
        String[] basicWords = {"aya", "ye", "woo", "ma"};

        int count = 0;
        for (String oneBabbling : babbling) {
            if (recursiveWordCheck(oneBabbling, basicWords, basicWords)) {
                count++;
            }
        }


        return count;
    }
    
    boolean recursiveWordCheck(String remainString, String[] enableBasicWords, String[] orginBasicWords) {
        if (remainString.isEmpty()) {
            return true;
        }

        String startWord = null;
        for (String basicWord : enableBasicWords) {
            if (remainString.startsWith(basicWord)) {
                startWord = basicWord;
                enableBasicWords = Arrays.stream(orginBasicWords).filter(s -> !s.equals(basicWord)).collect(Collectors.toList()).toArray(new String[0]);
                break;
            }
        }

        if (startWord == null) {
            return false;
        }

        return recursiveWordCheck(remainString.substring(startWord.length()), enableBasicWords, orginBasicWords);
    }
}

