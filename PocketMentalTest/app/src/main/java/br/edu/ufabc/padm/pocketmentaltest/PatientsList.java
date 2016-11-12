package br.edu.ufabc.padm.pocketmentaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class PatientsList extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);
        init();
    }

    private void init() {
        this.setTitle(R.string.title_patients);
        loadListView();
    }
    private void loadListView() {
        listView = (ListView)findViewById(R.id.patientslist_listview);
        listView.setAdapter(new PatientAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patients_list, menu);

        MenuItem item = menu.findItem(R.id.menu_search_patient);
        /*SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.menu_search_patient:
                break;
            case R.id.menu_add_patient:
                intent = new Intent(this, PatientPersist.class);
                break;
            default:
                break;
        }
        if(intent != null)
            startActivity(intent);
        else
            Toast.makeText(this,
                    R.string.option_not_found,
                    Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}
