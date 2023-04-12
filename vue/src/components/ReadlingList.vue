<template>

  <div>
       <div class="books">
      <div
        class="card"
        id="book_card"
        style="width: 18rem"
        v-for="book in books"
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

      test
  </div>
  
</template>

<script>

import ReadingList from '../services/ReadingList'
export default {
    data() {
        return {
            search: {
                author: '',
                title: '',
                isbn: '',
                books: []
            },
        }
    },
    methods: {
       
    },
    created(){
        ReadingList.getReadingList().then((response)=>{
          const books = response.data;
          this.books= books;
          this.$store.commit('set_books', books)
        })

    }


}
</script>

<style>



</style>