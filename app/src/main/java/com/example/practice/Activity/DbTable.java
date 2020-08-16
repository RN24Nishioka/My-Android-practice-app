package com.example.practice.Activity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class DbTable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String item;

    public int quantity;

}
