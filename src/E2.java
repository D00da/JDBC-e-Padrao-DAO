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
                case "1":
                    List<Categoria> categorias = new ArrayList<>();
                    categorias = categoriaDAO.obterTodas();
                    for(int i = 0; i < categorias.size(); i++){
                        System.out.println(categorias.get(i).getId()+". "+categorias.get(i).getDescricao());
                    }
                    break;
                case "2":
                    System.out.println("Qual o id da categoria? ");
                    id = scanner.nextLine();
                    String resultado = categoriaDAO.obterCategoria(Integer.parseInt(id));
                    if(resultado!=null){
                        System.out.println("Descrição: "+resultado);
                    }
                    else{
                        System.out.println("Categoria não encontrada!");
                    }
                    break;
                case "3":
                    System.out.println("Insira a descrição da categoria: ");
                    descricao = scanner.nextLine();
                    if(categoriaDAO.inserir(descricao)){
                        System.out.println("Categoria adicionada com sucesso!");
                    }
                    else{
                        System.out.println("Não possível foi adicionar a categoria!");
                    }
                    break;
                case "4":
                    System.out.println("Qual o id da categoria ? ");
                    id = scanner.nextLine();              
                    System.out.println("Descrição atual: "+categoriaDAO.obterCategoria(Integer.parseInt(id)));
                    System.out.println("Qual a nova descrição da categoria ? ");
                    descricao = scanner.nextLine();
                    if(categoriaDAO.atualizar(descricao, Integer.parseInt(id))){
                        System.out.println("Categoria atualizada com sucesso!");
                    }
                    else{
                        System.out.println("Não possível atualizar a categoria!");
                    }
                    break;
                case "5":
                    System.out.println("Qual o id da categoria ? ");
                    id = scanner.nextLine();
                    if(categoriaDAO.remover(Integer.parseInt(id))){
                        System.out.println("Categoria removida com sucesso!");
                    }
                    else{
                        System.out.println("Não possível remover a categoria!");
                    }
                    break;
                case "6":
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
