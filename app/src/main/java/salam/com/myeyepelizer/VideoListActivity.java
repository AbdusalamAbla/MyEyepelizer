package salam.com.myeyepelizer;

import android.app.Activity;
import android.os.Bundle;

import salam.com.myeyepelizer.Base.ToolBarActivity;

public class VideoListActivity extends ToolBarActivity {

    @Override
    public int providerLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list_activity);
    }
}
