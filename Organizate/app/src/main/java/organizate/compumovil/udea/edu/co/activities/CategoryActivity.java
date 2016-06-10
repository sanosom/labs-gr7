package organizate.compumovil.udea.edu.co.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.managers.CategoryManager;

public class CategoryActivity extends AppCompatActivity {

    EditText _name;
    EditText _color;

    CategoryManager categoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryManager = new CategoryManager(getApplicationContext());

        _name = (EditText) findViewById(R.id.category_name);
        _color = (EditText) findViewById(R.id.category_color);
    }

    public void addCategory(View view) {
        String name = _name.getText().toString();
        String color = _color.getText().toString();

        categoryManager.create(name, color, false);

        finish();
    }
}
