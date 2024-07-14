package com.example.kullaniciAktiviteRaporuSistemi.modelmapper;

import org.modelmapper.ModelMapper;



public interface ModelMapperService {

    ModelMapper forResponse();

    ModelMapper forRequest();
}
