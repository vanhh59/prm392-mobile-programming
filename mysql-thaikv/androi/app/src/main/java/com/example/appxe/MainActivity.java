package com.example.appxe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstvdsxe;
    XeAdapter xeadapter;
    ArrayList<Xe> mangxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstvdsxe=(ListView) findViewById(R.id.listViewDsxe);
        mangxe=new ArrayList<>();
        ReadJSON("http://10.0.0.139:88/androi/getdata_xe.php");
        xeadapter=new XeAdapter(MainActivity.this,R.layout.dongxe,mangxe);
        lstvdsxe.setAdapter(xeadapter);
    }
    private void ReadJSON(String url)
    {
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try{
                                JSONObject object = response.getJSONObject(i);
                                mangxe.add(new Xe(object.getString("tenxe"),
                                        object.getString("hangsx"),
                                        object.getInt("namsx"),object.getString("hinh")));
                            }catch (JSONException e){e.printStackTrace();}}
                        xeadapter.notifyDataSetChanged();
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}