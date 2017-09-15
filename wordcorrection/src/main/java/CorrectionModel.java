import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 2017/7/20.
 */
public class CorrectionModel {
    private String word;
    private double coeff;
    private List<Correction> corrections = new ArrayList<>();
    private String candidate;
    private double candidateCoeff;
    private boolean correct; //当采用原词正确时为true，采用原词；当原词错误时为false，采用候选词
    private boolean hasBetter = false; //当原词正确，但纠正词中出现词频更高词时，该字段为true，候选词为词频最高的纠正词

    public CorrectionModel(String json) {
        JSONObject jsonObj = JSONObject.parseObject(json);

        JSONObject input = jsonObj.getJSONObject("input");
        word = input.getString("word");
        coeff = input.getDouble("coeff");
        correct = true;
        hasBetter = false;

        JSONArray output = jsonObj.getJSONArray("output");
        for (int i = 0; i < output.size(); i++) {
            Correction correction = new Correction();
            Object object = output.get(i);
            if (object instanceof String) {
                correction.word = (String) object;
            } else {
                JSONObject jsonObject = (JSONObject) object;
                correction.word = jsonObject.getString("word");
                correction.coeff = jsonObject.getDouble("coeff");
                correction.editDistance = jsonObject.getInteger("editDistance");
            }
            corrections.add(correction);
        }
    }

    public CorrectionModel() {
        correct = true;
        hasBetter = false;
    }

    public class Correction {
        private String word;
        private Double coeff;
        private Integer editDistance;

        public String getWord() {
            return word;
        }

        public double getCoeff() {
            return coeff;
        }

        public int getEditDistance() {
            return editDistance;
        }

        @Override
        public String toString() {
            return "Correction{" + "word='" + word + '\'' + ", coeff=" + coeff + ", editDistance=" + editDistance + '}';
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public List<Correction> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<Correction> corrections) {
        this.corrections = corrections;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public double getCandidateCoeff() {
        return candidateCoeff;
    }

    public void setCandidateCoeff(double candidateCoeff) {
        this.candidateCoeff = candidateCoeff;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }


    public boolean isHasBetter() {
        return hasBetter;
    }

    public void setHasBetter(boolean hasBetter) {
        this.hasBetter = hasBetter;
    }

    @Override
    public String toString() {
        return "CorrectionModel{" +
                "word='" + word + '\'' +
                ", coeff=" + coeff +
                ", corrections=" + corrections +
                ", candidate='" + candidate + '\'' +
                ", candidateCoeff=" + candidateCoeff +
                ", correct=" + correct +
                ", hasBetter=" + hasBetter +
                '}';
    }
}
