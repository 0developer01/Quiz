package com.example.quizchannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.example.quizchannel.databinding.ActivityAdminPanelBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.UUID;

public class AdminPanel extends AppCompatActivity {
    ActivityAdminPanelBinding binding;

    //calling of quizAdapter
    QuizAdapter quizAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizAdapter = new QuizAdapter(this);


        binding.addQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //making of quiz model

                String id = UUID.randomUUID().toString();
                String name = binding.quizName.getText().toString();
                boolean visible = binding.quizVisibility.isChecked();
                QuizModel quizModel = new QuizModel(id, name, visible, 0);


                //To upload to DB

                uploadQuiz(quizModel);
            }
        });
        binding.quizRecycler.setAdapter(quizAdapter);
        binding.quizRecycler.setLayoutManager(new LinearLayoutManager(this));

        loadQuiz();



    }
    //Loading of Quiz
    private void loadQuiz() {
        FirebaseFirestore
                .getInstance()
                .collection("quiz")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds:dsList){
                            QuizModel quizModel = ds.toObject(QuizModel.class);
                            quizAdapter.addQuiz(quizModel);
                        }
                    }
                });
    }
//function for uploading quiz

    private void uploadQuiz(QuizModel quizModel) {
        FirebaseFirestore.getInstance()
                .collection("quiz")
                .document(quizModel.getQuizId())
                .set(quizModel);
        binding.quizName.setText("");
    }

}