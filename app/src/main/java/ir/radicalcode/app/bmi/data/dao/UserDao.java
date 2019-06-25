package ir.radicalcode.app.bmi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import ir.radicalcode.app.bmi.data.entity.UserModel;

@Dao
public interface UserDao extends BaseDao<UserModel> {

    @Query("SELECT * FROM user")
    LiveData<UserModel> getUser();
}
