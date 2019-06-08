package ir.radicalcode.app.bmi.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import ir.radicalcode.app.bmi.data.entity.BmiModel;
import ir.radicalcode.app.bmi.data.entity.UserModel;

@Database(entities = { BmiModel.class , UserModel.class }, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {


    private static final String DATABASE_NAME = "bmi_db";
    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getInstance( final Context context ) {
        if ( INSTANCE == null ) {
            synchronized ( DatabaseHelper.class ) {
                if ( INSTANCE == null ) {
                    INSTANCE = Room.databaseBuilder( context.getApplicationContext() , DatabaseHelper.class , DATABASE_NAME )
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();


    public abstract BmiDao bmiDao();
}
