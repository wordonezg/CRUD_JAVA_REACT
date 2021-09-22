import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import {Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';
import { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrashAlt } from "@fortawesome/free-solid-svg-icons";


const url = "http://localhost:8080/producto/listaProductos";
const urlPost = "http://localhost:8080/producto/crearProducto";
const urlPut = "http://localhost:8080/producto/actualizarProducto";
const urlDelete = "http://localhost:8080/producto/borrarProducto";

class App extends Component{
  state={
    data:[],
    modalInsertar: false,
    modalEliminar: false,
    form:{
      producto:'',
      id_marca:0,
      descripcion:'',
      precio_costo:0,
      precio_venta:0,
      existencia:0,
      
    },
    tipoModal: '',
  }

  modalEliminar=()=>{
    this.setState({modalEliminar:!this.state.modalEliminar});
  }

  modalInsertar=()=>{
    this.setState({modalInsertar:!this.state.modalInsertar});
  }

  seleccionarProducto=(producto)=>{
    this.setState({
      tipoModal:'actualizar',
      form:{
        id_producto: producto.id_producto,
        producto: producto.producto,
        id_marca: producto.id_marca,
        descripcion: producto.descripcion,
        precio_costo: producto.precio_costo,
        precio_venta: producto.precio_venta,
        existencia: producto.existencia
      }

    })
  }

  peticionesGet=()=>{
    axios.get(url).then(response=>{
      this.setState({data: response.data});
    })
  }

  handleChange=async (e)=>{
    e.persist();
    await this.setState({
      form:{
        ...this.state.form,
        [e.target.name]: e.target.value
      }
    });
  }

  peticionPost=async ()=>{
    delete this.state.form.id_producto;
    await axios.post(urlPost,this.state.form).then(response=>{
      this.modalInsertar();
      this.peticionesGet();
    }).catch((error)=>{
      console.log(error);
    });
  }

  peticionPut=async ()=>{
    console.log(this.state.form);
    await axios.put(urlPut+"/"+this.state.form.id_producto,this.state.form).then(response=>{
      this.modalInsertar();
      this.peticionesGet();
    }).catch((error)=>{
      console.log(error);
    });
  }

  peticionDelete=async ()=>{
    await axios.delete(urlDelete+"/"+this.state.form.id_producto).then(response=>{
      this.modalEliminar();
      this.peticionesGet();
    }).catch((error)=>{
      console.log(error);
    });
  }

  componentDidMount() {
    this.peticionesGet();
  }
  render(){
    const {form}=this.state;
    return (
      <div className="App">
        <button type="button" className="btn btn-success" onClick={()=>{this.setState({form:null,tipoModal:'insertar'}); this.modalInsertar()}}>Agregar Producto</button>
        <table className="table">
          <thead>
            <tr>
              <th>Codigo</th>
              <th>Producto</th>
              <th>Marca</th>
              <th>Descripción</th>
              <th>Costo</th>
              <th>Precio</th>
              <th>Existencia</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          {this.state.data.map(producto=>{
            return(
              <tr>
                <td>{producto.id_producto}</td>
                <td>{producto.producto}</td>
                <td>{producto.id_marca}</td>
                <td>{producto.descripcion}</td>
                <td>{producto.precio_costo}</td>
                <td>{producto.precio_venta}</td>
                <td>{producto.existencia}</td>
                <td>
                  <button type="button" className="btn btn-primary" onClick={()=>{this.seleccionarProducto(producto); this.modalInsertar();}} ><FontAwesomeIcon icon={faEdit}/></button>
                  <button type="button" className="btn btn-danger" onClick={()=>{this.seleccionarProducto(producto);this.modalEliminar();}}><FontAwesomeIcon icon={faTrashAlt}/></button>
                </td>
              </tr>
            )
          })}
          </tbody>
        </table>
        <Modal isOpen={this.state.modalInsertar}>
          <ModalHeader style={{display:'block'}}>
            <span style={{float:'right'}}>x</span>
          </ModalHeader>
          <ModalBody>
            <div className="form-group">
              <label htmlFor="id">ID</label>
              <input className="form-control" type="text" name="id_producto" id="id_producto" readOnly value={form?form.id_producto:this.state.data.length+1} />

              <label htmlFor="producto">Producto</label>
              <input className="form-control" type="text" name="producto" id="producto" onChange={this.handleChange} value={form?form.producto:''} />

              <label htmlFor="marca">Marca</label>
              <select className="form-control" name="id_marca" id="id_marca" onChange={this.handleChange} value={form?form.id_marca:0}>
                <option value="0">-- Seleccione --</option>
                <option value="1">Logitech</option>
                <option value="2">Samsung</option>
              </select>

              <label htmlFor="descripcion">Descripción</label>
              <input className="form-control" type="text" name="descripcion" id="descripcion" onChange={this.handleChange} value={form?form.descripcion:''}/>

              <label htmlFor="precio_costo">Costo</label>
              <input className="form-control" type="number" step="0.01" name="precio_costo" id="precio_costo" onChange={this.handleChange} value={form?form.precio_costo:0} />

              <label htmlFor="precio_venta">Precio</label>
              <input className="form-control" type="number" step="0.01" name="precio_venta" id="precio_venta" onChange={this.handleChange} value={form?form.precio_venta:0} />

              <label htmlFor="existencia">Existencia</label>
              <input className="form-control" type="number" name="existencia" id="existencia" onChange={this.handleChange} value={form?form.existencia:0} />
            </div>
          </ModalBody>
          <ModalFooter>
            {this.state.tipoModal=='insertar'?
              <button className="btn btn-success" onClick={()=>this.peticionPost()}>
                Insertar
              </button>:<button className="btn btn-primary" onClick={()=>this.peticionPut()}>
                Actualizar
              </button>
            }
            
            <button type="button" className="btn btn-dark" onClick={()=>this.modalInsertar()}>Cancelar</button>
            
          </ModalFooter>
        </Modal>
        <Modal isOpen={this.state.modalEliminar}>
          <ModalBody>
            ¿Seguro que desea eliminar el producto {form && form.producto} ? 
          </ModalBody>
          <ModalFooter>
            <button type="button" className="btn btn-danger" onClick={()=>{this.peticionDelete()}}>Sí</button>
            <button type="button" className="btn btn-secondary" onClick={()=>{this.modalEliminar();}} >No</button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
}

export default App;
