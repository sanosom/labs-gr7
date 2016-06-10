package organizate.compumovil.udea.edu.co.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import organizate.compumovil.udea.edu.co.R;

public class LoginActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private int action = 0;

    private EditText _email;
    private EditText _password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _email = (EditText) findViewById(R.id.email);
        _password = (EditText) findViewById(R.id.password);

        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            if (action == 1) {
                Toast.makeText(this, getString(R.string.signin_failed), Toast.LENGTH_LONG)
                        .show();
            } else if (action == 2) {
                Toast.makeText(this, getString(R.string.signup_failed), Toast.LENGTH_LONG)
                        .show();
            }

            action = 0;
        } else {
            action = 0;
            FirebaseAuth.getInstance().removeAuthStateListener(this);
            finishActivity(0);
        }


    }

    public void signin(View view) {
        String email = _email.getText().toString();
        String password = _password.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.email_password_required), Toast.LENGTH_LONG)
                    .show();
        } else {
            action = 1;

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.signInWithEmailAndPassword(email, password);
        }
    }

    public void signup(View view) {
        String email = _email.getText().toString();
        String password = _password.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.email_password_required), Toast.LENGTH_LONG)
                    .show();
        } else {
            action = 2;

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.createUserWithEmailAndPassword(email, password);
        }
    }
}
