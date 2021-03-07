package com.example.habitti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class CustomSpinnerAdapter extends ArrayAdapter {
    String[] spinnerTitles;
    int[] spinnerImages;
    Context context;

    public CustomSpinnerAdapter(@NonNull Context context, int[] images, String[] titles) {
        super(context, R.layout.spinner_adapter_layout);
        this.spinnerImages = images;
        this.spinnerTitles = titles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spinnerImages.length;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        ImageView mHabbit;
        TextView mName;
    }

    @NonNull
    @Override
    public  View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_adapter_layout, parent, false);
            mViewHolder.mHabbit = (ImageView) convertView.findViewById(R.id.spinnerImage);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mHabbit.setImageResource(spinnerImages[position]);

        return convertView;
    }

    public String getItemName(int position) {
        return spinnerTitles[position];
    }
}
