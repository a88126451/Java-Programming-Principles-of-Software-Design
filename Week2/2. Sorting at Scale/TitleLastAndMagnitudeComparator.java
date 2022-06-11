import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>
{
    private String getLastWord (QuakeEntry qe) {
        String title = qe.getInfo();
        String lastWord = title.substring(title.lastIndexOf(" ")+1);
        return lastWord;
    }
    public int compare (QuakeEntry q1, QuakeEntry q2) {
        String q1LastWord = getLastWord(q1);
        String q2LastWord = getLastWord(q2);
        
        if (q1LastWord.compareTo(q2LastWord) < 0) {
            return -1;
        } else if (q1LastWord.compareTo(q2LastWord) > 0) {
            return 1;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        
    }
}
