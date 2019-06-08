package ir.radicalcode.app.bmi.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {

    @Insert
    void insert( T model );


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertByReplaceIt( T model );

    @Delete
    void delete( T model );

    @Update
    void update(T model);
}
