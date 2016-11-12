package br.edu.ufabc.padm.pocketmentaltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.edu.ufabc.padm.pocketmentaltest.model.Patient;
import br.edu.ufabc.padm.pocketmentaltest.model.PatientDAO;

/**
 * Created by eadte on 11/11/2016.
 */

public class PatientAdapter extends BaseAdapter {

    private Context context;
    private PatientDAO dao;


    public PatientAdapter(Context c) {
        context = c;
        dao = PatientDAO.getInstance();
    }

    @Override
    public int getCount() {
        return dao.size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name;

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.patients_list_item, null);
        name = (TextView) convertView.findViewById(R.id.pacient_item_name);

        Patient p = dao.getAt(position);
        name.setText(p.getName());

        return convertView;
    }

}
