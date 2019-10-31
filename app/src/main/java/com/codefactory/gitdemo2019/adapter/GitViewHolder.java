package com.codefactory.gitdemo2019.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codefactory.gitdemo2019.R;
import com.codefactory.gitdemo2019.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.adapter_repo_name_textView)
    TextView tvName;

    @BindView(R.id.adapter_repo_fullName_textView)
    TextView tvFullName;

    @BindView(R.id.adapter_repo__description_textView)
    TextView tvDescription;

    public GitViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setRepoItem(Repo item){
        tvName.setText(item.getName());
//        tvFullName.setText(item.getFull_name());
        if (item.getDescription()==null) tvDescription.setVisibility(View.GONE);
        else tvDescription.setText(item.getDescription());
    }
}
