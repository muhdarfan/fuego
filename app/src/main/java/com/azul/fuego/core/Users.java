package com.azul.fuego.core;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Users{
    private String UID, Fullname, Email, Roles, Gender, Phone;
    private List<String> Favourites;

    public Users() {
    }

    public Users(String uid, String fullname, String email, String roles, String gender, List<String> favourites) {
        UID = uid;
        Fullname = fullname;
        Email = email;
        Roles = roles;
        Gender = gender;
        Favourites = favourites;
    }

    public Users(String uid, String name, String email, String phone) {
        UID = uid;
        Fullname = name;
        Email = email;
        Phone = phone;
        Favourites = new ArrayList<>();
    }

    public void UpdateProfile(String name, String email, String phone) {
        Email = email;
        Fullname = name;
        Phone = phone;

        Fuego.mStore.collection("users").document(UID).set(this, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Fuego.User.updateEmail(email);
                }
            }
        });
    }

    public void save() {
        Fuego.mStore.collection("users").document(UID).set(this, SetOptions.merge());
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public List<String> getFavourites() {
        return Favourites;
    }

    public void setFavourites(List<String> favourites) {
        Favourites = favourites;
    }
}
