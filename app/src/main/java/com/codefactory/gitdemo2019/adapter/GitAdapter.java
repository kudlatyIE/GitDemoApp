package com.codefactory.gitdemo2019.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codefactory.gitdemo2019.R;
import com.codefactory.gitdemo2019.model.Repo;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Repo> data;

    public GitAdapter(Context context) {
        this.context=context;
//        this.data = data;
    }

    public void swapData(List<Repo> newData){
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            parent.setElevation(10);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repo_item, parent, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GitViewHolder viewHolder = (GitViewHolder) holder;
        viewHolder.setRepoItem(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data!=null) return data.size();
        return 0;
    }
}
