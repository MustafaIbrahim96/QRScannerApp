package com.mustafa.qrscannerapp.data.repository;

import androidx.lifecycle.LiveData;

import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QRCodeRepositoryImpl implements QRCodeRepository {

    private final QRCodeDao qrCodeDao;
    private final Executor executor;

    @Inject
    public QRCodeRepositoryImpl(QRCodeDao qrCodeDao, Executor executor) {
        this.qrCodeDao = qrCodeDao;
        this.executor = executor;
    }

    @Override
    public LiveData<List<QRCode>> getAllQRCodes() {
        return qrCodeDao.getAllQRCodes();
    }

    @Override
    public LiveData<List<QRCode>> getFavoriteQRCodes() {
        return qrCodeDao.getFavoriteQRCodes();
    }

    @Override
    public void insertQRCode(String content) {
        try {
            QRCode qrCode = new QRCode(content, System.currentTimeMillis(), false);
            executor.execute(() -> {
                try {
                    qrCodeDao.insert(qrCode);
                } catch (Exception e) {
                    // Handle or log error
                }
            });
        } catch (Exception e) {
            // Handle or log error
        }
    }

    @Override
    public void toggleFavorite(QRCode qrCode) {

        executor.execute(() -> qrCodeDao.update(qrCode));
/*
        qrCode.setFavorite(!qrCode.isFavorite());
        new Thread(() -> qrCodeDao.update(qrCode)).start();*/
    }
}
