package net.estebanrodriguez.superherocharactergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import net.estebanrodriguez.superherocharactergenerator.character_model.Ability;
import net.estebanrodriguez.superherocharactergenerator.character_model.AbilityNamesEnum;
import net.estebanrodriguez.superherocharactergenerator.character_model.Power;
import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private CharacterFactory mCharacterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacterFactory = new CharacterFactory(this);
        setContentView(R.layout.activity_main);

        //Get Layout References
        Button rollButton = (Button) findViewById(R.id.rollButton);
        final TextView physicalFormTextView = (TextView)findViewById(R.id.textviewPhysicalForm);
        final TextView subFormTextView = (TextView)findViewById(R.id.textViewSubform);
        final TextView originTextView = (TextView)findViewById(R.id.textViewOrigin);
        final ListView listView = (ListView)findViewById(R.id.powersList);
        final EditText editText = (EditText)findViewById(R.id.editName);

        final TextView abilityFightingTextView = (TextView)findViewById(R.id.textViewAbilityFighting);
        final TextView abilityAgilityTextView = (TextView)findViewById(R.id.textViewAbilityAgility);
        final TextView abilityStrengthTextView = (TextView)findViewById(R.id.textViewAbilityStrength);
        final TextView abilityEnduranceTextView = (TextView)findViewById(R.id.textViewAbilityEndurance);
        final TextView abilityReasonTextView = (TextView)findViewById(R.id.textViewAbilityReason);
        final TextView abilityIntuitionTextView = (TextView)findViewById(R.id.textViewAbilityIntuition);
        final TextView abilityPsycheTextView = (TextView)findViewById(R.id.textViewAbilityPsyche);
        final TextView abilityKarmaTextView = (TextView) findViewById(R.id.textViewAbilityKarma);
        final TextView abilityHealthTextView = (TextView) findViewById(R.id.textViewAbilityHealth);



        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PoweredCharacter character = null;

                CharacterRollerAsyncTask task = new CharacterRollerAsyncTask();
                task.execute(mCharacterFactory);

                try {
                    character = task.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                //Get power Data
                List<String> powerData = new ArrayList<String>();
                Iterator<Power> iterator = character.getPowers().iterator();
                while(iterator.hasNext()){
                    powerData.add(iterator.next().getPowerName());
                }

                // update UI
                physicalFormTextView.setText(character.getForm().getFormType());
                subFormTextView.setText(character.getForm().getSubFormType());
                originTextView.setText(character.getOrigin().getOrigin());
                editText.setText(character.getCharacterName());

                Map<AbilityNamesEnum, Ability> abilityMap= character.getAbilityMap();

                abilityFightingTextView.setText(abilityMap.get(AbilityNamesEnum.FIGHTING).getCurrentRankName());
                abilityAgilityTextView.setText(abilityMap.get(AbilityNamesEnum.AGILITY).getCurrentRankName());
                abilityStrengthTextView.setText(abilityMap.get(AbilityNamesEnum.STRENGTH).getCurrentRankName());
                abilityEnduranceTextView.setText(abilityMap.get(AbilityNamesEnum.ENDURANCE).getCurrentRankName());
                abilityReasonTextView.setText(abilityMap.get(AbilityNamesEnum.REASON).getCurrentRankName());
                abilityIntuitionTextView.setText(abilityMap.get(AbilityNamesEnum.INTUITION).getCurrentRankName());
                abilityPsycheTextView.setText(abilityMap.get(AbilityNamesEnum.PSYCHE).getCurrentRankName());

                Integer health = character.getCurrentHealth();
                Integer karma = character.getCurrentKarma();

                abilityHealthTextView.setText(health.toString());
                abilityKarmaTextView.setText(karma.toString());



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
