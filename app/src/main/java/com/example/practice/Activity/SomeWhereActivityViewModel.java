package com.example.practice.Activity;

import android.app.Application;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practice.R;

import java.util.List;

class SomeWhereActivityViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<List<DbTable>> items;
    private LiveData<Integer> itemCount;

    public SomeWhereActivityViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application.getApplicationContext());
        items = itemRepository.getItems();
        itemCount = itemRepository.getItemCount();

    }




    public void insertNewItem(DbTable dbbTable){
        itemRepository.addNewItem(dbbTable);
    }

    public void deleteItem(DbTable dbbTable){
        itemRepository.deleteItem(dbbTable);
    }

    public LiveData<Integer> getItemCount(){
        return itemCount;
    }

    public LiveData<List<DbTable>> getItems(){
        return items;
    }


}
