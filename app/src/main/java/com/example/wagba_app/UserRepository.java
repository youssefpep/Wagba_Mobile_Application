package com.example.wagba_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wagba_app.Interfaces.UserDao;
import com.example.wagba_app.Models.UserDatabase;
import com.example.wagba_app.Models.User;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private User mUser;

    UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUsers();
    }

    User getUsers() {
        return mUser;
    }

    public void insert (User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }
    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mAsyncTaskDao;
        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }
}
