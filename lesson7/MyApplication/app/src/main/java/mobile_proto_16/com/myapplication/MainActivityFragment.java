package mobile_proto_16.com.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.button) Button button;
    @BindView(R.id.input) EditText input;
    @BindView(R.id.price) TextView price;

    private final String TAG = this.getClass().getName();

    private Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            // YOUR CODE HERE. DO SOMETHING WHEN A RESPONSE COMES IN.
            // Hint: remove the first three characters, parse the response into a JSONArray,
            // and pass it into your extractPriceFromJSON() function.
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "A VolleyError occurred.");
            error.printStackTrace();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        final Context c = this.getContext();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                format the ticker from text box into query url
                String ticker = buildSearchURL(input.getText().toString());

                // Create a StringRequest using the URL and the listeners declared above.
                // Add the request to your RequestQueue from your MySingleton class

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, ticker,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
//                                    convert the response from string to json
                                    JSONObject jsonObject = new JSONObject(response);
//                                    extract current price
                                    price.setText(jsonObject.getString("l_fix"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                });
//              create an instance of singleton and add the request to request queue
                MySingleton singleton = MySingleton.getInstance(c);
                singleton.addToRequestQueue(stringRequest);
            }
        });

        return view;
    }
//format the company ticker to a query url
    private String buildSearchURL(String companyTicker) {
        Uri.Builder builder = new Uri.Builder();
        Log.d("company", companyTicker);
        builder.scheme("http")
                .authority("finance.google.com")
                .appendPath("finance")
                .appendPath("info")
                .appendQueryParameter("client","iq")
                .appendQueryParameter("q",companyTicker);
        Log.d("url",builder.build().toString() );
        return builder.build().toString();
    }

}
