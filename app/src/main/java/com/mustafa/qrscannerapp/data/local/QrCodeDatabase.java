package com.mustafa.qrscannerapp.data.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;


@Database(entities = {QRCode.class}, version = 1)
public abstract class QrCodeDatabase extends RoomDatabase {
    public abstract QRCodeDao qrCodeDao();

    private static volatile QrCodeDatabase INSTANCE;

    public static QrCodeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QrCodeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    QrCodeDatabase.class, "qr_code_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}