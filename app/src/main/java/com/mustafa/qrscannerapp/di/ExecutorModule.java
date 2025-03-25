package com.mustafa.qrscannerapp.di;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ExecutorModule {

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(4); // يمكنك تغيير عدد الثريدات حسب الحاجة
    }
}