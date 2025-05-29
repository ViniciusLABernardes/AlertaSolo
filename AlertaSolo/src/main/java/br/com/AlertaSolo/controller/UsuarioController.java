package br.com.AlertaSolo.controller;

import br.com.AlertaSolo.dto.UsuarioRequestDto;
import br.com.AlertaSolo.dto.UsuarioResponseDto;
import br.com.AlertaSolo.entity.Login;
import br.com.AlertaSolo.entity.Usuario;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.repository.LoginRepository;
import br.com.AlertaSolo.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuário", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LoginRepository loginRepository;

    @Operation(summary = "Cadastrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario usuarioSalva = usuarioService.cadastrarUsuario(usuarioRequestDto);
        return ResponseEntity.ok(new UsuarioResponseDto(usuarioSalva.getIdUsuario(), usuarioSalva.getNome(),
                usuarioSalva.getCpf(), usuarioSalva.getIdade(), usuarioSalva.getCidade(), usuarioRequestDto.getUf(),
                usuarioSalva.getDataCadastro(), usuarioRequestDto.getEmail(), usuarioRequestDto.getSenha()));
    }

    @Operation(summary = "Buscar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> lerUsuarioEspecifica(
            @Parameter(description = "ID do usuário a ser buscado") @PathVariable long id) throws IdNaoEncontradoException {
        Login loginUser = loginRepository.findById(id).orElseThrow(()-> new IdNaoEncontradoException("Id do usuário não encontrado"));
        return usuarioService.visualizarDadosUsuarioEspecifico(id)

                .map(usuario -> ResponseEntity.ok(
                        new UsuarioResponseDto(usuario.getIdUsuario(), usuario.getNome(),
                                usuario.getCpf(), usuario.getIdade(), usuario.getCidade(), usuario.getUf(),
                                usuario.getDataCadastro(), loginUser.getEmail(), loginUser.getSenha()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar dados de login do usuário (e-mail e senha)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarDadosLoginUsuario(
            @Parameter(description = "ID do usuário a ser atualizado") @PathVariable long id,
            @Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {

        try {
            usuarioService.atualizarDadosLoginUsuario(id, usuarioRequestDto.getEmail(), usuarioRequestDto.getSenha());
            return ResponseEntity.noContent().build();
        } catch (IdNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> deletarUsuario(
            @Parameter(description = "ID do usuário a ser deletado") @PathVariable long id) {

        if (usuarioService.visualizarDadosUsuarioEspecifico(id).isPresent()) {
            try {
                usuarioService.removerUsuario(id);
                return ResponseEntity.noContent().build();
            } catch (IdNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
