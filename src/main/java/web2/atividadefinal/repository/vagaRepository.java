package web2.atividadefinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.atividadefinal.model.vagaModel;

import java.util.List;

public interface vagaRepository extends JpaRepository<vagaModel, Long> {
    List<vagaModel> findVagasByCategoria(String categoria);
    List<vagaModel> findVagasByEmpresaLike(String empresa);
    List<vagaModel> findVagasByStatus(String status);
}
