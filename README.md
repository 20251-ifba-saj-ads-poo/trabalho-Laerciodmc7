# Reavaliação de Relatório - trabalho-Laerciodmc7

## Descrição do Trabalho

Biblioteca Digital

Crie um sistema de biblioteca digital com livros, autores e categorias. Os livros pertencem a autores, e cada livro pode ser classificado em uma ou mais categorias. Os usuários podem fazer pesquisas por autor ou categoria e visualizar os livros disponíveis.

## Resumo da Pontuação (Reavaliação Crítica)

| Critério | Pontuação Máxima | Pontuação Obtida | Comentários |
| --- | --- | --- | --- |
| **Interface Gráfica (JavaFX)** | **20** | **15** | As telas existem, mas a funcionalidade principal de listar empréstimos está quebrada devido a um bug no controller. |
| **Camada de Negócio** | **30** | **10** | A lógica de negócio está fundamentalmente comprometida, operando sobre dados incorretos (usuário vazio) ou desatualizados e com mecanismos de sessão quebrados. |
| **Camada de Dados** | **20** | **20** |  |
| **Separação em Camadas** | **20** | **5** | A comunicação entre as camadas é inexistente ou falha. O estado do usuário não flui do login para as outras telas, e os controllers não obtêm dados corretamente dos serviços. |
| **Boas Práticas** | **10** | **2** | O uso de campos estáticos para estado global, métodos vazios e a falta de um fluxo de dados coerente representam violações severas de boas práticas de programação. |
| **Total** | **100** | **52** | **Reprovado** - O sistema possui falhas arquiteturais e bugs críticos que impedem o funcionamento de suas funcionalidades principais. |

---

### Falhas Críticas Identificadas

#### 1. [FUNCIONALIDADE QUEBRADA] Listagem de Empréstimos Inoperante
A tela de empréstimos não funciona. O controller `ListEmprestimosController` instancia um novo usuário vazio em vez de usar o usuário logado.

**Código do Aluno (`ListEmprestimosController.java`):**
```java
// ...
Usuario usuario = new Usuario(); // ERRO CRÍTICO: Cria um usuário novo e vazio.

@FXML
public void initialize() {
    // ...
    loadLivroList();
}

public void loadLivroList(){
    // A lista de empréstimos de um usuário novo será sempre vazia.
    tblEmprestimos.setItems(FXCollections.observableArrayList(usuario.getEmprestimos()));
}
```
**Impacto:** A principal funcionalidade de um usuário ver seus empréstimos está completamente quebrada. A tabela sempre aparecerá vazia.

#### 2. [ARQUITETURA FALHA] Mecanismo de Login e Sessão Quebrado
O sistema tenta gerenciar a sessão do usuário com um campo estático, o que já é uma má prática. No entanto, a implementação está triplamente falha:

*   **a) O método para definir o usuário logado está vazio:** O `LoginController` chama `usuarioService.setUsuarioLogado(usuario)`, mas o método não faz nada, então o usuário nunca é armazenado.

    **Código do Aluno (`UsuarioService.java`):**
    ```java
    public void setUsuarioLogado(Usuario usuario){
        // ERRO: O método está vazio. Deveria ser: UsuarioService.usuarioLogado = usuario;
    }
    ```

*   **b) O estado é gerenciado por um campo `static`:** O uso de `private static Usuario usuarioLogado;` cria um estado global, propenso a erros de concorrência e que acopla fortemente os componentes da aplicação.

*   **c) Os dados não são atualizados:** O sistema não busca dados atualizados do banco. Ele passa a mesma instância do objeto `Usuario` entre as telas, o que pode levar à exibição de dados desatualizados (stale data).

**Impacto:** O sistema não tem um mecanismo funcional para saber qual usuário está logado. Qualquer funcionalidade que dependa do usuário (ver perfil, listar empréstimos, etc.) está fadada a falhar.


### Conclusão da Reavaliação
O projeto apresenta uma série de falhas críticas interligadas que o tornam não funcional. Os erros demonstram uma compreensão insuficiente sobre gerenciamento de estado, fluxo de dados entre camadas e boas práticas de arquitetura de software. A pontuação foi drasticamente reduzida para refletir a gravidade dos problemas encontrados.