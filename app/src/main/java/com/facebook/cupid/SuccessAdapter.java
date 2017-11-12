package com.facebook.cupid;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.cupid.models.Match;
import com.facebook.cupid.models.Suggestion;
import com.facebook.cupid.models.User;

import java.util.ArrayList;

/**
 * Created by brucegatete on 11/12/17.
 */

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.ViewHolder>{
    ArrayList<Match> machlist;
    Context context;
    Match name_01;
    User name_02;


    @Override
    public SuccessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View matchListView = inflater.inflate(R.layout.item_match_success, parent, false);
        ViewHolder viewHolder = new ViewHolder(matchListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SuccessAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ViewHolder {
    }
}
