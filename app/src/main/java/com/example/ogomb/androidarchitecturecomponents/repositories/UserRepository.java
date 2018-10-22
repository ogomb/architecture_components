package com.example.ogomb.androidarchitecturecomponents.repositories;

import android.arch.lifecycle.LiveData;
import android.widget.Toast;

import com.example.ogomb.androidarchitecturecomponents.App;
import com.example.ogomb.androidarchitecturecomponents.api.UserWebService;
import com.example.ogomb.androidarchitecturecomponents.database.dao.UserDao;
import com.example.ogomb.androidarchitecturecomponents.database.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 3;

    private final UserWebService webservice;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(UserWebService webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }


    public LiveData<User> getUser(String userLogin) {
        refreshUser(userLogin);
        return userDao.load(userLogin);
    }

    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            if (!userExists) {
                webservice.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) { }
                });
            }
        });
    }

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
