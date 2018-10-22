package com.example.ogomb.androidarchitecturecomponents.di.module;

import com.example.ogomb.androidarchitecturecomponents.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract UserProfileFragment contributeUserProfileFragment();

}
