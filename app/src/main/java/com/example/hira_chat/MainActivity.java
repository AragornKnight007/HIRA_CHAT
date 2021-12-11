package com.example.hira_chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hira_chat.Adapters.FragmentsAdapter;
import com.example.hira_chat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());

        auth=FirebaseAuth.getInstance();
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));

        binding.tablayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater =getMenuInflater();

        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())

        {

            case R.id.settings:


                Intent Settingsintent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(Settingsintent);




//                Toast.makeText(MainActivity.this,"Setting is Clicked",Toast.LENGTH_SHORT).show();

                break;

            case R.id.logout:

                auth.signOut();

                Intent LogOutintent = new Intent(MainActivity.this , SignInActivity.class);
                startActivity(LogOutintent);

                break;


            case R.id.groupChat:


                Intent GroupChatintent = new Intent( MainActivity.this , GroupChatActivity.class);
                startActivity(GroupChatintent);




                break;


        }


        return true;



    }
}
