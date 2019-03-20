package com.trend.ai.view.ui.actitivy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.trend.ai.R;
import com.trend.ai.view.ui.actitivy.login.LoginActivity;
import com.trend.ai.view.ui.fragment.CalendarFragment;
import com.trend.ai.view.ui.fragment.HomeFragment;
import com.trend.ai.view.ui.fragment.ProfileFragment;
import com.trend.ai.view.ui.fragment.SettingsFragment;
import com.trend.ai.view.ui.fragment.main.MainFragment;

public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemHome1;
    private ResideMenuItem itemHome2;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);


        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_dot,     "Topic/Hashtags");
        itemHome1    = new ResideMenuItem(this, R.drawable.icon_dot,     "Contents");
        itemHome2     = new ResideMenuItem(this, R.drawable.icon_dot,     "Media");
        itemProfile  = new ResideMenuItem(this, R.drawable.icon_dot,  "Influencers");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_dot, "Calendar");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_dot, "Settings");

        itemHome.setOnClickListener(this);
        itemHome1.setOnClickListener(this);
        itemHome2.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHome2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.TEXT_DIRECTION_RTL);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new MainFragment());
        }else if (view == itemProfile){
            changeFragment(new MainFragment());
        }else if (view == itemCalendar){

            changeFragment(profileFragment);
        }else if (view == itemSettings){
            changeFragment(new MainFragment());
        }  else if (view == itemHome1){
            changeFragment(new MainFragment());
        } else if (view == itemHome2){
//            LoginActivity.Companion.startActivity(this,view);
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){

        name = ProfileFragment.class.getName();

        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    String name;
    public String getName(){
        return name;
    }
}
