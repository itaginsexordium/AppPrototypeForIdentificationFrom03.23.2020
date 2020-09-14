package com.kg.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;


    private SliderAdapter sliderAdapter;


     private Button btnNext;
    private Button  btnBack;
    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSliderViewPager=(ViewPager) findViewById(R.id.slideViewpager);
        mDotLayout=(LinearLayout)findViewById(R.id.dotsLayout);

        btnNext = (Button)findViewById(R.id.buttonNext);
        btnBack = (Button)findViewById(R.id.buttonBack);


        sliderAdapter = new SliderAdapter(this);

        mSliderViewPager.setAdapter(sliderAdapter);


        addDotsIndicator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNext.getText().toString()=="Finish") {
                    Intent intent = new Intent(HomeActivity.this, StartIdentificationActivity.class);
                    startActivity(intent);
                }
                mSliderViewPager.setCurrentItem(mCurrentPage +1);



            }
        });




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage -1);
            }
        });

    }

    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++) {


            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.backgroundLogo));

            mDotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0 ){
            mDots[position].setTextColor(getResources().getColor(R.color.dots));
        }



    }

    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage= position;

            if (position == 0){
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);
                btnBack.setVisibility(View.INVISIBLE);

                btnNext.setText("Next");
                btnBack.setText("");

            }else if (position == mDots.length -1 ) {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("Finish");
                btnBack.setText("Back");
            }
            else {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("Next");
                btnBack.setText("Back");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
