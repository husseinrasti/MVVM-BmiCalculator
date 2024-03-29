package ir.radicalcode.app.bmi.data.datasource;

import java.util.List;

import androidx.lifecycle.LiveData;
import ir.radicalcode.app.bmi.data.datasource.BaseDataSource;
import ir.radicalcode.app.bmi.data.entity.BmiModel;

public interface BmiDataSource extends BaseDataSource<BmiModel> {

    LiveData<List<BmiModel>> getAll();


    LiveData<BmiModel> getById( int id );


    LiveData<BmiModel> getLastRecord();


    int getHistoryCount();


    int getLastId();


    float getLastResult();


}
