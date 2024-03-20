package web2.atividadefinal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.atividadefinal.model.inscricaoModel;
import web2.atividadefinal.service.inscricaoService;

import java.util.List;
import web2.atividadefinal.repository.inscricaoRepository;

@Service
public class inscricaoServiceImpl implements inscricaoService {

    @Autowired
    inscricaoRepository inscricaoRepository;

    @Override
    public List<inscricaoModel> findAll() {
        return inscricaoRepository.findAll();
    }

    @Override
    public inscricaoModel findById(long id) {
        return inscricaoRepository.findById(id).get();
    }

    @Override
    public inscricaoModel save(inscricaoModel inscricaoModel) {
        return inscricaoRepository.save(inscricaoModel);
    }
}
