package model;

public enum SecurityQuestions {
    QUESTION1("your favorite anime character?"),
    QUESTION2("your favorite soccer team?"),
    QUESTION3("your favorite video game?");

    private String question;

    SecurityQuestions(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }

    public static SecurityQuestions getQuestion(String question){
        switch (question){
            case "your favorite anime character?":
                return QUESTION1;
            case "your favorite soccer team?":
                return QUESTION2;
            default:
                return QUESTION3;
        }
    }
}
