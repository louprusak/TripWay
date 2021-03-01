package fr.iut.projet.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.projet.R;
import fr.iut.projet.view.CarnetActivity;
import fr.iut.projet.view.MainActivity;

public class Create extends Fragment {

    private MainActivity monActivite;

    public Create() {
        super(R.layout.create);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monActivite = ((MainActivity) getContext());
        init(view);
    }


    private void init(View v){
        Button b=(Button)v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent=new Intent(getActivity(), CarnetActivity.class);
                startActivity(monIntent);
            }
        } );
    }
}
