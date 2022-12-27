package com.example.wagba_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba_app.Models.User;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private User mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getUsers();
    }

    public User getAllUsers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user); }
}
