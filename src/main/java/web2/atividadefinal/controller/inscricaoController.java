package web2.atividadefinal.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web2.atividadefinal.model.inscricaoModel;
import web2.atividadefinal.model.vagaModel;
import web2.atividadefinal.repository.inscricaoRepository;
import web2.atividadefinal.repository.vagaRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class inscricaoController {
    @Autowired
    inscricaoRepository inscricaoRepository;
    @Autowired
    vagaRepository vagaRepository;



    @RequestMapping(value="/imagem/{imagem}", method=RequestMethod.GET)
    @ResponseBody
    public byte[] getImagens(@PathVariable("imagem") String imagem) throws IOException {
        File caminho = new File ("./src/main/resources/static/img/"+imagem);
        if (imagem != null || imagem.trim().length() > 0) {
            return Files.readAllBytes(caminho.toPath());
        }
        return null;
    }

    @RequestMapping(value="/listarInscricoes/{id}", method= RequestMethod.GET)
    public ModelAndView getInscricoes(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("listarInscricoes");
        Optional<vagaModel> vagas = vagaRepository.findById(id);
        List<inscricaoModel> inscricoes = inscricaoRepository.findInscricoesByVaga(vagas);
        mv.addObject("inscricoes", inscricoes);
        return mv;
    }
}
