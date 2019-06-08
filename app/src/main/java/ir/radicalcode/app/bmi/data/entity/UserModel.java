package ir.radicalcode.app.bmi.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    private int id;

    @ColumnInfo()
    private String name;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] picProfile;

    public void setId( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public byte[] getPicProfile() {
        return picProfile;
    }

    public void setPicProfile( byte[] picProfile ) {
        this.picProfile = picProfile;
    }
}
