package compumovil.udea.edu.co.gr7.lab2apprun;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements View.OnClickListener {

    private EventsManager events;

    FloatingActionButton button;

    ListView listView;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        events = new EventsManager(view.getContext());

        button = (FloatingActionButton) view.findViewById(R.id.add_event);

        listView = (ListView) view.findViewById(R.id.list_events);

        button.setOnClickListener(this);

        Cursor cursor = events.read();

        List eventsList = new ArrayList();

        if (cursor.moveToFirst()) {
            do {
                eventsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_list_item_1, eventsList);

        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), EventsActivity.class);

        startActivity(intent);
    }
}
