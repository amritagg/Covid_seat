package com.amrit.practice.covidseattracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Data> list;

    public ListAdapter(Context context, ArrayList<Data> list){
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return getItemId(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            view = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView age18_45 = view.findViewById(R.id.age_data);
        TextView dose1 = view.findViewById(R.id.dose1_data);
        TextView dose2 = view.findViewById(R.id.dose2_data);

        Data data = list.get(position);

        boolean is18 = data.isAge18();
        if(is18) age18_45.setText("18");
        else age18_45.setText("45");

        dose1.setText(data.getDose1() + "");
        dose2.setText(data.getDose2() + "");

        return view;
    }

}
