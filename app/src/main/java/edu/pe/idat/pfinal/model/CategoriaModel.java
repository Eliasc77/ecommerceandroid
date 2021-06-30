package edu.pe.idat.pfinal.model;

public class CategoriaModel {

    private int idcategoria;
    private String descripcion;
    private String img;

    public CategoriaModel() {
    }

    public CategoriaModel(int idcategoria, String descripcion, String img) {
        this.idcategoria = idcategoria;
        this.descripcion = descripcion;
        this.img = img;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
