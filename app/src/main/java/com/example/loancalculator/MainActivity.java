package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.preference.PreferenceManager;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etNumberOfYears= (EditText) findViewById(R.id.etNumberOfYears);
        final EditText etLoan= (EditText) findViewById(R.id.etLoan);
        final EditText etInterestRate= (EditText) findViewById(R.id.etInterestRate);
        Button btnPayment= (Button) findViewById(R.id.btnPayment);

        final SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intNumberOfYears=Integer.parseInt(etNumberOfYears.getText().toString());
                int intLoanAmount=Integer.parseInt(etLoan.getText().toString());
                float decInterestRate=Float.parseFloat(etInterestRate.getText().toString());

                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt("keyNumberOfYears", intNumberOfYears);
                editor.putInt("keyLoanAmount", intLoanAmount);
                editor.putFloat("keyInterestRate",decInterestRate);
                editor.commit();

                startActivity(new Intent(MainActivity.this, Payment.class));

            }
        });

    }
}