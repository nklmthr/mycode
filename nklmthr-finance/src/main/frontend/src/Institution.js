import React from 'react';
import ReactDOM from 'react-dom/client';
import './Institution.css';
export default class Institution extends React.Component {
    constructor(props){
        super(props);
        this.state = {data:[], isEditing:false, isNew: false, editRow:''};
    }
    componentDidMount(){
        fetch('http://localhost:8080/api/institution')
            .then(response => response.json())
            .then(response => {
                this.setState({data:response});
            })
    }

    deleteInstitution = (institutionId) => {
        var index = this.state.data.findIndex(e => (e.id === institutionId));
        var institution = this.state.data[index];
        var url  = 'http://localhost:8080/api/institution/'+institution['id'];
        fetch(url, { method: 'DELETE' })
            .then(() => {
                var stateData = this.state.data;
                stateData.splice(index, 1);
                this.setState({data: stateData});
            })
    };

    updateStateChangeForEdit = (institutionId) => {
        this.setState({isEditing:true});
        this.setState({editRow: institutionId});
    }

   draftInstitutionName = (event)=> {
        if(event.target.name==='institutionName'){
            this.setState({newInstitutionName:event.target.value})
        }
    }
    draftInstitutionDescription = (event) => {
        if(event.target.name==='institutionDescription'){
            this.setState({newInstitutionDescription:event.target.value})
        }
    }
    createNewInstitution= (institutionId) =>{
        var newInstitutionName = this.state.newInstitutionName;
        var newInstitutionDescription = this.state.newInstitutionDescription;
        if(newInstitutionName===undefined){
            alert("Institution Name must Not be Null when adding new Institution");
            return;
        }
        var payloadStr = '{"name":"'+newInstitutionName+'","description":"'+newInstitutionDescription+'"}';
        var payload = JSON.parse(payloadStr);
        var url  = 'http://localhost:8080/api/institution';
        //alert(url);
        fetch(url, {
                method: 'POST',
                body: JSON.stringify(payload),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
        })
        .then((response) => response.json())
        .then((responseData) => {
              var stateData = this.state.data;
              stateData.push(responseData);
              var index = stateData.findIndex(e => (e.id === ''));
              stateData.splice(index,1);
              this.setState({data:stateData});
              this.setState({isNew:false});
              this.setState({newInstitutionName:""});
              this.setState({newInstitutionDescription:""});
        });
    }

    updateInstitution= (institutionId) => {
        var newInstitutionName = this.state.newInstitutionName;
        var newInstitutionDescription = this.state.newInstitutionDescription;
        var index = this.state.data.findIndex(e => (e.id === institutionId));
        var institution = this.state.data[index];
        if(newInstitutionName===undefined || newInstitutionName.trim()==='') {
            newInstitutionName = institution['name'];
        }
        var payloadStr = '{"id":"'+institutionId+'","name":"'+newInstitutionName+'","description":"'+newInstitutionDescription+'"}';
        var payload = JSON.parse(payloadStr);
        var url  = 'http://localhost:8080/api/institution/'+institution['id'];
        fetch(url, {
                method: 'PUT',
                body: JSON.stringify(payload),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
        })
        .then(() => {
            var stateData = this.state.data;
            stateData.splice(index, 1);
            stateData.push(payload);
            this.setState({data:stateData});
            this.setState({editRow:""});;
            this.setState({isEditing:false});
            this.setState({newInstitutionName:""});
            this.setState({newInstitutionDescription:""});
        });
    }
    saveInstitution = (institutionId) =>{
        if(this.state.isNew){
            this.createNewInstitution(institutionId);
        } else{
            this.updateInstitution(institutionId);
        }
    }
    cancelUpdate =() =>{
        this.setState({isEditing:false});
        this.setState({editRow:""});
    }
    addNewInstitution = () =>{
        var newInstitution = '{"id":"", "name":"","description":""}';
        this.state.data.push(JSON.parse(newInstitution));
        this.setState({data:this.state.data, isNew:true});
    }

    updateInstitutionHMTL = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                if((info.id === this.state.editRow) || (this.state.isNew===true && info.id === '')){
                    return(
                        <tr key={info.id}>
                            <td>{info.id}</td>
                            <td><input type='text' name='institutionName' defaultValue={info.name} onChange={this.draftInstitutionName.bind(this)}/></td>
                            <td><input type='text' name='institutionDescription' defaultValue={info.description} onChange={this.draftInstitutionDescription.bind(this)}/></td>
                            <td>
                                <button className='saveInstitutionButton' onClick={()=>this.saveInstitution(info.id)}>Save</button>
                                <button className='cancelUpdateButton' onClick={()=>this.cancelUpdate()}>Cancel</button>
                            </td>
                        </tr>
                    )
                } else {
                    return(
                        <tr key={info.id}>
                            <td>{info.id}</td>
                            <td>{info.name}</td>
                            <td>{info.description}</td>
                            <td></td>
                        </tr>
                   )
                }
            }
        )
        return DisplayData;
    }

    listInstitutionHTML = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                return(
                    <tr key={info.id}>
                        <td>{info.id}</td>
                        <td>{info.name}</td>
                        <td>{info.description}</td>
                        <td>
                            <div class="btn-group">
                            <button className='deleteInstitutionButton' onClick={()=>this.deleteInstitution(info.id)}>Delete</button>
                            <button className='updateInstitutionButton' onClick={()=>this.updateStateChangeForEdit(info.id)}>Update</button>
                            </div>
                        </td>
                    </tr>
                )
            }
        )
        return DisplayData;
    }
    render() {

        var displayHtml;
        if(this.state.isEditing===true || this.state.isNew===true){
                displayHtml = this.updateInstitutionHMTL();
        } else {
                displayHtml = this.listInstitutionHTML();
        }
        return (
            <div className="institutionTable">
            <button className='addInstitutionButton' onClick={this.addNewInstitution.bind(this)}>Add</button>
            <br/><br/>
                <table>
                    <thead>
                        <tr>
                            <th className="institutionIdCol">Institution Id</th>
                            <th className="institutionNameCol">Institution Name</th>
                            <th className="institutionDescCol">Description</th>
                            <th className="institutionIdActionCol">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {displayHtml}
                    </tbody>
               </table>
            </div>
        );
    }
}
