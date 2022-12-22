package com.android.eatproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.eatproject.myrequest.Myrequest;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btn_send;
    private EditText et_pseudo, et_password;
    private ProgressBar pb_loader;
    private RequestQueue queue;
    private Myrequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_send = (Button) findViewById(R.id.btnValider);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
        final EditText et_pseudo = (EditText) findViewById(R.id.editTextPseudonyme);
        final EditText et_password = (EditText) findViewById(R.id.editTextPassword);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new Myrequest(this,queue);

        btn_send.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                final String pseudo = et_pseudo.getText().toString().trim();
                final String password = et_password.getText().toString().trim();

                sendAndRequestResponse(pseudo,password);
            }
        });
    }

    private void sendAndRequestResponse(String pseudo, String password){

        queue = Volley.newRequestQueue(this);
        StringRequest mStringRequest;


        String url = "http://quickeat.fr/eatprojet/Login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        Map<String, String> errors = new HashMap<>();

                        System.out.println("request onresponse");

                        try {
                            JSONObject json = new JSONObject(response);
                            Boolean error = json.getBoolean("error");

                            if (error == false){
                                // L'inscription s'est bien deroulé
                                //callback.onSuccess("Inscription Reussie");
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);

                            }else{
                                JSONObject messages = json.getJSONObject("message");
                                if (messages.has("other")){
                                    Toast.makeText(getApplicationContext(), messages.getString("other"), Toast.LENGTH_SHORT).show();
                                    //errors.put("password", messages.getString("password"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // error
                        Log.d("Error.Response", volleyError.toString());
                        if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                        } else if (volleyError instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                        } else if (volleyError instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                        } else if (volleyError instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (volleyError instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("pseudo",pseudo);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }
}