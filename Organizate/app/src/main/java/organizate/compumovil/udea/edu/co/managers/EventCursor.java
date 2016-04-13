package organizate.compumovil.udea.edu.co.managers;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import organizate.compumovil.udea.edu.co.R;

/**
 * Created by santiago on 4/12/16.
 */
public class EventCursor extends CursorAdapter {

    public EventCursor(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.event_name);
        TextView date = (TextView) view.findViewById(R.id.event_date);
        ImageView alarm = (ImageView) view.findViewById(R.id.event_alarm);

        String _name = cursor.getString(cursor.getColumnIndexOrThrow(EventManager.CN_NAME));
        Integer _date = cursor.getInt(cursor.getColumnIndexOrThrow(EventManager.CN_DATE));

        if (_date == null) {
            alarm.setImageResource(R.drawable.ic_alarm_add);
        } else if (_date > 0) {
            alarm.setImageResource(R.drawable.ic_alarm_on);
        } else {
            alarm.setImageResource(R.drawable.ic_alarm_off);
        }

        name.setText(_name);

        if (_date != null || _date > 0) {
            date.setText(_date.toString());
        }
    }

}
