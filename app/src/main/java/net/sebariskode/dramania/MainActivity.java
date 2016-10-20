package net.sebariskode.dramania;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    View navDrawerHeader;
    ImageView imgNavDrawerHeader;

    // Navigation Drawer menu index
    public static int NAV_MENU_INDEX = 0;

    // Fragment TAGS
    private static final String TAG_AIRING_TODAY = "airing_today";
    private static final String TAG_TOP_RATED = "top_rated";
    private static final String TAG_POPULAR = "popular";
    private static final String TAG_MY_COLLECTIONS = "my_collections";
    private static final String TAG_SETTINGS = "settings";
    private static String TAG_CURRENT = TAG_AIRING_TODAY;

    // Toolbar titles
    @BindArray(R.array.nav_drawer_fragment_titles)
    String[] fragmentTitles;

    private boolean loadAiringTodayOnBackPress = true;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        handler = new Handler();

        navDrawerHeader = navigationView.getHeaderView(0);
        imgNavDrawerHeader = (ImageView) navDrawerHeader.findViewById(R.id.nav_drawer_image_bg);
        loadNavDrawerHeader();
        setupNavigationView();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_airing_today:
                        NAV_MENU_INDEX = 0;
                        TAG_CURRENT = TAG_AIRING_TODAY;
                        break;
                    case R.id.nav_top_rated:
                        NAV_MENU_INDEX = 1;
                        TAG_CURRENT = TAG_TOP_RATED;
                        break;
                    case R.id.nav_popular:
                        NAV_MENU_INDEX = 2;
                        TAG_CURRENT = TAG_POPULAR;
                        break;
                    case R.id.nav_collection:
                        NAV_MENU_INDEX = 3;
                        TAG_CURRENT = TAG_MY_COLLECTIONS;
                        break;
                    case R.id.nav_settings:
                        NAV_MENU_INDEX = 4;
                        TAG_CURRENT = TAG_SETTINGS;
                        break;
                    case R.id.nav_share:
                        // TODO: Implement share intent
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_report:
                        // TODO: Impelment share intent
                        drawerLayout.closeDrawers();
                        return true;
                    default:
                        NAV_MENU_INDEX = 0;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void loadFragment() {
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(TAG_CURRENT) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        // Cross Fading effect
        Runnable pendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, TAG_CURRENT);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (pendingRunnable != null) {
            handler.post(pendingRunnable);
        }

        drawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getFragment() {
        switch (NAV_MENU_INDEX) {
            case 0:
                AiringTodayFragment airingTodayFragment = new AiringTodayFragment();
                return airingTodayFragment;
            case 1:
                TopRatedFragment topRatedFragment = new TopRatedFragment();
                return topRatedFragment;
            case 2:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;
            case 3:
                MyCollectionsFragment myCollectionsFragment = new MyCollectionsFragment();
                return myCollectionsFragment;
            default:
                return new AiringTodayFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(fragmentTitles[NAV_MENU_INDEX]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(NAV_MENU_INDEX).setChecked(true);
    }

    private void loadNavDrawerHeader() {
        Picasso.with(this).load(R.drawable.red_pentagonal_background)
                .into(imgNavDrawerHeader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
