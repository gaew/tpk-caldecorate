package com.first.tripakey.caldecorate.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.first.tripakey.caldecorate.R;


import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {
////testgit
    //test merge with test



    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"ตกแต่งภายใน","ค่าใช้จ่าย","แปลงหน่วย","ค้นหาร้าน"};
    int Numboftabs =4;
    boolean check_firsttime = false ;
    public static final String PREFS_NAME = "MyPrefsFile";
    ViewPager pager;
    SQLiteDatabase mDb;


    private Tracker mTracker;
    String name = "main of Caldecorate";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.titleMain);
/*
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());

       /* AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
*/
        // startActivity(new Intent(MainActivity.this, firstTime.class));
        MainActivity.MyDbHelper db = new MainActivity.MyDbHelper(this);
        mDb = db.getWritableDatabase();
        Cursor mCursor = mDb.rawQuery("SELECT "+ MainActivity.MyDbHelper.CODE + " FROM "+  MainActivity.MyDbHelper.TABLE_NAME, null);

        Log.i("Data Count", String.valueOf(mCursor.getCount()));

        //test dialog

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and settingPleatedCurtain the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);





    }

    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences save_first_time = getPreferences(0);
        SharedPreferences.Editor editor = save_first_time.edit();
        editor.putBoolean("first_time", true);

        // Commit the edits!
        editor.commit();
    }


    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


  public static   class MyDbHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "caldecorate";
        private static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "fab";
        public static final String COMPANY = "company";
        public static final String TYPE = "type";
        public static final String CODE = "code";
      public static final String WIDTH = "width";
      public static final String PRICE = "price";
      public static final String IMAGE = "image";

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COMPANY + " TEXT, " + TYPE + " TEXT, "
                    + CODE + " TEXT, " + WIDTH + " TEXT, " + PRICE + " TEXT, " + IMAGE + " TEXT); ");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('Extra', 'ม่านตาไก่', 'EP001','64','445'," +
                    "'http://st.hzcdn.com/simgs/6aa1507c059574d4_9-9593/mediterranean-upholstery-fabric.jpg');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('U save', 'ม่านตาไก่', 'EP002','46','419'," +
                    "'http://www.arcadia-textiles.co.uk/userfiles/lg_images/Wild_Fern_Mineral_Curtain_Fabric-xAyno1.jpg');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('NEO extra', 'ม่านตาไก่', 'EP003','24','805'," +
                    "'https://d2d00szk9na1qq.cloudfront.net/Product/4211e281-1f6a-41b5-98f2-77004e929e35/Images/Large_0420649.jpg');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('NEO eyelet', 'ม่านตาไก่', 'EP004','93','630'," +
                    "'https://d2d00szk9na1qq.cloudfront.net/Product/c9eb56d6-da24-440c-892d-774cf7454cf3/Images/Large_UK-138.jpg');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('NEO eyelet', 'ม่านตาไก่', 'EP005','37','452'," +
                    "'https://d2d00szk9na1qq.cloudfront.net/Product/af22dcef-73f2-4655-b134-00b9f278eac1/Images/Large_0408385.jpg');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('NEO eyelet', 'ม่านตาไก่', 'EP006','91','348'," +
                    "'http://mayang.com/textures/Fabric/images/Patterned%20Fabric/rug_closeup_9280130.JPG');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('Extra', 'ม่านตาไก่', 'EP007','95','842'," +
                    "'http://mayang.com/textures/Fabric/images/Patterned%20Fabric/carpet_texture_071281.JPG');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COMPANY + ", " + TYPE + "," + CODE + "," + WIDTH + "," + PRICE + "," + IMAGE +")" +
                    " VALUES ('Extra', 'ม่านตาไก่', 'EP008','38','864'," +
                    "'http://mayang.com/textures/Fabric/images/Patterned%20Fabric/silk_071322.JPG');");

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }




}