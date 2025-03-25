package com.mustafa.qrscannerapp.data.repository;

import androidx.lifecycle.LiveData;

import com.mustafa.qrscannerapp.data.local.dao.QRCodeDao;
import com.mustafa.qrscannerapp.data.local.entity.QRCode;


import java.util.List;

import javax.inject.Inject;

public class QRCodeRepository {
    private final QRCodeDao qrCodeDao;

    @Inject
    public QRCodeRepository(QRCodeDao qrCodeDao) {
        this.qrCodeDao = qrCodeDao;
    }

    public LiveData<List<QRCode>> getAllQRCodes() {
        return qrCodeDao.getAllQRCodes();
    }

    public LiveData<List<QRCode>> getFavoriteQRCodes() {
        return qrCodeDao.getFavoriteQRCodes();
    }

    public void insertQRCode(String content) {
        QRCode qrCode = new QRCode(content, System.currentTimeMillis(), false);


        new Thread(() -> qrCodeDao.insert(qrCode)).start();
    }

    public void toggleFavorite(QRCode qrCode) {
        qrCode.setFavorite(!qrCode.isFavorite());
        new Thread(() -> qrCodeDao.update(qrCode)).start();
    }
}