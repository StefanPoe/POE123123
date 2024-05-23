package com.example.coachit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coachit.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFrag(new NotificationFragment_Player());


        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.notifications2) {
                replaceFrag(new NotificationFragment_Player());
            }else if (itemId == R.id.profile2){
                replaceFrag(new ProfileFragment_Player());
            }else if (itemId == R.id.calendar){
                replaceFrag(new Calendar_Fragment_Player());
            }

            return true;
        });




    }

    private void replaceFrag(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2,fragment);
        fragmentTransaction.commit();
    }

    public void logoutMain(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}