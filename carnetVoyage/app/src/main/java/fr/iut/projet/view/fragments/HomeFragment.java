package fr.iut.projet.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.projet.R;
import fr.iut.projet.view.activities.MainActivity;

public class HomeFragment extends Fragment {

    private MainActivity monActivite;

    public HomeFragment() {
        super(R.layout.home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        monActivite = ((MainActivity) getContext());


    }
}
