package dbiz.vn.qmobile;

import android.content.Context;
import android.support.v7.widget.Toolbar;

/**
 * Created by nguyenhoang on 3/16/2015.
 */
public class ToolBar extends Toolbar{
    public static Toolbar toolbar;
    public ToolBar(Context context) {
        super(context);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
    }
}
