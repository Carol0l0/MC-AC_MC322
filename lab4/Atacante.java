//vai ter um metodo abstrato base de atacar uma entidade e conseguir "matar" ela, (deletar no caso), vou deixar ser implementada apenas pelo blindado
//vai ser bem simples msm pra nao dar trabalho
public interface Atacante {
 
    void atacar(String alvo);
    
}
