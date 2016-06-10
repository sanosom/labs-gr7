package organizate.compumovil.udea.edu.co.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.activities.EditorActivity;
import organizate.compumovil.udea.edu.co.managers.ContactManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements View.OnClickListener {

    ListView listView;

    ContactManager contactManager;

    FloatingActionButton floatingActionButton;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactManager = new ContactManager(view.getContext());

        listView = (ListView) view.findViewById(R.id.contact_list);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_contact);

        Cursor contacts = contactManager.read();

        List contactsList = new ArrayList();

        if (contacts.moveToFirst()) {
            do {
                contactsList.add(contacts.getString(contacts.getColumnIndex("name")));
            } while (contacts.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_list_item_1, contactsList);

        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getContext(), EditorActivity.class);

        startActivity(intent);
    }
}
