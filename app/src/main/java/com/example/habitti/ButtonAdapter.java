package com.example.habitti;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ButtonAdapter extends BaseAdapter
{
    private Context mContext;
    private int btn_id;
    private int total_btns = 20;

    int[] clothesImages = {R.drawable.char_13, R.drawable.char_2, R.drawable.char_15, R.drawable.char_10, R.drawable.char_14};

    public ButtonAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return total_btns;
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
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        Button btn;

        if (view == null) {
            btn = new Button(mContext);
            btn.setText("Button " + (++btn_id));
        } else {
            btn = (Button) view;
        }

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), "Button #" + (i + 1), Toast.LENGTH_SHORT).show();
            }
        });

        return btn;
    }
}
