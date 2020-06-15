package com.mapp.problemssolutions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapp.problemssolutions.problemsActivities.RotateArrayActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static MyDatabase myDatabase;
    Button btnRefresh;
    RecyclerView rvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData=(RecyclerView) findViewById(R.id.rvData);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(this));

        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,
                "infodb").allowMainThreadQueries().build();

        getData();

        btnRefresh=(Button)findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int uid=1;
                String title="Rotate array";
                String site="LeetCode";
                String level="Easy";
                String url="www.google.com";

                MyDataList myDataList=new MyDataList();
                myDataList.setId(uid);
                myDataList.setTitle(title);
                myDataList.setSite(site);
                myDataList.setLevel(level);
                myDataList.setUrl(url);

                myDatabase.myDao().addData(myDataList);
            }
        });

    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void, List<MyDataList>> implements ItemClickListener{

            @Override
            protected List<MyDataList> doInBackground(Void... voids) {
                List<MyDataList> myDataLists=myDatabase.myDao().getMyData();
                return myDataLists;
            }

            @Override
            protected void onPostExecute(List<MyDataList> myDataList) {
                AdapterList adapter=new AdapterList(myDataList, this);
                rvData.setAdapter(adapter);
                super.onPostExecute(myDataList);
            }

            @Override
            public void onItemClick(MyDataList myData) {
                switch (myData.getId()){
                    case 1:
                        Intent intent = new Intent(MainActivity.this, RotateArrayActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("id",myData.getId());
                        b.putString("title",myData.getTitle());
                        b.putString("site",myData.getSite());
                        b.putString("level",myData.getLevel());
                        b.putString("url",myData.getUrl());
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        }

        GetData gd=new GetData();
        gd.execute();
    }

}
