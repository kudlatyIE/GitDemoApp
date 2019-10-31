package com.codefactory.gitdemo2019.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Repo repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRepos(List<Repo> repos);

    @Update
    public void update(Repo... repos);

    @Delete
    public void delete(Repo... repos);

    @Query("DELETE FROM table_repo")
    public void deleteAll();

    @Query("SELECT * FROM table_repo LIMIT 1")
    public LiveData<Repo> getAnyRepo();

    @Query("SELECT * FROM table_repo")
    public LiveData<List<Repo>> getAllRepos();

    @Query("SELECT * FROM table_repo WHERE name LIKE :pattern OR description LIKE :pattern")
    public LiveData<List<Repo>> findRepo(String pattern);

}
