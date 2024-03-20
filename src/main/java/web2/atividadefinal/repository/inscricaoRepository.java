package web2.atividadefinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.atividadefinal.model.inscricaoModel;
import web2.atividadefinal.model.vagaModel;

import java.util.List;
import java.util.Optional;

public interface inscricaoRepository extends JpaRepository<inscricaoModel, Long> {
    List<inscricaoModel> findInscricoesByVaga(Optional<vagaModel> vagaModel);
}
