<template>
  <div>{{info.items[0].volumeInfo.title}}</div>
</template>

<script>
import BookService from '../services/BookService.js'
export default {
    data() {
        return {
            info: ''
        }
    },
    methods: {
        getBook() {
            BookService.getBookFromAPI('harry potter and the goblet of fire').then( response => {
                this.info = response.data;
            })
        }
    },
    created() {
        this.getBook()
    }
}
</script>

<style>

</style>