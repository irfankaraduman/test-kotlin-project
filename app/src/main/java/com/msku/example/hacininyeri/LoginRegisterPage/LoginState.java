package com.msku.example.hacininyeri.LoginRegisterPage;

public interface LoginState {
    void onLogged();
    void onError();
    void onUserNotFound();
    void onProccess();

}
