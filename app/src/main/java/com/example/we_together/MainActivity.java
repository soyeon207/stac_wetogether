package com.example.we_together;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{

    TextView header_faimly_txt,header_name_txt,header_invitecode_txt;
    FirebaseAuth firebaseAuth;

    Toolbar toolbar;

    //FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();

    //4개의 메뉴에 들어갈 Fragment들
    private Menu_home menu_home = new Menu_home();
    private Menu_share menu_share = new Menu_share();
    private Menu_community menu_community = new Menu_community();
    private Menu_calendar menu_calendar = new Menu_calendar();

    private Button button;
    private ImageView imageView;

    //Dialog
    private inviteDialog inviteDialog;
    private add_dialog add_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////activity_bottom_nav//////////////////////////////
        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        BottomNavigationHelper.disableShiftMode(bottom_nav);

        //첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,menu_home).commitAllowingStateLoss();

        //bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //////////////activity_bottom_nav//////////////////////////////

        firebaseAuth = firebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.add_image);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //nav_header_main setText 바꾸기
        View nav_header_view = navigationView.getHeaderView(0);

        header_faimly_txt = ((View) nav_header_view).findViewById(R.id.header_family);
        header_invitecode_txt = ((View) nav_header_view).findViewById(R.id.header_invitecode);
        header_name_txt = ((View) nav_header_view).findViewById(R.id.header_name);

        SharedPreferences preferences = getSharedPreferences("SAVE",MODE_PRIVATE);
        header_invitecode_txt.setText("초대코드 : "+preferences.getString("invitecode",""));
        header_faimly_txt.setText(preferences.getString("room",""));
        header_name_txt.setText(preferences.getString("name",""));

        image_invisible();

        imageView.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.drawer_calendar) {
            // Handle the camera action
        } else if (id == R.id.drawer_invitecode) {
            inviteDialog = new inviteDialog(this,exit_listner);
            inviteDialog.show();
        } else if (id == R.id.drawer_community) {

        } else if (id == R.id.drawer_setting) {

        } else if (id == R.id.drawer_logout) {
            SharedPreferences pref2 = getSharedPreferences("SAVE", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = pref2.edit();
            editor2.clear();
            editor2.commit(); // SharedPreferences 의 데이터 모두 삭제

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void image_invisible(){
        imageView.setVisibility(View.INVISIBLE);
    }

    //bottomnavigation 클릭시
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    image_invisible();
                    transaction.replace(R.id.frame_layout,menu_home).commitAllowingStateLoss();
                    break;
                case R.id.bottom_share:
                    imageView.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.frame_layout,menu_share).commitAllowingStateLoss();
                    break;
                case R.id.bottom_cal:
                    image_invisible();
                    transaction.replace(R.id.frame_layout,menu_calendar).commitAllowingStateLoss();
                    break;

                case R.id.bottom_com:
                    image_invisible();
                    transaction.replace(R.id.frame_layout,menu_community).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    };

    private View.OnClickListener exit_listner = new View.OnClickListener(){
        public void onClick(View v){
            inviteDialog.dismiss();
        }
    };

    private View.OnClickListener exit_listner2 = new View.OnClickListener(){
        public void onClick(View v){
            add_dialog.dismiss();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_image:
                Log.e("a","a");
                add_dialog = new add_dialog(this,exit_listner2);
                add_dialog.show();
                break;
        }
    }
}
