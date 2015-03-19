package dbiz.vn.qmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import dbiz.vn.qmobile.Helper.GetContent;
import dbiz.vn.qmobile.Helper.JsonParser;
import dbiz.vn.qmobile.R;
import dbiz.vn.qmobile.Model.ItemInfor;

/**
 * Created by nguyenhoang on 3/5/2015.
 */
public class lvSpAdapter extends BaseAdapter {
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private Context activity;
    private LayoutInflater inflater;
    private List<ItemInfor> mlistItemInfo;
    DisplayImageOptions options;

    public lvSpAdapter(Context activity, List<ItemInfor> mlistItemInfo) {
        this.activity = activity;
        this.mlistItemInfo = mlistItemInfo;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder {
        TextView tvname;
        TextView tvprice;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.itemdetails, parent, false);
            holder = new ViewHolder();
            holder.tvname = (TextView) view.findViewById(R.id.tvname);
            holder.tvprice = (TextView) view.findViewById(R.id.tvprice);
            holder.image = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        List<ItemInfor> mlistItemInfo = null;
        try {
            mlistItemInfo = JsonParser.parseIteminfo(GetContent.getContent(ItemInfor.class.getResourceAsStream("itemInfoData")));
            for (int i = 0; i < mlistItemInfo.size(); i++) {
                holder.tvname.setText(mlistItemInfo.get(i).getName());
                holder.tvprice.setText(mlistItemInfo.get(i).getPrice());
                ImageLoader.getInstance().displayImage(mlistItemInfo.get(i).getImg_url(), holder.image, options, animateFirstListener);
            }
        } catch (Exception e) {

        }

        return view;
    }
}

