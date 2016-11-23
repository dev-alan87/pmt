package br.edu.ufabc.padm.pocketmentaltest.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.edu.ufabc.padm.pocketmentaltest.R;


public class PatientDAO extends SQLiteOpenHelper {

    private static PatientDAO dao;
    private Context context;
    private SQLiteDatabase db;

    private static final String DB_NAME = "patients.db";
    private static final int DB_VERSION = 1;

    private static final String LOGTAG = PatientDAO.class.getName();

    private PatientDAO(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
        this.context = c;
        this.db = getWritableDatabase();
    }
    public static PatientDAO newInstance(Context c) {
        if(dao == null) {
            dao = new PatientDAO(c);
        } else
            dao.context = c;

        return dao;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryStr = context.getString(R.string.create_table_query);

        try {
            db.execSQL(queryStr);
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryStr = context.getString(R.string.drop_table_query);

        try {
            db.execSQL(queryStr);
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to drop the database", e);
        }
    }

    // -- PATIENTS
    public int size() {
        String queryStr = context.getString(R.string.count_patients_query);
        int count = -1;

        try {
            Cursor cursor = db.rawQuery(queryStr, new String[]{});

            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to count in the database", e);
        }

        return count;
    }

    public ArrayList<Patient> list() {
        ArrayList<Patient> patients = new ArrayList<>();
        String queryStr = context.getString(R.string.list_patients_query);

        try {
            Cursor cursor = db.rawQuery(queryStr, new String[]{});
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Patient p = new Patient();
                p.setId(cursor.getLong(0));
                p.setName(cursor.getString(1));
                p.setAddress(cursor.getString(2));
                p.setBirthday(this.formatDateTime(cursor.getString(3)));
                p.setSchooling(cursor.getInt(4));
                p.setSex(cursor.getInt(5));
                p.setPhone(cursor.getString(6));
                p.setSusCard(cursor.getString(7));
                patients.add(p);

                cursor.moveToNext();
            }
            cursor.close();
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to list from the database", e);
        }

        return patients;
    }

    public Patient getPatientById(long id) {
        String query = "SELECT * FROM patients_list WHERE _id =  " + id + ";";
        try {
            Cursor cursor = db.rawQuery(query, new String[]{});
            cursor.moveToFirst();
            Patient p = new Patient();
            p.setId(cursor.getLong(0));
            p.setName(cursor.getString(1));
            p.setAddress(cursor.getString(2));
            p.setBirthday(this.formatDateTime(cursor.getString(3)));
            p.setSchooling(cursor.getInt(4));
            p.setSex(cursor.getInt(5));
            p.setPhone(cursor.getString(6));
            p.setSusCard(cursor.getString(7));

            cursor.close();
            return p;
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to get from the database", e);
        }
        return null;
    }

    public boolean add(Patient patient) {
        String queryStr = context.getString(R.string.insert_patient_query);
        boolean status = true;
        try {
            SQLiteStatement statement = db.compileStatement(queryStr);
            statement.bindString(1, patient.getName());
            statement.bindString(2, patient.getAddress());
            statement.bindString(3, patient.getBirthday().toString());
            statement.bindLong(4, patient.getSchooling());
            statement.bindLong(5, patient.getSex());
            statement.bindString(6, patient.getPhone());
            statement.bindString(7, patient.getSusCard());
            statement.execute();
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to add in the database", e);
            status = false;
        }

        return status;
    }

    public boolean remove(Patient p) {
        try {
            db.delete("tests_results_list", "_patientid = " + p.getId(), null);
            return db.delete("patients_list", "_id = "+p.getId(), null) > 0;
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to delete from the database", e);
        }
        return false;
    }

    public boolean update(Patient oldPatient, Patient newPatient) {
        String query = "UPDATE patients_list SET"+
                " nome = '" + newPatient.getName() + "', " +
                " endereco = " + newPatient.getAddress() +
                " nascimento = '" + newPatient.getBirthday().toString() + "', " +
                " escolaridade = " + newPatient.getSchooling() + ", " +
                " sexo = " + newPatient.getSex() + ", " +
                " telefone = '" + newPatient.getPhone() + "', " +
                " sus = '" + newPatient.getSusCard() + "', " +
                " WHERE _id =  " + oldPatient.getId() + ";";
        try {
            db.execSQL(query);
            return true;
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to update the database", e);
        }

        return false;
    }

    // -- RESULTS
    public ArrayList<Result> listResultsFromPatient(Patient p) {
        ArrayList<Result> results = new ArrayList<>();
        String queryStr = "SELECT * FROM tests_results_list WHERE _patientid =  " + p.getId() + ";";

        try {
            Cursor cursor = db.rawQuery(queryStr, new String[]{});
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Result r = new Result();
                r.setId(cursor.getLong(0));
                r.setData(cursor.getString(1));
                r.setScore(cursor.getDouble(2));
                r.setTeste(cursor.getString(3));
                results.add(r);

                cursor.moveToNext();
            }
            cursor.close();
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to list from the database", e);
        }

        return results;
    }

    public boolean addResult(Patient patient, Result result) {
        String queryStr = context.getString(R.string.insert_patientresult_query);
        boolean status = true;
        try {
            SQLiteStatement statement = db.compileStatement(queryStr);
            statement.bindLong(1, patient.getId());
            statement.bindString(2, result.getData());
            statement.bindDouble(3, result.getScore());
            statement.bindString(4, result.getTeste());

            statement.execute();
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to add in the database", e);
            status = false;
        }

        return status;
    }

    public boolean removeAllResultsFromPatient(Patient p) {
        try {
            return db.delete("tests_results_list", "_patientid = " + p.getId(), null) > 0;
        } catch (SQLiteException e) {
            Log.e(LOGTAG, "Failed to delete from the database", e);
        }
        return false;
    }

    // -- UTILS
    private Date formatDateTime(String timeToFormat) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (ParseException e) {
                date = null;
            }
        }
        return date;
    }

}
