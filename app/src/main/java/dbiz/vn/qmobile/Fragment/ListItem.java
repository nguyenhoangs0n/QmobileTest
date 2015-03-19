package dbiz.vn.qmobile.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import dbiz.vn.qmobile.Adapter.BannerAdapter;
import dbiz.vn.qmobile.Adapter.CustomAdapter;
import dbiz.vn.qmobile.Adapter.RecycleAdapter;
import dbiz.vn.qmobile.Helper.GetContent;
import dbiz.vn.qmobile.Helper.JsonParser;
import dbiz.vn.qmobile.Model.ItemInfor;
import dbiz.vn.qmobile.R;

/**
 * Created by nguyenhoang on 3/4/2015.
 */
public class ListItem extends Fragment {
    public static final int INDEX = 0;
    private CustomAdapter adapter;
    private BannerAdapter bannerAdapter;
    private RecycleAdapter recycleAdapter;
    public static ListView listView;
    public static GridView gridview;
    public static RecyclerView recList;
    public static int Listtype =0;

//    String[] imageUrls = Constants.IMAGES;

    DisplayImageOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        listView = (ListView) rootView.findViewById(R.id.list);

        ViewPager mViewPage = (ViewPager)rootView.findViewById(R.id.pager);
         listView = (ListView)rootView.findViewById(R.id.list);
        gridview = (GridView)rootView.findViewById(R.id.grid);
         recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        List<ItemInfor> mlistItemInfo = null;
        List<ItemInfor> mlistItemInfo2 = null;
        try {
            Log.d("sonnnn",""+getActivity().getAssets().open("itemInfoData"));
            mlistItemInfo = JsonParser.parseIteminfo(GetContent.getContent(getActivity().getAssets().open("itemInfoData")));
            mlistItemInfo2 = JsonParser.parseIteminfo(GetContent.getContent(getActivity().getAssets().open("BannerData")));
        } catch (Exception e) {
        Log.e("sonnn",""+e);
        }
        adapter = new CustomAdapter(getActivity(), mlistItemInfo);
        recycleAdapter =  new RecycleAdapter(getActivity(),mlistItemInfo);
        bannerAdapter  = new BannerAdapter(getActivity(), mlistItemInfo2);
        listView.setAdapter(adapter);
        gridview.setAdapter(adapter);
        mViewPage.setAdapter(bannerAdapter);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(recycleAdapter);

        if (Listtype == 0)
        {
            listView.setVisibility(View.VISIBLE);
            gridview.setVisibility(View.GONE);
            recList.setVisibility(View.GONE);
        }
        else if (Listtype==1)
        {
            gridview.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            recList.setVisibility(View.GONE);
        }
        else if (Listtype==2)
        {
                gridview.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                recList.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private String tag ="listitem";

    public void setCustomTag(String tag)
    {
        this.tag = tag;
    }

    public String getCustomTag()
    {
        return tag;
    }
}
