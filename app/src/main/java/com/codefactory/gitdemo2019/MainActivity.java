package com.codefactory.gitdemo2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.codefactory.gitdemo2019.adapter.GitAdapter;
import com.codefactory.gitdemo2019.model.Repo;
import com.codefactory.gitdemo2019.viewmodel.RepoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codefactory.gitdemo2019.net.NetworkService.ARG_ORGANISATION_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int DATA_LIMIT = 5;
    /*
    TODO: et list of repositories from https://github.com/applauseoss
     */
    @BindView(R.id.main_info_textView)
    TextView tvResult;
    @BindView(R.id.main_fiter_editText)
    EditText editFilter;
    @BindView(R.id.main_repoList_recycleView)
    RecyclerView recyclerView;

    private RepoViewModel viewModel;
    private GitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new GitAdapter(this);
        recyclerView.setAdapter(adapter);
        loadData();

        editFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence!=null) loadFilteredData(charSequence.toString());
                else loadData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void updateView(List<Repo> repos){
        if (repos!=null)  tvResult.setText("repositories num: "+repos.size());
        adapter.swapData(repos);

    }

    private void loadFilteredData(String pattern){
        viewModel.getFiltered(pattern).observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                updateView(repos);
            }
        });
    }


    private void loadData(){
        viewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
        viewModel.getReposFromDb(ARG_ORGANISATION_NAME, DATA_LIMIT).observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                if (repos!=null){
                    Log.d(TAG, "load data >> Git repos size: "+repos.size());
                    updateView(repos);
                }else {
                    Log.d(TAG, "load data >> Git repos NULL");
                }
            }
        });
    }
}
