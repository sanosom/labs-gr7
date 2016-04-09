package sqlite.gr7.compumovil.udea.edu.co.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Cursor cursor;
    private DataBaseManager manager;
    private SimpleCursorAdapter adapter;

    private ListView contacts;
    private EditText search;
    private ImageButton search_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);

        search = (EditText) findViewById(R.id.search);
        contacts = (ListView) findViewById(R.id.contacts);
        search_button = (ImageButton) findViewById(R.id.search_button);

        search_button.setOnClickListener(this);

        manager.create("Jaime", "111111111");
        manager.create_string("Luis", "4444444444");
        manager.create("Sebastian", "2222222222");
        manager.delete("Luis");
        manager.update_phone("Ana", "3333333333");

        String[] from = new String[]{manager.CN_NAME, manager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        cursor = manager.load_contacts();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);

        contacts.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_button) {
            Cursor c = manager.find_contact(search.getText().toString());

            adapter.changeCursor(c);
        }
    }
}
