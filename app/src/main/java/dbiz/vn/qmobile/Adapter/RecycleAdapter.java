package dbiz.vn.qmobile.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import dbiz.vn.qmobile.Model.ItemInfor;
import dbiz.vn.qmobile.R;

/**
 * Created by nguyenhoang on 3/17/2015.
 */
public class RecycleAdapter extends  RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    DisplayImageOptions options;
    Context context;
    List<ItemInfor> rowItem;

    public RecycleAdapter(Context context, List<ItemInfor> rowItem) {
        this.context = context;
        this.rowItem = rowItem;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_details, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        ItemInfor row_pos = rowItem.get(i);
        // setting the image resource and title
//        imgIcon.setImageResource(row_pos.getIcon());
        ViewHolder.vName.setText(row_pos.getName());
        ViewHolder.vPrice.setText(row_pos.getPrice());
        ImageLoader.getInstance().displayImage(row_pos.getImg_url(), ViewHolder.vImage, options, animateFirstListener);
    }

    @Override
    public int getItemCount() {
        return rowItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected static TextView vName;
        protected static TextView vPrice;
        protected static ImageView vImage;

        public ViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.tvname);
            vPrice = (TextView)  v.findViewById(R.id.tvprice);
            vImage = (ImageView)  v.findViewById(R.id.imageView);
        }
    }
}
