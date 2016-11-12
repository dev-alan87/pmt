package br.edu.ufabc.padm.pocketmentaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView mainOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void init() {
        loadMainOptions();
    }
    private void loadMainOptions() {
        final MainActivity self = this;

        mainOptions = (ListView) findViewById(R.id.main_options);
        mainOptions.setAdapter(new MainOptionsAdapter(this));
        mainOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(self, PatientsList.class);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                if(intent != null)
                    startActivity(intent);
                else
                    Toast.makeText(
                            self,
                            R.string.option_not_found,
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

}
