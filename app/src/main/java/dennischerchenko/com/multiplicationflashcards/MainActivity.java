package dennischerchenko.com.multiplicationflashcards;
//Dennis Cherchenko
//dcherche@bu.edu


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> success = new ArrayList<String>();
    private ArrayList<String> failure = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        success.add(getResources().getString(R.string.successMessage1));
        success.add(getResources().getString(R.string.successMessage2));
        success.add(getResources().getString(R.string.successMessage3));
        success.add(getResources().getString(R.string.successMessage4));
        failure.add(getResources().getString(R.string.failureMessage1));
        failure.add(getResources().getString(R.string.failureMessage2));
        failure.add(getResources().getString(R.string.failureMessage3));
        failure.add(getResources().getString(R.string.failureMessage4));

        generateNewProblem();

        Button btnEnter = (Button) findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doResult();
            }
        });
    }

    //Returns random number for which messge to display
    private int calculateIndex(int arrayLength){

        double temp = Math.random();

        for(int i = 0; i < arrayLength; i++){
            if(temp < ((i+1.0) / arrayLength)){
                return i;
            }
        }

        return 0;
    }

    //Returns the message to be displayed
    private String getMessage(boolean isCorrect){

        if(isCorrect == true){
            return success.get(calculateIndex(success.size()));
        }else{
            return failure.get(calculateIndex(failure.size()));
        }
    }

    //Generates new number for text on GUI
    private static int generateNewNumber(){
        System.out.print(R.string.successMessage1);
        return (int) (10 * Math.random());
    }

    //Generates two new numbers for text on GUI
    private void generateNewProblem(){
        ((TextView) findViewById(R.id.lblFirstNumber)).setText(Integer.toString(generateNewNumber()));
        ((TextView) findViewById(R.id.lblSecondNumber)).setText(Integer.toString(generateNewNumber()));
        ((TextView) findViewById(R.id.txtAnswer)).setText("");
    }

    //Checks if user got problem correct
    private static boolean isCorrectMultiplication(int firstNumber, int secondNumber, int submittedAnswer){
        return submittedAnswer == firstNumber * secondNumber;
    }

    //Sets the result label to whether the user got the problem right
    private void setResultLabel(boolean isCorrect){
        ((TextView) findViewById(R.id.lblResult)).setText(getMessage(isCorrect));
    }

    //Calculate result and displays appropriate response and does all other work
    private void doResult(){

        int firstNumber = Integer.parseInt(((TextView) findViewById(R.id.lblFirstNumber)).getText().toString());
        int secondNumber = Integer.parseInt(((TextView) findViewById(R.id.lblSecondNumber)).getText().toString());
        int submittedAnswer = Integer.parseInt(((TextView) findViewById(R.id.txtAnswer)).getText().toString());

        ((TextView) findViewById(R.id.txtAnswer)).setText("");

        boolean isCorrect = isCorrectMultiplication(firstNumber, secondNumber, submittedAnswer);
        setResultLabel(isCorrect);

        if(isCorrect == true){
            generateNewProblem();
        }
    }
}
