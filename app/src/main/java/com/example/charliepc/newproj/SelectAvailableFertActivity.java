package com.example.charliepc.newproj;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SelectAvailableFertActivity extends AppCompatActivity implements AddFertilizerDialog.AddfertilizerDialogInterface{
    ArrayList<String> selectedItems = new ArrayList<>();
    public TextView textview;
    String fname, fsize, crop, funit, n, p, k, s, z, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_avail_fert);
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

        final ListView ch1 = findViewById(R.id.fertchecklist_id);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor fertilizers = databaseAccess.getFertilizers();

        final ArrayList<String> items = new ArrayList<String>();

        while (fertilizers.moveToNext()){
            items.add(fertilizers.getString(0));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.fertilizer_id, items);
        ch1.setAdapter(adapter);
        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem);
                }
                else
                    selectedItems.add(selectedItem);
            }
        });
        final CheckedTextView select_all = findViewById(R.id.selectall_id);

        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               select_all.toggle();
               if(select_all.isChecked()){
                   for(int i=0; i<ch1.getAdapter().getCount(); i++){
                       ch1.setItemChecked(i, true);
                   }
                   for(String item:items){
                       if(!selectedItems.contains(item)){
                           selectedItems.add(item);
                       }
                   }
               }
               else{
                   for(int i=0; i<ch1.getAdapter().getCount(); i++){
                       ch1.setItemChecked(i, false);
                   }
                   for(String item:items){
                       if(selectedItems.contains(item)){
                           selectedItems.remove(item);
                       }
                   }
               }
            }
        });
        textview = findViewById(R.id.textView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fertilizer_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.addfert_id:
                openAddFertilizerDialogue();
                return true;
            case  R.id.checkfert_id:
                Toast.makeText(this, "Check fertilizer!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Done_id:
                showSelectedItems();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSelectedItems(){
        ArrayList<String> fercontents = new ArrayList<>();
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String warning = "";
        Boolean flag = false;
        for(String item:selectedItems){
            Cursor fertilizers = databaseAccess.getFertilizerNutrients(item);
            while (fertilizers.moveToNext()){
                fercontents.add(fertilizers.getString(0));
            }
        }
        if(!fercontents.contains("N")){
            warning = " N";
        }
        else if(!fercontents.contains("P")){
            warning = " P";
        }
        else if(!fercontents.contains("K")){
            warning = " K";
        }
        else if(!fercontents.contains("S")){
            warning = " S";
        }
        else if(!fercontents.contains("Zn")){
            warning = " Zn";
        }
        else if(!fercontents.contains("B")){
            warning = " B";
        }
        else{
            flag = true;
        }

        if(flag){
            String message = "";
            for(String item:selectedItems) {

                //Toast.makeText(getApplicationContext(),message + " " +item , Toast.LENGTH_LONG).show();

            }
            openRecommendedFertilizer();
        }
        else
            Toast.makeText(this, "You need to select \n"+ warning + " Fertilizers", Toast.LENGTH_LONG).show();
    }

    public  void openRecommendedFertilizer(){
        Intent intent = new Intent(this, OptimalFertilizerResultActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("Fieldname", fname);
        bundle.putString("Fieldsize", fsize);
        bundle.putString("Crop", crop);
        bundle.putString("FieldUnit", funit);
        bundle.putString("N_remark", n);
        bundle.putString("P_remark", p);
        bundle.putString("K_remark", k);
        bundle.putString("S_remark", s);
        bundle.putString("Z_remark", z);
        bundle.putString("B_remark", b);
        bundle.putStringArrayList("selFertilizers", selectedItems);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void openAddFertilizerDialogue(){
        AddFertilizerDialog addFertilizerDialog = new AddFertilizerDialog();
        addFertilizerDialog.show(getSupportFragmentManager(), "Add Fertilizer");
    }
    @Override
    public void applyTexts(String fertname, String fertprice, String fertnut, String fertnut2, String fertnut3, String fertnut4, String fercon, String fercon2, String fercon3, String fercon4){

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.addFertilizer(fertname, fertprice);
        databaseAccess.addFertilizerComposition(fertname, fertnut, fercon);

        databaseAccess.close();
        finish();
        startActivity(getIntent());
    }
}