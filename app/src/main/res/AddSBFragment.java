package com.example.smartbookpfa.ui.AddSB;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.smartbookpfa.RegisterActivity;
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

import com.example.smartbookpfa.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSBFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String titre,adresse,telephone,linkfb,linktwitter,linkinsta;
    EditText tit,adr,tel,fb,insta,twitter;
    Button b;
    RequestQueue requestQueue;
    String insertUrl = "http://127.0.0.2:8080/api/user/save";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   // tit = (EditText) findViewById(R.id.titresb);



    public AddSBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSBFragment newInstance(String param1, String param2) {
        AddSBFragment fragment = new AddSBFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//testcomit
        fragment.setArguments(args);
        return fragment;
        //dsf
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_sb, container,false);
        tit = (EditText) view.findViewById(R.id.titresb);
        adr = (EditText) view.findViewById(R.id.adresse);
        tel = (EditText)view.findViewById(R.id.telephone);
        fb = (EditText) view.findViewById(R.id.fb);
        insta = (EditText) view.findViewById(R.id.insta);
        twitter = (EditText) view.findViewById(R.id.twitter);
        b = (Button) view.findViewById(R.id.btn);


        return view;
    }
    public void GetData()
    {
        titre = tit.getText().toString();
        adresse = adr.getText().toString();
        telephone = tel.getText().toString();
        linkfb = fb.getText().toString();
        linkinsta = insta.getText().toString();
        linktwitter = twitter.getText().toString();
    }
    public void InsertData(final String titre,final String adresse ,final String telephone,final String fb, final String insta, final String twitter){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String titreHolder = titre ;
                String adresseHolder = adresse ;
                String telephoneHolder = telephone ;
                String fbHolder = fb ;
                String twitterHolder = twitter;
                String instaHolder = insta;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("titre", titreHolder));
                nameValuePairs.add(new BasicNameValuePair("adresse", adresseHolder));
                nameValuePairs.add(new BasicNameValuePair("telephone", telephoneHolder));
                nameValuePairs.add(new BasicNameValuePair("facebook", fbHolder));
                nameValuePairs.add(new BasicNameValuePair("twitter", twitterHolder));
                nameValuePairs.add(new BasicNameValuePair("instagram", instaHolder));

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

               // Toast.makeText(AddSBFragment.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(titre, adresse,telephone, linkfb,linkinsta, linktwitter);
    }
}
