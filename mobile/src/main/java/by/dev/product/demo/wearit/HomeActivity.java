package by.dev.product.demo.wearit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;


public class HomeActivity extends Activity {
    final String TAG = "HomeActivity";

    WatchListener wl;
    GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();

        wl = new WatchListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        googleApiClient.connect();
        Wearable.MessageApi.addListener(googleApiClient, wl);
    }

    @Override
    protected void onStop() {

        Wearable.MessageApi.removeListener(googleApiClient, wl);
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class WatchListener implements MessageApi.MessageListener {

        @Override
        public void onMessageReceived(MessageEvent messageEvent) {
            Log.d(TAG, "msg:  " + messageEvent.getPath() + " ");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(HomeActivity.this, "Pinged!!!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
