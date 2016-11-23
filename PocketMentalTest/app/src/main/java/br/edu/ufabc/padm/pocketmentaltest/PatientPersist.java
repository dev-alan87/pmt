package br.edu.ufabc.padm.pocketmentaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import br.edu.ufabc.padm.pocketmentaltest.model.Patient;

public class PatientPersist extends AppCompatActivity {
    private final String IS_EDITING = "IS_EDITING";
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private boolean editing = false;

    private TextView name;
    private TextView address;
    private TextView birthday;
    private TextView sex;
    private TextView phone;
    private TextView susCard;
    private Button save;

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

        loadForm();
    }
    private void loadForm() {
        name =      (TextView) findViewById(R.id.persist_patient_name);
        address =   (TextView) findViewById(R.id.persist_patient_address);
        birthday =  (TextView) findViewById(R.id.persist_patient_birthday);
        sex =       (TextView) findViewById(R.id.persist_patient_sex);
        phone =     (TextView) findViewById(R.id.persist_patient_phone);
        susCard =   (TextView) findViewById(R.id.persist_patient_sus_card);

        //TODO: set form validator and other things (e.g.: picker date on click in birthday textview)

        save =      (Button) findViewById(R.id.persist_patient_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patient = new Patient();
                patient.setName(name.getText().toString());
                patient.setAddress(address.getText().toString());
                //patient.setBirthday(birthday.getText().toString());
            }
        });
    }



}
