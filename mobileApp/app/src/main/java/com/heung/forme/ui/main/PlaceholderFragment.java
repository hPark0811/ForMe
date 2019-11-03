package com.heung.forme.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.heung.forme.R;
import com.heung.forme.custom.recommendation.Recommendation;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    static String userID;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index, String userID) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        PlaceholderFragment.userID = userID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setUserID(PlaceholderFragment.userID);
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView title = root.findViewById(R.id.title);
        final ImageView image = root.findViewById(R.id.recImage);
        final TextView description = root.findViewById(R.id.description);

        pageViewModel.getmRecommendation().observe(this, new Observer<Recommendation>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable Recommendation rec) {
                title.setText(rec.getType());
                image.setImageResource(rec.getDrawableImage());
                String descriptionAggregate = String.join("\n\n ", rec.getDescription());
                description.setText(descriptionAggregate);
            }
        });
        return root;
    }
}