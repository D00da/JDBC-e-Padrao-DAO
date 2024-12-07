package e2;

import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List;

public class E2 {
    public static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean loop = true;
        String id;
        int intID;
        String descricao;
        while(loop){
            System.out.println("----------------------");
            System.out.println("Banco de Categorias");
            System.out.println("----------------------");
            System.out.println("1. Mostrar todas as categorias");
            System.out.println("2. Mostra categoria por ID");
            System.out.println("3. Inserir categoria");
            System.out.println("4. Atualizar categoria");
            System.out.println("5. Remover categoria");
            System.out.println("6. Sair");
            System.out.println("----------------------");
            
            System.out.println("Qual opção deseja escolher ? ");
            String opcao = scanner.nextLine();
            
            switch(opcao){
                case "1": //Listar todas
                    List<Categoria> categorias = categoriaDAO.obterTodas();
                    for(int i = 0; i < categorias.size(); i++){
                        System.out.println(categorias.get(i).getId()+". "+categorias.get(i).getDescricao());
                    }
                    break;
                case "2": //Mostrar categoria por ID
                    System.out.println("Qual o id da categoria? ");
                    id = scanner.nextLine();
                    intID = Integer.parseInt(id);
                    Categoria categoria = categoriaDAO.obterCategoria(intID);
                    if(categoria.getDescricao()!=null){
                        System.out.println("Descrição: "+categoria.getDescricao());
                    }
                    else{
                        System.out.println("Categoria não encontrada!");
                    }
                    break;
                case "3": //Inserir categoria
                    System.out.println("Insira a descrição da categoria: ");
                    descricao = scanner.nextLine();
                    if(categoriaDAO.inserir(descricao)){
                        System.out.println("Categoria adicionada com sucesso!");
                    }
                    else{
                        System.out.println("Não possível foi adicionar a categoria!");
                    }
                    break;
                case "4": //Atualizar categoria
                    System.out.println("Qual o id da categoria ? ");
                    id = scanner.nextLine();
                    intID = Integer.parseInt(id);
                    System.out.println("Descrição atual: "+categoriaDAO.obterCategoria(intID).getDescricao());
                    System.out.println("Qual a nova descrição da categoria ? ");
                    descricao = scanner.nextLine();
                    if(categoriaDAO.atualizar(descricao, intID)){
                        System.out.println("Categoria atualizada com sucesso!");
                    }
                    else{
                        System.out.println("Não possível atualizar a categoria!");
                    }
                    break;
                case "5": //Remover categoria
                    System.out.println("Qual o id da categoria ? ");
                    id = scanner.nextLine();
                    intID = Integer.parseInt(id);
                    if(categoriaDAO.remover(intID)){
                        System.out.println("Categoria removida com sucesso!");
                    }
                    else{
                        System.out.println("Não possível remover a categoria!");
                    }
                    break;
                case "6": //Fechar o programa
                    System.out.println("Saindo... ");
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
