package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private UsersManager users;

    private Button sign_in;

    private Button register;

    private EditText email;

    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Registro de usuario");

        users = new UsersManager(this);

        sign_in = (Button) findViewById(R.id.sign_in);
        register = (Button) findViewById(R.id.register);

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.create(email.getText().toString(), username.getText().toString(),
                        password.getText().toString(), true);

                // Redirect to initial view
                Toast.makeText(v.getContext(), "Bienvenido!.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignInActivity.class);

                startActivity(intent);
            }
        });
    }
}
