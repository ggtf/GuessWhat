package com.ggtf.guesswhat;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.TextView;
import com.ggtf.guesswhat.Loaders.GuessLoader;
import com.ggtf.guesswhat.models.Enigma;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {

    private TextView title;
    private TextView answer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        title = (TextView) findViewById(R.id.title);
        answer = (TextView) findViewById(R.id.answer);
        LoaderManager loaderManager = getSupportLoaderManager();
        Bundle args = new Bundle();
        loaderManager.restartLoader(100,args,this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (args != null){
            loader = new GuessLoader(this,-1);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null){
            try {
                JSONObject jsonObject = new JSONObject(data);
                Enigma enigma = new Enigma();
                enigma.parseJson(jsonObject);
                title.setText(enigma.getTitle());
                answer.setText(enigma.getAnswer());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
