package web2.atividadefinal.service;

import web2.atividadefinal.model.inscricaoModel;

import java.util.List;

public interface inscricaoService {
    List<inscricaoModel> findAll();
    inscricaoModel findById(long id);
    inscricaoModel save(inscricaoModel inscricaoModel);
}
