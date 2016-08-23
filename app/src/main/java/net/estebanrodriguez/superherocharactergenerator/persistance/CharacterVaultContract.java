package net.estebanrodriguez.superherocharactergenerator.persistance;

import android.provider.BaseColumns;

/**
 * Created by Esteban Rodriguez on 8/22/2016.
 */
public final class CharacterVaultContract {

    public CharacterVaultContract() {}

    public static abstract class CharacterTable implements BaseColumns{
        public static final String CHARACTER_TABLE = "characters";
        public static final String COLUMN_CHARACTER_ID ="id_character";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_FORM = "form";
        public static final String COLUMN_SUBFORM = "subform";

        public static final String COLUMN_FIGHTING_INITIAL_RANK_NAME = "ability_fighting_initial_rank_name";
        public static final String COLUMN_FIGHTING_CURRENT_RANK_NAME = "ability_fighting_current_rank_name";
        public static final String COLUMN_FIGHTING_INITIAL_RANK_NUMBER = "ability_fighting_initial_rank_number";
        public static final String COLUMN_FIGHTING_CURRENT_RANK_NUMBER = "ability_fighting_current_rank_number";

        public static final String COLUMN_AGILITY_INITIAL_RANK_NAME = "ability_agility_initial_rank_name";
        public static final String COLUMN_AGILITY_CURRENT_RANK_NAME = "ability_agility_current_rank_name";
        public static final String COLUMN_AGILITY_INITIAL_RANK_NUMBER = "ability_agility_initial_rank_number";
        public static final String COLUMN_AGILITY_CURRENT_RANK_NUMBER = "ability_agility_current_rank_number";

        public static final String COLUMN_STRENGTH_INITIAL_RANK_NAME = "ability_strength_initial_rank_name";
        public static final String COLUMN_STRENGTH_CURRENT_RANK_NAME = "ability_strength_current_rank_name";
        public static final String COLUMN_STRENGTH_INITIAL_RANK_NUMBER = "ability_strength_initial_rank_number";
        public static final String COLUMN_STRENGTH_CURRENT_RANK_NUMBER = "ability_strength_current_rank_number";

        public static final String COLUMN_ENDURANCE_INITIAL_RANK_NAME = "ability_endurance_initial_rank_name";
        public static final String COLUMN_ENDURANCE_CURRENT_RANK_NAME = "ability_endurance_current_rank_name";
        public static final String COLUMN_ENDURANCE_INITIAL_RANK_NUMBER = "ability_endurance_initial_rank_number";
        public static final String COLUMN_ENDURANCE_CURRENT_RANK_NUMBER = "ability_endurance_current_rank_number";

        public static final String COLUMN_REASON_INITIAL_RANK_NAME = "ability_reason_initial_rank_name";
        public static final String COLUMN_REASON_CURRENT_RANK_NAME = "ability_reason_current_rank_name";
        public static final String COLUMN_REASON_INITIAL_RANK_NUMBER = "ability_reason_initial_rank_number";
        public static final String COLUMN_REASON_CURRENT_RANK_NUMBER = "ability_reason_current_rank_number";

        public static final String COLUMN_INTUITION_INITIAL_RANK_NAME = "ability_intuition_initial_rank_name";
        public static final String COLUMN_INTUITION_CURRENT_RANK_NAME = "ability_intuition_current_rank_name";
        public static final String COLUMN_INTUITION_INITIAL_RANK_NUMBER = "ability_intuition_initial_rank_number";
        public static final String COLUMN_INTUITION_CURRENT_RANK_NUMBER = "ability_intuition_current_rank_number";

        public static final String COLUMN_PSYCHE_INITIAL_RANK_NAME = "ability_psyche_initial_rank_name";
        public static final String COLUMN_PSYCHE_CURRENT_RANK_NAME = "ability_psyche_current_rank_name";
        public static final String COLUMN_PSYCHE_INITIAL_RANK_NUMBER = "ability_psyche_initial_rank_number";
        public static final String COLUMN_PSYCHE_CURRENT_RANK_NUMBER = "ability_psyche_current_rank_number";
        
        public static final String COLUMN_POWERS_INITIAL_AMOUNT = "powers_initial_amount";
        public static final String COLUMN_POWERS_MAX_AMOUNT = "powers_max_amount";
        public static final String COLUMN_POWERS_CURRENT_AMOUNT = "powers_current_amount";

        public static final String COLUMN_TALENTS_INITIAL_AMOUNT = "talents_initial_amount";
        public static final String COLUMN_TALENTS_MAX_AMOUNT = "talents_max_amount";
        public static final String COLUMN_TALENTS_CURRENT_AMOUNT = "talents_current_amount";

        public static final String COLUMN_CONTACTS_INITIAL_AMOUNT = "contacts_initial_amount";
        public static final String COLUMN_CONTACTS_MAX_AMOUNT = "contacts_max_amount";
        public static final String COLUMN_CONTACTS_CURRENT_AMOUNT = "contacts_current_amount";

        public static final String COLUMN_HEALTH_CURRENT = "health_current";
        public static final String COLUMN_KARMA_CURRENT = "karma_current";

        public static final String COLUMN_ORIGIN = "origin";
        
    }


}
