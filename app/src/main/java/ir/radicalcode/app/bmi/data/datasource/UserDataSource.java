package ir.radicalcode.app.bmi.data.datasource;

import ir.radicalcode.app.bmi.data.entity.UserModel;

public interface UserDataSource extends BaseDataSource<UserModel> {

    UserModel getUser();
}
