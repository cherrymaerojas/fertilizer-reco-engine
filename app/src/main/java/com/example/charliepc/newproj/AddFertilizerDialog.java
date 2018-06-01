package com.example.charliepc.newproj;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFertilizerDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "RegisterActivity";
    public EditText editTextFertname;
    public EditText editTextFertprice;
    public Spinner spinn, spinn2, spinn3, spinn4;
    public EditText editTextFertcontent, editTextFertcontent2, editTextFertcontent3, editTextFertcontent4;
    public AddfertilizerDialogInterface listener;
    public TextInputLayout addfertn, addfertp;
    public static Vibrator vib;
    Animation animShake;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.addfertilizer, null);

        builder.setView(view);
        builder.setTitle("Add Fertilizer");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String fertname = editTextFertname.getText().toString();
                String fertprice = editTextFertprice.getText().toString();
                String fertnut = spinn.getSelectedItem().toString();
                String fertnut2 = spinn2.getSelectedItem().toString();
                String fertnut3 = spinn3.getSelectedItem().toString();
                String fertnut4 = spinn4.getSelectedItem().toString();
                String fercon = editTextFertcontent.getText().toString();
                String fercon2 = editTextFertcontent2.getText().toString();
                String fercon3 = editTextFertcontent3.getText().toString();
                String fercon4 = editTextFertcontent4.getText().toString();

                nextQuery();


                // listener.applyTexts(fertname, fertprice, fertnut, fertnut2, fertnut3, fertnut4, fercon, fercon2, fercon3, fercon4);
            }


        });

        addfertn = view.findViewById(R.id.addfertn_id);
        addfertp = view.findViewById(R.id.addfertp_id);

        spinn = view.findViewById(R.id.nutspinner);
        spinn2 = view.findViewById(R.id.nutspinner2);
        spinn3 = view.findViewById(R.id.nutspinner3);
        spinn4 = view.findViewById(R.id.nutspinner4);

        editTextFertcontent = view.findViewById(R.id.addfertcontent_id);
        editTextFertcontent2 = view.findViewById(R.id.addfertcontent2_id);
        editTextFertcontent3 = view.findViewById(R.id.addfertcontent3_id);
        editTextFertcontent4 = view.findViewById(R.id.addfertcontent4_id);

        editTextFertname = view.findViewById(R.id.addfertname_id);
        editTextFertprice = view.findViewById(R.id.addfertprice_id);

        spinn = view.findViewById(R.id.nutspinner)  ;
        spinn.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.nutrients));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(aa);

        spinn2 = view.findViewById(R.id.nutspinner2)  ;
        spinn2.setOnItemSelectedListener(this);
        spinn2.setAdapter(aa);

        spinn3 = view.findViewById(R.id.nutspinner3)  ;
        spinn3.setOnItemSelectedListener(this);
        spinn3.setAdapter(aa);

        spinn4 = view.findViewById(R.id.nutspinner4)  ;
        spinn4.setOnItemSelectedListener(this);
        spinn4.setAdapter(aa);

        animShake = AnimationUtils.loadAnimation(getActivity().getApplication().getApplicationContext(),R.anim.shake );
        vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        return builder.create();

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (AddfertilizerDialogInterface) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement AddfertilizerDialogListener");
        }
    }

    public interface AddfertilizerDialogInterface{
        void applyTexts(String fertname, String fertprice, String fertnut, String fertnut2, String fertnut3, String fertnut4, String fercon, String fercon2, String fercon3, String fercon4);
    }


    public void addfert(){
        String msg = editTextFertname.getText().toString();
        String msg2 = editTextFertprice.getText().toString();
        String msg3 = spinn.getSelectedItem().toString();
        Toast.makeText(getContext(), "You add " + msg3, Toast.LENGTH_LONG).show();
    }

    public void nextQuery(){
        if(!checkFieldname()){
            editTextFertname.setAnimation(animShake);
            editTextFertname.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkFieldPrice()){
            editTextFertprice.setAnimation(animShake);
            editTextFertprice.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        addfertn.setErrorEnabled(false);
        addfertp.setErrorEnabled(false);
        Toast.makeText(getContext(), editTextFertname.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public boolean checkFieldname(){
        if(editTextFertname.getText().toString().trim().isEmpty()){
            addfertn.setErrorEnabled(true);
            addfertn.setError("Please Enter Fertlizer Name!");
            return false;
        }
        addfertn.setErrorEnabled(false);
        Toast.makeText(getContext(), "Not EMpty", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean checkFieldPrice(){
        if(editTextFertprice.getText().toString().trim().isEmpty()){
            addfertp.setErrorEnabled(true);
            addfertp.setError("Please Enter Fertilizer Price!");
            return false;
        }
        addfertp.setErrorEnabled(false);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //   Toast.makeText(getApplicationContext(), cropstr[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
