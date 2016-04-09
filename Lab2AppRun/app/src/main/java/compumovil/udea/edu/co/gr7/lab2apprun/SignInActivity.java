package compumovil.udea.edu.co.gr7.lab2apprun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private UsersManager users;

    private Button sign_in;

    private Button register;

    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Inicio de sesión");

        users = new UsersManager(this);

        sign_in = (Button) findViewById(R.id.sign_in);
        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (users.login(username.getText().toString(), password.getText().toString()) ) {
                    // Redirect to initial view
                    Toast.makeText(v.getContext(), "Bienvenido!.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(v.getContext(), MainActivity.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "Contraseña o nombre de usuario invalido(s).",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);

                startActivity(intent);
            }
        });
    }
}
