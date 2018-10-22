package com.example.ogomb.androidarchitecturecomponents.di.module;

import com.example.ogomb.androidarchitecturecomponents.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
