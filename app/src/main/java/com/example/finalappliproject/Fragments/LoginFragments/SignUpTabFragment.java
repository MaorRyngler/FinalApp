package com.example.finalappliproject.Fragments.LoginFragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.finalappliproject.MainActivity;
import com.example.finalappliproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpTabFragment extends Fragment {

    TextView emailTV;
    TextView passwordTV;
    TextView phoneNumTV;
    TextView confirmPasswordTV;
    Button signUp;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    float v = 0;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment,container,false);
        findViews(root);
        animation(root);

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(v -> {
            String email,password,confirmPassword,phoneNum;
            progressBar.setVisibility(View.VISIBLE);
            email = String.valueOf(emailTV.getText());
            password = String.valueOf(passwordTV.getText());
            confirmPassword = String.valueOf(confirmPasswordTV.getText());
            phoneNum = String.valueOf(phoneNumTV.getText());


            if(TextUtils.isEmpty(email)){
                MessageOnNoSomething("email");
                return;
            }
            if(TextUtils.isEmpty(password)){
                MessageOnNoSomething("password");
                return;
            }
            if(TextUtils.isEmpty(phoneNum)){
                MessageOnNoSomething("phone Number");
                return;
            }
            if(TextUtils.isEmpty(confirmPassword)){
                MessageOnNoSomething("confirm Password");
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpTabFragment.this.getContext(), "Account Created.", Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpTabFragment.this.getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });



        return root;
    }

    private void MessageOnNoSomething(String something){
        Toast.makeText(getContext(),"Please Put a "+ something,Toast.LENGTH_SHORT).show();
    }


    private void animation(ViewGroup root) {
        emailTV.setTranslationY(300);
        phoneNumTV.setTranslationY(300);
        passwordTV.setTranslationY(300);
        confirmPasswordTV.setTranslationY(300);
        signUp.setTranslationY(300);

        emailTV.setAlpha(v);
        phoneNumTV.setAlpha(v);
        passwordTV.setAlpha(v);
        confirmPasswordTV.setAlpha(v);
        signUp.setAlpha(v);


        emailTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        phoneNumTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        passwordTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        confirmPasswordTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        signUp.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();
    }

    private void findViews(ViewGroup root) {
        emailTV = root.findViewById(R.id.email);
        phoneNumTV = root.findViewById(R.id.phoneNumber);
        passwordTV = root.findViewById(R.id.password);
        confirmPasswordTV = root.findViewById(R.id.confirmPassword);
        signUp = root.findViewById(R.id.signUp);
        progressBar = root.findViewById(R.id.progressBar);
    }


}
