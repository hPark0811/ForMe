package com.heung.forme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.heung.forme.custom.recommendation.Login;
import com.heung.forme.ui.main.SectionsPagerAdapter;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }

    public void login(View view){
        String username = String.valueOf(((EditText) findViewById(R.id.username)).getText());
        String pw = String.valueOf(((EditText) findViewById(R.id.password)).getText());

        if (Login.checkLogin(username, pw)){
            setContentView(R.layout.activity_main);
            findViewById(R.id.view_pager).setAlpha(0f);
            newSingleThreadScheduledExecutor().schedule(task, 3, TimeUnit.SECONDS);
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(Login.getUserID(username), getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabLayout = findViewById(R.id.tabDots);
            tabLayout.setupWithViewPager(viewPager, true);


            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(view1 -> Snackbar.make(view1, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            );

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Login!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    Runnable task = () -> {
        findViewById(R.id.progressBar).setAlpha(0f);
        findViewById(R.id.loadingText).setAlpha(0f);
        findViewById(R.id.view_pager).setAlpha(1f);
    };

    public void openTdCreditCardURL(View view){
        Uri uri = Uri.parse("https://www.td.com/ca/en/personal-banking/products/credit-cards/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}