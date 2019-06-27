package ir.radicalcode.app.bmi.data.datasource;

import androidx.lifecycle.LiveData;
import ir.radicalcode.app.bmi.data.entity.UserModel;

public interface UserDataSource extends BaseDataSource<UserModel> {

    LiveData<UserModel> getUser();


    void updateImageProfile( int id , byte[] pic );


    void updateNameProfile( int id , String name );
}
