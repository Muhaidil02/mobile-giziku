package com.android.giziku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.giziku.konten.Artikel_fragment;
import com.android.giziku.konten.Asupanibu_fragment;
import com.android.giziku.konten.Mpasi_fragment;
import com.android.giziku.konten.Video_fragment;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;



public class Konten_fragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout fragment_konten untuk fragmen ini
        View rootView = inflater.inflate(R.layout.fragment_konten, container, false);

        tabLayout = rootView.findViewById(R.id.tabLayout);
        viewPager = rootView.findViewById(R.id.viewPager);

         tabLayout.setupWithViewPager(viewPager);


         VPAdapter vpAdapter = new VPAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
         vpAdapter.addFragment(new Asupanibu_fragment(), "Asupan ibu Hamil");
         vpAdapter.addFragment(new Mpasi_fragment(), "MPASI");
         vpAdapter.addFragment(new Artikel_fragment(), "Artikel");
         vpAdapter.addFragment(new Video_fragment(), "Video");
         viewPager.setAdapter(vpAdapter);

         setupTabIcons(vpAdapter);
        setupTabSelectedListener();

        return rootView;
    }

    private void setupTabIcons(VPAdapter vpAdapter) {
        int[] tabIcons = {
                R.drawable.vector_ibuhamil,
                R.drawable.logo_bubur,
                R.drawable.logo_artikel,
                R.drawable.logo_video
        };

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View customTab = LayoutInflater.from(getActivity()).inflate(R.layout.costum_tab_layout, null);
                ImageView tabIcon = customTab.findViewById(R.id.tabIcon);
                TextView tabText = customTab.findViewById(R.id.tabText);

                tabIcon.setImageResource(tabIcons[i]);
                tabText.setText(vpAdapter.getPageTitle(i));

                tab.setCustomView(customTab);
            }
        }
    }

    private void setupTabSelectedListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tabText = customView.findViewById(R.id.tabText);
                    tabText.setTextColor(getResources().getColor(R.color.primary));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tabText = customView.findViewById(R.id.tabText);
                    tabText.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing for now
            }
        });
    }
}