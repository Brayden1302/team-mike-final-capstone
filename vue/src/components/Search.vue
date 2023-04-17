<template>
  <div id="main">
    <div class="heading">
      <div class="list-title" id=list-title>
    <h1>Reading List</h1>
    <div class="error-message" v-show="this.errorMessage != ''">
    <p  >{{this.errorMessage}} <router-link v-bind:to="{ name: 'home' }">login here</router-link> </p>
    </div>
  </div>
    </div>
<div class="reading-list" v-show="$store.state.token != ''">
  
  <div id="reading-books">
<div class="card" style="width: 12rem; ;" v-for="book in readingList" v-bind:key="book.bookId" id="reading-card">
  <div>
  <img class="card-img-top" id="reading-img" v-bind:src="book.imageLink" alt="Card image cap">
  <div class="book-read"  v-show="book.read"><p>You've read this book</p></div>
  <div class="book-not-read"  v-show="!book.read"><p>You haven't read this book</p></div>
  </div>
  <div class="card-body">
    <strong class="card-text">{{book.title}}</strong>
  </div>
  <b-card-footer v-show="book.read" class="read">
  <b-button variant="outline-danger" v-on:click="markBookReadUnread(book.bookId)">Mark not read</b-button>
  </b-card-footer>
   <b-card-footer v-show="!book.read" class="not-read">
    <b-button variant="outline-success" v-on:click="markBookReadUnread(book.bookId)">Mark read</b-button>
  </b-card-footer>
  </div>
</div>
</div>


<div id="buttons">
<button id="btn-primary" type="button" class="btn btn-primary" v-on:click="search = !search">Search books</button>
    <button id="btn-primary" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" v-show="isAdmin">
  Add book
</button>
</div>

<div id="buttons">
<div id="toggle-button" class="custom-control custom-switch">
  <input type="checkbox" class="custom-control-input" id="customSwitch1" v-model="newBooks" v-on:change="updateSearch()">
  <label class="custom-control-label" for="customSwitch1" v-show="$store.state.token != ''">New arrivals</label>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Add a book</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="rendered-form">
    <div class="formbuilder-text form-group field-text-1681140692767">
        <label for="text-1681140692767" class="formbuilder-text-label">Title<span class="formbuilder-required">*</span></label>
        <input type="text" placeholder="Title" class="form-control" name="text-1681140692767" access="false" id="text-1681140692767" required="required" aria-required="true" v-model="bookToAdd.title">
    </div>
    <div class="formbuilder-text form-group field-text-1681140675908">
        <label for="text-1681140675908" class="formbuilder-text-label">Author</label>
        <input type="text" placeholder="Author (optional)" class="form-control" name="text-1681140675908" access="false" id="text-1681140675908" v-model="bookToAdd.author">
    </div>
    <div class="formbuilder-text form-group field-text-1681140763601">
        <label for="text-1681140763601" class="formbuilder-text-label">ISBN</label>
        <input type="text" placeholder="ISBN (optional)" class="form-control" name="text-1681140763601" access="false" id="text-1681140763601" v-model="bookToAdd.isbn">
    </div>
    
</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn-success btn" name="button-1681141042305" access="false" style="success" id="button-1681141042305" data-dismiss="modal" v-on:click="getBook">Add Book</button>
      </div>
    </div>
  </div>
</div>
<form v-show="search" id="search-forum">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="title-input">Title</label>
      <input type="text" class="form-control" v-model="filter.title" id="title-input"  placeholder="title">
    </div>
    <div class="form-group col-md-6">
      <label for="author-input">Author</label>
      <input type="text" class="form-control" v-model="filter.author" id="author-input" placeholder="author">
    </div>
  </div>
  
  <div class="form-row">
    
    
    <div class="form-group col">
      <label for="keyword-input">Keyword</label>
      <input type="text" class="form-control" v-model="filter.keyword" id="keyword-input" placeholder="keyword">
    </div>
  </div>
    
<div class="custom-control custom-checkbox custom-control-inline" v-for="genre in libraryGenres" v-bind:key="genre.id">
  <b-form-checkbox  v-model="genres" v-bind:value="genre" name="check-button" button button-variant="outline-info">
      {{genre}}
    </b-form-checkbox>
</div>

  <div class="form-group">
    

  </div>
</form>
<h3 v-show="filteredBooks.length === 0" id="no-books">No books found</h3>
    
    <div class="books">
      <div
        class="card"
        id="book_card"
        style="width: 18rem"
        v-for="book in filteredBooks"
        v-bind:key="book.isbn"
        v-on:click="toggleCard(book)"
      >
      <div>
        <img v-bind:src="book.imageLink" class="card-img-top" alt="..." />
      </div>
        <div class="card-body">
          <h5 class="card-title">{{ book.title }}</h5>
        </div>
        
        <ul class="list-group list-group-flush">
          <li class="list-group-item">{{ "Pages: " + book.pageCount }}</li>
          <li class="list-group-item"><p v-for="author in book.authors" v-bind:key="author.name">Author(s): {{author}}</p></li>
          <li class="list-group-item"><!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" :data-target="'#' + book.bookId">
  More book info
</button>

<!-- Modal -->
<div class="modal fade" :id="book.bookId" tabindex="-1" role="dialog" aria-labelledby="ModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">{{book.title}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <b-list-group>
          <b-list-group-item><strong>Publisher: </strong>{{book.publisher}}</b-list-group-item>
          <b-list-group-item><strong>Genre(s): </strong> <p v-for="genre in book.categories" v-bind:key="genre.name">{{genre}}</p></b-list-group-item>
          <b-list-group-item><strong>Publish date: </strong>{{book.publishedDate}}</b-list-group-item>
          <b-list-group-item><strong>Date Added: </strong>{{book.dateAdded}}</b-list-group-item>
          <b-list-group-item><strong>Description: </strong>{{book.description}}</b-list-group-item>
          </b-list-group>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div></li>
        </ul>
        <div class="card-body">
          <button class="btn btn-outline-success" type="button" v-on:click.prevent="addToReadingList(book)" v-show="checkIfBookIsInReadingList(book.bookId) && $store.state.token != ''">Add to reading list</button>
          <p v-show="!checkIfBookIsInReadingList(book.bookId)">Book already in reading list</p>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "../services/BookService";
import ReadingList from '../services/ReadingList';
export default {
  data() {
    return {
      books: [],
      filter: {
          title: '',
          author: '',
          keyword: ''

      },
      genres: [],
      newBooks: false,
      lastSearchDate: '',
      showDescription: false,
      libraryGenres: [],
      search : false,
      bookToAdd: {
        title: '',
        author: '',
        isbn: ''
      },
      readingList: [],
      errorMessage: ''
    };
  },
  created() {
    BookService.getbooks().then((response) => {
      this.books = response.data;
    });
    BookService.getLastSearchDate().then( (response) => {
      this.lastSearchDate = response.data
    }).catch(error => {
      console.log(error)
    });
    
    BookService.getGenres().then( (response) => {
      this.libraryGenres = response.data
    });
    ReadingList.getReadingList().then((response)=>{
      if (response.status == 200) {
        this.readingList = response.data
      }     
    }).catch(error => {
      if (error.response.status === 401) {
        this.errorMessage = "You must be logged in to have a reading list"
      }
    })

  },
  computed: {
      filteredBooks(){
          let books = this.books
          if (this.filter.title != ''){
              books = books.filter((book) => {
                  return book.title.toLowerCase().includes(this.filter.title.toLowerCase())
              })
          }
          if (this.filter.author != ''){
              books = books.filter((book) =>{
                  let authorfilter = false;
                  book.authors.forEach(author => {
                      
                      if (author.toLowerCase().includes(this.filter.author.toLowerCase())) {
                          authorfilter = true;
                      } 
                  });
                  return authorfilter;
              }) 
          }
          if (this.filter.keyword != ''){
              books = books.filter((book) =>{
                 return book.description.toLowerCase().includes(this.filter.keyword.toLowerCase())
              }) 
          }
          if (this.genres.length != 0) {
              books = books.filter((book) => {
                  let containGenre = false;
                  this.genres.forEach((genre) =>{
                    // if ( book.categories.includes(genre) ){
                    //     containGenre = true;
                    // }
                    book.categories.forEach( category => {
                      if (category.toLowerCase().includes(genre.toLowerCase())) {
                        containGenre = true;
                      }
                    })
                   })
                   return containGenre;
              }) 

          }
          if (this.newBooks) {
            books = books.filter( (book) => {
              return book.dateAdded >= this.lastSearchDate
            })
          }
          
          return books;
      },

       isAdmin()  {
            const token = this.$store.state.token;
            let found = false;   // this is a boolean that will determine if we found the admin role
            if (token == '') {
                return false;   // no token, so no need to continue. user not logged in.
            }
            else {
                //we have a token.  Now see if we have an admin.
                const user = this.$store.state.user;
                user.authorities.forEach(element => {
                   
                    if (element.name == 'ROLE_ADMIN') {
                        found = true;
                    }
                })
            }
            return found;
        }
      

  },

methods: {
    toggleCard(book) {
      book.cardFlipped = !book.cardFlipped
    },
    updateSearch(){
    BookService.updateSearch(this.$store.state.token).then(response => {
        this.lastSearchDate = response.data
    })
    },
    addToReadingList(book){
      ReadingList.addToReadingList(book.bookId).then((response)=>{
        if(response.status == 200){
          this.readingList.push(book)
          // this.$router.push({name: 'readinglist'})
        }

      })
    },
    
    deleteFromReadingList(bookid){
      ReadingList.deletReadingList(bookid).then((response)=>{
         if(response.status == 200){
          this.$router.push({name: 'readinglist'})
        }

      })
},
checkIfBookIsInReadingList(bookId) {
      let doesntContainBook = true;
      this.readingList.forEach( (book) => {
        if (book.bookId == bookId) {
          doesntContainBook = false;
        }
      })
      return doesntContainBook;
    },

    getBook() {
            BookService.getBookFromAPI(this.bookToAdd.title).then( response => {
                const data = response.data.items[0];
                const bookToAdd = {
                    authors: data.volumeInfo.authors,
                    title: data.volumeInfo.title,
                    isbn: data.volumeInfo.industryIdentifiers[0].identifier,
                    publisher: data.volumeInfo.publisher,
                    imageLink: data.volumeInfo.imageLinks.thumbnail,
                    publishedDate: data.volumeInfo.publishedDate,
                    pageCount: data.volumeInfo.pageCount,
                    description: data.volumeInfo.description,
                    categories: data.volumeInfo.categories,
                }
                this.bookToAdd = {
                    author: '',
                    title: '',
                    isbn: ''
                }
                this.addBook(bookToAdd);
            })
        },
        addBook(book) {
            BookService.addBook(book).then( response => {
              this.books.push(response.data)
            })
        },
        markBookReadUnread(bookId) {
          ReadingList.markBookReadOrUnread(bookId).then( () => {
            this.readingList.forEach( (book) => {
              if (book.bookId == bookId) {
                book.read = !book.read;
              }
            })
          })
        }
    
}

};
</script>

<style scoped>

.books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  row-gap: 2%;
  /* align-content: center;
  flex-wrap: wrap; */
  /* margin-top: 2vh; */
  margin-left: 6%;
  
  /* justify-content: space-around; */
  margin-top: 2%;
  margin-bottom: 15%;
  
}

#book_card {
  max-height: fit-content;
  /* max-width: fit-content; */
}
.card {
  display: flex;
  /* border: 2px solid black; */
  /* offset-x | offset-y | blur-radius | color */
box-shadow: 10px 5px 5px black;
  border-radius: 30px;
  /* margin: 5vw 5vw 5vh 5vh; */
  transition: all .2s ease-in-out;
}
/* .card:hover { 
transform: scale(1.02); 
} */
.card-img-top {
  
  border-radius: 30px;
}

.card-body {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

h5 {
  font-size: 1.5em;
  font-weight: bold;
}

#list-title {
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: white;
  text-align: center;
  width: fit-content;
}

.error-message {
  color: #ba3939;
  background: #ffe0e0;
  border: 1px solid #a33a3a;
  height: fit-content;
  width: fit-content;
text-align: center;
  border-radius: 30px;
}

.error-message > p {
  margin-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
}

#main {
  text-align: center;
}

.heading {
  display: flex;
  justify-content: center;
}

h1 {
  font-family: 'Times New Roman', Times, serif;
  font-size: 2.5em;
}

.reading-list {
  margin-top: 1vh;
  border: 11px solid #28a745;
  height: auto;
  background-color: #28a745;
}

#reading-books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  margin-left: 5%;
  row-gap: 20px;
}

#buttons {
  display: flex;
  justify-content: center;
  margin-top: 2vh;
}

.btn-primary {
  display: inline-flex;
  margin: 20px 20px 20px 20px;
  color: black;
  background-color: #FFFF33;
  justify-content: center;
  border-color: black;
  /* offset-x | offset-y | blur-radius | color */
  /* box-shadow: 6px 5px 5px black; */
}

.btn-primary:hover {
  background-color: #28a745; 
}

#toggle-button {
  color:#FAF5E9;
}

.read {
  color: green;
  
}

.not-read {
  color: red;
}

.not-read > button, .read > button {
  width: fit-content;
}

.book-read {
  width: 190px;
  height: 30px;
  
  /* transform: translate(0, 11px) rotate(-45deg); */
  position: absolute;
  top: 20px;
  border-radius: 5px;
  background-color: green;
  color: #FAF5E9;
  text-align: center;
}

.book-not-read {
  width: 190px;
  height: 30px;
  
  /* transform: translate(0, 11px) rotate(-45deg); */
  position: absolute;
  top: 20px;
  border-radius: 5px;
  background-color: red;
  color: #FAF5E9;
  text-align: center;
}

#no-books {
  color: #FAF5E9;
  font-size: 3em;
}

form {
  margin-top: 15px;
  margin-bottom: 15px;
  margin-left: 30px;
  margin-right: 30px;
  color: #FAF5E9;
  text-align:left;
}

.rendered-form {
  text-align: left;
}

@media screen and (max-width: 1684px) {
 .books {
   display: flex;
   align-content: center;
   justify-content: space-around;
   flex-wrap: wrap;
   margin-left: 0px;
 }

 #book_card {
   margin-bottom: 2%;
 }
}

@media screen and (max-width: 1130px) {
   #reading-books {
     margin-left: 8%;
   grid-template-columns: 1fr 1fr 1fr;
 }
}

 /* #28a745 #FFFF33 #FAF5E9 #F694C1 #3C3744 
    Green  Yellow  White  Pink   Grey*/

</style>