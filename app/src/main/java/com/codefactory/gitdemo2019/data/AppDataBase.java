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

import java.util.List;


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

//    private void insertData(Context context, AppExecutors executors,List<Repo> data){
//        if (INSTANCE!=null){
//            executors.diskIO().execute(
//                    () -> INSTANCE.runInTransaction(
//                            ()-> INSTANCE.repoDao().insertAllRepos(data)
//                    )
//            );
//        }
//    }

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

    /*
TODO: insert retrofit response into DB:
                    executors.diskIO().execute(() -> {
                            AppDataBase database = AppDataBase.getAppDataBase(context, executors);
                            List<ProductEntity> products = DataGenerator.generateProducts();
                            List<CommentEntity> comments =
                                    DataGenerator.generateCommentsForProducts(products);
                            insertData(database, products, comments);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });

        private static void insertData(final AppDatabase database, final List<Repo_Entity> repos {
            database.runInTransaction(() -> {
                database.repoDao().insertAll(repos);

            });
        }
 */

}
