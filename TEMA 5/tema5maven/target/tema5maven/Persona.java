public class Persona implements Serializable {
    private int id;
    private String nombre;
    private boolean casado;
    private String sexo;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

}
