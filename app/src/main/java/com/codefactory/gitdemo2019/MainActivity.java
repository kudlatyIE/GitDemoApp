package com.codefactory.gitdemo2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.codefactory.gitdemo2019.model.Repo;
import com.codefactory.gitdemo2019.viewmodel.RepoViewModel;
import com.codefactory.gitdemo2019.viewmodel.RepoViewModelFactory;

import java.util.List;

import static com.codefactory.gitdemo2019.net.NetworkService.ARG_ORGANISATION_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /*
    TODO: et list of repositories from https://github.com/applauseoss
     */
    private TextView tvResult;
    private RepoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.main_info_textView);

        loadData();
    }

    private void loadData(){
        viewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
//        viewModel = ViewModelProviders.of(this, new RepoViewModelFactory(getApplication()/*, ARG_ORGANISATION_NAME */)).get(RepoViewModel.class);
        viewModel.getReposFromDb().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                if (repos!=null){
                    Log.d(TAG, "Git repos size: "+repos.size());
                    String result = "";
                    for (Repo rep: repos){
                        result = result.concat(rep.getName()).concat("/n");
                    }
                    tvResult.setText(result);
                }else {
                    Log.d(TAG, "Git repos NULL");
                }
            }
        });
    }
}
