"use client";
import { useEffect, useRef, useState } from "react";
import { Button } from "primereact/button";
import { Column } from "primereact/column";
import { DataTable } from "primereact/datatable";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Toast } from "primereact/toast";
import { Toolbar } from "primereact/toolbar";
import { classNames } from "primereact/utils";
import axios from "axios";

interface Producto {
    id: string;
    nombre: string;
    precio: number;
    stock: number;
}

const ProductoPage = () => {
    const emptyProducto: Producto = { id: "", nombre: "", precio: 0, stock: 0 };

    const [productos, setProductos] = useState<Producto[] | null>(null);
    const [productoDialog, setProductoDialog] = useState(false);
    const [deleteProductoDialog, setDeleteProductoDialog] = useState(false);
    const [producto, setProducto] = useState<Producto>(emptyProducto);
    const [selectedProductos, setSelectedProductos] = useState<Producto[] | null>(null);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState("");
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<Producto[]>>(null);

    useEffect(() => {
        fetchProductos();
    }, []);

    const fetchProductos = async () => {
        try {
            const response = await axios.get("http://client-api:8080/api/productos");
            setProductos(response.data);
        } catch (error) {
            toast.current?.show({ severity: "error", summary: "Error", detail: "No se pudieron cargar los productos", life: 3000 });
        }
    };

    const openNew = () => {
        setProducto(emptyProducto);
        setSubmitted(false);
        setProductoDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setProductoDialog(false);
    };

    const hideDeleteProductoDialog = () => {
        setDeleteProductoDialog(false);
    };

    const saveProducto = async () => {
        setSubmitted(true);

        if (producto.nombre.trim() && producto.precio > 0) {
            try {
                if (producto.id) {
                    await axios.put(`http://client-api:8080/api/productos/${producto.id}`, producto);
                    toast.current?.show({ severity: "success", summary: "Éxito", detail: "Producto actualizado", life: 3000 });
                } else {
                    const response = await axios.post("http://client-api:8080/api/productos", producto);
                    setProductos([...(productos || []), response.data]);
                    toast.current?.show({ severity: "success", summary: "Éxito", detail: "Producto creado", life: 3000 });
                }
                fetchProductos();
                setProductoDialog(false);
                setProducto(emptyProducto);
            } catch (error) {
                toast.current?.show({ severity: "error", summary: "Error", detail: "No se pudo guardar el producto", life: 3000 });
            }
        }
    };

    const editProducto = (producto: Producto) => {
        setProducto({ ...producto });
        setProductoDialog(true);
    };

    const confirmDeleteProducto = (producto: Producto) => {
        setProducto(producto);
        setDeleteProductoDialog(true);
    };

    const deleteProducto = async () => {
        try {
            await axios.delete(`http://client-api:8080/api/productos/${producto.id}`);
            toast.current?.show({ severity: "success", summary: "Éxito", detail: "Producto eliminado", life: 3000 });
            fetchProductos();
            setDeleteProductoDialog(false);
        } catch (error) {
            toast.current?.show({ severity: "error", summary: "Error", detail: "No se pudo eliminar el producto", life: 3000 });
        }
    };

    const leftToolbarTemplate = () => (
        <Button label="Nuevo" icon="pi pi-plus" severity="success" onClick={openNew} />
    );

    const nameBodyTemplate = (rowData: Producto) => <span>{rowData.nombre}</span>;

    const priceBodyTemplate = (rowData: Producto) => <span>${rowData.precio.toFixed(2)}</span>;

    const stockBodyTemplate = (rowData: Producto) => <span>{rowData.stock}</span>;

    const actionBodyTemplate = (rowData: Producto) => (
        <>
            <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editProducto(rowData)} />
            <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteProducto(rowData)} />
        </>
    );

    const header = (
        <div className="flex justify-content-between align-items-center">
            <h5 className="m-0">Gestión de Productos</h5>
            <span className="p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Buscar..." />
            </span>
        </div>
    );

    const productoDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Guardar" icon="pi pi-check" text onClick={saveProducto} />
        </>
    );

    const deleteProductoDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" text onClick={hideDeleteProductoDialog} />
            <Button label="Sí" icon="pi pi-check" text onClick={deleteProducto} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} />

                    <DataTable
                        ref={dt}
                        value={productos || []}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        globalFilter={globalFilter}
                        header={header}
                        emptyMessage="No se encontraron productos."
                        responsiveLayout="scroll"
                    >
                        <Column field="nombre" header="Nombre" body={nameBodyTemplate} sortable />
                        <Column field="precio" header="Precio" body={priceBodyTemplate} sortable />
                        <Column field="stock" header="Stock" body={stockBodyTemplate} sortable />
                        <Column body={actionBodyTemplate} />
                    </DataTable>

                    <Dialog visible={productoDialog} style={{ width: "450px" }} header="Detalles del Producto" modal className="p-fluid" footer={productoDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            <label htmlFor="name">Nombre</label>
                            <InputText
                                id="name"
                                value={producto.nombre}
                                onChange={(e) => setProducto({ ...producto, nombre: e.target.value })}
                                required
                                className={classNames({ "p-invalid": submitted && !producto.nombre })}
                            />
                            {submitted && !producto.nombre && <small className="p-invalid">El nombre es obligatorio.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="precio">Precio</label>
                            <InputText
                                id="precio"
                                type="number"
                                value={producto.precio.toString()}
                                onChange={(e) => setProducto({ ...producto, precio: parseFloat(e.target.value) })}
                                required
                                className={classNames({ "p-invalid": submitted && producto.precio <= 0 })}
                            />
                            {submitted && producto.precio <= 0 && <small className="p-invalid">El precio debe ser mayor a 0.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="stock">Stock</label>
                            <InputText
                                id="stock"
                                type="number"
                                value={producto.stock.toString()}
                                onChange={(e) => setProducto({ ...producto, stock: parseInt(e.target.value, 10) })}
                                required
                                className={classNames({ "p-invalid": submitted && producto.stock <= 0 })}
                            />
                            {submitted && producto.stock <= 0 && <small className="p-invalid">El stock debe ser mayor a 0.</small>}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProductoDialog} style={{ width: "450px" }} header="Confirmar" modal footer={deleteProductoDialogFooter} onHide={hideDeleteProductoDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: "2rem" }} />
                            {producto && <span>¿Estás seguro de que deseas eliminar a <b>{producto.nombre}</b>?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default ProductoPage;
