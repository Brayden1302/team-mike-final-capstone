<template>
  <div id="main">
<div class="reading-list">
  <h1>Reading List</h1>
<div class="card" style="width: 12rem; ;" v-for="book in readingList" v-bind:key="book.bookId" id="reading-card">
  <img class="card-img-top" id="reading-img" v-bind:src="book.imageLink" alt="Card image cap">
  <div class="card-body">
    <h6 class="card-text">{{book.title}}</h6>
  </div>
  <b-card-footer v-show="book.read" class="read">
    Read
  </b-card-footer>
   <b-card-footer v-show="!book.read" class="not-read">
    Not read
  </b-card-footer>
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
<form v-show="search">
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
  <input type="checkbox" class="custom-control-input" v-bind:id="genre" v-bind:value="genre" v-model="genres">
  <label class="custom-control-label" v-bind:for="genre">{{genre}}</label>
</div>

  <div class="form-group">
    

  </div>
</form>

    
    <div class="books">
      <div
        class="card"
        id="book_card"
        style="width: 18rem"
        v-for="book in filteredBooks"
        v-bind:key="book.isbn"
      >
        <img v-bind:src="book.imageLink" class="card-img-top" alt="..." />
        <div class="card-body">
          <h5 class="card-title">{{ book.title }}</h5>
          <div>
            <a  v-on:click.prevent="book.showDescription = !book.showDescription">Show description</a>
          <p class="card-text" v-show="book.showDescription">{{ book.description }}</p>
          </div>
        </div>
        
        <ul class="list-group list-group-flush">
          <li class="list-group-item">{{ "Pages: " + book.pageCount }}</li>
          <li class="list-group-item">{{ "Publisher: " + book.publisher }}</li>
          <li class="list-group-item">{{ "Isbn: " + book.isbn }}</li>
        </ul>
        <div class="card-body">
          <button class="btn btn-outline-success" type="button" v-on:click.prevent="addToReadingList(book)" v-show="checkIfBookIsInReadingList(book.bookId)">Add to reading list</button>
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
      readingList: []
    };
  },
  created() {
    BookService.getbooks().then((response) => {
      this.books = response.data;
    });
    BookService.getLastSearchDate().then( (response) => {
      this.lastSearchDate = response.data
    });
    
    BookService.getGenres().then( (response) => {
      this.libraryGenres = response.data
    });
    ReadingList.getReadingList().then((response)=>{
            this.readingList = response.data
    });

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
        }
    
}

};
</script>

<style scoped>
/* .books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas: "books books books";
  margin-left: 5%;
  margin-top: 5%;
} */

.books {
  display: flex;
  align-content: center;
  flex-wrap: wrap;
  margin-top: 5%;
  /* margin-left: 5%; */
  justify-content: space-around;
  /* margin-top: 5%; */
}

#book_card {
  margin-bottom: 5%;
  max-height: fit-content;
  /* max-width: fit-content; */
}
.card {
  display: flex;
  border: 2px solid black;
  /* offset-x | offset-y | blur-radius | color */
box-shadow: 10px 5px 5px black;
  border-radius: 2%;
  /* margin: 5vw 5vw 5vh 5vh; */
}
.card-img-top {
  width: auto;
  border-radius: 2%;
}

.btn-outline-success {
  margin-left: 40px;
}

.reading-list {
  margin-top: 10vh;
  border: 11px solid #28a745;
  display: flex;
  justify-content: space-around;
  height: auto;
  background-color: #28a745;
}

#buttons {
  display: flex;
  justify-content: center;
  
}

.btn-primary {
  display: inline-flex;
  margin: 20px 20px 20px 20px;
  color: black;
  background-color: #FFFF33;
  justify-content: center;
  border-color: black;
  /* offset-x | offset-y | blur-radius | color */
box-shadow: 6px 5px 5px black;

}

#toggle-button {
  color:cornsilk;

}

.read {
  color: green;
}

.not-read {
  color: red;
}

 /* #28a745 #FFFF33 #FAF5E9 #F694C1 #3C3744 
    Green  Yellow  White  Pink   Grey*/

</style>