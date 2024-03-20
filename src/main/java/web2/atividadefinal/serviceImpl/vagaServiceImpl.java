package web2.atividadefinal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.atividadefinal.model.vagaModel;
import web2.atividadefinal.service.vagaService;
import web2.atividadefinal.repository.vagaRepository;

import java.util.List;

@Service
public class vagaServiceImpl implements vagaService {
    @Autowired
    vagaRepository vagaRepository;

    @Override
    public List<vagaModel> findAll() {
        return vagaRepository.findAll();
    }

    @Override
    public vagaModel findById(long id) {
        return vagaRepository.findById(id).get();
    }

    @Override
    public vagaModel save(vagaModel vagaModel) {
        return vagaRepository.save(vagaModel);
    }

    @Override
    public List<vagaModel> findVagasByCategoria(String categoria) {
        return vagaRepository.findVagasByCategoria(categoria);
    }

    @Override
    public List<vagaModel> findVagasByEmpresaLike(String empresa) {
        return vagaRepository.findVagasByEmpresaLike(empresa);
    }

    @Override
    public List<vagaModel> findVagasByStatus(String status) {
        return vagaRepository.findVagasByStatus(status);
    }
}
