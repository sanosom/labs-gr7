package compumovil.udea.edu.co.gr7.lab2apprun;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private UsersManager users;

    private TextView email;

    private TextView username;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        users = new UsersManager(view.getContext());

        email = (TextView) view.findViewById(R.id.email);
        username = (TextView) view.findViewById(R.id.username);

        if (users.is_logged()) {
            // Load normal activities
            Cursor cursor = users.read();

            cursor.moveToFirst();

            String _email = cursor.getString(1);
            String _username = cursor.getString(2);

            email.setText(_email);
            username.setText(_username);
        }

        return view;
    }

}
