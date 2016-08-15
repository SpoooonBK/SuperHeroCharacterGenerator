package net.estebanrodriguez.superherocharactergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CharacterFactory mCharacterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterFactory = new CharacterFactory(this);

        Button rollButton = (Button) findViewById(R.id.rollButton);
        final TextView physicalForm = (TextView)findViewById(R.id.textviewPhysicalForm);
        final TextView subForm = (TextView)findViewById(R.id.textViewSubform);
        final TextView origin = (TextView)findViewById(R.id.textViewOrigin);


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PoweredCharacter character = (PoweredCharacter) mCharacterFactory.generatePoweredCharacter();
                physicalForm.setText(character.getForm().getFormType());
                subForm.setText(character.getForm().getSubFormType());
                origin.setText(character.getOrigin().getOrigin());
            }
        });




    }
}
