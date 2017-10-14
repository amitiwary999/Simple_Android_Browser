package com.example.meeera.browserapp.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meeera.browserapp.Fragment.Home;
import com.example.meeera.browserapp.Fragment.News;
import com.example.meeera.browserapp.Fragment.Repo;
import com.example.meeera.browserapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.tabLayout)
    TabLayout tabLayout;
    //@BindView(R.id.viewpager)
      ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupviewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabIcons();
    }

    public void setUpTabIcons() {
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(viewPagerAdapter.getTabView(i));
            }
            tabLayout.getTabAt(1).getCustomView().setSelected(true);
        }
    }

    public void setupviewpager(ViewPager viewpager){
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new Home(),"Home");
        viewPagerAdapter.addFrag(new News(),"News");
        viewPagerAdapter.addFrag(new Repo(),"Repo");
        viewpager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentListtitle = new ArrayList<>();
        private final String[] mTabsTitle={"Home","News","Repo"};
        private final int[] mTabsIcons={R.drawable.ic_home_white_24dp,R.drawable.ic_news_white_24dp,R.drawable.ic_repo_white_24dp};
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public View getTabView(int position) {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab_text, null);
            TextView title = (TextView) view.findViewById(R.id.titletab);
            title.setText(mTabsTitle[position]);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        public void addFrag(Fragment fragment,String title) {
            mFragmentList.add(fragment);
            mFragmentListtitle.add(title);
            //  title.setText(ttle);
        }
    }
}
