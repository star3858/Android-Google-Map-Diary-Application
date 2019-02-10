package com.example.star3.takehomeexam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by star3 on 2018-04-18.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<TripData> listViewItemList = new ArrayList<TripData>();

    public ListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item1, parent, false);
        }

        TextView NameText = (TextView) convertView.findViewById(R.id.Name);
        TextView ContentsText = (TextView) convertView.findViewById(R.id.Contents);
        Button share = (Button) convertView.findViewById(R.id.share);

        final TripData listViewItem = listViewItemList.get(position);
        NameText.setText(listViewItem.getTitle());
        ContentsText.setText(listViewItem.getContent());

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String title1 = listViewItem.getTitle();
                String content = listViewItem.getContent();
                sendIntent.putExtra(Intent.EXTRA_TEXT, "다이어리\n제목 : "+title1+ "\n내용 : "+content);
                sendIntent.setType("text/plain");   // Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(sendIntent);
                }
                String title = "Share this text with";
                //Create intent to show the chooser dialog
                Intent chooser = Intent.createChooser(sendIntent, title);
                //Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(chooser);
                }
            }
        });
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String contents, double x, double y) {
        TripData item = new TripData();
        item.setTitle(title);
        item.setContent(contents);
        item.setX(x);
        item.setY(y);
        listViewItemList.add(item);
    }
}