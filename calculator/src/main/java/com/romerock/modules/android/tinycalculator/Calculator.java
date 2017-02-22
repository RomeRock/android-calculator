package com.romerock.modules.android.tinycalculator;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romerock.calculator.R;

/**
 * Created by Ebricko on 10/11/2016.
 */

public class Calculator extends LinearLayout {
    final String TAG = "Simple Calculator";
    Button btnClear;
    Button btnBack;
    Button btnZero;
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnMultiply;
    Button btnSubtract;
    Button btnDecimal;
    Button btnEquals;
    Button btnAdd;
    Button btnDivide;
    Button btnClose;
    Button btnDone;
    TextView screen;
    TextView txtHistory;
    String history;
    double number1;
    double number2;
    double result;
    Boolean firstTime;
    String operationScreen;
    Context context;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(final View v) {
            Button b;
            if (firstTime) {
                if (v.getId() != R.id.btnOne && v.getId() != R.id.btnTwo && v.getId() != R.id.btnThree && v.getId() != R.id.btnFour && v.getId() != R.id.btnFive
                        && v.getId() != R.id.btnSix && v.getId() != R.id.btnSeven && v.getId() != R.id.btnEight && v.getId() != R.id.btnNine && v.getId() != R.id.btnDecimal) {
                    return;
                } else {
                    b = (Button) v;
                    String buttonText = b.getText().toString();
                    firstTime = false;
                    double value;
                    if (v.getId() == R.id.btnDecimal)
                        value = 0;
                    else
                        value = Double.parseDouble(b.getText().toString());
                    screen.setText(b.getText().toString());
                    number1 = value;
                    screen.setHint("0");

                }
            } else {
                if (v.getId() == R.id.btnClear) {
                    screen.setText("");
                    screen.setHint("0");
                    txtHistory.setText("");
                    operationScreen = "";
                    number1 = number2 = result = 0;
                    firstTime = true;
                } else if (v.getId() == R.id.btnBack) {
                    if (!screen.getText().toString().trim().isEmpty())
                        if (screen.getText().toString().length() == 1) {
                            screen.setText("");
                            number1 = 0;
                        } else {
                            screen.setText(screen.getText().toString().substring(0, screen.getText().toString().length() - 1));
                            number1 = roundTwoDecimals(Double.parseDouble(screen.getText().toString()));
                        }

                } else {
                    b = (Button) v;
                    if (v.getId() == R.id.btnOne || v.getId() == R.id.btnTwo || v.getId() == R.id.btnThree || v.getId() == R.id.btnFour || v.getId() == R.id.btnFive
                            || v.getId() == R.id.btnSix || v.getId() == R.id.btnSeven || v.getId() == R.id.btnEight || v.getId() == R.id.btnNine || v.getId() == R.id.btnZero || v.getId() == R.id.btnDecimal) {
                        String buttonText = b.getText().toString();
                        if (!txtHistory.getText().toString().equals("")) {


                            if (txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == '=') {
                                txtHistory.setText("");
                                operationScreen = "";
                                number1 = number2 = result = 0;
                            }
                        }
                        if (v.getId() == R.id.btnDecimal) {
                            if (screen.getText().toString().contains("."))
                                return;
                            if (screen.getText().toString().equals("0.0") || screen.getText().toString().equals("0") || screen.getText().toString().equals(".") || screen.getText().toString().equals("0.") || screen.getText().toString().equals(""))
                                screen.setText("0.");
                        }
                        String screenT = screen.getText().toString() + buttonText;
                        screenT = screenT.replace("..", ".");
                        screen.setText(screenT);
                        number1 = roundTwoDecimals(Double.parseDouble(screenT));
                    } else {
                        if (v.getId() == R.id.btnAdd || v.getId() == R.id.btnMultiply || v.getId() == R.id.btnSubtract || v.getId() == R.id.btnDivide || v.getId() == R.id.btnEquals) {
                            if (txtHistory.getText().toString() != "" && screen.getText().toString().equals("")) {
                                if (txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == '+'
                                        || txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == '-'
                                        || txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == 'x'
                                        || txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == '='
                                        || txtHistory.getText().toString().charAt(txtHistory.getText().toString().length() - 1) == '/') {
                                    txtHistory.setText(txtHistory.getText().toString().substring(0, txtHistory.getText().toString().length() - 1) + b.getText().toString());
                                    operationScreen = b.getText().toString();
                                    screen.setText("");
                                    return;
                                }
                            }
                            txtHistory.setText(txtHistory.getText() + String.valueOf(roundTwoDecimals(number1)) + b.getText().toString());
                            if (operationScreen == "") {
                                screen.setHint("0");
                                operationScreen = b.getText().toString();
                                number2 = number1;
                                number1 = 0;
                                screen.setText("");
                            } else {
                                operation();

                                operationScreen = b.getText().toString();
                                screen.setText("");
                                if (!context.getResources().getString(R.string.no_division_cero).equals(screen.getHint()))
                                    screen.setHint(String.valueOf(roundTwoDecimals(result)));
                                number2 = result;
                                number1 = result = 0;
                            }
                        }
                    }
                }
            }
        }
    };

    public Calculator(Context context, int style) {
        super(context);

        this.context = context;
        Log.d(TAG, "Welcome " + TAG);
        inflate(context, R.layout.calculator, this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnZero = (Button) findViewById(R.id.btnZero);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDone= (Button) findViewById(R.id.btnDone);
        btnClear.setOnClickListener(onClickListener);
        btnBack.setOnClickListener(onClickListener);
        btnSeven.setOnClickListener(onClickListener);
        btnEight.setOnClickListener(onClickListener);
        btnNine.setOnClickListener(onClickListener);
        btnDivide.setOnClickListener(onClickListener);
        btnFour.setOnClickListener(onClickListener);
        btnFive.setOnClickListener(onClickListener);
        btnSix.setOnClickListener(onClickListener);
        btnMultiply.setOnClickListener(onClickListener);
        btnOne.setOnClickListener(onClickListener);
        btnTwo.setOnClickListener(onClickListener);
        btnThree.setOnClickListener(onClickListener);
        btnSubtract.setOnClickListener(onClickListener);
        btnDecimal.setOnClickListener(onClickListener);
        btnZero.setOnClickListener(onClickListener);
        btnEquals.setOnClickListener(onClickListener);
        btnAdd.setOnClickListener(onClickListener);
        screen = (TextView) findViewById(R.id.txtScreen);
        screen.setOnClickListener(onClickListener);
        txtHistory = (TextView) findViewById(R.id.txtHistory);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Rubik-Light.ttf");
        btnOne.setTypeface(font);
        btnTwo.setTypeface(font);
        btnThree.setTypeface(font);
        btnSubtract.setTypeface(font);
        btnDecimal.setTypeface(font);
        btnZero.setTypeface(font);
        btnEquals.setTypeface(font);
        btnAdd.setTypeface(font);
        btnClear.setTypeface(font);
        btnBack.setTypeface(font);
        btnSeven.setTypeface(font);
        btnEight.setTypeface(font);
        btnNine.setTypeface(font);
        btnDivide.setTypeface(font);
        btnFour.setTypeface(font);
        btnFive.setTypeface(font);
        btnSix.setTypeface(font);
        btnMultiply.setTypeface(font);
        screen.setTypeface(font);
        txtHistory.setTypeface(font);
        btnDone.setTypeface(font);

        operationScreen = "";
        number1 = number2 = result = 0;
        firstTime = true;
    }



    public Calculator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void operation() {
        switch (operationScreen) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number2 - number1;
                break;
            case "x":
                result = number1 * number2;
                break;
            case "/":
                if (number1 != 0) {
                    result = number2 / number1;
                } else {
                    screen.setHint(context.getResources().getString(R.string.no_division_cero));
                    txtHistory.setText("");
                    operationScreen = "";

                    number1 = number2 = result = 0;
                    firstTime = true;
                }
                break;
        }
    }

    public int Animation(int form) {
        switch (form) {
            case 1:
                form = R.style.DialogAnimation_RightToLeft;
                break;
            case 2:
                form = R.style.DialogAnimation_DownToUp;
                break;
            case 0:
            default:
                break;
        }
        return form;
    }

    double roundTwoDecimals(double d) {
        long rounded = Math.round(d * 100);
        return rounded / 100.0;
    }
}
