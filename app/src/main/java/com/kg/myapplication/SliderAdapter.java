package com.kg.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images={
      R.drawable.logo,
      R.drawable.qiwi_verified,
      R.drawable.qiwi_maximal
    };

    public String[] slide_headings= {

            "welcome",
            "verified status",
            "Qiwi maximal"
    };
    public String[] slide_descs = {
            "Привет наша компания занимается индификацией киви кошельков на рынке кыргызстана"
                    + " мы предоставляем следующие услуги индификации ",
            "Статус основной (параметры основного статуса "
                    +"стоймость 750 руб ",
            "Статутс профессиональный является максимальным в системе киви и снимает все ограничения на платежи "
                    +"стоймость 1300 Руб"


    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject( View view,  Object o) {
        return view==(RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_laout,container,false);


        ImageView slideImageView= (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription=(TextView)view.findViewById(R.id.slide_decs);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);



        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((RelativeLayout)object);
    }
}
