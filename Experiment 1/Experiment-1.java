interface ModerationRule {
    int applyModeration(int rawScore);
}

class AttendanceModeration implements ModerationRule {
    public int applyModeration(int rawScore) {
        return rawScore + 5;
    }
}

class DifficultyModeration implements ModerationRule {
    public int applyModeration(int rawScore) {
        return (int)(rawScore * 1.1);
    }
}

class ManualModeration implements ModerationRule {
    public int applyModeration(int rawScore) {
        return rawScore + 2;
    }
}

interface Evaluation {
    void evaluateStudent(int theoryMarks, int labMarks);
}

class BTechEvaluation implements Evaluation {
    private ModerationRule rule;

    public BTechEvaluation(ModerationRule rule) {
        this.rule = rule;
    }

    public void evaluateStudent(int theoryMarks, int labMarks) {
        int rawScore = (int)(0.7 * theoryMarks + 0.3 * labMarks);
        int finalScore = rule.applyModeration(rawScore);

        String grade = (finalScore >= 85) ? "A" :
                       (finalScore >= 70) ? "B" :
                       (finalScore >= 50) ? "C" : "F";

        System.out.println("B.Tech Final Score: " + finalScore + ", Grade: " + grade);
    }
}

class MCAEvaluation implements Evaluation {
    private ModerationRule rule;

    public MCAEvaluation(ModerationRule rule) {
        this.rule = rule;
    }

    public void evaluateStudent(int theoryMarks, int labMarks) {
        int rawScore = (int)(0.6 * theoryMarks + 0.4 * labMarks);
        int finalScore = rule.applyModeration(rawScore);

        String grade = (finalScore >= 80) ? "Distinction" :
                       (finalScore >= 60) ? "First Class" :
                       (finalScore >= 40) ? "Pass" : "Fail";

        System.out.println("MCA Final Score: " + finalScore + ", Grade: " + grade);
    }
}

class PhDEvaluation implements Evaluation {
    private ModerationRule rule;

    public PhDEvaluation(ModerationRule rule) {
        this.rule = rule;
    }

    public void evaluateStudent(int theoryMarks, int labMarks) {
        int rawScore = theoryMarks + labMarks;
        int finalScore = rule.applyModeration(rawScore);

        String grade = (finalScore >= 90) ? "Outstanding" :
                       (finalScore >= 75) ? "Excellent" :
                       (finalScore >= 60) ? "Good" : "Needs Improvement";

        System.out.println("PhD Final Score: " + finalScore + ", Grade: " + grade);
    }
}

public class EvaluationSystem {
    public static void main(String[] args) {
        Evaluation btechEval = new BTechEvaluation(new AttendanceModeration());
        btechEval.evaluateStudent(80, 70);

        Evaluation mcaEval = new MCAEvaluation(new DifficultyModeration());
        mcaEval.evaluateStudent(75, 85);

        Evaluation phdEval = new PhDEvaluation(new ManualModeration());
        phdEval.evaluateStudent(90, 80);
    }
}