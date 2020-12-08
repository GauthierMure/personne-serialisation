package com.example.personne_serialisation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText Nometx;
    private EditText Prenometx;
    private EditText Ageetx;

    private Button Enregisterbtn;
    private Button Chargerbtn;
    private Button Effacerbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Nometx = findViewById(R.id.editTextTextPersonNom);
        Prenometx = findViewById(R.id.editTextTextPersonPrenom);
        Ageetx = findViewById(R.id.editTextTextPersonAge);

        Enregisterbtn = findViewById(R.id.buttonEnregistrer);
        Chargerbtn = findViewById(R.id.buttonCharger);
        Effacerbtn = findViewById(R.id.buttonEffacer);
    }

    public void Charger(View view) {
        try {
            FileInputStream file = this.openFileInput("LaPersonne.dta");
            ObjectInputStream ois = new ObjectInputStream(file);
            Personne UnePersonne = (Personne) ois.readObject();
            Log.i("Message", UnePersonne.toString());
            Nometx.setText(UnePersonne.getNom());
            Prenometx.setText(UnePersonne.getPrenom());
            Ageetx.setText(String.valueOf(UnePersonne.getAge()));
            ois.close();
            file.close();
        }catch (IOException e){
            Log.i("Erreur",e.getMessage());
        }
        catch (ClassNotFoundException e){
            Log.i("Erreur",e.getMessage());
        }
    }

    public void Effacer(View view) {
        Nometx.setText("");
        Prenometx.setText("");
        Ageetx.setText("");
    }

    public void Enregistrer(View view) {
        try {
            FileOutputStream file = this.openFileOutput("LaPersonne.dta", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            Personne UnePersonne = new Personne(Nometx.getText().toString(),Prenometx.getText().toString(),Integer.parseInt(Ageetx.getText().toString()));
            oos.writeObject(UnePersonne);
            oos.close();
            file.close();
        }catch (IOException e){
            Log.i("Erreur",e.getMessage());
        }

    }
}