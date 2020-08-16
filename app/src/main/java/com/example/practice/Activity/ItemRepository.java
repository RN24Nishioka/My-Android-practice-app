package com.example.practice.Activity;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class ItemRepository {

    private Context context;

    public ItemRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addNewItem(DbTable dbTable) {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDAO().insertItem(dbTable));
    }



    public LiveData<List<DbTable>> getItems() {
        return DbDatabase.getInstance(context).dbDAO().getAllItems();
    }

    public LiveData<Integer> getItemCount() {
        return DbDatabase.getInstance(context).dbDAO().countItems();
    }

    public void deleteItem(DbTable dbTable) {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDAO().deleteItem(dbTable));
    }

}
