package ir.radicalcode.app.bmi.data.local.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import ir.radicalcode.app.bmi.data.dao.BmiDao;
import ir.radicalcode.app.bmi.data.entity.BmiModel;
import ir.radicalcode.app.bmi.data.datasource.BmiDataSource;

public class LocalBmiDataSource implements BmiDataSource {

    private BmiDao bmiDao;

    public LocalBmiDataSource( BmiDao bmiDao ) {
        this.bmiDao = bmiDao;
    }

    @Override
    public void insert( BmiModel model ) {
        bmiDao.insert( model );
    }

    @Override
    public void insertFirst( BmiModel model ) {
        bmiDao.insertByReplaceIt( model );
    }

    @Override
    public void delete( BmiModel model ) {
        bmiDao.delete( model );
    }

    @Override
    public void update( BmiModel model ) {
        bmiDao.update( model );
    }

    @Override
    public LiveData<List<BmiModel>> getAll() {
        return bmiDao.getAllBmi();
    }

    @Override
    public LiveData<BmiModel> getById( int id ) {
        return bmiDao.getByIdBmi( id );
    }

    @Override
    public LiveData<BmiModel> getLastRecord() {
        return bmiDao.getLastRecordBmi();
    }

    @Override
    public int getHistoryCount() {
        return bmiDao.getHistoryCountBmi();
    }

    @Override
    public int getLastId() {
        return bmiDao.getLastIdBmi();
    }

    @Override
    public float getLastResult() {
        return bmiDao.getLastResultBmi();
    }
}
