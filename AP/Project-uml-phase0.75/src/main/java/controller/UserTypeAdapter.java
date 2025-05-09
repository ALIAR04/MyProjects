package controller;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import java.io.IOException;

import model.SecurityQuestions;
import model.User;
import model.game.Deck;
import model.game.DeckList;
import model.game.GameHistory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class UserTypeAdapter extends TypeAdapter<User> {
    @Override
    public void write(JsonWriter jsonWriter, User user) throws IOException {
        System.out.println(user.getUsername());
        jsonWriter.beginObject();
        jsonWriter.name("username").value(user.getUsername());
        jsonWriter.name("password").value(user.getPassword());
        jsonWriter.name("nickname").value(user.getNickname());
        jsonWriter.name("email").value(user.getEmail());
        jsonWriter.name("securityQuestions").value(user.getSecurityQuestions().toString());
        jsonWriter.name("answerOfSecurityQuestion").value(user.getAnswerOfSecurityQuestion());
        jsonWriter.name("numberOfGames").value(user.getNumberOfGames());
        jsonWriter.name("wins").value(user.getWins());
        jsonWriter.name("draws").value(user.getDraws());
        jsonWriter.name("loses").value(user.getLoses());
        jsonWriter.name("highScore").value(user.getHighScore());
        jsonWriter.name("totalScore").value(user.getTotalScore());
        jsonWriter.name("savedDecks").value(user.getSavedDecks().toString());
        jsonWriter.name("gameHistory").value(user.getGameHistory().toString());
        if (user.getLastDeck() != null) {
            jsonWriter.name("lastDeck").value(user.getLastDeck().toString());
        } else {
            jsonWriter.name("lastDeck").value("null");
        }
        jsonWriter.endObject();
    }

    @Override
    public User read(JsonReader jsonReader) throws IOException {
        User user = new User();
        jsonReader.beginObject();
        String fieldName;
        while (jsonReader.hasNext()) {
            fieldName = jsonReader.nextName();
            switch (fieldName) {
                case "username":
                    user.setUsername(jsonReader.nextString(), false);
                    break;
                case "password":
                    user.setPassword(jsonReader.nextString(), false);
                    break;
                case "nickname":
                    user.setNickname(jsonReader.nextString(), false);
                    break;
                case "email":
                    user.setEmail(jsonReader.nextString(), false);
                    break;
                case "securityQuestions":
                    user.setSecurityQuestions(SecurityQuestions.getQuestion(jsonReader.nextString())
                            , false);
                    break;
                case "answerOfSecurityQuestion":
                    user.setAnswerOfSecurityQuestion(jsonReader.nextString(), false);
                    break;
                case "numberOfGames":
                    user.setNumberOfGames(Integer.parseInt(jsonReader.nextString()), false);
                    break;
                case "wins":
                    user.setWins(jsonReader.nextInt(), false);
                    break;
                case "draws":
                    user.setDraws(jsonReader.nextInt(), false);
                    break;
                case "loses":
                    user.setLoses(jsonReader.nextInt(), false);
                    break;
                case "highScore":
                    user.setHighScore(jsonReader.nextInt(), false);
                    break;
                case "totalScore":
                    user.setTotalScore(jsonReader.nextInt(), false);
                    break;
                case "savedDecks":
                    user.setSavedDecks(new DeckList(jsonReader.nextString()), false);
                    break;
                case "gameHistory":
                    user.setGameHistory(new GameHistory(jsonReader.nextString()), false);
                    break;
                case "lastDeck":
                    String deckString = jsonReader.nextString();
                    if (deckString.equals("null")) continue;
                    user.setLastDeck(new Deck(deckString), false);
                    break;
            }
        }
        jsonReader.endObject();
        return user;
    }
}