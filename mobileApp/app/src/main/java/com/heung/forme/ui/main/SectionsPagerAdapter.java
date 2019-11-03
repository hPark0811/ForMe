package com.heung.forme.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.heung.forme.custom.APIManager;

import java.util.Map;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private String userID;

    public SectionsPagerAdapter(String userID, FragmentManager fm) {
        super(fm);
        try {
            Map<String, String> recMap = APIManager.getCustomerRecommendation(userID);
            PageViewModel.cardType = recMap.get("creditCard");
            PageViewModel.bankType = recMap.get("bankAccount");
            System.out.println("RESTCALL MADE!");
        } catch (Exception e) {
            PageViewModel.cardType = "cashback_infinite";
            PageViewModel.bankType = "chequing";
            System.out.println("RESTCALL ERROR!: " + e.getMessage());
        }
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}