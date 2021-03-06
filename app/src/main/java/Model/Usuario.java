package Model;

public class Usuario {

    private String id;
    private String nome;
    private String login;
    private String telefone;
    private String senha;
    private boolean adm;
    private boolean funcionario;
    private String foto;

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setTelefone(String telefone){this.telefone = telefone; }

    public String getTelefone(){ return this.telefone; }

    public String getSenha(){
        return this.senha;
    }

    public void  setSenha(String senha){
        this.senha = senha;
    }

    public String getId(){
        return  this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
