package com.example.lin.boylove.activity.Home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lin.boylove.DXApplication;
import com.example.lin.boylove.R;
import com.example.lin.boylove.activity.AboutUsActivity;
import com.example.lin.boylove.activity.DxBaseActivity;
import com.example.lin.boylove.activity.Settings.SettingActivity;
import com.example.lin.boylove.fragment.Chat.ChatRoomFragment;
import com.example.lin.boylove.fragment.FinanceFragment;
import com.example.lin.boylove.fragment.LiveStream.LiveStreamFragment;
import com.example.lin.boylove.fragment.NewFeed.NewfeedFragment;
import com.example.lin.boylove.fragment.NotificationsFragment;
import com.example.lin.boylove.fragment.Online.OnlineFragment;
import com.example.lin.boylove.fragment.Profile.ProfileFragment;
import com.example.lin.boylove.fragment.SettingsFragment;
import com.example.lin.boylove.helper.BottomNavigationBehavior;
import com.example.lin.boylove.utilities.Constant;
import com.example.lin.boylove.utilities.NotificationUtils;
import com.example.lin.boylove.utilities.Utils;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;

public class HomeActivity extends DxBaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        HomeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavView;

    private View navHeader;

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private FinanceFragment financeFragment;

    private HomePresenter presenter;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_ONLINE = "online";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    public static int navItemIndex = 0;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, AddPaymentActivity.class);
//                startActivity(intent);
//            }
//        });


        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new FinanceFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constant.Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Constant.Config.TOPIC_GLOBAL);
                } else if (intent.getAction().equals(Constant.Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                    Utils.showToast(getApplicationContext(), message);
                }

            }
        };

        // connect to websocket
        DXApplication.get(context).connectToWebsocket();
    }

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
    protected void initViews() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(toolbar);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        bottomNavView.setOnNavigationItemSelectedListener(this);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
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
    private void loadHomeFragment() {
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
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_container_wrapper, fragment, CURRENT_TAG);
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

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // app_menu fragment
                financeFragment = new FinanceFragment();
                return financeFragment;
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
                return new FinanceFragment();
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
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
                        Toast.makeText(getApplicationContext(), "Movies", Toast.LENGTH_LONG).show();
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
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
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

//    // show or hide the fab
//    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
//    }

//    @OnClick(R.id.fab)
//    public void navigateToAddPayment() {
//        Intent intent = new Intent(this, AddPaymentActivity.class);
//        startActivity(intent);
//    }

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
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Constant.Config.REGISTRATION_COMPLETE));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Constant.Config.PUSH_NOTIFICATION));

        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_newfeed:
                toolbar.setTitle(getString(R.string.title_newfeed));
                loadIconFragment(item.getItemId());
                fragment = new NewfeedFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_chat:
                toolbar.setTitle(getString(R.string.title_chat));
                fragment = new ChatRoomFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_online:
                toolbar.setTitle(getString(R.string.title_online));
                fragment = new OnlineFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_livestream:
                toolbar.setTitle(getString(R.string.title_livestream));
                fragment = new LiveStreamFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_profile:
                loadIconFragment(item.getItemId());
                toolbar.setTitle(getString(R.string.title_profile));
                fragment = new ProfileFragment();
//                loadIconFragment(item.getItemId());
                loadFragment(fragment);
                return true;
        }
        return false;
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container_wrapper, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadIconFragment(int itemId) {
        if (menu == null) {
            return;
        }
        MenuItem setting = menu.findItem(R.id.action_settings);
        MenuItem calendar = menu.findItem(R.id.action_calendar);
        switch (itemId) {
            case R.id.navigation_profile:
                setting.setVisible(true);
                calendar.setVisible(false);
                break;
            case R.id.navigation_newfeed:
                setting.setVisible(false);
                calendar.setVisible(true);
                break;
            case R.id.navigation_chat:
                setting.setVisible(false);
                calendar.setVisible(false);
                break;
            case R.id.navigation_livestream:
                setting.setVisible(false);
                calendar.setVisible(false);
                break;
            case R.id.navigation_online:
                setting.setVisible(false);
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


