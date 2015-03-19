package dbiz.vn.qmobile.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.LoginButton;

import java.util.Arrays;

import dbiz.vn.qmobile.R;

/**
 * Created by nguyenhoang on 3/17/2015.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java
    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    public Context mcontext;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        LoginButton authButton;
        public static int pos;
        LinearLayout llnav;
        TextView email;
        Context context;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            this.context = itemView.getContext();
            itemView.setOnClickListener(this);
            pos = ViewType;
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                authButton = (LoginButton) itemView.findViewById(R.id.authButton);
                Holderid = 1;
            } else {
                llnav = (LinearLayout) itemView.findViewById(R.id.llnav);
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);

                Holderid = 0;
            }
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"Click on"+getPosition(),Toast.LENGTH_SHORT).show();
            Log.d("DEM", "onClick " + getPosition());
        }


    }

    public NavigationAdapter(String Titles[], int Icons[], String Name, String Email, int Profile, Context context) {
        this.mcontext = context;
        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
    }


    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navi_item_row, parent, false); //Inflating the layout
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.navi_head, parent, false); //Inflating the layout
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, final int position) {
        if (holder.Holderid == 1) {
            if (position==6)
            {
                holder.authButton.setVisibility(View.VISIBLE);
                holder.authButton.setReadPermissions(Arrays.asList("public_profile"));
                holder.textView.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.GONE);
            }
            else {
                holder.textView.setText(mNavTitles[position - 1]);
                holder.imageView.setImageResource(mIcons[position - 1]);
            }
        } else {
            Log.d("Dem","poss: "+position);
                holder.profile.setImageResource(profile);
                holder.Name.setText(name);
                holder.email.setText(email);

        }

    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
