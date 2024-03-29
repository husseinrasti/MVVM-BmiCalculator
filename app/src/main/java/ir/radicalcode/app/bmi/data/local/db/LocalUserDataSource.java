package ir.radicalcode.app.bmi.data.local.db;

import androidx.lifecycle.LiveData;
import ir.radicalcode.app.bmi.data.dao.UserDao;
import ir.radicalcode.app.bmi.data.entity.UserModel;
import ir.radicalcode.app.bmi.data.datasource.UserDataSource;

public class LocalUserDataSource implements UserDataSource {

    private UserDao userDao;

    public LocalUserDataSource( UserDao userDao ) {
        this.userDao = userDao;
    }

    @Override
    public void insert( UserModel model ) {
        userDao.insert( model );
    }

    @Override
    public void insertFirst( UserModel model ) {
        userDao.insertByReplaceIt( model );
    }

    @Override
    public void delete( UserModel model ) {
        userDao.delete( model );
    }

    @Override
    public void update( UserModel model ) {
        userDao.update( model );
    }

    @Override
    public LiveData<UserModel> getUser() {
        return userDao.getUser();
    }

    @Override
    public void updateImageProfile( int id , byte[] pic ) {
        userDao.updateImageProfile( id , pic );
    }

    @Override
    public void updateNameProfile( int id , String name ) {
        userDao.updateNameProfile( id , name );
    }
}
