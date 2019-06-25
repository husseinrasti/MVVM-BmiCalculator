package ir.radicalcode.app.bmi.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ir.radicalcode.app.bmi.data.entity.UserModel;
import ir.radicalcode.app.bmi.data.datasource.UserDataSource;

public class UserViewModel extends ViewModel {

    private UserDataSource userDataSource;

    public UserViewModel( UserDataSource userDataSource ) {
        this.userDataSource = userDataSource;
    }

    public void insert( UserModel model ) {
        userDataSource.insert( model );
    }

    public void insertFirst( UserModel model ) {
        userDataSource.insertFirst( model );
    }

    public void update( UserModel model ) {
        userDataSource.update( model );
    }

    public void delete( UserModel model ) {
        userDataSource.delete( model );
    }

    public LiveData<UserModel> getUserModel() {
        return userDataSource.getUser();
    }
}
