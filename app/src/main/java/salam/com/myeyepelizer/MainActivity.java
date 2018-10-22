package salam.com.myeyepelizer;

import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;


import es.dmoral.toasty.Toasty;
import io.reactivex.android.MainThreadDisposable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import salam.com.myeyepelizer.Base.ToolBarActivity;
import salam.com.myeyepelizer.api.DailyApi;

public class MainActivity extends ToolBarActivity {
    public static final String PROVIDER_ITEM="item";
    public static final String CATEGORY_ID="categoryID";
    public static final String TITLE="title";

    private MultiTypeAdapter adapter;
    private RecyclerView list;
    private SwipeRefreshLayout refreshLayout;
    private DrawerLayout drawerLayout;
    private DailyApi dailyApi;
    private Items items=new Items();
    private String dateTime="";

    @Override
    public int providerLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        list=(RecyclerView)findViewById(R.id.list);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Window window=getWindow();
                window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,android.R.color.transparent));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));
            }
        });
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);


    }




}
