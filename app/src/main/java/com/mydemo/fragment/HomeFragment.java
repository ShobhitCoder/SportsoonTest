package com.mydemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import static com.mydemo.R.id.circle_indicator;
import static com.mydemo.R.id.viewpager_tab;

/**
 * Created by SS091 on 6/12/2017.
 */

public class HomeFragment extends Fragment {
    private ViewPager viewPagerHeader, viewPagerTab;
    private String mStrings[] = new String[3];
    private View rootView;
    private CircleIndicator mCircleIndicator;
    private Activity mActivity;
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<Fragment> myHeaderFragmentList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mActivity = getActivity();
        initializeView();

        mStrings[0] = getString(R.string.videos);
        mStrings[1] = getString(R.string.images);
        mStrings[2] = getString(R.string.milestone);

        mFragmentList.add(new VideosFragment());
        mFragmentList.add(new VideosFragment());
        mFragmentList.add(new VideosFragment());

        for (int i = 0; i <= 4; i++) {
            myHeaderFragmentList.add(new HeaderViewPagerFragment());
        }
        setViewPagerAdapter();
        return rootView;
    }

    private void initializeView() {
        viewPagerHeader = (ViewPager) rootView.findViewById(R.id.viewpager_header);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        viewPagerTab = (ViewPager) rootView.findViewById(viewpager_tab);
        mCircleIndicator = (CircleIndicator) rootView.findViewById(circle_indicator);
    }

    void setViewPagerAdapter() {
        MyHeaderPagerAdapter myHeaderPagerAdapter = new MyHeaderPagerAdapter(getChildFragmentManager());
        viewPagerHeader.setAdapter(myHeaderPagerAdapter);
        mCircleIndicator.setViewPager(viewPagerHeader);
        myHeaderPagerAdapter.registerDataSetObserver(mCircleIndicator.getDataSetObserver());
        viewPagerHeader.setOffscreenPageLimit(3);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewPagerTab.setAdapter(myPagerAdapter);
        viewPagerTab.setOffscreenPageLimit(3);
        viewPagerTab.setCurrentItem(0);

        //Adding the tabs using addTab() method
        mTabLayout.addTab(mTabLayout.newTab().setText(mStrings[0]).setIcon(R.drawable.video_selector));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStrings[1]).setIcon(R.drawable.image_selector));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStrings[2]).setIcon(R.drawable.milestone_selector));
        mTabLayout.getTabAt(0).select();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPagerTab.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return MyPagerAdapter.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStrings[position];
        }
    }

    public class MyHeaderPagerAdapter extends FragmentPagerAdapter {

        public MyHeaderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return myHeaderFragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return MyPagerAdapter.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return myHeaderFragmentList.get(position);
        }
    }
}