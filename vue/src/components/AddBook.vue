<template>
  <div class="rendered-form">
    <div class="formbuilder-text form-group field-text-1681140692767">
        <label for="text-1681140692767" class="formbuilder-text-label">Title<span class="formbuilder-required">*</span></label>
        <input type="text" placeholder="Title" class="form-control" name="text-1681140692767" access="false" id="text-1681140692767" required="required" aria-required="true" v-model="search.title">
    </div>
    <div class="formbuilder-text form-group field-text-1681140675908">
        <label for="text-1681140675908" class="formbuilder-text-label">Author</label>
        <input type="text" placeholder="Author (optional)" class="form-control" name="text-1681140675908" access="false" id="text-1681140675908" v-model="search.author">
    </div>
    <div class="formbuilder-text form-group field-text-1681140763601">
        <label for="text-1681140763601" class="formbuilder-text-label">ISBN</label>
        <input type="text" placeholder="ISBN (optional)" class="form-control" name="text-1681140763601" access="false" id="text-1681140763601" v-model="search.isbn">
    </div>
    <div class="formbuilder-button form-group field-button-1681141042305">
        <button type="button" class="btn-success btn" name="button-1681141042305" access="false" style="success" id="button-1681141042305" v-on:click="getBook">Add Book</button>
    </div>
</div>
</template>

<script>
import BookService from '../services/BookService'
export default {
    data() {
        return {
            search: {
                author: '',
                title: '',
                isbn: ''
            },
        }
    },
    methods: {
        getBook() {
            BookService.getBookFromAPI(this.search.title).then( response => {
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
                this.search = {
                    author: '',
                    title: '',
                    isbn: ''
                }
                this.addBook(bookToAdd);
            })
        },
        addBook(book) {
            BookService.addBook(book)
        }
    }
}
</script>

<style>

</style>