package com.heung.forme.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private String userID;

    public SectionsPagerAdapter(String userID, FragmentManager fm) {
        super(fm);
        this.userID = userID;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, this.userID);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}