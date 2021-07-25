package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        TextView tvMontlyPayment =(TextView) findViewById(R.id.tvMontlyPayment);
        TextView tvTotalInterest =(TextView) findViewById(R.id.tvTotalInterest);
        TextView tvTotalPayment=(TextView) findViewById(R.id.tvTotalPayment);

        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this);

        int intNumberOfYears=sharedPreferences.getInt( "keyNumberOfYears", 0);
        int intLoanAmount=sharedPreferences.getInt( "keyLoanAmount", 0);
        float decInterestRate=sharedPreferences.getFloat( "keyInterestRate", 0);
        float decMonthlyPayment, decTotalPayment;
        //calculate montly_payment
        float calculatedInterest=decInterestRate/100/12;
        float x=(float) Math.pow(1+calculatedInterest,intNumberOfYears*12);
        decMonthlyPayment=(intLoanAmount*x*calculatedInterest)/(x-1);
        decTotalPayment=decMonthlyPayment*intNumberOfYears*12;
        float decTotalInterest = decTotalPayment-intLoanAmount;
        DecimalFormat decimalFormat= new DecimalFormat("$###,###.##");
        tvMontlyPayment.setText("Monthly Payment: " +decimalFormat.format(decMonthlyPayment));
        tvTotalInterest.setText("Total Interest: " +decimalFormat.format(decTotalInterest));
        tvTotalPayment.setText("Total Payment: " + decimalFormat.format(decTotalPayment));


    }
}