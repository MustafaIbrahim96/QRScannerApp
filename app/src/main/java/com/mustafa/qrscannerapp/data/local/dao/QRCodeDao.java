package com.mustafa.qrscannerapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.mustafa.qrscannerapp.data.local.entity.QRCode;

import java.util.List;

@Dao
public interface QRCodeDao {
    @Insert
    void insert(QRCode qrCode);

    @Update
    void update(QRCode qrCode);

    @Query("SELECT * FROM qr_codes ORDER BY timestamp DESC")
    LiveData<List<QRCode>> getAllQRCodes();

    @Query("SELECT * FROM qr_codes WHERE isFavorite = 1 ORDER BY timestamp DESC")
    LiveData<List<QRCode>> getFavoriteQRCodes();
}