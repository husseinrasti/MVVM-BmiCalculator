package ir.radicalcode.app.bmi.data.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import ir.radicalcode.app.bmi.data.entity.BmiModel;

@Dao
public interface BmiDao extends BaseDao<BmiModel> {

    @Query("SELECT * FROM bmi ORDER BY id DESC")
    LiveData<List<BmiModel>> getAllBmi();


    @Query("SELECT * FROM bmi WHERE id = :id")
    LiveData<BmiModel> getByIdBmi( int id );


    @Query("SELECT * FROM bmi ORDER BY id DESC LIMIT 1")
    LiveData<BmiModel> getLastRecordBmi();


    @Query("SELECT COUNT(*) FROM bmi")
    int getHistoryCountBmi();


    @Query("SELECT id FROM bmi WHERE status=1 ORDER BY id DESC LIMIT 1")
    int getLastIdBmi();


    @Query("SELECT result FROM bmi WHERE status=1 ORDER BY id DESC LIMIT 1")
    float getLastResultBmi();

}
