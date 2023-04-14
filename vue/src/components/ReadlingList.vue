<template>

  <div>
    
    <h4 v-show="this.$store.state.readList.length == 0">No books in reading list</h4>
       <div class="books">
      <div
        class="card"
        id="book_card"
        style="width: 18rem"
        v-for="book in this.$store.state.readList"
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
          <div class="card-buttons">
          <button type="button" class="btn btn-outline-danger">Remove</button>
          <button class="btn btn-outline-success" type="button">Mark read</button>
        </div>
        </div>
      </div>
       </div>
  </div>
  
</template>

<script>

// import ReadingList from '../services/ReadingList'
export default {
    data() {
        return {
            search: {
                author: '',
                title: '',
                isbn: '',
            },
        }
    },
    methods: {
       
    },
    created(){
        // ReadingList.getReadingList().then((response)=>{
        //   const books = response.data;
        //   this.$store.commit('set_books', books)
        // })
        

    }


}
</script>

<style scoped>

</style>