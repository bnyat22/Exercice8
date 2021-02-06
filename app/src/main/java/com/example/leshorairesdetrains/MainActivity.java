package com.example.leshorairesdetrains;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


String[] ville = {"montpellier" ,"paris", "strasbourg", "nice"};
String[] montpellier_strasbourg = {"8:00h" ,"10:00h", "11:50h", "2:34h"};
String[] montpellier_paris = {"8:00h" ,"11:00h", "12:30h", "17:30h"};
String[] montpellier_nice = {"9:00h" ,"12:00h", "14:30h", "18:30h"};
String[] paris_montpellier = {"9:15h" ,"13:00h", "15:30h", "19:30h"};
String[] paris_strasbourg = {"9:10h" ,"13:10h", "15:00h", "19:15h"};
String[] paris_nice = {"9:17h" ,"13:17h", "15:35h", "17:18h"};
String[] strasbourg_montpellier = {"7:20h" ,"17:17h", "18:35h", "20:18h"};
String[] strasbourg_paris = {"8:20h" ,"9:17h", "12:35h", "15:18h"};
String[] strasbourg_nice = {"9:20h" ,"11:17h", "14:00h", "19:18h"};
String[] nice_montpellier = {"8:00h" ,"10:20h", "14:00h", "16:18h"};
String[] nice_paris = {"7:00h" ,"10:20h", "14:00h", "16:18h"};
String[] nice_strasbourg = {"9:00h" ,"10:40h", "14:50h", "19:30h"};

Spinner spinner1 , spinner2;
Button search ;
ListView listView;
Map<String , String[]> trajet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        search = (Button) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_dropdown_item,ville);
trajet = new HashMap<>();
trajet.put("montpellier_paris" , montpellier_paris);
trajet.put("montpellier_strasbourg" , montpellier_strasbourg);
trajet.put("montpellier_nice" , montpellier_nice);
trajet.put("nice_paris" , nice_paris);
trajet.put("nice_montpellier" , nice_montpellier);
trajet.put("nice_strasbourg" , nice_strasbourg);
trajet.put("strasbourg_paris" , strasbourg_paris);
trajet.put("strasbourg_montpellier" , strasbourg_montpellier);
trajet.put("strasbourg_nice" , strasbourg_nice);
trajet.put("paris_strasbourg" , paris_strasbourg);
trajet.put("paris_nice" , paris_nice);
trajet.put("paris_montpellier" , paris_montpellier);


        final Map<String , ArrayAdapter> adapterMap = addAdapters(trajet);





        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String s1 = spinner1.getSelectedItem().toString();
                String s2 = spinner2.getSelectedItem().toString();

                for (String t : trajet.keySet())
                {
                    String[] t1 =t.toString().split("_");

                    if (t1[0].equals(s1) && t1[1].equals(s2))
                    {

                       listView.setAdapter(adapterMap.get(t));
                    }
                }

            }
        });
    }


    private Map<String , ArrayAdapter> addAdapters( Map<String , String[]> trajets)
    {
        Map<String , ArrayAdapter> adapters = new HashMap<>();
        for (String t:trajets.keySet()) {

            ArrayAdapter add = new ArrayAdapter<String>(this,
                    R.layout.support_simple_spinner_dropdown_item, trajets.get(t));

            adapters.put(t,add);
        }
        return adapters;
    }
}
