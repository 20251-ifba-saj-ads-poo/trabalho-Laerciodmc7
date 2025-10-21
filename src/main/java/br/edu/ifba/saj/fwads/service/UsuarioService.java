package br.edu.ifba.saj.fwads.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import br.edu.ifba.saj.fwads.exception.CadastroInvalidoException;
import br.edu.ifba.saj.fwads.exception.LoginInvalidoException;
import br.edu.ifba.saj.fwads.model.Usuario;

public class UsuarioService extends Service<Usuario> {

    // Campo estático para armazenar a lista de resultados da busca
    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuario){
        UsuarioService.usuarioLogado = usuario;
    }

    public UsuarioService() {
        super(Usuario.class);
    }

    public Usuario validaLogin(String userName, String senha) throws LoginInvalidoException {
        try {
            return findByMap(Map.of("userName", userName, "senha", senha)).getFirst();
        } catch (NoSuchElementException e) {
            throw new LoginInvalidoException(
                "Não foi possível localizar o usuário " + userName + ", ou a senha esta errada");
        }
    }

    public void validaCadastro(Usuario usuario) throws CadastroInvalidoException{

        if(usuario.getNomeCompleto().length() < 5|| usuario.getNomeCompleto() == null || usuario.getNomeCompleto().isBlank()){
            throw new CadastroInvalidoException("O nome completo não pode ser vazio.");
        }
        if (usuario.getUserName().length() < 5 || usuario.getUserName() == null || usuario.getUserName().isBlank()) {
            throw new CadastroInvalidoException("O nome de usuário não pode ser vazio.");
        }
        if (usuario.getSenha().length() < 4 || usuario.getSenha().isBlank()) {
            throw new CadastroInvalidoException("A senha deve ter pelo menos 4 caracteres.");
        }
        if(usuario.getCpf().length() < 11 || usuario.getCpf().length() > 14 || usuario.getCpf().isBlank()){
            throw new CadastroInvalidoException("Cpf inválido.");
        }
        //cadastrando usuario
        create(usuario);
    }

}
