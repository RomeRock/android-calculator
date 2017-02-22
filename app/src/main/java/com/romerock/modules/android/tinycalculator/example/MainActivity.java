package com.romerock.modules.android.tinycalculator.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.romerock.modules.android.tinycalculator.Calculator;
import com.romerock.modules.android.tinycalculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private View.OnClickListener onClickListener;
    @BindView(R.id.calcEfect1)
    Button calcEfect1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (v.getId() == R.id.btnDone || v.getId() == R.id.btnClose) {
                    alertDialog.dismiss();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_visitanos) {
            Uri uri = Uri.parse(getString(R.string.romerock_site)); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.calcEfect1})
    public void onClick(View view) {
        Calculator calc=new Calculator(this, 0);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialog);
        LayoutInflater inflater = (this).getLayoutInflater();
        builder.setCancelable(true);
        view = calc;
        view.findViewById(R.id.btnDone).setOnClickListener(onClickListener);
        view.findViewById(R.id.btnClose).setOnClickListener(onClickListener);
        builder.setView(view);
        builder.create();
        alertDialog = builder.show();
    }
}
