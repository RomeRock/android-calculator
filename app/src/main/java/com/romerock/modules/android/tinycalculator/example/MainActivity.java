package com.romerock.modules.android.tinycalculator.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.romerock.modules.android.tinycalculator.Calculator;
import com.romerock.modules.android.tinycalculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.calcEfect1)
    Button calcEfect1;
    @BindView(R.id.calcEfect2)
    Button calcEfect2;
    @BindView(R.id.calcEfect3)
    Button calcEfect3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = getString(R.string.share_text_msn);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.romerock));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
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

    @OnClick({R.id.calcEfect1, R.id.calcEfect2, R.id.calcEfect3})
    public void onClick(View view) {
        Calculator calc=new Calculator(this, 0);
        View v = calc;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        int optionEfect=0;
        switch (view.getId()) {
            case R.id.calcEfect1:
                optionEfect=0;
                break;
            case R.id.calcEfect2:
                optionEfect=1;
                break;
            case R.id.calcEfect3:
                optionEfect=2;
                break;
        }
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations =calc.Animation(optionEfect);
        dialog.show();
    }
}
