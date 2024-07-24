package com.example.jogodaforca;

import android.os.Bundle;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] words = {"percy", "cardan", "wylan", "jason", "hades"};
    private int currentWordIndex;
    private String currentWord = "";
    private int attempts = 0;
    private ImageView hangmanImageView;
    private EditText guessEditText;
    private Button guessButton;
    private TextView attemptsTextView;
    private TextView wordTextView;
    private char[] wordArray;
    private Random random = new Random();
    private String previousWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hangmanImageView = findViewById(R.id.hangman_image_view);
        guessEditText = findViewById(R.id.guess_edit_text);
        guessButton = findViewById(R.id.guess_button);
        attemptsTextView = findViewById(R.id.attempts_text_view);
        wordTextView = findViewById(R.id.word_text_view);

        generateNewWord();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = guessEditText.getText().toString();
                if (guess.length() == 1) {
                    boolean correct = false;
                    for (int i = 0; i < currentWord.length(); i++) {
                        if (String.valueOf(currentWord.charAt(i)).equalsIgnoreCase(guess)) {
                            correct = true;
                            wordArray[i] = currentWord.charAt(i);
                        }
                    }
                    if (correct) {
                        updateWordTextView(wordArray);
                    } else {
                        attempts++;
                        updateHangmanImage(attempts);
                        attemptsTextView.setText("Tentativas: " + attempts);
                    }
                }
            }
        });
    }

    private void generateNewWord() {
        do {
            currentWordIndex = random.nextInt(words.length);
            currentWord = words[currentWordIndex];
        } while (currentWord.equals(previousWord));
        previousWord = currentWord;

        wordArray = new char[currentWord.length()];
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = '_';
        }
        updateWordTextView(wordArray);
        updateHangmanImage(attempts);
    }

    private void updateHangmanImage(int attempts) {
        switch (attempts) {
            case 0:
                hangmanImageView.setImageResource(R.drawable.vazia);
                break;
            case 1:
                hangmanImageView.setImageResource(R.drawable.cabeca);
                break;
            case 2:
                hangmanImageView.setImageResource(R.drawable.umbraco);
                break;
            case 3:
                hangmanImageView.setImageResource(R.drawable.doisbraco);
                break;
            case 4:
                hangmanImageView.setImageResource(R.drawable.umaperna);
                break;
            case 5:
                hangmanImageView.setImageResource(R.drawable.duasperna);
                break;
        }
    }

    private void updateWordTextView(char[] wordArray) {
        StringBuilder wordStringBuilder = new StringBuilder();
        for (char c : wordArray) {
            wordStringBuilder.append(c).append(" ");
        }
        wordTextView.setText(wordStringBuilder.toString());
    }
}