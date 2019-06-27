package ir.radicalcode.app.bmi.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import ir.radicalcode.app.bmi.data.entity.UserModel;

@Dao
public interface UserDao extends BaseDao<UserModel> {

    @Query("SELECT * FROM user")
    LiveData<UserModel> getUser();


    @Query("UPDATE user SET picProfile=:pic WHERE id=:id")
    void updateImageProfile( int id , byte[] pic );


    @Query("UPDATE user SET name=:name WHERE id=:id")
    void updateNameProfile( int id , String name );
}
