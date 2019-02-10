package com.example.star3.takehomeexam;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.common.GooglePlayServicesUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class Test1Fragment extends Fragment {
    OnListViewClickedListener mCallback2;
    String name_arr[];
    String contents_arr[];
    double x_val[];
    double y_val[];
    int i = 0;

    public Test1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test1, container, false);

        ListView listview = (ListView) view.findViewById(R.id.ListView);

        name_arr = new String[100];
        contents_arr = new String[100];
        x_val = new double[100];
        y_val = new double[100];

        ListViewAdapter listviewadapter = new ListViewAdapter();
        listview.setAdapter(listviewadapter);

        //SQLite Helper 를 초기화 한다.
        LocationDB sqh = new LocationDB(getActivity());

        // 읽고 쓸 수 있는 데이터베이스를 가져온다.
        SQLiteDatabase sqdb = sqh.getWritableDatabase();

        Cursor c = sqdb.query(LocationDB.TABLE_NAME, new String[]{LocationDB.NAME,
                LocationDB.CONTENTS, LocationDB.POS_X, LocationDB.POS_Y}, null, null, null, null, null);

        while (c.moveToNext()) {
            // 컬럼 인덱스와 해당 컬럼의 값을 얻어 온다.
            name_arr[i] = c.getString(c.getColumnIndex(LocationDB.NAME));
            contents_arr[i] = c.getString(c.getColumnIndex(LocationDB.CONTENTS));
            x_val[i] = c.getDouble(c.getColumnIndex(LocationDB.POS_X));
            y_val[i] = c.getDouble(c.getColumnIndex(LocationDB.POS_Y));
            //화면에 데이터 보이게 함
            listviewadapter.addItem(name_arr[i], contents_arr[i], x_val[i], y_val[i]);
            i++;
        }
        c.close();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback2.onListViewClick(view, x_val[i], y_val[i]);
            }
        });
        return view;
    }

    public interface OnListViewClickedListener {
        void onListViewClick(View v, double x, double y);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mCallback2 = (OnListViewClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTest1ButtonClickedListener");
        }
    }
}