package com.mustafa.qrscannerapp.di;


import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;
import com.mustafa.qrscannerapp.data.repository.QRCodeRepositoryImpl;
import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    @Singleton
    public abstract QRCodeRepository bindQRCodeRepository(QRCodeRepositoryImpl impl);
}