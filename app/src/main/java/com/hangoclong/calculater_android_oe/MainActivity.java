package com.hangoclong.calculater_android_oe;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hangoclong.calculater_android_oe.Fragment.CalculatorFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        CalculatorFragment mCalculatorFragment = new CalculatorFragment();
        fragmentTransaction.add(R.id.linear, mCalculatorFragment);
        fragmentTransaction.commit();
    }

}
