package com.example.habitti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <h1>RewardsItemAdapter</h1>
 * Creates a grid view adapter based on base adapter.
 *
 * @author Anna Raevskaia
 */
public class RewardsItemAdapter extends BaseAdapter
{
    private String[] imageNames;
    private int[] imagesPictures;
    private Context context;
    private LayoutInflater layoutInflater;

    /**
     * @param imageNames get text of images names
     * @param imagesPictures get array of images
     * @param context get the context
     */
    public RewardsItemAdapter(String[] imageNames, int[] imagesPictures, Context context) {
        this.imageNames = imageNames;
        this.imagesPictures = imagesPictures;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @return imagesPictures
     */
    @Override
    public int getCount() {
        return imagesPictures.length;
    }

    /**
     * @param i get item
     * @return null
     */
    @Override
    public Object getItem(int i) {
        return null;
    }

    /**
     * @param i get item id
     * @return null
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Fills the grid view with images and text.
     * @param i get index
     * @param view get view
     * @param viewGroup get viewGroup
     * @return view
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_item, viewGroup, false);
        }

        TextView txName = view.findViewById(R.id.textViewItem);
        ImageView imageView = view.findViewById(R.id.imageViewItem);

        txName.setText(imageNames[i]);
        imageView.setImageResource(imagesPictures[i]);

        return view;
    }
}

