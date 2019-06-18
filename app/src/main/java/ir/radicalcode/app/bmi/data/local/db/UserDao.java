package ir.radicalcode.app.bmi.data.local.db;

import androidx.room.Dao;
import androidx.room.Query;
import ir.radicalcode.app.bmi.data.BaseDao;
import ir.radicalcode.app.bmi.data.entity.UserModel;

@Dao
public interface UserDao extends BaseDao<UserModel> {

    @Query("SELECT * FROM user")
    UserModel getUser();
}
