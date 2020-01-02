import React from 'react';
// import logo from './logo.svg';
import './App.css';

class App extends React.Component {


  constructor(props){
    super(props);

    this.state={
      newWord:"",
      list:[],
      isDone:false
    }

    
  }

  changenewWord(word)
    {
      this.setState(
        {
          newWord:word
        }
      )
  }
  add(word)
  {

    // alert(word);
        if(word !== '')
        {
          const newword={

            id: Date.now(),
            value: word,
            isDone:false
          }

       
      const modifiedlist= [...this.state.list]
      modifiedlist.unshift(newword);
      this.setState({
        list:modifiedlist,
        newWord:""
      });
      // alert('Success');
    }
    
  }

  deletetodo(id)
  {
    const itemss = [...this.state.list];
    const items= itemss.filter( item => item.id !== id);

    this.setState({
      list:items
    })
  }

  updateCheck(item)
  {
    item.isDone =!item.isDone;
    const list = [...this.state.list];
    const modifiedlist=list.filter(e => item.id !==e.id);
    modifiedlist.push(item);
    this.setState({
      list:modifiedlist
    })
    
  }
  render(){
  return (
    <React.Fragment>
     <div>
     <h1 className="heading neon pink">Todo</h1>
     </div>
     <div className='container'>
       <div className='header-container'>

     
          <input 
          className='input-text'
          type="text"
          placeholder="What's in your mind?"
          value={this.state.newWord}
          onChange={e => this.changenewWord(e.target.value)}
          required
          />
   
          <button
            className='input-button'
            onClick={()=>this.add(this.state.newWord)}
          >    
            <i class="fas fa-plus"></i></button>
      </div>

          <div className='todos'>
            <ul>
              {this.state.list.map(item => {
                return(  
                <li key={item.id}>
                 
                <div className='item'>
                <input type ='checkbox' 
                  checked={item.isDone} 
                  onClick={() => this.updateCheck(item)}
                />
                <span className={item.isDone?'done-list':'items-list'}> {item.value} </span>

                  <button className='deletebtn'
                  onClick={()=>this.deletetodo(item.id)}
                  ><i class="fas fa-trash"></i></button>
                </div>
               
                </li>
                );
              })}
              </ul>
          </div>
          </div>
     
    </React.Fragment>
  );
}
}
export default App;
