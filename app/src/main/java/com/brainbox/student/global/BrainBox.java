package com.brainbox.student.global;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import com.brainbox.student.dto.SessionDTO;
import com.brainbox.student.dto.StudentDTO;
import com.google.gson.Gson;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class BrainBox {
    public static Fragment currentFragment;

    public static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences;
    }


    public static SharedPreferences.Editor getSharedEditor(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.edit();
    }

    public static Boolean isLogin(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        return isLogin;
    }

    public static void setSessionDTO(Context context, SessionDTO sessionDTO){
        SharedPreferences.Editor editor = getSharedEditor(context);
        Gson gson = new Gson();
        editor.putString("session", gson.toJson(sessionDTO));
        editor.commit();
    }


    public static SessionDTO getSessionDTO(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Gson gson = new Gson();
        SessionDTO sessionDTO = gson.fromJson(sharedPreferences.getString("session", null), SessionDTO.class);
        return sessionDTO;
    }


    public static void setStudentDTO(Context context, StudentDTO studentDTO){
        SharedPreferences.Editor editor = getSharedEditor(context);

        Gson gson = new Gson();
        SessionDTO sessionDTO = getSessionDTO(context);
        sessionDTO.setStudentDTO(studentDTO);

        editor.putString("session", gson.toJson(sessionDTO));
        editor.commit();
    }


    public static StudentDTO getStudentDTO(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Gson gson = new Gson();
        SessionDTO sessionDTO = gson.fromJson(sharedPreferences.getString("session", null), SessionDTO.class);
        return sessionDTO.getStudentDTO();
    }

    public static void login(Context context){
        SharedPreferences.Editor editor = getSharedEditor(context);
        editor.putBoolean("isLogin", true);
        editor.commit();
    }


    public static void logout(Context context){
        SharedPreferences.Editor editor = getSharedEditor(context);
        editor.putBoolean("isLogin", false);
        editor.commit();
    }



}
