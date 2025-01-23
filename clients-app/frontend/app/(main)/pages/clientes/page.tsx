"use client";
import { useEffect, useRef, useState } from 'react';
import axios from "axios";
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';

interface Cliente {
    id: number;
    nombre: string;
    correo: string;
}

const ClientesPage = () => {
    const emptyCliente: Cliente = { id: 0, nombre: '', correo: '' };

    const [clientes, setClientes] = useState<Cliente[]>([]);
    const [clienteDialog, setClienteDialog] = useState(false);
    const [deleteClienteDialog, setDeleteClienteDialog] = useState(false);
    const [cliente, setCliente] = useState<Cliente>(emptyCliente);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState('');
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<Cliente[]>>(null);

    // Obtener clientes al cargar el componente
    useEffect(() => {
        fetchClientes();
    }, []);

    const fetchClientes = async () => {
        try {
            const response = await axios.get("http://client-api:8080/api/clientes");
            console.log("Data recibida ",response.data)
            setClientes(response.data);
        } catch (error) {
            toast.current?.show({ severity: 'error', summary: 'Error', detail: 'No se pudieron cargar los clientes', life: 3000 });
        }
    };

    const saveCliente = async () => {
        setSubmitted(true);

        if (cliente.nombre.trim() && cliente.correo.trim()) {
            try {
                let response;
                if (cliente.id) {
                    response = await axios.put(`http://client-api:8080/api/clientes/${cliente.id}`, cliente);
                    toast.current?.show({ severity: 'success', summary: 'Éxito', detail: 'Cliente actualizado', life: 3000 });
                } else {
                    response = await axios.post('http://client-api:8080/api/clientes', cliente);
                    toast.current?.show({ severity: 'success', summary: 'Éxito', detail: 'Cliente creado', life: 3000 });
                }

                fetchClientes(); // Refrescar la lista de clientes
                setClienteDialog(false);
                setCliente(emptyCliente);
            } catch (error) {
                toast.current?.show({ severity: 'error', summary: 'Error', detail: 'No se pudo guardar el cliente', life: 3000 });
            }
        }
    };

    const deleteCliente = async () => {
        try {
            await axios.delete(`http://client-api:8080/api/clientes/${cliente.id}`);
            toast.current?.show({ severity: 'success', summary: 'Éxito', detail: 'Cliente eliminado', life: 3000 });
            fetchClientes(); // Refrescar la lista
            setDeleteClienteDialog(false);
        } catch (error) {
            toast.current?.show({ severity: 'error', summary: 'Error', detail: 'No se pudo eliminar el cliente', life: 3000 });
        }
    };

    const editCliente = (cliente: Cliente) => {
        setCliente({ ...cliente });
        setClienteDialog(true);
    };

    const confirmDeleteCliente = (cliente: Cliente) => {
        setCliente(cliente);
        setDeleteClienteDialog(true);
    };

    const openNew = () => {
        setCliente(emptyCliente);
        setSubmitted(false);
        setClienteDialog(true);
    };

    const nameBodyTemplate = (rowData: Cliente) => <span>{rowData.nombre}</span>;

const emailBodyTemplate = (rowData: Cliente) => <span>{rowData.correo}</span>;

const actionBodyTemplate = (rowData: Cliente) => (
    <>
        <Button icon="pi pi-pencil" className="mr-2" onClick={() => editCliente(rowData)} />
        <Button icon="pi pi-trash" severity="warning" onClick={() => confirmDeleteCliente(rowData)} />
    </>
);

const header = (
    <div className="flex justify-content-between align-items-center">
        <h5 className="m-0">Gestión de Clientes</h5>
        <span className="p-input-icon-left">
            <i className="pi pi-search" />
            <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Buscar..." />
        </span>
    </div>
);

    // Otros métodos como `hideDialog`, `hideDeleteClienteDialog`, etc., permanecen iguales

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={() => <Button label="Nuevo" icon="pi pi-plus" onClick={openNew} />} />

                    <DataTable
    ref={dt}
    value={clientes}
    dataKey="id"
    paginator
    rows={10}
    rowsPerPageOptions={[5, 10, 25]}
    className="datatable-responsive"
    globalFilter={globalFilter}
    header={header}
    emptyMessage="No se encontraron clientes."
    responsiveLayout="scroll"
>
    <Column field="name" header="Nombre" body={nameBodyTemplate} sortable />
    <Column field="email" header="Correo" body={emailBodyTemplate} sortable />
    <Column body={actionBodyTemplate} />
</DataTable>

                    <Dialog visible={clienteDialog} header="Detalles del Cliente" onHide={() => setClienteDialog(false)}>
                        <div className="field">
                            <label htmlFor="name">Nombre</label>
                            <InputText id="name" value={cliente.nombre} onChange={(e) => setCliente({ ...cliente, nombre: e.target.value })} />
                        </div>
                        <div className="field">
                            <label htmlFor="email">Correo</label>
                            <InputText id="email" value={cliente.correo} onChange={(e) => setCliente({ ...cliente, correo: e.target.value })} />
                        </div>
                        <Button label="Guardar" onClick={saveCliente} />
                    </Dialog>

                    <Dialog visible={deleteClienteDialog} header="Confirmar" onHide={() => setDeleteClienteDialog(false)}>
                        <p>¿Estás seguro de eliminar a {cliente.nombre}?</p>
                        <Button label="Sí" onClick={deleteCliente} />
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default ClientesPage;

