package com.example.lin.boylove.activity.Home;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lin.boylove.DXApplication;
import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.AboutUsActivity;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Login.LoginActivity;
import com.example.lin.boylove.activity.Settings.SettingActivity;
import com.example.lin.boylove.fragment.Chat.ChatRoomFragment;
import com.example.lin.boylove.fragment.LiveStream.LiveStreamFragment;
import com.example.lin.boylove.fragment.NewFeed.NewfeedFragment;
import com.example.lin.boylove.fragment.NotificationsFragment;
import com.example.lin.boylove.fragment.Online.OnlineFragment;
import com.example.lin.boylove.fragment.Profile.ProfileFragment;
import com.example.lin.boylove.fragment.SettingsFragment;
import com.example.lin.boylove.helper.BottomNavigationBehavior;

import butterknife.BindView;

public class HomeActivity extends DxBaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        HomeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavView;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_ONLINE = "online";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAB_NEWFEED = "newfeed";
    private static final String TAB_CHAT = "chat";
    private static final String TAB_ONLINE = "online";
    private static final String TAB_LIVESTREAM = "livestream";
    private static final String TAB_PROFILE = "profile";
    public static String CURRENT_TAG = TAG_HOME;
    public static String CURRENT_TAB = TAB_ONLINE;
    public static int navItemIndex = 0;
    public static int botNavItemIndex = 2;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private Menu menu;
    private Handler mHandler;
    private View navHeader;

    private HomePresenter presenter;

//    public void openHome(Activity activity){
//        Intent intent = new Intent(, HomeActivity.class);
//        startActivity(intent);
//    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAttributes() {
        mHandler = new Handler();
        presenter = new HomePresenterIml(this, context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(toolbar);

        //initialize bottom view
        setupBottomNavView();
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // Online fragment is displayed when user enter the app
        if (savedInstanceState == null) {
            botNavItemIndex = 2;
            CURRENT_TAB = TAB_ONLINE;
            //set title
            toolbar.setTitle(getString(R.string.title_online));
            bottomNavView.getMenu().getItem(2).setChecked(true);
            loadBotNavFragment();
        }

        // load nav menu header data
        loadNavHeader();
        // Navigation view header
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);

        // connect to websocket
        DXApplication.get(context).connectToWebsocket();
    }

    @Override
    protected void initViews() {
    }

    private void loadNavHeader() {
        // name, website
//        tvWebsite.setText("@lin.nguyen");
        // use picaso library to make a circular image
//        Picasso.with(this).load(R.drawable.dog)
//                .transform(new CropCircleTransformation())
//                .into(imvProfile);
        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /*
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadNavFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();
        // if user select the current navigation menu again, don't do anything, just close
        // the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getNavFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment, CURRENT_TAG);
                fragmentTransaction.addToBackStack(TAB_ONLINE);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

//         If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void loadBotNavFragment() {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getBotNavFragment();
                loadFragment(fragment);
            }
        };

//         If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getNavFragment() {
        switch (navItemIndex) {
            case 0:
                // app_menu fragment
//                financeFragment = new FinanceFragment();
//                return financeFragment;
            case 1:
                // photos
                OnlineFragment photosFragment = new OnlineFragment();
                return photosFragment;
            case 2:
                // movies fragment
                ChatRoomFragment moviesFragment = new ChatRoomFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return null;
        }
    }

    private Fragment getBotNavFragment() {
        switch (botNavItemIndex) {
            case 0:
                // newfeed fragment
                NewfeedFragment newfeedFragment = new NewfeedFragment();
                return newfeedFragment;
            case 1:
                // chatroom fragment
                ChatRoomFragment chatRoomFragment = new ChatRoomFragment();
                return chatRoomFragment;
            case 2:
                // online fragment
                OnlineFragment onlineFragment = new OnlineFragment();
                return onlineFragment;
            case 3:
                // livestream fragment
                LiveStreamFragment liveStreamFragment = new LiveStreamFragment();
                return liveStreamFragment;

            case 4:
                // profile fragment
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setupBottomNavView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_newfeed:
                        CURRENT_TAB = TAB_NEWFEED;
                        botNavItemIndex = 0;
                        break;
                    case R.id.navigation_chat:
                        CURRENT_TAB = TAB_CHAT;
                        botNavItemIndex = 1;
                        break;
                    case R.id.navigation_online:
                        CURRENT_TAB = TAB_ONLINE;
                        botNavItemIndex = 2;
                        break;
                    case R.id.navigation_livestream:
                        CURRENT_TAB = TAB_LIVESTREAM;
                        botNavItemIndex = 3;
                        break;
                    case R.id.navigation_profile:
                        CURRENT_TAB = TAB_PROFILE;
                        botNavItemIndex = 4;
                        break;
                    default:
                        return false;
                }
                // change the icon and the title
                loadIconFragment();

                // load the according fragment
                loadBotNavFragment();

                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_date_picker) {
            // This will be done later, call the method from Payment fragment, use interface or something
//            DialogFragment dialogFragment = new DatePickerFragment();
//            dialogFragment.show(getSupportFragmentManager(), "datePicker");
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // This method will trigger on item Click of navigation menu
        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_home:
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                break;
            case R.id.nav_online:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ONLINE;
                break;
            case R.id.nav_movies:
                navItemIndex = 2;
                CURRENT_TAG = TAG_MOVIES;
                break;
            case R.id.nav_notifications:
                navItemIndex = 3;
                CURRENT_TAG = TAG_NOTIFICATIONS;
                break;
            case R.id.nav_settings:
                navItemIndex = 4;
                CURRENT_TAG = TAG_SETTINGS;
                break;
            case R.id.nav_about_us:
                // launch new intent instead of loading fragment
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                drawer.closeDrawers();
                return true;
            case R.id.nav_privacy_policy:
                // launch new intent instead of loading fragment
//                        startActivity(new Intent(HomeActivity.this, PrivacyPolicyActivity.class));
//                        drawer.closeDrawers();
                return true;
            default:
                navItemIndex = 0;
        }

        //Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);

        loadNavFragment();

        return true;
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container_wrapper, fragment, CURRENT_TAB);
        transaction.addToBackStack(TAB_ONLINE);
        transaction.commit();
    }

    private void loadIconFragment() {
        if (menu == null) {
            return;
        }
        MenuItem setting = menu.findItem(R.id.action_settings);
        MenuItem calendar = menu.findItem(R.id.action_calendar);
        switch (botNavItemIndex) {
            case 0:
                toolbar.setTitle(getString(R.string.title_newfeed));
                setting.setVisible(false);
                calendar.setVisible(true);
                break;
            case 1:
                toolbar.setTitle(getString(R.string.title_chat));
                setting.setVisible(false);
                calendar.setVisible(false);
                break;
            case 2:
                toolbar.setTitle(getString(R.string.title_online));
                setting.setVisible(false);
                calendar.setVisible(false);
                break;
            case 3:
                toolbar.setTitle(getString(R.string.title_livestream));
                setting.setVisible(false);
                calendar.setVisible(false);
                break;
            case 4:
                toolbar.setTitle(getString(R.string.title_profile));
                setting.setVisible(true);
                calendar.setVisible(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideProgress() {

    }
}


