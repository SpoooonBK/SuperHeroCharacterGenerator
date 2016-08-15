package net.estebanrodriguez.superherocharactergenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.estebanrodriguez.superherocharactergenerator.databasehelper.DatabaseAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getForms();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);
    }
}
