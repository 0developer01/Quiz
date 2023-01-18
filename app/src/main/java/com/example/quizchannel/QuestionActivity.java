package com.example.quizchannel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.quizchannel.databinding.ActivityQuestionBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class QuestionActivity extends AppCompatActivity {

    ActivityQuestionBinding binding;
    private String quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizId = getIntent().getStringExtra("id");

        binding.uploadQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = "";
                String questionId = UUID.randomUUID().toString();
                String question = binding.questionTitle.getText().toString();
                String choice1 = binding.choice1.getText().toString();
                String choice2 = binding.choice2.getText().toString();
                String choice3 = binding.choice3.getText().toString();
                String choice4 = binding.choice4.getText().toString();
                boolean visible = binding.showCheckBox.isChecked();

                RadioButton selectedRadio = (RadioButton)findViewById(binding.radioGroup.getCheckedRadioButtonId());
                String selctedText = selectedRadio.getText().toString();
                if (selctedText.equals("1")){
                    answer = choice1;
                }
                if (selctedText.equals("2")){
                    answer = choice2;
                }
                if (selctedText.equals("3")){
                    answer = choice3;
                }
                if (selctedText.equals("4")){
                    answer = choice4;
                }

                QuestionModel questionModel =
                        new QuestionModel(questionId, quizId, question, choice1, choice2, choice3,
                                choice4, answer, visible);

                FirebaseFirestore.getInstance()
                        .collection("questions")
                        .document(questionId)
                        .set(questionModel);

            }
        });

    }
}