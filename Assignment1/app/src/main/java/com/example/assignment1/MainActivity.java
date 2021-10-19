package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double interestRate, monthlyPay;
    int numYears, mortgageAmount;
    String output;


    EditText mortgage;
    EditText interest;
    EditText years;
    TextView outputText1;

    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //used to connect the the views by id
        mortgage = findViewById(R.id.mortgage);
        interest = findViewById(R.id.interestRate);
        years = findViewById(R.id.years);
        outputText1 = findViewById(R.id.outputText);

        calculate = (Button) findViewById(R.id.calculate);

        //event lister used when the user presses the calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //used to check if any of the user input fields are left blank
                // if so than set to zero... this is one way to handle the errors so the app won't crash
                if (mortgage.getText().toString().length() == 0){
                    mortgage.setText("0");
                }
                if (interest.getText().toString().length() == 0){
                    interest.setText("0");
                }
                if (years.getText().toString().length() == 0){
                    years.setText("0");
                }

                //Take user input and store in there specified variables
                mortgageAmount = Integer.valueOf(mortgage.getText().toString());
                interestRate = Double.valueOf(interest.getText().toString()) / 100;
                numYears = Integer.valueOf(years.getText().toString());

                //if interest is 0% then the payment is the total amount / divided by the number of payments
                if (interestRate == 0 && mortgageAmount > 0 && numYears > 0){
                    monthlyPay = mortgageAmount / (numYears * 12);
                }
                //used to calculate to monthly payment
                else {
                    monthlyPay = (mortgageAmount * (interestRate / 12) * Math.pow((1 + (interestRate / 12)), 12 * numYears)) / (Math.pow(1 + (interestRate / 12), 12 * numYears) - 1);
                }

                //round answer to 2 decimal places
                monthlyPay = Math.round(monthlyPay * 100.00) / 100.00;

                //set the text view that is under the monthly payment amount to the calculated monthly payment amount
                output = "$" + String.valueOf(monthlyPay);
                outputText1.setText(output);

            }
        });
    }
}