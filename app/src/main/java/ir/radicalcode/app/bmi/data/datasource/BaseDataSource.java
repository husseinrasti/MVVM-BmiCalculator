package ir.radicalcode.app.bmi.data.datasource;

public interface BaseDataSource<T> {

    void insert( T model );


    void insertFirst( T model );


    void delete( T model );


    void update( T model );
}
