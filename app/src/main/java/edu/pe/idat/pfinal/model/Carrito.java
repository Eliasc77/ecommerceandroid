package edu.pe.idat.pfinal.model;

public class Carrito {


    private int idproducto;
    private String nombreprod;
    private double precioUnitario;
    private int cantidad;
    private String talla;
    private double subtotal;
    private String foto;

    public Carrito( int idproducto, String nombreprod, double precioUnitario, int cantidad, String talla, double subtotal, String foto) {

        this.idproducto = idproducto;
        this.nombreprod = nombreprod;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.talla = talla;
        this.subtotal = subtotal;
        this.foto = foto;
    }



    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreprod() {
        return nombreprod;
    }

    public void setNombreprod(String nombreprod) {
        this.nombreprod = nombreprod;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
