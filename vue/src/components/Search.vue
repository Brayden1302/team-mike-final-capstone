<template>
  <div>
    

    <form>
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
    <div class="custom-control custom-checkbox custom-control-inline">
  <input type="checkbox" class="custom-control-input" id="customCheck1" v-bind:value="'Fantasy'" v-model="genres"> 
  <label class="custom-control-label" for="customCheck1">Fantasy</label>
</div>

<div class="custom-control custom-checkbox custom-control-inline">
  <input type="checkbox" class="custom-control-input" id="customCheck2" v-bind:value="'Fiction'" v-model="genres">
  <label class="custom-control-label" for="customCheck2">Fiction</label>
</div>

  <div class="form-group">
    <div class="custom-control custom-switch">
  <input type="checkbox" class="custom-control-input" id="customSwitch1" v-model="newBooks" v-on:change="updateSearch()">
  <label class="custom-control-label" for="customSwitch1" v-show="$store.state.token != ''">New arrivals</label>
</div>

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
          <a href="#" class="card-link">Card link</a>
          <a href="#" class="card-link">Another link</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "../services/BookService";
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
      showDescription: false
    };
  },
  created() {
    BookService.getbooks().then((response) => {
      this.books = response.data;
    });
    BookService.getLastSearchDate().then( (response) => {
      this.lastSearchDate = response.data
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
      }
  },

methods: {
    updateSearch(){
    BookService.updateSearch(this.$store.state.token).then(response => {
        this.lastSearchDate = response.data
    })
    }
}

};
</script>

<style scoped>
.books {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
#book_card, .card > .list-group > li {
  margin-bottom: 5%;
  background-color: orange;
  
}
.card {
  border: 2px solid black;
    box-shadow: 1px 1px;
}
</style>