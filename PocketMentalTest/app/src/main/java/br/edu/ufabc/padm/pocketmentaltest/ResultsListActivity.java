package br.edu.ufabc.padm.pocketmentaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import br.edu.ufabc.padm.pocketmentaltest.model.Patient;
import br.edu.ufabc.padm.pocketmentaltest.model.PatientDAO;

/**
 * Created by victor on 12/4/16.
 */

public class ResultsListActivity  extends AppCompatActivity {
    private ListView listView;
    PatientDAO dao;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);

        listView = (ListView)findViewById(R.id.listview_testsresults);
        listView.setAdapter(new ResultsAdapter(this, patient));

        dao = PatientDAO.newInstance(this);

        // OBS: important to pass as extra the patientID before starting this activity
        long id = getIntent().getExtras().getLong("patientId");
        patient = dao.getPatientById(id);

        this.setTitle("Resultados dos Testes");
    }
}
