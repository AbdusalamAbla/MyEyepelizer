package salam.com.myeyepelizer.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toolbar;

import salam.com.myeyepelizer.R;

public class InsetsDrawerLayout extends DrawerLayout implements View.OnApplyWindowInsetsListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    public InsetsDrawerLayout(Context context){this(context,null);}
    public InsetsDrawerLayout(Context context, AttributeSet attrs){this(context,attrs,0);}
    public InsetsDrawerLayout(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        setOnApplyWindowInsetsListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.list);
    }

    @Override
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int l=windowInsets.getSystemWindowInsetLeft();
        int r=windowInsets.getStableInsetRight();
        int t=windowInsets.getStableInsetTop();
        toolbar.setPadding(l,toolbar.getPaddingTop()+t,0,0);
        final boolean ltr=recyclerView.getLayoutDirection()==View.LAYOUT_DIRECTION_LTR;
        setPadding(getPaddingLeft(),getPaddingTop(),getPaddingEnd()+(ltr?r:0),getPaddingBottom());
        setOnApplyWindowInsetsListener(null);

        return windowInsets.consumeSystemWindowInsets();
    }
}
