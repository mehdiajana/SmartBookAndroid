package com.example.smartbookpfa;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText first,last,em,pass,user;
    String nom,prenom,email,password,username;
    Button b;
    RequestQueue requestQueue;
    String insertUrl = "http://127.0.0.2:8080/api/user/save";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        b = (Button) findViewById(R.id.btn);
        first = (EditText) findViewById(R.id.nom);
        last = (EditText) findViewById(R.id.prenom);
        em = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        user = (EditText) findViewById(R.id.username);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    GetData();
                    if(nom!="" && prenom!="" && email!="" && password!=""&& username!="")
                    {
                        Toast.makeText(getApplicationContext(),"Loading... ",Toast.LENGTH_LONG).show();
                        InsertData(nom, prenom,username, email,password);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please fill the whole information",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    Log.d("dd", e.getMessage());

                }

            }
        });



    }
    public void GetData(){
        nom = first.getText().toString();
        prenom = last.getText().toString();
        email = em.getText().toString();
        password = pass.getText().toString();
        username = user.getText().toString();
    }

    public void InsertData(final String nom,final String prenom ,final String username,final String email, final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String nomHolder = nom ;
                String prenomHolder = prenom ;
                String EmailHolder = email ;
                String passwordHolder = password ;
                String usernameHolder = username;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("nom", nomHolder));
                nameValuePairs.add(new BasicNameValuePair("prenom", prenomHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("password", passwordHolder));
                nameValuePairs.add(new BasicNameValuePair("username", usernameHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(insertUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(RegisterActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(nom, prenom,username, email,password);
    }


}