package dbiz.vn.qmobile.Helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by nguyenhoang on 3/4/2015.
 */
public class GetContent {

    public static String getContent(InputStream inputStream) throws IOException {
        String line, content = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = br.readLine()) != null) {
                content += line;
            }
            br.close();
        }
        catch(Exception e)
        {
            Log.e("sonnnn",""+e);
        }
        return content;
    }
}
