package organizate.compumovil.udea.edu.co.managers;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import organizate.compumovil.udea.edu.co.R;

/**
 * Created by santiago on 4/12/16.
 */
public class EventCursor extends CursorAdapter {

    public EventCursor(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    SimpleDateFormat format = new SimpleDateFormat("dd MMM - hh:mm a");

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view;

        long time = cursor.getLong(cursor.getColumnIndexOrThrow(EventManager.CN_DATE));

        if (time == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.event_item_single, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        }

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        long now = new Date().getTime();

        TextView _name = (TextView) view.findViewById(R.id.event_name);
        TextView _date = (TextView) view.findViewById(R.id.event_date);
        ImageView _alarm = (ImageView) view.findViewById(R.id.event_alarm);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(EventManager.CN_NAME));

        long time = cursor.getLong(cursor.getColumnIndexOrThrow(EventManager.CN_DATE)) * 1000;

        Date date = new Date(time);

        _name.setText(name);

        if (time == 0) {
            _alarm.setImageResource(R.drawable.ic_alarm_add);
        } else if (time > 0) {
            _alarm.setImageResource(R.drawable.ic_alarm_on);

            if (_date != null) {
                if (time > now) {
                    _date.setText(format.format(date));
                } else {
                    _date.setText(DateUtils.formatDateTime(context, time, DateUtils.FORMAT_ABBREV_TIME));
                    _alarm.setImageResource(R.drawable.ic_alarm_off);
                }
            }
        }
    }

}
