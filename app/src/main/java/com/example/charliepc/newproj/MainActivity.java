package com.example.charliepc.newproj;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String TAG = "RegisterActivity";
    public Vibrator vib;
    Animation animShake;
    public EditText fieldname, fieldsize;
    public TextInputLayout tv_fname, tv_size;

    DrawerLayout nDrawerLayout;
    public ActionBarDrawerToggle nToggle;
    public EditText name;
    Button query_button;
    RadioButton acre, hectare;
    String funit = "acre";
    Spinner spin;

   /* public TextView result_desc; */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_fname = findViewById(R.id.fieldname_id);
        tv_size = findViewById(R.id.fieldsize_id);

        fieldname = findViewById(R.id.name_field);
        fieldsize = findViewById(R.id.size_field);
        query_button=findViewById(R.id.addnut_btn);
        acre = findViewById(R.id.acre_id);
        hectare = findViewById(R.id.hectare_id);

        spin = findViewById(R.id.spinner)  ;

        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake );
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        nDrawerLayout = findViewById(R.id.drawerID);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      //  result_desc=findViewById(R.id.result);

        //setting onclicklistener
        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the instance of DatabaseAccess class
               // DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
               // databaseAccess.open();

                //getting string value
               // String n=name.getText().toString();
               // String desc = databaseAccess.getDescription(n);

                //setting text to text field
          //      result_desc.setText(desc);
                //databaseAccess.close();
                nextQuery();
            }
        });
        hectare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acre.setChecked(false);
                funit = "hectare";
            }
        });
        acre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hectare.setChecked(false);
                funit = "acre";
            }
        });
        getStringCrops();
    }
    public void getStringCrops(){

        spin.setOnItemSelectedListener(this);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor crops = databaseAccess.getCrops();
        ArrayList<String> cropstr = new ArrayList<String>();

        while (crops.moveToNext()){
            cropstr.add(crops.getString(0));
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cropstr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        databaseAccess.close();
    }

    public  void openFertlizerInput(){
        Intent intent = new Intent(this, SoilHealthInfoInputActivity.class);
        String fname = fieldname.getText().toString();
        String fsize = fieldsize.getText().toString();
        String fdunit = funit;
        String c = spin.getSelectedItem().toString();

        Bundle bundle = new Bundle();
        bundle.putString("Fieldname", fname);
        bundle.putString("Fieldsize", fsize);
        bundle.putString("Crop", c);
        bundle.putString("FieldUnit", funit);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void nextQuery(){
        if(!checkName()){
            fieldname.setAnimation(animShake);
            fieldname.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkSize()){
            fieldsize.setAnimation(animShake);
            fieldsize.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        tv_fname.setErrorEnabled(false);
        tv_size.setErrorEnabled(false);
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
        openFertlizerInput();
    }

    public boolean checkName(){
        if(fieldname.getText().toString().trim().isEmpty()){
            tv_fname.setErrorEnabled(true);
            tv_fname.setError("Please Enter Field Name!");
            return false;
        }
        tv_fname.setErrorEnabled(false);
        return true;
    }

    public boolean checkSize(){
        if(fieldsize.getText().toString().trim().isEmpty()){
            tv_size.setErrorEnabled(true);
            tv_size.setError("Please Enter Field Size!");
            return false;
        }
        tv_size.setErrorEnabled(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          //   Toast.makeText(getApplicationContext(), cropstr[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}