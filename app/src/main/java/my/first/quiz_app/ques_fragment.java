package my.first.quiz_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ques_fragment extends Fragment {
    QuestionBank obj = new QuestionBank();
    TextView quesText;
    int question;
    int color;

    public static ques_fragment newInstance(int quesID,int colorId) {
        
        Bundle args = new Bundle();
        args.putInt("QuestionId",quesID);
        args.putInt("ColorId",colorId);
        ques_fragment fragment = new ques_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //   Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_ques_fragment, container, false);
        quesText = (TextView) view.findViewById(R.id.quesText);
//        quesText.setText(obj.questionList.get(index).question);
//        index++;
       // quesText.setText(mainObj.QuestionId);
       // quesText.setBackgroundColor(mainObj.ColorId);
        question = getArguments().getInt("QuestionId");
      //  color = getArguments().getInt("ColorId");
        quesText.setText(question);
        //quesText.setBackgroundColor(color);
        //System.out.println(color);

        return view;
    }
    
}