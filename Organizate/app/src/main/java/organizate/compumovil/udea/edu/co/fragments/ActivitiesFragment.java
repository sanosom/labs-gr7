package organizate.compumovil.udea.edu.co.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import organizate.compumovil.udea.edu.co.R;
import organizate.compumovil.udea.edu.co.activities.EditorActivity;
import organizate.compumovil.udea.edu.co.managers.EventCursor;
import organizate.compumovil.udea.edu.co.managers.EventManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment implements View.OnClickListener {

    EventCursor adapter;

    ListView listView;

    EventManager eventManager;

    FloatingActionButton floatingActionButton;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        eventManager = new EventManager(view.getContext());

        listView = (ListView) view.findViewById(R.id.event_list);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_event);

        loadActivities();

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getContext(), EditorActivity.class);

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        loadActivities();
    }

    public void loadActivities() {
        Cursor activities = eventManager.read();

        adapter = new EventCursor(this.getContext(), activities, 0);

        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(this);
    }
}
