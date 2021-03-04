package com.example.habitti;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class ShopPopUp extends Activity {

    private SharedPreferences sharedPrefHabbits;
    int[] clothesImages;
    int[] hairsImages;
    int[] numberImage;
    private Context mContext;

    GridView shopItemGV;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SHOP", "Shop onCreate()");

        setContentView(R.layout.shop_pop_up_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int) (width*.8), (int) (height*.6));
        getWindow().setLayout((int) (width), (int) (height));

        updateUI();

        //shopItemGV = findViewById(R.id.idGV);

        GridView gridview = (GridView) findViewById(R.id.idGV);
        gridview.setAdapter(new ButtonAdapter(this));




        /*ArrayList<ShopModel> courseModelArrayList = new ArrayList<ShopModel>();
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "0"));
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "1"));
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "2"));
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "3"));
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "4"));
        courseModelArrayList.add(new ShopModel(R.drawable.char_13, "5"));

        ShopItemAdapter adapter = new ShopItemAdapter(this, courseModelArrayList);
        shopItemGV.setAdapter(adapter);*/

        // @Override public View getView(final int position, View convertView, ViewGroup parent) { ImageView imageView = new ImageView(getContext()); imageView.setOnClickListener(new OnClickListener() { @Override onClick(View v) { if (getOnItemClickListener() != null) { getOnItemClickListener().onItemClick(MyGridView.this, v, position, getItemId(position)); } return imageView; }

        /*shopItemGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("SHOP", "Item button OnClick");
            }

        });*/

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SHOP", "Item button OnClick");

            }
        });*/

        /*GridView gridview = (GridView) findViewById(R.id.activity_grid);
        gridview.setAdapter(new ButtonAdapter(this));*/


        /*Button btn = new Button(mContext);
        int btn_id = 0;

        for (int i = 0; i > 3; i++) {
            btn_id++;
        }

        Button ItemBtn1 = (Button) findViewById(R.id.shopItemBtn1);
        Button ItemBtn2 = (Button) findViewById(R.id.shopItemBtn2);
        Button ItemBtn3 = (Button) findViewById(R.id.shopItemBtn3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SHOP", "Item button OnClick");
                btn.setText("Button " + (++btn_id));
            }
        });*/
    }

        //GridView gridview = (GridView) findViewById(R.id.activity_grid);
        //gridview.setAdapter(new ButtonAdapter(this));


        //CLOTHES BUTTON:







    // LOAD CHARACTER IMAGES THEN ACTIVITY STARTS:
    private void updateUI() {
        Log.d("SHOP", "updateUI()");

        sharedPrefHabbits = getSharedPreferences("shared preference", Activity.MODE_PRIVATE);

        // GET CLOTHES FROM SHARED PREFERENCE.XML:
        clothesImages = new int[] {R.drawable.char_13, R.drawable.char_2, R.drawable.char_15, R.drawable.char_10, R.drawable.char_14};
        ImageView imageViewCharacterClothes1 = (ImageView) findViewById(R.id.userClothesImage1);
        if (sharedPrefHabbits.contains("LastUserClothes")) {
            imageViewCharacterClothes1.setImageResource(clothesImages[sharedPrefHabbits.getInt("LastUserClothes", -1)]);
        }

        // GET HAIRS FROM SHARED PREFERENCE.XML:
        hairsImages = new int[] {R.drawable.char_5, R.drawable.char_4, R.drawable.char_8, R.drawable.char_11, R.drawable.char_12, R.drawable.char_9};
        ImageView imageViewCharacterHairs1 = (ImageView) findViewById(R.id.userHairsImage1);
        if (sharedPrefHabbits.contains("LastUserHairs")) {
            imageViewCharacterHairs1.setImageResource(hairsImages[sharedPrefHabbits.getInt("LastUserHairs", -1)]);
        }

        // GET SEX FROM SHARED PREFERENCE.XML:
        ImageView imageViewCharacter1 = (ImageView) findViewById(R.id.userCharacterImage1);
        int i = 0;
        if (sharedPrefHabbits.contains("LastUserSex")) {
            i = sharedPrefHabbits.getInt("LastUserSex", -1);
        }
        if (i == 1) {
            imageViewCharacter1.setImageResource(R.drawable.char_6);
        } else if (i == 0) {
            imageViewCharacter1.setImageResource(R.drawable.char_7);
        }
        Log.d("SHOP", "updateUI() end ");
    }

}
