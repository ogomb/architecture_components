package com.example.ogomb.androidarchitecturecomponents.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.ogomb.androidarchitecturecomponents.FactoryViewModel;
import com.example.ogomb.androidarchitecturecomponents.UserProfileViewModel;
import com.example.ogomb.androidarchitecturecomponents.di.key.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    abstract ViewModel bindUserProfileViewModel(UserProfileViewModel userProfileViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factoryViewModel);
}
