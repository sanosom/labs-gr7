package co.edu.udea.compumovil.lab4weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import co.edu.udea.compumovil.lab4weather.POJO.Model;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    TextView city, press, status, humidity;

    ImageView image;

    AutoCompleteTextView query;

    String key = "8cf7cf9202e00e5a08c2bfdab7eea988";

    String url = "http://api.openweathermap.org/data/2.5";

    String url_image = "http://openweathermap.org/img/w/";

    RestInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (TextView) findViewById(R.id.txt_city);
        press = (TextView) findViewById(R.id.txt_press);
        status = (TextView) findViewById(R.id.txt_status);
        humidity = (TextView) findViewById(R.id.txt_humidity);

        image = (ImageView) findViewById(R.id.image);

        query = (AutoCompleteTextView) findViewById(R.id.txt_query);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

        restInterface = adapter.create(RestInterface.class);

        ArrayAdapter<CharSequence> capitals = ArrayAdapter.createFromResource(this,
                R.array.capitals, android.R.layout.simple_spinner_dropdown_item);

        query.setAdapter(capitals);
    }

    public void getWeatherReport(View view) {
        restInterface.getWheatherReport(query.getText().toString(), key, "es", new Callback<Model>() {
            @Override
            public void success(Model model, Response response) {
                city.setText(String.format(getString(R.string.city_text), model.getName()));
                press.setText(String.format(getString(R.string.pressure_text), model.getMain().getPressure().toString()));
                status.setText(String.format(getString(R.string.status_text), model.getWeather().get(0).getDescription()));
                humidity.setText(String.format(getString(R.string.humidity_text), model.getMain().getHumidity().toString()));

                String code = model.getWeather().get(0).getIcon();

                ImageDownloader imageDownloader = new ImageDownloader();

                imageDownloader.execute(code);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection con = null;
            Bitmap img = null;
            InputStream is = null;

            try {
                con = (HttpURLConnection) (new URL(url_image + params[0] + ".png")).openConnection();

                con.setRequestMethod("GET");
                con.connect();

                is = con.getInputStream();

                img = BitmapFactory.decodeStream(new BufferedInputStream(is));
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                try {
                    if (con != null) {
                        con.disconnect();
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            return img;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, true));
            }
        }
    }
}
