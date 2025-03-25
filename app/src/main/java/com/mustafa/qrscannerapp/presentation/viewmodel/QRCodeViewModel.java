package com.mustafa.qrscannerapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.data.repository.QRCodeRepository;

import java.util.List;
import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QRCodeViewModel extends ViewModel {
    private final QRCodeRepository repository;
    private final LiveData<List<QRCode>> allQRCodes;
    private final LiveData<List<QRCode>> favoriteQRCodes;

    @Inject
    public QRCodeViewModel(QRCodeRepository repository) {
        this.repository = repository;
        this.allQRCodes = repository.getAllQRCodes();
        this.favoriteQRCodes = repository.getFavoriteQRCodes();
    }

    public LiveData<List<QRCode>> getAllQRCodes() {
        return allQRCodes;
    }

    public LiveData<List<QRCode>> getFavoriteQRCodes() {
        return favoriteQRCodes;
    }


    public void insertQRCode(String content) {
        repository.insertQRCode(content);
    }

    public void toggleFavorite(QRCode qrCode) {
        repository.toggleFavorite(qrCode);
    }
}
