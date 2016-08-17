package net.estebanrodriguez.superherocharactergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import net.estebanrodriguez.superherocharactergenerator.character_model.Character;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;

import java.util.ArrayList;
import java.util.Iterator;
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
        final ListView listView = (ListView)findViewById(R.id.powersList);
        final EditText editText = (EditText)findViewById(R.id.editName);


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PoweredCharacter character = (PoweredCharacter) mCharacterFactory.generatePoweredCharacter();
                //Get power Data
                List<String> powerData = new ArrayList<String>();

                Iterator<Power> iterator = character.getPowers().iterator();
                while(iterator.hasNext()){
                    powerData.add(iterator.next().getPowerName());
                }

                // update UI
                physicalForm.setText(character.getForm().getFormType());
                subForm.setText(character.getForm().getSubFormType());
                origin.setText(character.getOrigin().getOrigin());
                editText.setText(character.getCharacterName());


                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(
                                MainActivity.this,
                                R.layout.list_item_power,
                                R.id.list_item_textview,
                                powerData);
                listView.setAdapter(adapter);

            }
        });




    }
}
