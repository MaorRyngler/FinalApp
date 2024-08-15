package com.example.finalappliproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.finalappliproject.Fragments.HomeFragment;
import com.example.finalappliproject.Fragments.NavFragments.FavoriteFragment;
import com.example.finalappliproject.Fragments.NavFragments.MainFragment;
import com.example.finalappliproject.Fragments.NavFragments.UserInfoFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FrameLayout frameLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    LinearLayout rightCheckboxesLayout;
    LinearLayout leftCheckboxesLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        /*-----------------Hooks----------------------*/
        findViews();

        /*-----------------Navigation Drawer Menu----------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Begin the transaction
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new MainFragment())
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchBetweenAdapters(item);
                return false;
            }
        });

    }

    private void switchBetweenAdapters(@NonNull MenuItem item){
        if(item.getItemId() == R.id.nav_home){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new MainFragment())
                    .commit();
        }
        else if(item.getItemId() == R.id.nav_favorite){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new HomeFragment())
                    .commit();
        }
        else if(item.getItemId() == R.id.nav_info){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new UserInfoFragment())
                    .commit();
        }
        else if(item.getItemId() == R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void findViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frame_layout);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //        /*-----------------LogOut----------------------*/
//        firebaseAuth = FirebaseAuth.getInstance();
//        user = firebaseAuth.getCurrentUser();
//        if (user == null) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            info.setText(user.getEmail());
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

}

//public class MainActivity extends AppCompatActivity {
//    private List<CheckBox> selectedCheckboxes = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        LinearLayout leftCheckboxesLayout = findViewById(R.id.leftCheckboxesLayout);
//        LinearLayout rightCheckboxesLayout = findViewById(R.id.rightCheckboxesLayout);
//
//        // Set the listener for checkbox selection changes
//        CompoundButton.OnCheckedChangeListener checkboxListener = new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                CheckBox checkbox = (CheckBox) buttonView;
//
//                if (isChecked) {
//                    selectedCheckboxes.add(checkbox);
//                } else {
//                    selectedCheckboxes.remove(checkbox);
//                }
//            }
//        };
//
//        // Add checkbox listeners for the left checkboxes
//        for (int i = 0; i < leftCheckboxesLayout.getChildCount(); i++) {
//            CheckBox checkbox = (CheckBox) leftCheckboxesLayout.getChildAt(i);
//            checkbox.setOnCheckedChangeListener(checkboxListener);
//        }
//
//        // Add checkbox listeners for the right checkboxes
//        for (int i = 0; i < rightCheckboxesLayout.getChildCount(); i++) {
//            CheckBox checkbox = (CheckBox) rightCheckboxesLayout.getChildAt(i);
//            checkbox.setOnCheckedChangeListener(checkboxListener);
//        }
//
//        // Handle saving the selected choices and navigating to a different fragment
//        Button continueButton = findViewById(R.id.continueButton);
//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle saving the selected choices and navigate to a different fragment
//                List<String> selectedChoices = new ArrayList<>();
//                for (CheckBox checkbox : selectedCheckboxes) {
//                    selectedChoices.add(checkbox.getText().toString());
//                }
//
//                // Pass the selected choices to the next fragment
//                // You can use Intent extras or a ViewModel to communicate the data to the next fragment
//                // Replace "NextFragment" with the appropriate fragment class
//                NextFragment fragment = NextFragment.newInstance(selectedChoices);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainer, fragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//    }
//}

