package com.mustafa.qrscannerapp.di;


import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;
import com.mustafa.qrscannerapp.data.repository.QRCodeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public QRCodeRepository provideQRCodeRepository(QRCodeDao qrCodeDao) {
        return new QRCodeRepository(qrCodeDao);
    }
}
