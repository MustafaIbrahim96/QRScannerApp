package com.mustafa.qrscannerapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.domain.usecase.GetFavoriteQRCodesUseCase;
import com.mustafa.qrscannerapp.domain.usecase.GetQRCodesUseCase;
import com.mustafa.qrscannerapp.domain.usecase.InsertQRCodeUseCase;
import com.mustafa.qrscannerapp.domain.usecase.ToggleFavoriteUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QRCodeSharedViewModel extends ViewModel {

    private final GetQRCodesUseCase getQRCodesUseCase;
    private final InsertQRCodeUseCase insertQRCodeUseCase;
    private final ToggleFavoriteUseCase toggleFavoriteUseCase;

    private final GetFavoriteQRCodesUseCase getFavoriteQRCodesUseCase;


    @Inject
    public QRCodeSharedViewModel(GetQRCodesUseCase getQRCodesUseCase,
                                 InsertQRCodeUseCase insertQRCodeUseCase,
                                 ToggleFavoriteUseCase toggleFavoriteUseCase,
                                 GetFavoriteQRCodesUseCase getFavoriteQRCodesUseCase) {
        this.getQRCodesUseCase = getQRCodesUseCase;
        this.insertQRCodeUseCase = insertQRCodeUseCase;
        this.toggleFavoriteUseCase = toggleFavoriteUseCase;
        this.getFavoriteQRCodesUseCase = getFavoriteQRCodesUseCase;
    }

    public LiveData<List<QRCode>> getAllQRCodes() {
        return getQRCodesUseCase.execute();
    }

    public LiveData<List<QRCode>> getFavoriteQRCodes() {
        return getFavoriteQRCodesUseCase.execute();
    }

    public void insertQRCode(String content) {
        insertQRCodeUseCase.execute(content);
    }

    public void toggleFavorite(QRCode qrCode) {
        toggleFavoriteUseCase.execute(qrCode);
    }
}
