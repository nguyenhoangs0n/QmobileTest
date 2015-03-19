package dbiz.vn.qmobile;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.facebook.AppEventsLogger;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dbiz.vn.qmobile.Adapter.NavigationAdapter;
import dbiz.vn.qmobile.Fragment.ListItem;
import dbiz.vn.qmobile.Model.Constance;
import dbiz.vn.qmobile.Model.LocalData;


public class MainActivity extends ActionBarActivity {
    GoogleCloudMessaging gcm;
    private String Tag = "MainActivity";
    private String regid;
    public static FrameLayout fmlayout;
    String TITLES[] = {"Home", "Events", "New", "Shop", "Travel",""};
    int ICONS[] = {R.drawable.ic_home, R.drawable.ic_events, R.drawable.ic_mail, R.drawable.ic_shop, R.drawable.ic_travel};


    String NAME = "Username";
    String EMAIL = "UserPassword";
    int PROFILE = R.drawable.aka;
    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NavigationAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        fmlayout = (FrameLayout) findViewById(R.id.details);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.details, new ListItem(), Constance.MainFragment)
                    .commit();
        }
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "dbiz.vn.qmobile",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.reg_id) {
            getRegId();
        }
        if (id == R.id.grid_list) {
//        Toast.makeText(MainActivity.this,""+getSupportFragmentManager().findFragmentById(R.id.details).getTag().toString()  ,Toast.LENGTH_LONG).show();
            if (getSupportFragmentManager().findFragmentById(R.id.details).getTag().toString().equals(Constance.MainFragment)) {
                if (ListItem.Listtype == 0) {
//                    Toast.makeText(MainActivity.this,""+ListItem.Listtype  ,Toast.LENGTH_SHORT).show();
                    ListItem.Listtype = 1;
                    ListItem.listView.setVisibility(View.VISIBLE);
                    ListItem.gridview.setVisibility(View.GONE);
                    ListItem.recList.setVisibility(View.GONE);
                    item.setIcon(R.drawable.abc_btn_check_to_on_mtrl_000);
                } else if (ListItem.Listtype == 1) {
//                    Toast.makeText(MainActivity.this,""+ListItem.Listtype  ,Toast.LENGTH_SHORT).show();
                    ListItem.Listtype = 2;
                    ListItem.gridview.setVisibility(View.VISIBLE);
                    ListItem.listView.setVisibility(View.GONE);
                    ListItem.recList.setVisibility(View.GONE);
                    item.setIcon(R.drawable.abc_btn_check_to_on_mtrl_015);
                } else if (ListItem.Listtype == 2) {
//                    Toast.makeText(MainActivity.this,""+ListItem.Listtype  ,Toast.LENGTH_SHORT).show();
                    ListItem.Listtype = 0;
                    ListItem.gridview.setVisibility(View.GONE);
                    ListItem.listView.setVisibility(View.GONE);
                    ListItem.recList.setVisibility(View.VISIBLE);
                    item.setIcon(R.drawable.abc_btn_check_material);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void getRegId() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register(LocalData.PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
//                etRegId.setText(msg + "\n");
                Toast.makeText(MainActivity.this, msg + "/n", Toast.LENGTH_LONG);
            }

        }.execute(null, null, null);
    }

    public void showGridList() {

    }


}
