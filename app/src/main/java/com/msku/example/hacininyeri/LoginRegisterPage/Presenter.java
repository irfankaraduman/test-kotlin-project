package com.msku.example.hacininyeri.LoginRegisterPage;

public class Presenter implements LoginState{
    Model model;

    LoginView loginView;

    public Presenter(LoginView loginView) {
        this.loginView = loginView;
        model = new Model(this);
    }

    // LoginView fonksiyonlarını tetiklemek için view alıyor.
    public void login(String username, String password){
        model.loginUser(username,password);

    }

    @Override
    public void onLogged() {
        loginView.showLoginSuccesssText();
    }

    @Override
    public void onError() {
        loginView.showErrorText();
    }

    @Override
    public void onUserNotFound() {
        loginView.showUserNotFoundText();
    }

    @Override
    public void onProccess() {
        loginView.showProcessText();
    }
}
