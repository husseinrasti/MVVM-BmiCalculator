package ir.radicalcode.app.bmi.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BmiHelper {

    private static BmiHelper INSTANCE;

    public static BmiHelper getInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new BmiHelper();
        }

        return INSTANCE;
    }

    /**
     * @param value double that is formatted
     * @return double that has 1 decimal place
     */
    private double format( double value ) {
        if ( value != 0 ) {
            DecimalFormat df = new DecimalFormat( "###.#" , DecimalFormatSymbols.getInstance( Locale.US ) );
            return Double.parseDouble( df.format( value ) );
        } else {
            return -1;
        }
    }

    /**
     * @param lb - pounds
     * @return kg rounded to 1 decimal place
     */
    public double lbToKgConverter( double lb ) {
        return format( lb * 0.45359237 );
    }

    /**
     * @param kg - kilograms
     * @return lb rounded to 1 decimal place
     */
    public double kgToLbConverter( double kg ) {
        return format( kg * 2.20462262 );
    }

    /**
     * @param cm - centimeters
     * @return feet rounded to 1 decimal place
     */
    public double cmToFeetConverter( double cm ) {
        return format( cm * 0.032808399 );
    }

    /**
     * @param feet - feet
     * @return centimeters rounded to 1 decimal place
     */
    public double feetToCmConverter( double feet ) {
        return format( feet * 30.48 );
    }

    /**
     * @param feet - feet
     * @param inch - inch
     * @return centimeters rounded to 1 decimal place
     */
    public double feetInchToCmConverter( double feet , double inch ) {
        return format( ( feet * 30.48 ) + ( inch * 2.54 ) );
    }

    /**
     * @param height in <b>cm</b>
     * @param weight in <b>kilograms</b>
     * @return BMI index with 1 decimal place
     */
    public double getBMIKg( double height , double weight ) {
        double meters = height / 100;
        return format( weight / Math.pow( meters , 2 ) );
    }

    /**
     * @param height     in <b>feet</b>
     * @param heightInch in <b>inch</b>
     * @param weight     in <b>pounds</b>
     * @return BMI index with 1 decimal place
     */
    public double getBMILb( double height , double heightInch , double weight ) {
        int inch = ( int ) ( ( height * 12 ) + heightInch );
        return format( ( weight * 703 ) / Math.pow( inch , 2 ) );
    }

    /**
     * @param bmi (Body Mass Index)
     * @return BMI classification based on the bmi number
     */
    public String getBMIClassification( double bmi ) {

        if ( bmi <= 0 ) {
            return Const.KEY_UNKNOWN;
        }
        String classification;

        if ( bmi < 18.5 ) {
            classification = Const.KEY_underweight;
        } else if ( bmi < 25 ) {
            classification = Const.KEY_normal;
        } else if ( bmi < 30 ) {
            classification = Const.KEY_overweight;
        } else {
            classification = Const.KEY_obese;
        }

        return classification;
    }

    public float getBMIResult( int userWeightUnitPos , int userHeightUnitPos
            , double userHeight , double userWeight , double userHeightInch ) {
        if ( userWeightUnitPos == 0 && userHeightUnitPos == 0 ) {
            return ( float ) getBMIKg( userHeight , userWeight );
        } else if ( userWeightUnitPos == 1 && userHeightUnitPos == 0 ) {
            return ( float ) getBMIKg( userHeight , lbToKgConverter( userWeight ) );
        } else if ( userWeightUnitPos == 0 && userHeightUnitPos == 1 ) {
            return ( float ) getBMIKg( feetInchToCmConverter( userHeight , userHeightInch ) , userWeight );
        } else if ( userWeightUnitPos == 1 && userHeightUnitPos == 1 ) {
            return ( float ) getBMILb( userHeight , userHeightInch , userWeight );
        }
        return 0;
    }
}
