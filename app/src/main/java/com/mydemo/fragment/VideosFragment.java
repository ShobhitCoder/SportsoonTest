package com.mydemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydemo.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SS091 on 6/12/2017.
 */

public class VideosFragment extends Fragment {
    private RecyclerView recyclerView;
    View rootView;
    Activity mActivity;
    List<JSONObject> mJsonObjects=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.videos_fragment, container, false);
        mActivity=getActivity();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        mJsonObjects.add(new JSONObject());
        recyclerView.setAdapter(new MyAdapter(mJsonObjects));
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<JSONObject> jsonObjectArrayList;
        boolean NoRecordDataFound = false;

        public MyAdapter(List<JSONObject> jsonListingArray) {
            jsonObjectArrayList = jsonListingArray;
        }

        public void notifyDataSetChanged(ArrayList<JSONObject> mJsonObjectArrayList) {
            super.notifyDataSetChanged();
            jsonObjectArrayList = mJsonObjectArrayList;

        }

        class NoRecordViewHolder extends RecyclerView.ViewHolder {

            public NoRecordViewHolder(View itemView) {
                super(itemView);
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.card_view:

                        break;
                }
            }

            CardView card_view;


            public ViewHolder(View v) {
                super(v);
                card_view = (CardView) v.findViewById(R.id.card_view);
                card_view.setOnClickListener(this);

            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            if (viewType == 1) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_record_found, parent, false);
                return new NoRecordViewHolder(v);
            } else {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_row, parent, false);
                return new ViewHolder(v);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder mViewHolder, int position) {
            if (mViewHolder instanceof ViewHolder) {
                ViewHolder holder = (ViewHolder) mViewHolder;
            }
        }

        @Override
        public int getItemCount() {
            if (jsonObjectArrayList.size() == 0) {
                NoRecordDataFound = true;
                return 1;
            } else {
                NoRecordDataFound = false;
                return jsonObjectArrayList.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (NoRecordDataFound && position == 0) {
                return 1;
            } else {
                return super.getItemViewType(position);
            }
        }
    }
}
