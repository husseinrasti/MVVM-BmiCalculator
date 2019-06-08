package ir.radicalcode.app.bmi.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bmi")
public class BmiModel {

    @PrimaryKey
    @ColumnInfo()
    private int id;
    @ColumnInfo()
    private int age;
    @ColumnInfo()
    private String sex;
    @ColumnInfo()
    private double weight;
    @ColumnInfo()
    private int weightUnitPosition;
    @ColumnInfo()
    private String weightUnit;
    @ColumnInfo()
    private double height;
    @ColumnInfo()
    private int heightUnitPosition;
    @ColumnInfo()
    private double heightInch;
    @ColumnInfo()
    private String heightUnit;
    @ColumnInfo()
    private float result;
    @ColumnInfo()
    private String date;
    @ColumnInfo()
    private int status;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getWeightUnitPosition() {
        return weightUnitPosition;
    }

    public void setWeightUnitPosition( int weightUnitPosition ) {
        this.weightUnitPosition = weightUnitPosition;
    }

    public int getHeightUnitPosition() {
        return heightUnitPosition;
    }

    public void setHeightUnitPosition( int heightUnitPosition ) {
        this.heightUnitPosition = heightUnitPosition;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex( String sex ) {
        this.sex = sex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight( double weight ) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit( String weightUnit ) {
        this.weightUnit = weightUnit;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public double getHeightInch() {
        return heightInch;
    }

    public void setHeightInch( double heightInch ) {
        this.heightInch = heightInch;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit( String heightUnit ) {
        this.heightUnit = heightUnit;
    }

    public float getResult() {
        return result;
    }

    public void setResult( float result ) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus( int status ) {
        this.status = status;
    }
}