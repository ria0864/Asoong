package org.androidtown.tauction1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    Button nav_login, nav_signup;
    MyViewPagerAdapter adapter;
    private String memId;

    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent.hasExtra("mem_id")) {
            memId = intent.getExtras().getString("mem_id");
        } else {
            memId = null;
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TextActivity.class)  );

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //login & signup button
        //View nav_header_view=navigationView.inflateHeaderView(R.layout.nav_header_main);
        View nav_header_view = navigationView.getHeaderView(0);
        nav_login = (Button)nav_header_view.findViewById(R.id.nav_login);
        nav_login.setOnClickListener(new OnClickListener() {//로그인 버튼 클릭
            @Override
            public void onClick(View v) {
                if(memId == null) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);//Navigation Drawer 닫기
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
        nav_signup = (Button)nav_header_view.findViewById(R.id.nav_signup);
        nav_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//회원가입 버튼 클릭
                if(memId == null) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);//Navigation Drawer 닫기
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                } else {
                    memId = null;
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);//Navigation Drawer 닫기
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    MainActivity.this.finish();
                }
            }
        });
        if(memId != null) {
            nav_login.setText("");
            nav_signup.setText("로그아웃");
            SharedPreferences setting = getSharedPreferences("setting", 0);
            SharedPreferences.Editor editor = setting.edit();
            editor.remove("mem_id");
            editor.commit();
        } else {
            nav_login.setText("로그인");
            nav_signup.setText("회원가입");
        }

        //tabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("문의사례"));
        tabLayout.addTab(tabLayout.newTab().setText("업체랭킹"));
        tabLayout.addTab(tabLayout.newTab().setText("톡션톡"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.rgb(0,0,0), Color.rgb(216,65,128));

        //viewPager
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        //Creating TabPagerAdapter adapter
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if(intent.hasExtra("from_filter")){
            viewPager.setCurrentItem(1);
        }

    //    Toast.makeText(this, "" + adapter.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() { //뒤로가기 한번 더 눌러야 종료되도록 함
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {//drawer 열린 상태
            drawer.closeDrawer(GravityCompat.START);
        } else {//drawer 닫힌 상태
            //super.onBackPressed();
            if(pressedTime == 0){
                Toast.makeText(this, "한번 더 누르면 종료됩니다", Toast.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else{
                int seconds = (int)(System.currentTimeMillis() - pressedTime);
                if(seconds > 2000){
                    pressedTime = 0;
                }
                else{
                    finish();
                }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //액션바 타이틀 제거

        return true;
    }

    /*
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
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myquestion) {
            Intent intent=new Intent(MainActivity.this,MyAskActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_mypick) {
            Intent intent=new Intent(MainActivity.this,DibsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_registration) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_favorites){
            Intent intent=new Intent(MainActivity.this,FavoritesActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setViewPage(int type, int num) {
        adapter.toPageNum(num);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(type);
    }

    public String getMemId() {
        return memId;
    }
}
