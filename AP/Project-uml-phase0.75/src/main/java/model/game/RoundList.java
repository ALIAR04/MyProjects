package model.game;

import java.util.ArrayList;

public class RoundList extends ArrayList<Round> {
    public RoundList(){
        super();
    }
    public RoundList(String roundListString){
        super();
        roundListString = roundListString.substring(10, roundListString.length() - 1);
        String[] rounds = roundListString.split("\\|");
        for (String roundString: rounds)
            this.add(new Round(roundString));
    }

    @Override
    public String toString() {
        StringBuilder roundListString = new StringBuilder();
        roundListString.append("RoundList{");
        for (Round round: this){
            if (this.indexOf(round) < this.size() - 1)
                roundListString.append(round.toString()).append("|");
            else roundListString.append(round.toString());
        }
        roundListString.append("}");
        return roundListString.toString();
    }
}
