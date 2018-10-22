package com.example.ogomb.androidarchitecturecomponents.di.component;

import android.app.Application;

import com.example.ogomb.androidarchitecturecomponents.di.module.ActivityModule;
import com.example.ogomb.androidarchitecturecomponents.App;
import com.example.ogomb.androidarchitecturecomponents.di.module.AppModule;
import com.example.ogomb.androidarchitecturecomponents.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules={AndroidSupportInjectionModule.class,
        ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}