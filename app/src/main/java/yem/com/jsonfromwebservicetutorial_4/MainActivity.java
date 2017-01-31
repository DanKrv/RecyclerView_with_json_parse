package yem.com.jsonfromwebservicetutorial_4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    View ChildView ;
    int RecyclerViewItemPosition ;
    List<YoutubeData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JSONAsync().execute();



    }

    private class JSONAsync extends AsyncTask<Void, Void, Void>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            ProgressDialog.show(MainActivity.this, null, "Loading data...", true, false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONArray jsonArray = new JSONhttp().getJSONfromUrl();

            JSONParser jsonParser = new JSONParser();
            dataList = jsonParser.parse(jsonArray);
            String listTitle = jsonParser.listTitle;


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            recyclerview = (RecyclerView)findViewById(R.id.recyclerview1);

            RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

            recyclerview.setLayoutManager(RecyclerViewLayoutManager);

            progressDialog.dismiss();

            RecyclerView.Adapter adapter = new RecyclerViewAdapter(dataList);

            recyclerview.setAdapter(adapter);

            recyclerview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                    @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                        return true;
                    }

                });


                @Override
                public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                    ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                        RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);

                        Toast.makeText(MainActivity.this, (CharSequence) dataList.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                    }

                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(dataList);
            recyclerview.setAdapter(recyclerViewAdapter);

        }
    }
}