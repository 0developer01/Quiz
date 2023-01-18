package com.example.quizchannel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder>{
    private Context context;
    private List<QuizModel> quizModelList;

    public QuizAdapter(Context context) {
        this.context = context;
        quizModelList = new ArrayList<>();
    }
    public void addQuiz(QuizModel quizModel){
        quizModelList.add(quizModel);
        notifyDataSetChanged();
    }
    public void removeQuiz(int pos) {
        quizModelList.remove(pos);
        notifyDataSetChanged();
    }
    public void clear(){
        quizModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_view,
                parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuizModel quizModel = quizModelList.get(position);
        holder.bindViews(quizModel);
    }

    @Override
    public int getItemCount() {
        return quizModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText quizName;
        private CheckBox checkBox;
        private Button updateBtn;
        private Button dltBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.quizName);
            checkBox = itemView.findViewById(R.id.quizVisibility);
            updateBtn = itemView.findViewById(R.id.addQuiz);
            dltBtn = itemView.findViewById(R.id.dltQuiz);
        }

        public void bindViews(QuizModel quizModel) {
            quizName.setText(quizModel.getQuizName());
            checkBox.setChecked(quizModel.isVisible());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuestionActivity.class);
                    intent.putExtra("id", quizModel.getQuizId());
                    context.startActivity(intent);
                }
            });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = quizName.getText().toString();
                    boolean visible = checkBox.isChecked();
                    FirebaseFirestore
                            .getInstance()
                            .collection("quiz")
                            .document(quizModel.getQuizId())
                            .update("quizName",name,
                                    "visible",visible);
                }
            });
            dltBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name  = quizName.getText().toString();
                    boolean visible = checkBox.isChecked();
                    FirebaseFirestore
                            .getInstance()
                            .collection("quiz")
                            .document(quizModel.getQuizId())
                            .delete();
                }
            });
        }
    }
}
