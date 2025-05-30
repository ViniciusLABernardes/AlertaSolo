package br.com.AlertaSolo.controller;

import br.com.AlertaSolo.dto.LocalRiscoRequestDto;
import br.com.AlertaSolo.dto.LocalRiscoResponseDto;
import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.services.LocalRiscoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local-risco")
public class LocalRiscoController {

    @Autowired
    private LocalRiscoService localRiscoService;

    @Operation(summary = "Cadastrar um novo local de risco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local de risco cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<LocalRiscoResponseDto> cadastrarLocalRisco(
            @Valid @RequestBody LocalRiscoRequestDto localRiscoRequestDto) {

        LocalRisco localRisco = new LocalRisco(localRiscoRequestDto.getNomeLocal(), localRiscoRequestDto.getLatitude(),
                localRiscoRequestDto.getLongitude(), localRiscoRequestDto.getCidade(), localRiscoRequestDto.getUf(),
                localRiscoRequestDto.getGrauRisco(), localRiscoRequestDto.getAtivo());

        LocalRisco localRiscoSalvo = localRiscoService.salvarLocalRisco(localRisco);

        return ResponseEntity.ok(new LocalRiscoResponseDto(localRiscoSalvo.getIdLocal(), localRiscoSalvo.getNomeLocal(),
                localRiscoSalvo.getLatitude(), localRiscoSalvo.getLongitude(),
                localRiscoSalvo.getCidade(), localRiscoSalvo.getUf(), localRiscoSalvo.getGrauRisco(), localRiscoSalvo.getAtivo()));
    }

    @Operation(summary = "Buscar um local de risco específico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local de risco encontrado"),
            @ApiResponse(responseCode = "404", description = "Local de risco não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocalRiscoResponseDto> lerLocalRiscoEspecifico(
            @Parameter(description = "ID do local de risco") @PathVariable long id) {

        return localRiscoService.visualizarDadosLocalRiscoEspecifico(id)
                .map(localRisco -> ResponseEntity.ok(
                        new LocalRiscoResponseDto(localRisco.getIdLocal(), localRisco.getNomeLocal(),
                                localRisco.getLatitude(), localRisco.getLongitude(), localRisco.getCidade(),
                                localRisco.getUf(), localRisco.getGrauRisco(), localRisco.getAtivo(),localRisco.getSensores()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar dados de um local de risco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local de risco atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Local de risco não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LocalRiscoResponseDto> atualizarLocalRisco(
            @Parameter(description = "ID do local de risco") @PathVariable long id,
            @Valid @RequestBody LocalRiscoRequestDto localRiscoRequestDto) {

        LocalRisco novoLocalRisco = new LocalRisco(localRiscoRequestDto.getNomeLocal(), localRiscoRequestDto.getLatitude(),
                localRiscoRequestDto.getLongitude(), localRiscoRequestDto.getCidade(), localRiscoRequestDto.getUf(),
                localRiscoRequestDto.getGrauRisco(), localRiscoRequestDto.getAtivo());

        try {
            localRiscoService.atualizarDadosLocalRisco(id, novoLocalRisco);

            return ResponseEntity.ok(new LocalRiscoResponseDto(id, localRiscoRequestDto.getNomeLocal(),
                    localRiscoRequestDto.getLatitude(), localRiscoRequestDto.getLongitude(),
                    localRiscoRequestDto.getCidade(), localRiscoRequestDto.getUf(),
                    localRiscoRequestDto.getGrauRisco(), localRiscoRequestDto.getAtivo()));

        } catch (IdNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um local de risco por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Local de risco deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Local de risco não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LocalRiscoResponseDto> deletarLocalRisco(
            @Parameter(description = "ID do local de risco") @PathVariable long id) {

        if (localRiscoService.visualizarDadosLocalRiscoEspecifico(id).isPresent()) {
            try {
                localRiscoService.removerLocalRisco(id);
                return ResponseEntity.noContent().build();
            } catch (IdNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar todos os locais de risco cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de locais de risco retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<LocalRiscoResponseDto>> listarLocalRiscos() {
        List<LocalRiscoResponseDto> localRiscos = localRiscoService.listarLocalRiscos();
        try{
            return ResponseEntity.ok(localRiscos);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}

