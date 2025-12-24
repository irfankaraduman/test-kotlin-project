package com.msku.example.hacininyeri.LoginRegisterPage;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.msku.example.hacininyeri.Constants;

public class Model {
    LoginState state;

    public Model(LoginState state) {
        this.state = state;
    }


    // ******  YUSUF YILDIZ  *******

    public void loginUser(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        state.onProccess();
                    }
                });
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                System.out.println(username +"-----"+ password);
                Task<QuerySnapshot> task = db.collection("users")
                        .whereEqualTo("username", username)
                        .whereEqualTo("password", password)
                        .get();

                try {
                    QuerySnapshot querySnapshot = Tasks.await(task);

                    if (!querySnapshot.isEmpty()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                state.onLogged();
                                Constants.userId =querySnapshot.getDocuments().get(0).getId() ;
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                state.onUserNotFound();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("Hata",e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            state.onError();
                        }
                    });
                }
            }
        }).start();
    }

    private void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }


}
