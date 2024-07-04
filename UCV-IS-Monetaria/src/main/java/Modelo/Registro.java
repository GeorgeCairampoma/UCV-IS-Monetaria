package Modelo;

public class Registro {
    private String usuario, contraseña;
    private String contraseñaex;
    
    public Registro(){};
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseñaex() {
        return contraseñaex;
    }

    public void setContraseñaex(String contraseñaex) {
        this.contraseñaex = contraseñaex;
    }
    
    public Object[] RegistroUsuarios(int numeracion){
        Object[] fila={numeracion,usuario,contraseña};
        return fila;
    }
    
}