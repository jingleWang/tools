import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Ben on 2017/8/18.
 */
public class CorrectTest {
    //结果
    final static ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
    //返回不存在原词
    final static ConcurrentHashMap<String, String> diffs = new ConcurrentHashMap<>();
    //系数为-1的词
    final static ConcurrentLinkedDeque notExist = new ConcurrentLinkedDeque();

    final static WordsCorrectionService correctionService = new WordsCorrectionService();

    static String root = "C:\\Users\\Ben\\Desktop\\纠错\\";

    //输入路径
    static String v = "idf_item_v.txt";
    static String n = "idf_item_n.txt";
    static String worngWord = "newWrong.txt";

    //输出路径
    static String out = "out-2.txt";
    static String diffPath = "diff-2.txt";
    static String notExistPath = "notExist-2.txt";

    static Map<String, String> wrongWords = DataService.getWrongWords(root + worngWord);

    static List<String> v_data = DataService.getData(root + v);
    static List<String> n_data = DataService.getData(root + n);


    public static void main(String[] args) {
        List<String> words = new ArrayList<>();


//        words.addAll(wrongWords.keySet());
        words.addAll(n_data);
        words.addAll(v_data);

        Map<String, String> newDic = new HashMap<>();

        for (String word : words) {
            CorrectionModel correct = correctionService.correct(word);
            String trueWord = wrongWords.get(word);
            if (correct.isCorrect()) {
                if (correct.getWord().equals(trueWord)) {
                    continue;
                }

                if (correct.isHasBetter() && correct.getCandidate().equals(trueWord)) {
                    continue;
                }
            } else {
                if (correct.getCandidate().equals(trueWord)) {
                    continue;
                }
            }
            diffs.put(word+"(" + trueWord + ")", "correct=" + correct.isCorrect() + ",hasBetter=" + correct.isHasBetter() + ",candidate="+correct.getCandidate());

        }

//        DataService.writeMap(root + out, result);
//        DataService.writeMap(root + diffPath, diffs);
//        DataService.writeQueue(root + notExistPath, notExist);
        //        DataService.writeMap(root + "newWrong.txt", newDic, " ");
    }
}
