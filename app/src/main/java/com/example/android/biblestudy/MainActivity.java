package com.example.android.biblestudy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.example.android.biblestudy.R.string.question5;
import static com.example.android.biblestudy.R.string.question6;
import static com.example.android.biblestudy.R.string.question7;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void SubmitAnswers(View view) {
        EditText nameField = (EditText) findViewById(R.id.userName);
        String name = nameField.getText().toString();

        RadioButton question1 = (RadioButton) findViewById(R.id.question1_true);
        boolean question1Checked = question1.isChecked();

        RadioButton question2 = (RadioButton) findViewById(R.id.question2_true);
        boolean question2Checked = question2.isChecked();

        RadioButton question3 = (RadioButton) findViewById(R.id.question3_false);
        boolean question3Checked = question3.isChecked();

        RadioButton question4 = (RadioButton) findViewById(R.id.question4_true);
        boolean question4Checked = question4.isChecked();

        RadioButton question5 = (RadioButton) findViewById(R.id.question5_false);
        boolean question5Checked = question5.isChecked();

        RadioButton question6 = (RadioButton) findViewById(R.id.question6_false);
        boolean question6Checked = question6.isChecked();

        RadioButton question7 = (RadioButton) findViewById(R.id.question7_true);
        boolean question7Checked = question7.isChecked();

        //Score calculation
        int totalScore = calculateScore(question1Checked, question2Checked, question3Checked, question4Checked, question5Checked, question6Checked, question7Checked);
        String ResultMessage = createReportSummary(name, totalScore);
        displayMessage(ResultMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz results for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, ResultMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
    /**
     * Calculates the score of the test.
     *
     * @param q1 is whether or not the user gets question one
     * @param q2,q3,q4,q5,q6,q7 is whether or not the user gets questions
     * @return total score
     */
    private int calculateScore(boolean q1, boolean q2, boolean q3, boolean q4, boolean q5, boolean q6, boolean q7) {
        int score = 0;
        if (q1){
            score = score + 1;
        }
        if (q2){
            score = score + 1;
        }
        if (q3){
            score = score + 1;
        }
        if (q4){
            score = score + 1;
        }
        if (q5){
            score = score + 1;
        }
        if (q6){
            score = score + 1;
        }
        if (q7){
            score = score + 1;
        }
        return score;
    }
    /**
     * Create result of test
     *
     * @param name            of the quiz taker
     * @param score           of the test
     * @return text summary
     */
    private String createReportSummary(String name, int score) {
        String ResultMessage = "Name: " + name;
        ResultMessage += "\nyour score is: " + score;
        ResultMessage += "\n" + getString(R.string.weldone);
        return ResultMessage;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.your_result);
        orderSummaryTextView.setText(message);
    }
}
