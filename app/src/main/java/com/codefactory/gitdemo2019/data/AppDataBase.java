package com.codefactory.gitdemo2019.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.codefactory.gitdemo2019.AppExecutors;
import com.codefactory.gitdemo2019.model.Repo;
import com.codefactory.gitdemo2019.model.RepoDao;


@Database(entities = {Repo.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "git_database";
    private static AppDataBase INSTANCE;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public abstract RepoDao repoDao();

    public static AppDataBase getAppDataBase(Context context, AppExecutors executors){
        if (INSTANCE==null){
            synchronized (AppDataBase.class){
                if (INSTANCE==null){

                    INSTANCE = buildDatabase(context.getApplicationContext(), executors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDataBase.class, DATABASE_NAME)
//                            .allowMainThreadQueries()// don't allow queries on the main thread - just temporary
//                            .build();
                    INSTANCE.updateDatabaseCreated(context);
                }
            }

        }
        return INSTANCE;
    }

    private static AppDataBase buildDatabase(final Context context, final AppExecutors executors){
        return Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                })
//                .allowMainThreadQueries()// don't allow queries on the main thread - just temporary
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            isDatabaseCreated.postValue(true);
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
