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
import organizate.compumovil.udea.edu.co.managers.CategoryManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    ListView listView;

    CategoryManager categoryManager;

    FloatingActionButton floatingActionButton;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        categoryManager = new CategoryManager(view.getContext());

        listView = (ListView) view.findViewById(R.id.category_list);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_category);

        Cursor categories = categoryManager.read();

        List categoriesList = new ArrayList();

        if (categories.moveToFirst()) {
            do {
                categoriesList.add(categories.getString(categories.getColumnIndex("name")));
            } while (categories.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_list_item_1, categoriesList);

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
