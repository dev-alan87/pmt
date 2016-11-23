package br.edu.ufabc.padm.pocketmentaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PatientPersist extends AppCompatActivity {
    private final String IS_EDITING = "IS_EDITING";
    private boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_persist);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        editing = intent.getBooleanExtra(IS_EDITING, false);

        if(!editing)
            this.setTitle(R.string.new_patient);
        else
            this.setTitle(R.string.edit_patient);

        
    }

}
