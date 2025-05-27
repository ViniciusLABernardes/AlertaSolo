package br.com.AlertaSolo.controller;

import br.com.UWbike.dto.MotoResponseDto;
import br.com.UWbike.dto.PatioRequestDto;
import br.com.UWbike.dto.PatioResponseDto;
import br.com.UWbike.entity.Patio;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.services.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patio")
public class PatioController {


    @Autowired
    private PatioService patioService;


   @PostMapping
    public ResponseEntity<PatioResponseDto> cadastrarPatio(@Valid @RequestBody PatioRequestDto patioRequestDto){

        Patio patio = new Patio(patioRequestDto.getLogradouro(), patioRequestDto.getNumero(), patioRequestDto.getComplemento(),
                patioRequestDto.getCep(), patioRequestDto.getCidade(),
                patioRequestDto.getUf(), patioRequestDto.getPais(), patioRequestDto.getLotacao());

        Patio patioSalvo = patioService.salvarPatio(patio);

        return ResponseEntity.ok(new PatioResponseDto(patioSalvo.getIdPatio(), patioSalvo.getLogradouro(),
                patioSalvo.getNumero(),patioSalvo.getComplemento(), patioSalvo.getCep(),
                patioSalvo.getCidade(), patioSalvo.getUf(), patioSalvo.getPais(), patioSalvo.getLotacao()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioResponseDto> lerPatioEspecifica(@PathVariable Long id){

        return patioService.visualizarDadosPatioEspecifica(id)
                .map(patio -> ResponseEntity.ok(
                        new PatioResponseDto(patio.getIdPatio(),patio.getLogradouro(),patio.getNumero(),
                                patio.getComplemento(), patio.getCep(), patio.getCidade(),
                                patio.getUf(), patio.getPais(),patio.getLotacao()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioResponseDto> atualizarPatio(@PathVariable Long id, @Valid @RequestBody PatioRequestDto patioRequestDto) {

        Patio novoPatio = new Patio(patioRequestDto.getLogradouro(), patioRequestDto.getNumero(), patioRequestDto.getComplemento(),
                patioRequestDto.getCep(), patioRequestDto.getCidade(), patioRequestDto.getUf(), patioRequestDto.getPais(), patioRequestDto.getLotacao());

        try {
            patioService.atualizarDadosPatio(id, novoPatio);

            return ResponseEntity.ok(new PatioResponseDto(id, patioRequestDto.getLogradouro(),
                    patioRequestDto.getNumero(), patioRequestDto.getComplemento(), patioRequestDto.getCep(),
                    patioRequestDto.getCidade(), patioRequestDto.getUf(), patioRequestDto.getPais(), patioRequestDto.getLotacao()));

        } catch (IdNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatioResponseDto> deletarPatio(@PathVariable Long id){
        if(patioService.visualizarDadosPatioEspecifica(id).isPresent()){
            try {
                patioService.removerPatio(id);
                return ResponseEntity.noContent().build();
            } catch (IdNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PatioResponseDto>> listarPatios() {
        List<PatioResponseDto> patios = patioService.listarPatios();
        return ResponseEntity.ok(patios);
    }

    @GetMapping("/{id}/motos")
    public  ResponseEntity<List<MotoResponseDto>> listarMotosQueJaPassaramEstaoNoPatio(@PathVariable Long id) throws IdNaoEncontradoException {
        List<MotoResponseDto> motos = patioService.visualizarMotosQueJaPassaramEstaoNoPatio(id);
        return ResponseEntity.ok(motos);

    }

}
