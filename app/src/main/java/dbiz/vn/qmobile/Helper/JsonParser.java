package dbiz.vn.qmobile.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dbiz.vn.qmobile.Model.ItemInfor;

/**
 * Created by nguyenhoang on 3/4/2015.
 */
public class JsonParser {
    public static List<ItemInfor> parseIteminfo(String json)
    {
        ItemInfor mItemInfor = new ItemInfor();
        List<ItemInfor> mListItemInfo = new ArrayList<ItemInfor>();
        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("item");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject youValue = jsonArray.getJSONObject(i);
                ItemInfor mIteminfo = new ItemInfor();
                mIteminfo.setName(youValue.getString("name"));
                mIteminfo.setPrice(youValue.getString("price"));
                mIteminfo.setImg_url(youValue.getString("url_img"));
                mListItemInfo.add(mIteminfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mListItemInfo;
    }


}
