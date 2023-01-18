package com.example.quizchannel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizA extends RecyclerView.Adapter<QuizA.MyViewHolder> {
    private Context context;
    private List<QuizM> quizMList;

    public QuizA(Context context) {
        this.context = context;
        quizMList = new ArrayList<>();
    }

    public void addQuiz(QuizM quizM) {
        quizMList.add(quizM);
        notifyDataSetChanged();
    }

    public void removeQuiz(int pos) {
        quizMList.remove(pos);
        notifyDataSetChanged();
    }

    public void clear() {
        quizMList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizA.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz,
                parent, false);
        return new QuizA.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizA.MyViewHolder holder, int position) {
        QuizM quizM = quizMList.get(position);
        holder.bindViews(quizM);
    }

    @Override
    public int getItemCount() {
        return quizMList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView quizName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.quizName);

        }

        public void bindViews(QuizM quizM) {
            quizName.setText(quizM.getQuizName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuizQuestion.class);
                    intent.putExtra("id", quizM.getQuizId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
