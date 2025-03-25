package com.mustafa.qrscannerapp.domain.repository;

import androidx.lifecycle.LiveData;

import com.mustafa.qrscannerapp.data.local.entity.QRCode;

import java.util.List;

public interface QRCodeRepository {

    LiveData<List<QRCode>> getAllQRCodes();
    LiveData<List<QRCode>> getFavoriteQRCodes();
    void insertQRCode(String content);
    void toggleFavorite(QRCode qrCode);

}
