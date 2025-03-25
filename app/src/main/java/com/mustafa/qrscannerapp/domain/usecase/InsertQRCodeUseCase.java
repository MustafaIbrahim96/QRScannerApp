package com.mustafa.qrscannerapp.domain.usecase;

import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;

import javax.inject.Inject;

public class InsertQRCodeUseCase {
    private final QRCodeRepository repository;

    @Inject
    public InsertQRCodeUseCase(QRCodeRepository repository) {
        this.repository = repository;
    }

    public void execute(String content) {
        repository.insertQRCode(content);
    }
}
