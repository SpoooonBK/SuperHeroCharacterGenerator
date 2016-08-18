package net.estebanrodriguez.superherocharactergenerator;

import android.os.AsyncTask;
import android.util.Log;

import net.estebanrodriguez.superherocharactergenerator.character_model.PoweredCharacter;
import net.estebanrodriguez.superherocharactergenerator.utilities.CharacterPool;

/**
 * Created by Esteban Rodriguez on 8/18/2016.
 */
public class CharacterRollerAsyncTask extends AsyncTask <CharacterFactory, Void, PoweredCharacter> {

    CharacterPool mCharacterPool;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCharacterPool = CharacterPool.getInstance();
    }

    @Override
    protected PoweredCharacter doInBackground(CharacterFactory... characterFactories) {
        if(!mCharacterPool.checkIfFull()) {
            mCharacterPool.fillPoweredCharacterPool(characterFactories[0]);
        }
        PoweredCharacter character = mCharacterPool.getCharacter();
        return character;
    }

    @Override
    protected void onPostExecute(PoweredCharacter poweredCharacter) {
        super.onPostExecute(poweredCharacter);
        mCharacterPool.replenishPoweredCharacterPool();
        Log.d("HERO", "ASYNCTASK: Characters in pool: " + mCharacterPool.getSize());
    }
}