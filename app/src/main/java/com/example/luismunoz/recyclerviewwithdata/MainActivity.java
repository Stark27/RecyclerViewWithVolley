package com.example.luismunoz.recyclerviewwithdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Photo> myDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        //This add line to each item in the list
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        loadPhotosFromWeb();
    }

    private void loadPhotosFromWeb() {
        myDataSet = new ArrayList<Photo>();
        String url = "https://jsonplaceholder.typicode.com/photos";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest aRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject p = (JSONObject) response.get(i);

                                    if (p != null) {
                                        Photo photo = new Photo();
                                        if (p.has("title"))
                                            photo.setTitle(p.getString("title"));
                                        if (p.has("url"))
                                            photo.setImageUrl(p.getString("url"));

                                        myDataSet.add(photo);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }finally {
                                    if (myDataSet.size() > 0) {
                                        refreshDataSet();
                                    }
                                }

                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "errorOnResponse");

            }
        });

        queue.add(aRequest);
    }

    private void refreshDataSet() {
        if (mRecyclerView == null) {
            return;
        }

        if (mAdapter == null) {
            mAdapter = new ListAdapter(this, myDataSet);
            mRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }


}
