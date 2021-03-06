package salam.com.myeyepelizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import salam.com.myeyepelizer.Base.ToolBarActivity;
import salam.com.myeyepelizer.api.SearchApi;
import salam.com.myeyepelizer.rx.ErrorAction;

public class SearchActivity extends ToolBarActivity implements View.OnClickListener {


    public static  final String KEYWORD="keyword";
    private TagFlowLayout tagLayout;
    private EditText searchEdit;
    private List<String> tags=new ArrayList<>();

    @Override
    public int providerLayoutId() {
        return R.layout.search_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagLayout=(TagFlowLayout)findViewById(R.id.tag_layout);
        searchEdit=(EditText) findViewById(R.id.search_edit);
        ImageButton clearButton=(ImageButton)findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(this);
        loadTrendingTag();
    }
     private void loadTrendingTag(){
         SearchApi trendingApi=RetrofitFactory.getRetrofit()
                 .createApi(SearchApi.class);

         trendingApi.getTrendingTag()
                 .compose(bindToLifecycle())
                 .filter(data->data!=null)
                 .doOnNext(data->tags.addAll(data))
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(data->{
                     bindData();
                 }, (Consumer<? super Throwable>) ErrorAction.error(this));
     }

     private void bindData(){
        tagLayout.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position,String tagName) {
                TextView tag=(TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.item_tag,parent,false);
                tag.setText(tagName);
                return tag;
            }
        });
        tagLayout.setOnTagClickListener(((view, position, parent) -> {
            startResultActivity(tags.get(position));
            return true;
        }));
     }


     private void keyListener(){
        searchEdit.setOnKeyListener((v,keyCode,event)->{
            if (event.getAction()!=KeyEvent.ACTION_DOWN)return true;

            if (keyCode==KeyEvent.KEYCODE_ENTER){
                if (searchEdit.getText().toString().isEmpty()){
                    Toasty.warning(SearchActivity.this,"Keyword must not empty").show();
                }else {
                    search(searchEdit.getText().toString());
                }
            }else if (keyCode==KeyEvent.KEYCODE_BACK){
                finish();
            }
            return false;
        });
     }
     private void search(String key){
        startResultActivity(key);
     }
     private void startResultActivity(String keyword){
        Intent intent=new Intent(this,ResultActivity.class);
        intent.putExtra(KEYWORD,keyword);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
     }
    @Override
    public void onClick(View view) {
        searchEdit.setText(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
