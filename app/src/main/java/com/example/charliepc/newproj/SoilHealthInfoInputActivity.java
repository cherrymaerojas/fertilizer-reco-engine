package com.example.charliepc.newproj;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Float.parseFloat;

public class SoilHealthInfoInputActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";
    public Vibrator vib;
    Animation animShake;
    TextInputLayout n, p, k, s, z, b, i, c, m;
    EditText ntext, ptext, ktext, stext, ztext, btext, itext, ctext, mtext;
    TextView ntextview, ptextview, ktextview, stextview, ztextview, btextview, itextview, ctextview, mtextview;
    Button next_button;

    String fname, fsize, crop, funit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soil_health_info);
        Bundle bundle = getIntent().getExtras();
        fname = bundle.getString("Fieldname");
        fsize = bundle.getString("Fieldsize");
        crop = bundle.getString("Crop");
        funit = bundle.getString("FieldUnit");

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAct();
            }
        });
        ntext = findViewById(R.id.nitrogen_id);
        ntextview = findViewById(R.id.nitrogen_re);
        ptext = findViewById(R.id.phosphorous_id);
        ptextview = findViewById(R.id.phosphorous_re);
        ktext = findViewById(R.id.potassium_id);
        ktextview = findViewById(R.id.potassium_re);
        stext = findViewById(R.id.sulfur_id);
        stextview = findViewById(R.id.sulfur_re);
        ztext = findViewById(R.id.zinc_id);
        ztextview = findViewById(R.id.zinc_re);
        btext = findViewById(R.id.boron_id);
        btextview = findViewById(R.id.boron_re);
        itext = findViewById(R.id.iron_id);
        itextview = findViewById(R.id.iron_re);
        ctext = findViewById(R.id.copper_id);
        ctextview = findViewById(R.id.copper_re);
        mtext = findViewById(R.id.manganese_id);
        mtextview = findViewById(R.id.manganese_re);

        n = findViewById(R.id.nitrogen);
        p = findViewById(R.id.phosphorous);
        k = findViewById(R.id.potassium);
        s = findViewById(R.id.sulfur);
        z = findViewById(R.id.zinc);
        b = findViewById(R.id.boron);
        i = findViewById(R.id.iron);
        c = findViewById(R.id.copper);
        m = findViewById(R.id.manganese);


        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake );
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        ntext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = ntext.getText().toString();
                if (!hasFocus) {
                    if (parseFloat(str) >= 2.0)
                        ntextview.setText(R.string.suff);
                    else
                        ntextview.setText(R.string.deff);
                }
            }

        });
        ptext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = ptext.getText().toString();
                if (!hasFocus) {
                    if (parseFloat(str) >= 10.0)
                        ptextview.setText(R.string.suff);
                    else
                        ptextview.setText(R.string.deff);
                }
            }

        });

        ktext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = ktext.getText().toString();
                if (!hasFocus) {
                    if (parseFloat(str) >= 75.0)
                        ktextview.setText(R.string.suff);
                    else
                        ktextview.setText(R.string.deff);
                }
            }

        });
        stext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = stext.getText().toString();
                if (!hasFocus) {
                    if (parseFloat(str) >= 10.0)
                        stextview.setText(R.string.suff);
                    else
                        stextview.setText(R.string.deff);
                }
            }
        });
        ztext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = ztext.getText().toString();

                if (!hasFocus) {
                    if (parseFloat(str) >= 0.75)
                        ztextview.setText(R.string.suff);
                    else
                        ztextview.setText(R.string.deff);
                }
            }
        });
        btext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = btext.getText().toString();

                if (!hasFocus) {
                    if (parseFloat(str) >= 0.58)
                        btextview.setText(R.string.suff);
                    else
                        btextview.setText(R.string.deff);
                }
            }
        });
        itext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = itext.getText().toString();

                if (!hasFocus) {
                    if (parseFloat(str) >= 2.0)
                        itextview.setText(R.string.suff);
                    else
                        itextview.setText(R.string.deff);
                }
            }
        });
        ctext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = ctext.getText().toString();

                if (!hasFocus) {
                    if (parseFloat(str) >= 0.5)
                        ctextview.setText(R.string.suff);
                    else
                        ctextview.setText(R.string.deff);
                }
            }
        });
        mtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String str = mtext.getText().toString();

                if (!hasFocus) {
                    if (parseFloat(str) >= 1.0)
                        mtextview.setText(R.string.suff);
                    else
                        mtextview.setText(R.string.deff);
                }
            }
        });


    }

    public void nextAct(){
        if(!checkContent_N()){
            n.setAnimation(animShake);
            n.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_P()){
            p.setAnimation(animShake);
            p.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_K()){
            k.setAnimation(animShake);
            k.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_S()){
            s.setAnimation(animShake);
            s.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_Z()){
            z.setAnimation(animShake);
            z.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_B()){
            b.setAnimation(animShake);
            b.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_I()){
            i.setAnimation(animShake);
            i.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_C()){
            c.setAnimation(animShake);
            c.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }
        if(!checkContent_M()){
            m.setAnimation(animShake);
            m.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        n.setErrorEnabled(false);
        p.setErrorEnabled(false);
        k.setErrorEnabled(false);
        s.setErrorEnabled(false);
        z.setErrorEnabled(false);
        b.setErrorEnabled(false);
        i.setErrorEnabled(false);
        c.setErrorEnabled(false);
        m.setErrorEnabled(false);
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
        openSelectFertilizer();
    }
    public boolean checkContent_N(){
        if(ntext.getText().toString().trim().isEmpty()){
            n.setErrorEnabled(true);
            n.setError("Please Enter Nitrogen (%)");
            return false;
        }
        n.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_P(){
        if(ptext.getText().toString().trim().isEmpty()){
            p.setErrorEnabled(true);
            p.setError("Please Enter Phosphorous (mg kg-1)");
            return false;
        }
        p.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_K(){
        if(ktext.getText().toString().trim().isEmpty()){
            k.setErrorEnabled(true);
            k.setError("Please Enter Potassium (mg kg-1)");
            return false;
        }
        k.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_S(){
        if(stext.getText().toString().trim().isEmpty()){
            s.setErrorEnabled(true);
            s.setError("Please Enter Sulfur (mg kg-1)");
            return false;
        }
        s.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_Z(){
        if(ztext.getText().toString().trim().isEmpty()){
            z.setErrorEnabled(true);
            z.setError("Please Enter Zinc (mg kg-1)");
            return false;
        }
        z.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_B(){
        if(btext.getText().toString().trim().isEmpty()){
            b.setErrorEnabled(true);
            b.setError("Please Enter Boron (mg kg-1)");
            return false;
        }
        b.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_I() {
        if (itext.getText().toString().trim().isEmpty()) {
            i.setErrorEnabled(true);
            i.setError("Please Enter Iron (mg kg-1)");
            return false;
        }
        i.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_C(){
        if(ctext.getText().toString().trim().isEmpty()){
            c.setErrorEnabled(true);
            c.setError("Please Enter Copper (mg kg-1)");
            return false;
        }
        c.setErrorEnabled(false);
        return true;
    }
    public boolean checkContent_M(){
        if(mtext.getText().toString().trim().isEmpty()){
            m.setErrorEnabled(true);
            m.setError("Please Enter Manganese (mg kg-1)");
            return false;
        }
        m.setErrorEnabled(false);
        return true;
    }
   public void  openSelectFertilizer(){
       Intent intent = new Intent(this, OptimalFertilizerResultActivity.class);
       Bundle bundle = new Bundle();
       bundle.putString("Fieldname", fname);
       bundle.putString("Fieldsize", fsize);
       bundle.putString("Crop", crop);
       bundle.putString("FieldUnit", funit);
       bundle.putString("N_remark", ntextview.getText().toString());
       bundle.putString("P_remark", ptextview.getText().toString());
       bundle.putString("K_remark", ktextview.getText().toString());
       bundle.putString("S_remark", stextview.getText().toString());
       bundle.putString("Z_remark", ztextview.getText().toString());
       bundle.putString("B_remark", btextview.getText().toString());
       intent.putExtras(bundle);

       startActivity(intent);
   }
}
