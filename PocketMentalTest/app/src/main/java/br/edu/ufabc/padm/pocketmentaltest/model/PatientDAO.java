package br.edu.ufabc.padm.pocketmentaltest.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eadte on 11/11/2016.
 */

public class PatientDAO {

    private static PatientDAO dao;
    private static List<Patient> patients;

    private PatientDAO() {

    }
    public static PatientDAO getInstance() {
        if(dao == null) {
            dao = new PatientDAO();
            patients = new ArrayList<>();
            loadSimpleData();
        }
        return dao;
    }
    private static void loadSimpleData() {
        Patient p;
        try {
            p = new Patient();
            p.setName("Paciente #1");
            p.setAddress("Rua Teste, 111 - Bairro Y - São Paulo, SP - Brazil");
            p.setBirthday(new Date(
                    new SimpleDateFormat("yyyy-MM-dd").parse("1991-01-01").getTime()));
            p.setSex(1);
            p.setSusCard("1111 1111 1111 1111");
            p.setPhone("11 1111 1111");
            patients.add(p);

            p = new Patient();
            p.setName("Paciente #2");
            p.setAddress("Rua Teste, 222 - Bairro Y - São Paulo, SP - Brazil");
            p.setBirthday(new Date(
                            new SimpleDateFormat("yyyy-MM-dd").parse("1982-02-02").getTime()));
            p.setSex(2);
            p.setSusCard("2222 2222 2222 2222");
            p.setPhone("11 2222 2222");
            patients.add(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return patients.size();
    }
    public Patient getAt(int position) {
        return patients.get(position);
    }

}
