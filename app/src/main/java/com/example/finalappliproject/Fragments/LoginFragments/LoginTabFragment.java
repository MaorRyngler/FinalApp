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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalappliproject.MainActivity;
import com.example.finalappliproject.R;
import com.example.finalappliproject.Utilitis.DataManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {

    TextView emailTV;
    TextView passwordTV;
    TextView forgotPasswordTV;
    Button login;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    float v = 0;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
        findViews(root);
        animation(root);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            String email,password;
            progressBar.setVisibility(View.VISIBLE);
            email = String.valueOf(emailTV.getText());
            password = String.valueOf(passwordTV.getText());

            if(TextUtils.isEmpty(email)){
                MessageOnNoSomething("email");
                return;
            }
            if(TextUtils.isEmpty(password)){
                MessageOnNoSomething("password");
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                DataManager.getInstance().setUser(mAuth.getCurrentUser());
                                Toast.makeText(LoginTabFragment.this.getContext(), "Login Success.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginTabFragment.this.getContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
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
        passwordTV.setTranslationY(300);
        forgotPasswordTV.setTranslationY(300);
        login.setTranslationY(300);

        emailTV.setAlpha(v);
        passwordTV.setAlpha(v);
        forgotPasswordTV.setAlpha(v);
        login.setAlpha(v);


        emailTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPasswordTV.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
    }

    private void findViews(ViewGroup root) {
        emailTV = root.findViewById(R.id.email);
        passwordTV = root.findViewById(R.id.password);
        forgotPasswordTV = root.findViewById(R.id.forgotPassword);
        login = root.findViewById(R.id.loginButton);
        progressBar = root.findViewById(R.id.progressBar);
    }


}
