package ir.radicalcode.app.bmi.data.local;

import ir.radicalcode.app.bmi.data.BaseDataSource;
import ir.radicalcode.app.bmi.data.entity.UserModel;

public interface UserDataSource extends BaseDataSource<UserModel> {

    UserModel getUser();
}
