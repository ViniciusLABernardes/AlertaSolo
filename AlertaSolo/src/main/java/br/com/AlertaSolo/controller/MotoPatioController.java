package br.com.AlertaSolo.controller;

import br.com.UWbike.dto.MotoPatioRequestDto;
import br.com.UWbike.dto.MotoPatioResponseDto;
import br.com.UWbike.dto.PosicaoRequestDto;
import br.com.UWbike.entity.Moto;
import br.com.UWbike.entity.MotoPatio;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.services.MotoPatioService;
import br.com.UWbike.services.MotoService;
import br.com.UWbike.services.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moto-patio")
public class MotoPatioController {

    @Autowired
    private MotoPatioService motoPatioService;

    @Autowired
    private MotoService motoService;

    @Autowired
    private PatioService patioService;



    @PostMapping
    public ResponseEntity<MotoPatioResponseDto> adicionarMotoAoPatio(@RequestBody @Valid MotoPatioRequestDto dto) {
        try {
            MotoPatio motoPatio = motoPatioService.adicionarMotoAoPatio(dto.getIdMoto(),dto.getIdPatio());

            return ResponseEntity.ok(new MotoPatioResponseDto(motoPatio.getId(),motoPatio.getMoto(),motoPatio.getPatio(),
                    motoPatio.getDataHoraEntrada(),motoPatio.getDataHoraSaida()));

        } catch (IdNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoPatioResponseDto> adicionarSaidaDeMoto(@PathVariable Long id){
        try {
            MotoPatio motoPatio = motoPatioService.adicionarSaidaMoto(id);

            return ResponseEntity.ok(new MotoPatioResponseDto(motoPatio.getId(),motoPatio.getMoto(),motoPatio.getPatio(),
                    motoPatio.getDataHoraEntrada(),motoPatio.getDataHoraSaida()));

        } catch (IdNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/posicao")
    public ResponseEntity<Moto> calcularPosicaoMoto(@Valid @RequestBody PosicaoRequestDto posicaoRequestDto) {

        try {
            Moto motoSimulada = motoPatioService.calcularPosicaoMoto(posicaoRequestDto.getIdMoto(), posicaoRequestDto.getIdPatio(),
                    posicaoRequestDto.getD1(), posicaoRequestDto.getD2(),
                    posicaoRequestDto.getD3());
            return ResponseEntity.ok(motoSimulada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}


