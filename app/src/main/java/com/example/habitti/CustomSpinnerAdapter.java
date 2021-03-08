package com.example.habitti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <h1>Custom adapter for spinner</h1>
 * Needed for making a custom spinner with only images
 * @author Santeri Hytönen
 */
public class CustomSpinnerAdapter extends ArrayAdapter {
    String[] spinnerTitles;
    int[] spinnerImages;
    Context context;

    /**
     * Basic information for creating spinner
     * @param context Set context for spinner
     * @param images list of image ids
     * @param titles list of names for images and default Habit names
     */
    public CustomSpinnerAdapter(@NonNull Context context, int[] images, String[] titles) {
        super(context, R.layout.spinner_adapter_layout);
        this.spinnerImages = images;
        this.spinnerTitles = titles;
        this.context = context;
    }

    /**
     * Get size of the images-list
     * @return size of the images-list
     */
    @Override
    public int getCount() {
        return spinnerImages.length;
    }

    /**
     * Used to set the dropDownView
     * @param position current item position
     * @param convertView convertView
     * @param parent paret
     * @return getView
     */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    /**
     * Constructor for ViewHolder
     */
    private static class ViewHolder {
        ImageView mHabbit;
        TextView mName;
    }

    /**
     * Used to setup the adapter
     * @param position current item position
     * @param convertView convertView
     * @param parent current parent
     * @return builded convertView
     */
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

    /**
     * Used to get current item name
     * @param position current position
     * @return name of the item
     */
    public String getItemName(int position) {
        return spinnerTitles[position];
    }
}
