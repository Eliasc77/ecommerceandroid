package edu.pe.idat.pfinal.model;

public class SearchModel {

    private int idproducto;
    private int idcategoria;
    private String nombreprod;
    private String foto;
    private String descripcion;
    private String estado;
    private double precio;
    private int stock;

    public SearchModel() {
    }

    public SearchModel(int idproducto, int idcategoria, String nombreprod, String foto, String descripcion, String estado, double precio, int stock) {
        this.idproducto = idproducto;
        this.idcategoria = idcategoria;
        this.nombreprod = nombreprod;
        this.foto = foto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreprod() {
        return nombreprod;
    }

    public void setNombreprod(String nombreprod) {
        this.nombreprod = nombreprod;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
