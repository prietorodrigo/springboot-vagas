package web2.atividadefinal.service;

import web2.atividadefinal.model.vagaModel;

import java.util.List;

public interface vagaService {
    List<vagaModel> findAll();
    vagaModel findById(long id);
    vagaModel save(vagaModel vagaModel);
    List<vagaModel> findVagasByCategoria(String categoria);
    List<vagaModel> findVagasByEmpresaLike(String empresa);
    List<vagaModel> findVagasByStatus(String status);
}
