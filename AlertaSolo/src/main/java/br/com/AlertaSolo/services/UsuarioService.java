package br.com.AlertaSolo.services;


import br.com.AlertaSolo.dto.UsuarioRequestDto;
import br.com.AlertaSolo.entity.Login;
import br.com.AlertaSolo.entity.Usuario;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.repository.LoginRepository;
import br.com.AlertaSolo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private LoginRepository loginRepository;

    public Usuario cadastrarUsuario(UsuarioRequestDto usuario){
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setNome(usuario.getNome());
        usuarioNovo.setCpf(usuario.getCpf());
        usuarioNovo.setIdade(usuario.getIdade());
        usuarioNovo.setCidade(usuario.getCidade());
        usuarioNovo.setUf(usuario.getUf());
        usuarioNovo.setDataCadastro(LocalDate.now());
        Login loginUsuario = new Login();
        try {
            System.out.println("Usuario cadastrado com sucesso!");
           usuarioRepository.save(usuarioNovo);
           loginUsuario.setId(usuarioNovo.getIdUsuario());
           loginUsuario.setEmail(usuario.getEmail());
           loginUsuario.setSenha(usuario.getSenha());
           loginRepository.save(loginUsuario);

        }catch (Exception e){
            System.out.println("Houve um erro ao tentar cadastrar o usuario");
           e.printStackTrace();
        }
        return usuarioNovo;
    }

    public void removerUsuario(long id) throws IdNaoEncontradoException{
        Usuario usuarioAchado= usuarioRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Usuario não encontrado"));

                usuarioRepository.deleteById(id);

            System.out.println("Usuario: " + usuarioAchado.getIdUsuario() + ", " + usuarioAchado + " deletado com sucesso!");

    }

    @Transactional
    public void atualizarDadosLoginUsuario(long id, String email, String senha) throws IdNaoEncontradoException {
        Login loginUsuarioAchado = loginRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Usuario não encontrado"));

        loginUsuarioAchado.setEmail(email);
        loginUsuarioAchado.setSenha(senha);

        System.out.println("Usuario: " + loginUsuarioAchado.getId() + ", "
                + " atualizado com sucesso para: " + loginUsuarioAchado.getEmail() + " " + loginUsuarioAchado.getSenha());

    }

    public Optional<Usuario> visualizarDadosUsuarioEspecifico(long id) {
      return usuarioRepository.findById(id);


    }

}
