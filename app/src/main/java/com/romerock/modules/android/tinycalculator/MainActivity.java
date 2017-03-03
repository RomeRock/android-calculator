package com.romerock.modules.android.tinycalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.img_logo_romerock)
    ImageView img_logo_romerock;
    @BindView(R.id.follow_twitter)
    ImageView followTwitter;
    @BindView(R.id.follow_gitHub)
    ImageView followGitHub;
    @BindView(R.id.follow_facebook)
    ImageView followFacebook;
    @BindView(R.id.calculator)
    Button calcEfect1;
    private AlertDialog alertDialog;
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (v.getId() == R.id.btnDone || v.getId() == R.id.btnClose) {
                    alertDialog.dismiss();
                }
            }
        };


        WebView view = new WebView(this);
        view.setVerticalScrollBarEnabled(false);
        view.setBackgroundColor(getResources().getColor(R.color.drawable));
        ((RelativeLayout)findViewById(R.id.relContent)).addView(view);
        view.loadData(getString(R.string.thank_you), "text/html; charset=utf-8", "utf-8");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.menu_main, menu);*/
        return true;
    }


    @OnClick({R.id.img_logo_romerock, R.id.follow_twitter, R.id.follow_gitHub, R.id.follow_facebook, R.id.calculator})
    public void onClick(View view) {
        String url = "";
        switch (view.getId()) {
            case R.id.img_logo_romerock:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.romerock_page))));
                break;
            case R.id.follow_facebook:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.follow_us_facebook_profile)));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.follow_us_facebook))));
                }
                break;
            case R.id.follow_gitHub:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.follow_us_git_hub))));
                break;
            case R.id.follow_twitter:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.follow_us_twitter_profile)));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.follow_us_twitter))));
                }
                break;
            case R.id.calculator:
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    Calculator calc = new Calculator(this, 0);
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
                break;
        }
    }
}
