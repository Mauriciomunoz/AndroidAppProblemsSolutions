package com.mapp.problemssolutions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDao {
    @Insert
    public void addData(MyDataList mydatalist);

    @Query("select * from mydatalist")
    public List<MyDataList> getMyData();

    @Delete
    public void delete(MyDataList mydatalist);

    @Update
    public void update(MyDataList mydatalist);

}
