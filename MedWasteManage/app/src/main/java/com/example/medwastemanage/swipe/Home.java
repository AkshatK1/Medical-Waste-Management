package com.example.medwastemanage.swipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.medwastemanage.R;
import com.example.medwastemanage.SignupActivity;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button registor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.corona, "Covid19 Pandemic", "This app will help us to prevent the spread of Covid19 disease by dumping the infectious equipment " +
                "such as Masks,Gloves,infected injections" +
                " "));
        models.add(new Model(R.drawable.spread, "WideSpread", "We well known about the rapid spread of the viruse which is capable of spreading exponetially" +
                "we all together can break tha chain and this app will play a vital role in doing so"));
        models.add(new Model(R.drawable.precautions, "Precaution is better than cure", "To avoid spread of the disease several several precautionary steps are given which is suggested by WHO"));
        models.add(new Model(R.drawable.waste, "Dump it till the end!", "We should keep a check on the waste produced in tackling the cautious disease remember if the equipments related to Covid19 is not removed from environment it will become hard for us to loose the virus. We all have a role to play So report for any biomedical waste nearby"));
        registor= findViewById(R.id.btnOrder);
        registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };
        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}

