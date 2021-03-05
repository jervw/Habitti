package com.example.habitti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopItemAdapter extends BaseAdapter
{
    private String[] imageNames;
    private int[] imagesPhoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public ShopItemAdapter(String[] imageNames, int[] imagesPhoto, Context context) {
        this.imageNames = imageNames;
        this.imagesPhoto = imagesPhoto;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesPhoto.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_item, viewGroup, false);
        }

        TextView txName = view.findViewById(R.id.textViewItem);
        ImageView imageView = view.findViewById(R.id.imageViewItem);

        txName.setText(imageNames[i]);
        imageView.setImageResource(imagesPhoto[i]);

        return view;
    }
}

