package com.mustafa.qrscannerapp.domain.usecase;

import androidx.lifecycle.LiveData;

import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;

import java.util.List;

import javax.inject.Inject;

public class GetQRCodesUseCase {
    private final QRCodeRepository repository;

    @Inject
    public GetQRCodesUseCase(QRCodeRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<QRCode>> execute() {
        return repository.getAllQRCodes();
    }
}
