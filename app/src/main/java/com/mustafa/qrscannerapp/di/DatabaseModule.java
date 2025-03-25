package com.mustafa.qrscannerapp.di;

import android.content.Context;
import androidx.room.Room;

import com.mustafa.qrscannerapp.data.local.QrCodeDatabase;
import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public QrCodeDatabase provideDatabase(@ApplicationContext Context context) {
        return QrCodeDatabase.getDatabase(context);
    }

    @Provides
    @Singleton
    public QRCodeDao provideQRCodeDao(QrCodeDatabase database) {
        return database.qrCodeDao();
    }
}
