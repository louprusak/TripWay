package fr.iut.projet.view;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import fr.iut.projet.R;

public class AddTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_text_activity);

    }

    //SAUVEGARDE LEGERE
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ((EditText)findViewById(R.id.texte_a_ajouter)).setText(String.valueOf(savedInstanceState.getInt("POSITION_X")));
    }

}
