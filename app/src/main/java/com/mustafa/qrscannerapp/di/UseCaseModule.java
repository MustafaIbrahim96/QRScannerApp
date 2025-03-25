package com.mustafa.qrscannerapp.di;

import com.mustafa.qrscannerapp.domain.repository.QRCodeRepository;
import com.mustafa.qrscannerapp.domain.usecase.GetFavoriteQRCodesUseCase;
import com.mustafa.qrscannerapp.domain.usecase.GetQRCodesUseCase;
import com.mustafa.qrscannerapp.domain.usecase.InsertQRCodeUseCase;
import com.mustafa.qrscannerapp.domain.usecase.ToggleFavoriteUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    @Singleton
    public GetQRCodesUseCase provideGetQRCodesUseCase(QRCodeRepository repository) {
        return new GetQRCodesUseCase(repository);
    }


    @Provides
    @Singleton
    public InsertQRCodeUseCase provideInsertQRCodeUseCase(QRCodeRepository repository) {
        return new InsertQRCodeUseCase(repository);
    }

    @Provides
    @Singleton
    public GetFavoriteQRCodesUseCase provideGetFavoriteQRCodesUseCase(QRCodeRepository repository) {
        return new GetFavoriteQRCodesUseCase(repository);
    }

    @Provides
    @Singleton
    public ToggleFavoriteUseCase provideToggleFavoriteUseCase(QRCodeRepository repository) {
        return new ToggleFavoriteUseCase(repository);
    }

}
