package com.example.quizchannel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quizchannel.databinding.ActivityQuizQuestionBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion extends AppCompatActivity {
    ActivityQuizQuestionBinding binding;
    String quizId;
    private List<QuestionModel> questionModelList;

    private static int position = 0;
    private static String answer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizId = getIntent().getStringExtra("id");

        loadQuestions(quizId);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionModelList != null && answer != null){
                    RadioButton radioButton = (RadioButton) findViewById(binding.optionGroup.getCheckedRadioButtonId());
                    String selectedRadioText = radioButton.getText().toString();
                    if (selectedRadioText.equals(answer)){
                        Toast.makeText(QuizQuestion.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(QuizQuestion.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    }
                    position += 1;
                    showQsOnebyOne(position);
                }
            }
        });
    }
//Loading of Questions
    private void loadQuestions(String quizId) {
        FirebaseFirestore
                .getInstance()
                .collection("questions")
                .whereEqualTo("quizId",quizId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                        questionModelList = new ArrayList<>();
                        for (DocumentSnapshot ds:dsList){
                            QuestionModel questionModel = ds.toObject(QuestionModel.class);
                            questionModelList.add(questionModel);
                        }
                        if (dsList.size() > 0){
                            showQsOnebyOne(position);
                        }else {
                            Toast.makeText(QuizQuestion.this, "No Questions Found", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    private void showQsOnebyOne(int i) {
        QuestionModel questionModel = questionModelList.get(i);

        answer = questionModel.getCorrectOne();

        binding.quizQuesion.setText(questionModel.getQuestion());
        binding.option1.setText(questionModel.getChoice1());
        binding.option2.setText(questionModel.getChoice2());
        binding.option3.setText(questionModel.getChoice3());
        binding.option4.setText(questionModel.getChoice4());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(quizId == null){
            Toast.makeText(this, "Session timeout", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}