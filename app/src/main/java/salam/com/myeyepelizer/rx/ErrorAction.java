package salam.com.myeyepelizer.rx;

import android.content.Context;
import android.support.v4.util.Consumer;

import es.dmoral.toasty.Toasty;

public class ErrorAction {
    private ErrorAction(){throw new AssertionError("No instance");}
    public  static Consumer<Throwable> error(Context context){
        return throwable -> {
            throwable.printStackTrace();
            Toasty.error(context,throwable.getMessage()).show();
        };
    }

}
