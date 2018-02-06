package com.hangoclong.calculater_android_oe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Float mPara1, mPara2;
    private String mOperator, mString1 = "", mString2 = "";
    private int mTemp = 0;
    private Float mResult;
    private TextView mTextViewResult;
    private boolean mNext = false;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        int[] idButton = {R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four,
                R.id.button_five, R.id.button_six, R.id.button_seven, R.id.button_eight, R.id.button_night,
                R.id.button_clear, R.id.button_plus, R.id.button_minus, R.id.button_mul,
                R.id.button_div, R.id.button_equal};
        for (int i : idButton) {
            View mView = findViewById(i);
            mView.setOnClickListener(this);
        }

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mSharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        float result = mSharedPreferences.getFloat("result",0);
        mTextViewResult.setText(String.valueOf(result));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                refresh();
                break;
            case R.id.save:
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                if (mResult != null) {
                    mEditor.putFloat("result", mResult);
                } else {
                    mEditor.putFloat("result", 0.f);
                }
                mEditor.apply();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_plus:
                operatorCondition("+");
                break;
            case R.id.button_minus:
                operatorCondition("-");
                break;
            case R.id.button_mul:
                operatorCondition("X");
                break;
            case R.id.button_div:
                operatorCondition("/");
                break;
            case R.id.button_equal:
                equal();
                break;
            case R.id.button_clear:
                refresh();
                break;
            default:
                if (mNext && mOperator.equals("")) {
                    refresh();
                    mNext = false;
                }

                if (mTemp == 1) {
                    mString2 += ((Button)view).getText().toString();
                    mString1 += ((Button)view).getText().toString();
                    mTextViewResult.setText(mString1);
                } else {
                    mString1 += ((Button)view).getText().toString();
                    mTextViewResult.setText(mString1);
                }
                break;
        }
    }

    private void operatorCondition(String operator) {
        if (operator.equals("-") && mString1.equals("")) {
            mString1 += operator;
            mTextViewResult.setText(mString1);
        }
        if (!mString1.equals("-") && !mString1.equals("")) {
            mTemp++;
            if (mTemp > 1) {
                mTemp = 1;
            } else {
                mOperator = operator;
                mPara1 = Float.parseFloat(mTextViewResult.getText().toString());
                mString1 += operator;
                mTextViewResult.setText(mString1);
            }
        }
    }

    private void equal() {
        if (!mString1.equals("") && !mString2.equals("")) {
            switch (mOperator) {
                case "+":
                    mPara2 = Float.parseFloat(mString2);
                    mResult = mPara1 + mPara2;
                    break;
                case "-":
                    mPara2 = Float.parseFloat(mString2);
                    mResult = mPara1 - mPara2;
                    break;
                case "*":
                    mPara2 = Float.parseFloat(mString2);
                    mResult = mPara1 * mPara2;
                    break;
                case "/":
                    mPara2 = Float.parseFloat(mString2);
                    mResult = mPara1 / mPara2;
                    break;
            }

            mNext = true;
            mTextViewResult.setText(String.valueOf(mResult));
            mTemp = 0;
            mString1 = mResult.toString();
            mString2 = "";
            mOperator = "";
        }
    }

    private void refresh() {
        mString1 = "";
        mString2 = "";
        mPara2 = null;
        mPara1 = null;
        mTextViewResult.setText("0");
        mTemp = 0;
        mResult = null;
        mOperator = "";
        mNext = false;
    }

}
