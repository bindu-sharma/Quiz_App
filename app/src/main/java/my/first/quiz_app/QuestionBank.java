package my.first.quiz_app;

import java.util.ArrayList;
public class QuestionBank {
    ArrayList<Question> questionList = new ArrayList<>();
  //  ArrayList<Integer> colorList = new ArrayList<>();


    QuestionBank(){
        Question ques1 = new Question(R.string.Question1,R.color.Red,false);
        Question ques2 = new Question(R.string.Question2,R.color.Blue,true);
        Question ques3 = new Question(R.string.Question3,R.color.Yellow,false);
        Question ques4 = new Question(R.string.Question4,R.color.Silver,true);
        Question ques5 = new Question(R.string.Question5,R.color.Pink,true);
        Question ques6 = new Question(R.string.Question6,R.color.Magenta,false);
        Question ques7 = new Question(R.string.Question7,R.color.Maroon,true);
        Question ques8 = new Question(R.string.Question8,R.color.Beige,true);
        Question ques9 = new Question(R.string.Question9,R.color.Orange,true);
        Question ques10 = new Question(R.string.Question10,R.color.Gray,false);
        questionList.add(ques1);
        questionList.add(ques2);
        questionList.add(ques3);
        questionList.add(ques4);
        questionList.add(ques5);
        questionList.add(ques6);
        questionList.add(ques7);
        questionList.add(ques8);
        questionList.add(ques9);
        questionList.add(ques10);



    }

}
