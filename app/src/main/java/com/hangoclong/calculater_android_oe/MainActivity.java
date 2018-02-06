package com.hangoclong.calculater_android_oe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Float mPara1, mPara2;
    private String mOperator, mString1 = "", mString2 = "";
    private int mTemp = 0;
    private Float mResult;
    private TextView mTextViewResult;
    private boolean mNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_result);
        int[] idButton = {R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four,
                R.id.button_five, R.id.button_six, R.id.button_seven, R.id.button_eight, R.id.button_night,
                R.id.button_clear, R.id.button_plus, R.id.button_minus, R.id.button_mul,
                R.id.button_div, R.id.button_equal};
        for (int i : idButton) {
            View view = findViewById(i);
            view.setOnClickListener(this);
        }
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
        mTextViewResult.setText("");
        mTemp = 0;
        mResult = null;
        mOperator = "";
        mNext = false;
    }

}