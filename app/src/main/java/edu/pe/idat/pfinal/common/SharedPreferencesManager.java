package edu.pe.idat.pfinal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import edu.pe.idat.pfinal.model.Carrito;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";
    private static final String LIST_KEY ="list key" ;

    public SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences(){
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE,
                MyApp.MODE_PRIVATE);
    }


    //almacenar valores

    public static  void setSomeIntValue(String nombrePropiedad, int valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(nombrePropiedad, valor);
        editor.commit();
    }
    //devolver valores
    public static  int getSomeIntValue(String nombrePropiedad){
        return  getSharedPreferences().getInt(nombrePropiedad,0);
    }





    //tO ADD CARTO LIST

    public static void writeListInPreference(Context context, List<Carrito> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static List<Carrito> readListFromPreference(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = preferences.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Carrito>>(){}.getType();
        List<Carrito> list = gson.fromJson(jsonString,type);
        return list;
    }
}
