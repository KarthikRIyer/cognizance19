package com.mdg.iitr.cognizance19.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ViewPagerAdapter;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;
import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class EventFinfestAndLitfestFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private CircleImageView smallImageView;
    private PreferenceHelper preferenceHelper;
    private ImageView backIcon;

    @SuppressLint("UseSparseArrays")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_finfest_and_litfest, container, false);

        tabLayout = view.findViewById(R.id.finfest_litfest_tabs);
        viewPager = view.findViewById(R.id.finfest_litfest_view_pager);
        smallImageView = view.findViewById(R.id.small_profile_image);
        backIcon = view.findViewById(R.id.back_arrow);

        preferenceHelper = new PreferenceHelper(getActivity());

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);

        map.put(0, new EventFinfestFragment());
        map.put(1, new EventLitfestFragment());
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

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.home);
                navController.navigateUp();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    bottomNavigationView.setSelectedItemId(R.id.home);
                    navController.navigateUp();
                    return true;
                }
                return false;
            }
        });

        bottomNavigationView.setVisibility(View.VISIBLE);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);

        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_finfestAndLitfestFragment_to_myProfileFragment));

        return view;
    }

    private void setUpTabs() {
        tabLayout.getTabAt(0).setText("FinFest");
        tabLayout.getTabAt(1).setText("LitFest");

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
