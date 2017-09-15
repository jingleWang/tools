import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import javax.annotation.PostConstruct;

/**
 * Created by Ben on 2017/7/10.
 */
public class WordsCorrectionService {


    private String addr = "tcp://192.168.140.112:5555";


    private int timeout = 1000;


    private int ioThreads = 10;

    private ZContext context;

    public WordsCorrectionService() {
        init();
    }

    public void init() {
        context = new ZContext(ioThreads);
    }

    public CorrectionModel wordCorrect(String word) {
        ZMQ.Socket requester = null;
        try {
            requester = context.createSocket(ZMQ.REQ);
            requester.connect(this.addr);//服务器地址和端口
            String request = word;

            requester.send(request.getBytes(), 0);

            requester.setReceiveTimeOut(timeout);

            byte[] reply = requester.recv(0);

            if (reply != null) {
                String str = new String(reply);
                //                System.out.println(word + ":" + str);
                CorrectionModel model = new CorrectionModel(new String(reply));
                return model;
            } else {
                CorrectionModel model = new CorrectionModel();
                model.setWord(word);
                return model;
            }

        } catch (Exception e) {
            CorrectionModel model = new CorrectionModel();
            model.setWord(word);
            return model;
        } finally {
            if (requester != null) {
                requester.close();
                context.destroySocket(requester);
            }
        }
    }

    public CorrectionModel correct(String word) {

        CorrectionModel corrects = wordCorrect(word);
        try {


         /* 1.返回纠正词只有一个，返回纠正词；
         * 2.纠正词有多个，如果原词在纠正词中，且coeff>=1,返回原词
         * 3.纠正词有多个， 如果原词在纠正词组内且0<coeff<1，纠正词的coeff都小于1，如果其中一个纠正词系数是原词的0.5倍以上，返回纠正词，否则返回原词;
         * 4.纠正词有多个，如果原词在纠正词中，且0<coeff<1,纠正词中有coeff>=1,取coeff最大的纠正词；
         * 5.纠正词有多个，原词在纠正词中，且coeff<=0,纠正词的coeff都小于0，返回原词；
         * 6.纠正词有多个，原词在纠正词中，且coeff<=0,纠正词中有coeff>0,取coeff最大的纠正词；
         * 7.如果不在纠正词内，则根据系数探测纠正词的区分度, 有明显差异才选择信赖纠正词:
         *   差异比较方法： 如果其中一个与其他纠正词，相关系数大于其他所有系数0.5倍以上时，取系数最大的这个，否则返回原词 */


            //如果没有纠正词，返回原词
            if (corrects.getCorrections().size() == 0) {
                return corrects;
            }

            //如果只有一个纠正词，返回纠正词
            if (corrects.getCorrections().size() == 1) {
                CorrectionModel.Correction correction = corrects.getCorrections().get(0);
                if (!correction.getWord().equals(word)) {
                    corrects.setCandidate(correction.getWord());
                    corrects.setCandidateCoeff(correction.getCoeff());
                }
                return corrects;
            }




            boolean originWordInCorrects = false;   //原词是否在纠正词中
            boolean allCorrectLT0 = true;          //纠正词中的coeff都小于0
            boolean allCorrectLT1 = true;          //纠正词中的coeff是否都小于1
            double maxCoeff = -Double.MAX_VALUE;    //系数最大值
            String firstCorrect = "";               //系数最多的纠正词
            double secondCoeff = -Double.MAX_VALUE; //系数第二大值
            String secondCorrect = "";              //系数第二大的纠正词
            //统计
            for (CorrectionModel.Correction correction : corrects.getCorrections()) {

                //原词是否在纠正词中
                if (correction.getWord().equals(word)) {
                    originWordInCorrects = true;
                }

                //判断纠正词系数是否都小于0
                if (correction.getCoeff() > 0) {
                    allCorrectLT0 = false;
                }

                //判读纠正词系数是否都小于1
                if (correction.getCoeff() > 1) {
                    allCorrectLT1 = false;
                }

                //寻找最大的coeff
                if (correction.getCoeff() > secondCoeff) {
                    secondCoeff = correction.getCoeff();
                    secondCorrect = correction.getWord();

                    if (secondCoeff > maxCoeff) {
                        double tempCoeff = secondCoeff;
                        secondCoeff = maxCoeff;
                        maxCoeff = tempCoeff;

                        String tempCorrect = firstCorrect;
                        firstCorrect = secondCorrect;
                        secondCorrect = tempCorrect;
                    }
                }
            }


            //如果原词coeff>1
            if (corrects.getCoeff() >= 1) {
                //存在词频比原词更高的纠正词
                if (!firstCorrect.equals(word)) {
                    corrects.setCandidate(firstCorrect);
                    corrects.setCandidateCoeff(maxCoeff);
                    corrects.setHasBetter(true);
                }
                return corrects;
            }

            //原词在纠正词组内且0<coeff<1
            if (originWordInCorrects & corrects.getCoeff() > 0 && corrects.getCoeff() < 1) {

                //纠正词的coeff都小于1，返回原词
                if (allCorrectLT1) {
                    if ((maxCoeff / corrects.getCoeff()) >= 0.5) {
                        corrects.setCorrect(false);
                        corrects.setCandidate(firstCorrect);
                        corrects.setCandidateCoeff(maxCoeff);
                    }
                    return corrects;
                } else {
                    corrects.setCorrect(false);
                    corrects.setCandidate(firstCorrect);
                    corrects.setCandidateCoeff(maxCoeff);
                    return corrects;
                }
            }

            //原词在纠正词组内且coeff<=0
            if (originWordInCorrects & corrects.getCoeff() <= 0) {
                if (allCorrectLT0) {
                    return corrects;
                } else {
                    corrects.setCorrect(false);
                    corrects.setCandidate(firstCorrect);
                    corrects.setCandidateCoeff(maxCoeff);
                    return corrects;
                }
            }


            //原词不在纠正词组内
            if (!originWordInCorrects) {
                if ((maxCoeff * secondCoeff < 0) || (maxCoeff / secondCoeff) >= 0.5) {
                    corrects.setCorrect(false);
                    corrects.setCandidate(firstCorrect);
                    corrects.setCandidateCoeff(maxCoeff);
                    return corrects;
                } else {
                    return corrects;
                }
            }

            return corrects;
        } finally {
            if (corrects != null) {
                word += "(";
                if (true) {
                    word += ",coeff=" + corrects.getCoeff();
                }
                word += ")";

                StringBuilder builder = new StringBuilder();

                for (CorrectionModel.Correction correction : corrects.getCorrections()) {
                    builder.append(correction.getWord());
                    if (true) {
                        builder.append("(" + correction.getCoeff() + ")");
                    }
                    builder.append(" ");
                }
                System.out.println(word + ": " + builder.toString());
            }

        }
    }
}
