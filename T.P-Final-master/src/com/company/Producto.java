package com.company;

import Interface.ICategoria;

public class Producto implements ICategoria {
    private static int count = 0;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;
    private String marcaProducto;
    private String comentario;
    private String categoria;
    private int idProducto;
    private boolean isActivo;

    public Producto() {
        this.idProducto = ++count;
        isActivo = true;
    }

    /**
     * @param idProducto
     * @param nombreProducto
     * @param precioProducto
     * @param stockProducto
     * @param marcaProducto
     * @param comentario
     */

    public Producto(int idProducto, String nombreProducto, double precioProducto, int stockProducto,
                    String marcaProducto, String comentario, String categoria) {

        setIdProducto(count++);
        isActivo = true;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.marcaProducto = marcaProducto;
        this.comentario = comentario;
        setCategoria(categoria);
    }

    /**
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }
    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }
    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    /**
     * @return the precioProducto
     */
    public double getPrecioProducto() {
        return precioProducto;
    }
    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
    /**
     * @return the stockProducto
     */
    public int getStockProducto() {
        return stockProducto;
    }
    /**
     * @param stockProducto the stockProducto to set
     */
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }
    /**
     * @return the marcaProducto
     */
    public String getMarcaProducto() {
        return marcaProducto;
    }
    /**
     * @param marcaProducto the marcaProducto to set
     */
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }
    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria (){
        return categoria;
    }

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }

    public String muestraProducto() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ID: " + getIdProducto() + ", ");
        sb.append("Nombre: " + getNombreProducto() + ", ");
        sb.append("Marca: " + getMarcaProducto() + ", ");
        sb.append("Precio:$ " + getPrecioProducto() + ", ");
        sb.append("Stock: " + getStockProducto() + ", ");
        sb.append("Comentario: " + getComentario() + ", ");
        sb.append("Categoria: " + getCategoria());
        sb.append("]" + "\n");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Nombre: " + getNombreProducto() + ", ");
        sb.append("Marca: " + getMarcaProducto() + ", ");
        sb.append("Precio:$ " + getPrecioProducto() + ", ");
        sb.append("Categoria: " + getCategoria());
        sb.append("]");
        return sb.toString();
    }
}
