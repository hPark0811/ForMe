package com.heung.forme;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.heung.forme.ui.main.SectionsPagerAdapter;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
    }

    public void login(View view){
        boolean isCorrect = true;

        /*@TODO: Add login verification*/

        if (isCorrect){
            setContentView(R.layout.activity_main);
            findViewById(R.id.view_pager).setAlpha(0f);
            newSingleThreadScheduledExecutor().schedule(task, 3, TimeUnit.SECONDS);
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabLayout = findViewById(R.id.tabDots);
            tabLayout.setupWithViewPager(viewPager, true);
            /*FloatingActionButton fab = findViewById(R.id.fab);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            })*/;
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Login!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    Runnable task = new Runnable() {
        public void run() {
            findViewById(R.id.progressBar).setAlpha(0f);
            findViewById(R.id.loadingText).setAlpha(0f);
            findViewById(R.id.view_pager).setAlpha(1f);
        }
    };
}