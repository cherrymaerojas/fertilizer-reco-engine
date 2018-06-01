package com.example.charliepc.newproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class OptimalFertilizerResultActivity extends AppCompatActivity {

    String fname, fsize, crop, funit, n, p, k, s, z, b;
    TextView name, size, crp, nrem, prem, krem, srem, zrem, brem;
    TextView nrec, prec, krec, srec, zrec, brec;
    float[] rrate;

    //For testing only
    String message = "";
    TextView test;

    ArrayList<Float> weights;
    ArrayList<String> fertilizers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optimal_fert_result);
        Bundle bundle = getIntent().getExtras();
        fname = bundle.getString("Fieldname");
        fsize = bundle.getString("Fieldsize");
        crop = bundle.getString("Crop");
        funit = bundle.getString("FieldUnit");
        n = bundle.getString("N_remark");
        p = bundle.getString("P_remark");
        k = bundle.getString("K_remark");
        s = bundle.getString("S_remark");
        z = bundle.getString("Z_remark");
        b = bundle.getString("B_remark");

        name = findViewById(R.id.fieldname_res);
        size = findViewById(R.id.fieldsize_res);
        crp = findViewById(R.id.crop_res);

        nrem = findViewById(R.id.n_remark);
        prem = findViewById(R.id.p_remark);
        krem = findViewById(R.id.k_remark);
        srem = findViewById(R.id.s_remark);
        zrem = findViewById(R.id.z_remark);
        brem = findViewById(R.id.b_remark);

        nrec = findViewById(R.id.n_rec);
        prec = findViewById(R.id.p_rec);
        krec = findViewById(R.id.k_rec);
        srec = findViewById(R.id.s_rec);
        zrec = findViewById(R.id.z_rec);
        brec = findViewById(R.id.b_rec);

        //for testing
        test = findViewById(R.id.texttest);

        name.setText(fname);
        size.setText(fsize + " " + funit);
        crp.setText(crop);
        nrem.setText(n);
        prem.setText(p);
        krem.setText(k);
        srem.setText(s);
        zrem.setText(z);
        brem.setText(b);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String[] nut = {"N", "P", "K", "S", "Zn", "B"};

        float value;

        for (int i = 0; i < 6; i++) {
            String val = databaseAccess.getRecValue(crop, nut[i]);
            if ((i == 0) && (n.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                nrec.setText(String.valueOf(value));
            } else if ((i == 0) && (n.equals("Defficient"))) {
                nrec.setText(val);
            }
            if ((i == 1) && (p.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                prec.setText(String.valueOf(value));
            } else if ((i == 1) && (p.equals("Defficient"))) {
                prec.setText(val);
            }
            if ((i == 2) && (k.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                krec.setText(String.valueOf(value));
            } else if ((i == 2) && (k.equals("Defficient"))) {
                krec.setText(val);
            }
            if ((i == 3) && (s.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                srec.setText(String.valueOf(value));
            } else if ((i == 3) && (s.equals("Defficient"))) {
                srec.setText(val);
            }
            if ((i == 4) && (z.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                zrec.setText(String.valueOf(value));
            } else if ((i == 4) && (z.equals("Defficient"))) {
                zrec.setText(val);
            }
            if ((i == 5) && (b.equals("Sufficient"))) {
                value = Float.valueOf(val) / 2;
                brec.setText(String.valueOf(value));
            } else if ((i == 5) && (b.equals("Defficient"))) {
                brec.setText(val);
            }
        }

        rrate = new float[6];

        rrate[0] = Float.valueOf((nrec.getText().toString()));
        rrate[1] = Float.valueOf((prec.getText().toString()));
        rrate[2] = Float.valueOf((krec.getText().toString()));
        rrate[3] = Float.valueOf((srec.getText().toString()));
        rrate[4] = Float.valueOf((zrec.getText().toString()));
        rrate[5] = Float.valueOf((brec.getText().toString()));

        //float[] rrates -> contains the recommended rates
        //ArrayList<String> -> contains the arraylist of selected fertilizer


    }
}







