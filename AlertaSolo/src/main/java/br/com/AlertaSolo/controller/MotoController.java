package br.com.AlertaSolo.controller;

import br.com.UWbike.dto.MotoRequestDto;
import br.com.UWbike.dto.MotoResponseDto;
import br.com.UWbike.entity.Moto;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.services.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoResponseDto> cadastrarMoto(@Valid @RequestBody MotoRequestDto motoRequestDto){
        Moto moto = new Moto(motoRequestDto.getModelo(), motoRequestDto.getPlaca(), motoRequestDto.getChassi());
        Moto motoSalva = motoService.salvarMoto(moto);
        return ResponseEntity.ok(new MotoResponseDto(motoSalva.getId_moto(),motoSalva.getModelo(),motoSalva.getPlaca(),motoSalva.getChassi()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponseDto> lerMotoEspecifica(@PathVariable Long id){
        return motoService.visualizarDadosMotoEspecifica(id)
                .map(moto -> ResponseEntity.ok(
                        new MotoResponseDto(moto.getId_moto(),moto.getModelo(),moto.getPlaca(),moto.getChassi()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponseDto> atualizarMoto(@PathVariable Long id, @Valid @RequestBody MotoRequestDto motoRequestDto) {

        Moto novaMoto = new Moto(motoRequestDto.getModelo(), motoRequestDto.getPlaca(), motoRequestDto.getChassi());

        try {
            motoService.atualizarDadosMoto(id, novaMoto);
            return ResponseEntity.ok(new MotoResponseDto(id, motoRequestDto.getModelo(), motoRequestDto.getPlaca(), motoRequestDto.getChassi()));
        } catch (IdNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MotoResponseDto> deletarMoto(@PathVariable Long id){
        if(motoService.visualizarDadosMotoEspecifica(id).isPresent()){
                try {
                motoService.removerMoto(id);
                return ResponseEntity.noContent().build();
            } catch (IdNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
