package my.first.quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    QuestionBank obj = new QuestionBank();
    int index = 0;
    int QuestionId;
    int ColorId;
    ques_fragment fragmentObj;
    Button trueButton;
    Button falseButton;
    StorageManager storageObject = new StorageManager();

    int attempts = 0;
    int averageScore;

    public void UpdateFragment(int qId, int colorId) {

        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.fragment_container);
        fragmentObj = ques_fragment.newInstance(qId, colorId);
        manager.beginTransaction().replace(R.id.fragment_container, fragmentObj).commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("QuestionIndex");
        }


        QuestionId = obj.questionList.get(index).question;
        ColorId = obj.questionList.get(index).color;
        UpdateFragment(QuestionId, ColorId);

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QuestionIndex", index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.average:{

                int attemptCount = storageObject.CountNumberOfAttempts();
                int totalAverage = storageObject.CountAverageScore();

                String dialogMessage = "Your correct answers are " + totalAverage
                                        + " in " + attemptCount + " attempts";
                System.out.println(dialogMessage);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(dialogMessage);
                builder.setPositiveButton("OK",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
            case R.id.reset_data:{
                storageObject.ResetData(MainActivity.this);
                break;
            }
        }
        return true;
    }

    Boolean tag;
    int totalScore;
    String getAverageDialogString;


    public void ButtonClicked(View view) {
        if (index < obj.questionList.size()-1) {
            if(trueButton.isPressed()){
                tag = true;
                System.out.println(tag);
            }
            else{
                tag = false;
            }

            if(tag==obj.questionList.get(index).answer){
                totalScore++;
                Toast.makeText(this, "Your answer is correct", Toast.LENGTH_SHORT).show();
            }
            if(tag!=obj.questionList.get(index).answer) {
                Toast.makeText(this, "Your answer is incorrect", Toast.LENGTH_SHORT).show();
            }
            System.out.println("*** Total Score ****" + totalScore);

            index++;
            QuestionId = obj.questionList.get(index).question;
            ColorId = obj.questionList.get(index).color;
            System.out.println("****Button Clicked*****");

            UpdateFragment(QuestionId, ColorId);


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Your Scores are"+ "\t" + totalScore +"\t"+ "out of 10 !!");
            getAverageDialogString = totalScore +"/" + 10 + "#";
            builder.setPositiveButton("Save", (dialogInterface, i) -> storageObject.SaveData
                    (MainActivity.this,getAverageDialogString));
            totalScore=0;

           // builder.setPositiveButton("Save", null);
            builder.setNegativeButton("Ignore",null);
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            index = 0;
            UpdateFragment(obj.questionList.get(index).question,obj.questionList.get(index).color);


        }
    }
}

