package com.mustafa.qrscannerapp.domain.usecase;

import com.mustafa.qrscannerapp.data.local.entity.QRCode;
import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;

import javax.inject.Inject;

public class ToggleFavoriteUseCase {
    private final QRCodeRepository repository;

    @Inject
    public ToggleFavoriteUseCase(QRCodeRepository repository) {
        this.repository = repository;
    }

    public void execute(QRCode qrCode) {
        QRCode updatedQrCode = new QRCode(
                qrCode.getContent(),
                qrCode.getTimestamp(),
                !qrCode.isFavorite()
        );
        updatedQrCode.setId(qrCode.getId());

        repository.toggleFavorite(updatedQrCode);
    }
}