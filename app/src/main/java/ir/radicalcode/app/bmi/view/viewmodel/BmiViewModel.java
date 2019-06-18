package ir.radicalcode.app.bmi.view.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ir.radicalcode.app.bmi.data.datasource.BmiDataSource;
import ir.radicalcode.app.bmi.data.entity.BmiModel;

public class BmiViewModel extends ViewModel {

    private final BmiDataSource dataSource;

    public BmiViewModel( BmiDataSource dataSource ) {
        this.dataSource = dataSource;
    }

    public void insert( BmiModel model ) {
        dataSource.insert( model );
    }

    public void insertFirst( BmiModel model ) {
        dataSource.insertFirst( model );
    }

    public void delete( BmiModel model ) {
        dataSource.delete( model );
    }

    public LiveData<List<BmiModel>> getAll() {
        return dataSource.getAll();
    }

    public LiveData<BmiModel> getById( int id ) {
        return dataSource.getById( id );
    }

    public LiveData<BmiModel> getLastRecord() {
        return dataSource.getLastRecord();
    }

    public int getHistoryCount() {
        return dataSource.getHistoryCount();
    }

    public int getLastId() {
        return dataSource.getLastId();
    }

    public float getLastResult() {
        return dataSource.getLastResult();
    }
}
