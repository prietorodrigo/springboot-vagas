package web2.atividadefinal.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web2.atividadefinal.model.inscricaoModel;
import web2.atividadefinal.model.vagaModel;
import web2.atividadefinal.repository.vagaRepository;
import web2.atividadefinal.repository.inscricaoRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class vagaController {
    @Autowired
    vagaRepository vagaRepository;

    @Autowired
    inscricaoRepository inscricaoRepository;

    @RequestMapping(value="/novaVaga", method=RequestMethod.GET)
    public String novaVaga() {
        return "cadastrarVaga";
    }

    @RequestMapping(value="/novaVaga", method=RequestMethod.POST)
    public String cadastroVaga(@Valid vagaModel vaga, BindingResult result, RedirectAttributes msg, @RequestParam("file") MultipartFile imagem) {
        if(result.hasErrors()) {
            msg.addFlashAttribute("erro", "Erro ao cadastrar. Por favor, preencha todos os campos");
            return "redirect:/novaVaga";
        }
        vaga.setData(LocalDate.now());

        try {
            if (!imagem.isEmpty()) {
                byte[] bytes = imagem.getBytes();
                Path caminho = Paths.get("./src/main/resources/static/img/"+imagem.getOriginalFilename());
                Files.write(caminho, bytes);
                vaga.setImagem(imagem.getOriginalFilename());
            }
        } catch (IOException e) {
            System.out.println("Erro imagem");
        }

        vagaRepository.save(vaga);
        msg.addFlashAttribute("sucesso", "Vaga cadastrada.");

        return "redirect:/novaVaga";
    }

    @RequestMapping(value="/imagem/{imagem}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getVagaImagens(@PathVariable("imagem") String imagem) throws IOException {
        File caminho = new File ("./src/main/resources/static/img/"+imagem);
        if (imagem != null || imagem.trim().length() > 0) {
            return Files.readAllBytes(caminho.toPath());
        }
        return null;
    }

    @RequestMapping(value="/listarVagas", method= RequestMethod.GET)
    public ModelAndView getVagas() {
        ModelAndView mv = new ModelAndView("listarVagas");
        List<vagaModel> vagas = vagaRepository.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public ModelAndView getVagasUsuario() {
        ModelAndView mv = new ModelAndView("index");
        List<vagaModel> vagas = vagaRepository.findVagasByStatus("Habilitado");
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value="/editarVaga/{id}", method=RequestMethod.GET)
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("editarVaga");
        Optional<vagaModel> vaga = vagaRepository.findById(id);
        List<String> categorias = Arrays.asList("Tempo integral", "Meio período", "Freelancer");
        List<String> estados = Arrays.asList("Habilitado", "Deshabilitado");

        mv.addObject("titulo", vaga.get().getTitulo());
        mv.addObject("empresa", vaga.get().getEmpresa());
        mv.addObject("categoria", vaga.get().getCategoria());
        mv.addObject("descricao", vaga.get().getDescricao());
        mv.addObject("contato", vaga.get().getContato());
        mv.addObject("status", vaga.get().getStatus());
        mv.addObject("id", vaga.get().getId());

        mv.addObject("categoriasList", categorias);
        mv.addObject("estadosList", estados);
        return mv;
    }

    @RequestMapping(value="/editarVaga/{id}", method=RequestMethod.POST)
    public String editarVagaBanco(vagaModel vaga, RedirectAttributes msg) {
        vagaModel vagaExistente = vagaRepository.findById(vaga.getId()).orElse(null);
        vagaExistente.setTitulo(vaga.getTitulo());
        vagaExistente.setEmpresa(vaga.getEmpresa());
        vagaExistente.setCategoria(vaga.getCategoria());
        vagaExistente.setDescricao(vaga.getDescricao());
        vagaExistente.setContato(vaga.getContato());
        vagaExistente.setStatus(vaga.getStatus());
        vagaRepository.save(vagaExistente);
        return "redirect:/listarVagas";
    }

    @RequestMapping(value="/excluirVaga/{id}", method=RequestMethod.GET)
    public String excluirVaga(@PathVariable("id") Long id) {
        vagaRepository.deleteById(id);
        return "redirect:/listarVagas";
    }

    @RequestMapping(value="/vermaisVaga/{id}", method=RequestMethod.GET)
    public ModelAndView vermaisVaga(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("vermaisVaga");
        Optional<vagaModel> vagas = vagaRepository.findById(id);
        mv.addObject("imagem", vagas.get().getImagem());
        mv.addObject("titulo", vagas.get().getTitulo());
        mv.addObject("data", vagas.get().getData());
        mv.addObject("empresa", vagas.get().getEmpresa());
        mv.addObject("descricao", vagas.get().getDescricao());
        mv.addObject("categoria", vagas.get().getCategoria());
        mv.addObject("status", vagas.get().getStatus());
        mv.addObject("contato", vagas.get().getContato());
        return mv;
    }

    @RequestMapping(value={"/categoria/{categoria}"}, method=RequestMethod.GET)
    public ModelAndView getVagasByCategoria(@PathVariable("categoria") String categoria) {
        ModelAndView mv = new ModelAndView("listarVagas");
        List<vagaModel> vagas = vagaRepository.findVagasByCategoria(categoria);
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value={"/pesquisar", "/categoria/{categoria}"}, method=RequestMethod.POST)
    public ModelAndView getVagasByEmpresa(@RequestParam("texto") String pesquisar) {
        ModelAndView mv = new ModelAndView("listarVagas");
        List<vagaModel> vagas = vagaRepository.findVagasByEmpresaLike("%"+pesquisar+"%");
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value={"/categoriaUs/{categoriaUs}"}, method=RequestMethod.GET)
    public ModelAndView getVagasByCategoriaUsuario(@PathVariable("categoriaUs") String categoriaUs) {
        ModelAndView mv = new ModelAndView("index");
        List<vagaModel> vagas = vagaRepository.findVagasByCategoria(categoriaUs);
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value={"/pesquisarUs", "/categoriaUs/{categoriaUs}"}, method=RequestMethod.POST)
    public ModelAndView getVagasByEmpresaUsuario(@RequestParam("texto") String pesquisar) {
        ModelAndView mv = new ModelAndView("index");
        List<vagaModel> vagas = vagaRepository.findVagasByEmpresaLike("%"+pesquisar+"%");
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value="/vermaisVaga/{id}", method=RequestMethod.POST)
    public String cadastroInscricao(@PathVariable("id") Long id, @Valid inscricaoModel novaInscricao, BindingResult result, RedirectAttributes msg, @RequestParam("file") MultipartFile imagem) {
        Optional<vagaModel> vagaOptional = vagaRepository.findById(id);
        vagaModel vaga = vagaOptional.get();
        if(result.hasErrors()) {
            msg.addFlashAttribute("erro", "Erro ao cadastrar. Por favor, preencha todos os campos");
            return "redirect:/vermaisVaga/{id}";
        }

        inscricaoModel inscricao = new inscricaoModel();
        inscricao.setNome(novaInscricao.getNome());
        inscricao.setEmail(novaInscricao.getEmail());
        inscricao.setLinkedin(novaInscricao.getLinkedin());
        inscricao.setVaga(vaga);

        try {
            if (!imagem.isEmpty()) {
                byte[] bytes = imagem.getBytes();
                Path caminho = Paths.get("./src/main/resources/static/img/"+imagem.getOriginalFilename());
                Files.write(caminho, bytes);
                inscricao.setImagem(imagem.getOriginalFilename());
            }
        } catch (IOException e) {
            System.out.println("Erro imagem");
        }

        // Asegurar que la vaga tiene una lista de inscripciones
        if (vaga.getInscricoes() == null) {
            vaga.setInscricoes(new ArrayList<>());
        }

        // Agregar la nueva inscripción a la lista de inscripciones de la vaga
        vaga.getInscricoes().add(inscricao);

        // Guardar la nueva inscripción y actualizar la vaga
        inscricaoRepository.save(inscricao);
        vagaRepository.save(vaga);
        msg.addFlashAttribute("sucesso", "Inscrição feita com sucesso.");

        return "redirect:/vermaisVaga/{id}";
    }
}
