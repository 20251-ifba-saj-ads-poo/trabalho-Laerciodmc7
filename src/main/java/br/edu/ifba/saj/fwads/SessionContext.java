package br.edu.ifba.saj.fwads;

import br.edu.ifba.saj.fwads.model.Usuario;

public final class SessionContext {

    private static Usuario usuarioLogado;

    private SessionContext() {
    }

    public static void login(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void logout() {
        usuarioLogado = null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static boolean isLogado() {
        return usuarioLogado != null;
    }
}
