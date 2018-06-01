package com.example.charliepc.newproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //private constructor so that the object creator from outside the class is avoided
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }
    //to return the instance of the class
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    //to open the database
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    //to close the database connection
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    //create method to query
    public String getDescription(String name){
        c=db.rawQuery("select cropdesc from crop where cropname = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String desccription = c.getString(0);
            buffer.append(""+desccription);
        }
        return buffer.toString();
    }

    public Cursor getCrops(){
        String query = "select cropname from crop";
        Cursor crops = db.rawQuery(query, null);
        return crops;
    }

    public Cursor getFertilizers(){
        String query = "select fertname from fertilizer";
        Cursor fertilizers = db.rawQuery(query, null);
        return fertilizers;
    }

    public Cursor getFertilizerNutrients(String fertname){
        String query = "Select n.nutrientname from nutrient n, fertnut_comp c, fertilizer f where f.fertname = '"+fertname+"' and f.fertID = c.fertID and c.nutrientcode = n.nutrientcode and c.content > 0";
        Cursor nutrients = db.rawQuery(query, null);
        return nutrients;
    }
    public Cursor getNutrients(){
        String query = "select nutrientname from nutrient";
        Cursor nutrients = db.rawQuery(query, null);
        return nutrients;
    }
    public void addFertilizer(String fertname, String fertprice){
        int price = Integer.decode(fertprice);
        String query = "insert into fertilizer (fertID, fertname, fertdesc, unitprice) values (null, '"+fertname+"', \"\", '"+ price +"')";
        db.execSQL(query);
    }

    public void addFertilizerComposition(String fertname, String nutname, String fercon){
        int content = Integer.decode(fercon);
        String query3 = "insert into fertnut_comp (fertID, nutrientcode, content) select f.fertID, n.nutrientcode,'"+ content +"' from fertilizer f, nutrient n where f.fertname = '"+fertname+"' and n.nutrientname = '"+nutname+"'";
        db.execSQL(query3);
    }

    public Cursor getRecommendedRate(int code){
        String query = "select recommendrate.nutrientcode, nutrient.nutrientname, recommendrate.value from recommendrate " +
                "inner join nutrient on recommendrate.nutrientcode=nutrient.nutrientcode and recommendrate.cropcode '"+code+"'";
        Cursor recommendedrates = db.rawQuery(query, null);
        return recommendedrates;
    }

    public Cursor getNutComposition(int code){
        String query = "select fertID, nutrientcode, content from fertnut_comp where fertID = '"+code+"' and content > 0" ;
        Cursor nutcom = db.rawQuery(query, null);
        return nutcom;
    }


    public String getRecValue(String crop, String nut){
        c=db.rawQuery("select r.value from nutrient n, recommendrate r, crop c where r.nutrientcode = n.nutrientcode " +
                        "and c.cropcode = r.cropcode and c.cropname = '"+crop+"' and n.nutrientname = '"+nut+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String val = c.getString(0);
            buffer.append(""+val);
        }
        return buffer.toString();
    }

    public String getFertPrice(String fert){
        c = db.rawQuery("select unitprice from fertilizer where fertname = '"+fert+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String val = c.getString(0);
            buffer.append(""+val);
        }
        return buffer.toString();
    }


    public String getFeedID(String fert){
        c = db.rawQuery("select fertID from fertilizer where fertname = '"+fert+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String val = c.getString(0);
            buffer.append(""+val);
        }
        return buffer.toString();
    }
    public Cursor getNutComposition(String fert){
        String query = "select fn.nutrientcode, fn.content from fertnut_comp fn, fertilizer f where " +
                "f.fertID = fn.fertID and f.fertname = '"+fert+"' and fn.content > 0";
        Cursor nutcom = db.rawQuery(query, null);
        return nutcom;
    }



}
