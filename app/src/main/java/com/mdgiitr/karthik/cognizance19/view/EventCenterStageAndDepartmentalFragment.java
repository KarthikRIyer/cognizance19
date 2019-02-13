package com.mdgiitr.karthik.cognizance19.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.ViewPagerAdapter;

import java.util.HashMap;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class EventCenterStageAndDepartmentalFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private ImageView backIcon;

    @SuppressLint("UseSparseArrays")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_center_stage_and_departmental, container, false);

        tabLayout = view.findViewById(R.id.center_stage_departmental_tabs);
        viewPager = view.findViewById(R.id.center_stage_departmental_view_pager);

        backIcon = view.findViewById(R.id.back_arrow);

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);

        map.put(0, new EventCenterStageFragment());
        map.put(1, new EventDepartmentalFragment());
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setUpTabs();

        switch (getArguments().getInt("event_frag_id")) {
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
        }

        backIcon.setOnClickListener(v -> navController.navigateUp());

        return view;
    }

    private void setUpTabs() {
        tabLayout.getTabAt(0).setText("Center Stage");
        tabLayout.getTabAt(1).setText("Departmental");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setAllCaps(false);
                    ((TextView) tabViewChild).setTextSize(15);
                }
            }
        }
    }
}
