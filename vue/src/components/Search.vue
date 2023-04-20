<template>
  <div id="main">
    <div class="heading">
      <div class="list-title" id="list-title">
        <h1>My Reading List</h1>
        <div class="error-message" v-show="this.errorMessage != ''">
          <p>
            {{ this.errorMessage }}
            <router-link v-bind:to="{ name: 'login' }">login here</router-link>
          </p>
        </div>
      </div>
    </div>
    <div class="reading-list" v-show="$store.state.token != ''">
      <h4 v-show="readingList.length == 0">No books in reading list</h4>
      <div id="reading-books"  @drop="onDrop($event, 1)"
        @dragover.prevent
        @dragenter.prevent>
        <div
          class="card"
          style="width: 12rem ;"
          v-for="book in readingList"
          v-bind:key="book.bookId"
          id="reading-card"
          draggable
          @dragstart="startDrag($event, book)"
        >
          <div>
            <img
              class="card-img-top"
              id="reading-img"
              v-bind:src="book.imageLink"
              alt="Card image cap"
              draggable="false"
            />
            <div class="book-read" v-show="book.read">
              <p>You've read this book</p>
            </div>
            <div class="book-not-read" v-show="!book.read">
              <p>You haven't read this book</p>
            </div>
          </div>
          <div class="card-body">
            <strong class="card-text">{{ book.title }}</strong>
          </div>
          <b-card-footer v-show="book.read" class="read">
            <b-button
              variant="outline-danger"
              v-on:click="markBookReadUnread(book.bookId)"
              >Mark not read</b-button
            >
          </b-card-footer>
          <b-card-footer v-show="!book.read" class="not-read">
            <b-button
              variant="outline-success"
              v-on:click="markBookReadUnread(book.bookId)"
              >Mark read</b-button
            >
          </b-card-footer>
        </div>
      </div>
    </div>

    
    <h3 v-show="filteredBooks.length === 0" id="no-books">No books found</h3>
    
    <h1 class="book-inventory">Digital Books</h1>
    
<div id="buttons">
      <button
        id="btn-primary"
        type="button"
        class="btn btn-primary"
        v-on:click="search = !search"
      >
        Search books
      </button>
      <button
        id="btn-primary"
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#exampleModalCenter"
        v-show="isAdmin"
      >
        Add book
      </button>
    </div>

    <div id="buttons">
      <div id="toggle-button" class="custom-control custom-switch">
        <input
          type="checkbox"
          class="custom-control-input"
          id="customSwitch1"
          v-model="newBooks"
          v-on:change="updateSearch()"
        />
        <label
          class="custom-control-label"
          for="customSwitch1"
          v-show="$store.state.token != ''"
          >New arrivals</label
        >
      </div>
    </div>

    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModalCenter"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Add a book</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="rendered-form">
              <div class="formbuilder-text form-group field-text-1681140692767">
                <label for="text-1681140692767" class="formbuilder-text-label"
                  >Title<span class="formbuilder-required">*</span></label
                >
                <input
                  type="text"
                  placeholder="Title"
                  class="form-control"
                  name="text-1681140692767"
                  access="false"
                  id="text-1681140692767"
                  required="required"
                  aria-required="true"
                  v-model="bookToAdd.title"
                />
              </div>
              <div class="formbuilder-text form-group field-text-1681140675908">
                <label for="text-1681140675908" class="formbuilder-text-label"
                  >Author</label
                >
                <input
                  type="text"
                  placeholder="Author (optional)"
                  class="form-control"
                  name="text-1681140675908"
                  access="false"
                  id="text-1681140675908"
                  v-model="bookToAdd.author"
                />
              </div>
              <div class="formbuilder-text form-group field-text-1681140763601">
                <label for="text-1681140763601" class="formbuilder-text-label"
                  >ISBN</label
                >
                <input
                  type="text"
                  placeholder="ISBN (optional)"
                  class="form-control"
                  name="text-1681140763601"
                  access="false"
                  id="text-1681140763601"
                  v-model="bookToAdd.isbn"
                />
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">
              Cancel
            </button>
            <button
              type="button"
              class="btn-success btn"
              name="button-1681141042305"
              access="false"
              style="success"
              id="button-1681141042305"
              data-dismiss="modal"
              v-on:click="getBook"
            >
              Add Book
            </button>
          </div>
        </div>
      </div>
    </div>
    <form v-show="search" id="search-forum">
      <div class="form-row">
        <div class="form-group col-md-6">
          <label for="title-input">Title</label>
          <input
            type="text"
            class="form-control"
            v-model="filter.title"
            id="title-input"
            placeholder="title"
          />
        </div>
        <div class="form-group col-md-6">
          <label for="author-input">Author</label>
          <input
            type="text"
            class="form-control"
            v-model="filter.author"
            id="author-input"
            placeholder="author"
          />
        </div>
      </div>

      <div class="form-row">
        <div class="form-group col">
          <label for="keyword-input">Keyword</label>
          <input
            type="text"
            class="form-control"
            v-model="filter.keyword"
            id="keyword-input"
            placeholder="keyword"
          />
        </div>
      </div>

      <div
        class="custom-control custom-checkbox custom-control-inline"
        v-for="genre in libraryGenres"
        v-bind:key="genre.id"
      >
        <b-form-checkbox
          v-model="genres"
          v-bind:value="genre"
          name="check-button"
          button
          button-variant="outline-info"
        >
          {{ genre }}
        </b-form-checkbox>
      </div>

      <div class="form-group"></div>
    </form>

    <div class="books"  @drop="onDrop($event, 2)"
        @dragover.prevent
        @dragenter.prevent>
      <div
        class="card"
        id="book_card"
        style="width: 18rem"
        v-for="book in filteredBooks"
        v-bind:key="book.isbn"
        draggable
            @dragstart="startDrag($event, book)"
      >
        <div>
          <img v-bind:src="book.imageLink" class="card-img-top" alt="..." draggable="false"/>
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ book.title }}</h5>
        </div>

        <ul class="list-group list-group-flush">
          <li class="list-group-item">{{ "Pages: " + book.pageCount }}</li>
          <li class="list-group-item">
            <p v-for="author in book.authors" v-bind:key="author.name">
              Author(s): {{ author }}
            </p>
          </li>
          <li class="list-group-item">
            <!-- Button trigger modal -->
            <button
              type="button"
              class="btn btn-primary"
              data-toggle="modal"
              :data-target="'#' + book.bookId"
            >
              More book info
            </button>

            <!-- Modal -->
            <div
              class="modal fade"
              :id="book.bookId"
              tabindex="-1"
              role="dialog"
              aria-labelledby="ModalCenterTitle"
              aria-hidden="true"
            >
              <div
                class="modal-dialog modal-dialog-centered modal-lg"
                role="document"
              >
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">
                      {{ book.title }}
                    </h5>
                    <button
                      type="button"
                      class="close"
                      data-dismiss="modal"
                      aria-label="Close"
                    >
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <b-list-group>
                      <b-list-group-item
                        ><strong>Publisher: </strong
                        >{{ book.publisher }}</b-list-group-item
                      >
                      <b-list-group-item
                        ><strong>Genre(s): </strong>
                        <p
                          v-for="genre in book.categories"
                          v-bind:key="genre.name"
                        >
                          {{ genre }}
                        </p></b-list-group-item
                      >
                      <b-list-group-item
                        ><strong>Publish date: </strong
                        >{{ book.publishedDate }}</b-list-group-item
                      >
                      <b-list-group-item
                        ><strong>Date Added: </strong
                        >{{ book.dateAdded }}</b-list-group-item
                      >
                      <b-list-group-item
                        ><strong>Description: </strong
                        >{{ book.description }}</b-list-group-item
                      >
                    </b-list-group>
                  </div>
                  <div class="modal-footer">
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-dismiss="modal"
                    >
                      Close
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
        <div class="card-body">
          <button
            class="btn btn-outline-success"
            type="button"
            v-on:click.prevent="addToReadingList(book)"
            v-show="
              checkIfBookIsInReadingList(book.bookId) &&
              $store.state.token != ''
            "
          >
            Add to reading list
          </button>
          <p v-show="!checkIfBookIsInReadingList(book.bookId)">
            Book already in reading list
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BookService from "../services/BookService";
import ReadingList from "../services/ReadingList";
export default {
  data() {
    return {
      books: [],
      filter: {
        title: "",
        author: "",
        keyword: "",
      },
      genres: [],
      newBooks: false,
      lastSearchDate: "",
      showDescription: false,
      libraryGenres: [],
      search: false,
      bookToAdd: {
        title: "",
        author: "",
        isbn: "",
      },
      readingList: [],
      errorMessage: "",
    };
  },
  created() {
    BookService.getbooks().then((response) => {
      this.books = response.data;
    });
    BookService.getLastSearchDate()
      .then((response) => {
        this.lastSearchDate = response.data;
      })
      .catch((error) => {
        console.log(error);
      });

    BookService.getGenres().then((response) => {
      this.libraryGenres = response.data;
    });
    ReadingList.getReadingList()
      .then((response) => {
        if (response.status == 200) {
          this.readingList = response.data;
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          this.errorMessage = "You must be logged in to have a reading list";
        }
      });
  },
  computed: {
    filteredBooks() {
      let books = this.books;
      if (this.filter.title != "") {
        books = books.filter((book) => {
          return book.title
            .toLowerCase()
            .includes(this.filter.title.toLowerCase());
        });
      }
      if (this.filter.author != "") {
        books = books.filter((book) => {
          let authorfilter = false;
          book.authors.forEach((author) => {
            if (
              author.toLowerCase().includes(this.filter.author.toLowerCase())
            ) {
              authorfilter = true;
            }
          });
          return authorfilter;
        });
      }
      if (this.filter.keyword != "") {
        books = books.filter((book) => {
          return book.description
            .toLowerCase()
            .includes(this.filter.keyword.toLowerCase());
        });
      }
      if (this.genres.length != 0) {
        books = books.filter((book) => {
          let containGenre = false;
          this.genres.forEach((genre) => {
            // if ( book.categories.includes(genre) ){
            //     containGenre = true;
            // }
            book.categories.forEach((category) => {
              if (category.toLowerCase().includes(genre.toLowerCase())) {
                containGenre = true;
              }
            });
          });
          return containGenre;
        });
      }
      if (this.newBooks) {
        books = books.filter((book) => {
          return book.dateAdded >= this.lastSearchDate;
        });
      }

      return books;
    },

    isAdmin() {
      const token = this.$store.state.token;
      let found = false; // this is a boolean that will determine if we found the admin role
      if (token == "") {
        return false; // no token, so no need to continue. user not logged in.
      } else {
        //we have a token.  Now see if we have an admin.
        const user = this.$store.state.user;
        user.authorities.forEach((element) => {
          if (element.name == "ROLE_ADMIN") {
            found = true;
          }
        });
      }
      return found;
    },
    listOne() {
      console.log("inside listOne");
      return this.books.filter((book) => book.list === 1);
    },
    listTwo() {
      console.log("inside listOne");
      return this.books.filter((book) => book.list === 2);
    }
  },

  methods: {
    toggleCard(book) {
      book.cardFlipped = !book.cardFlipped;
    },
    updateSearch() {
      BookService.updateSearch(this.$store.state.token).then((response) => {
        this.lastSearchDate = response.data;
      });
    },
    addToReadingList(book) {
      ReadingList.addToReadingList(book.bookId).then((response) => {
        if (response.status == 200) {
          this.readingList.push(book);
          // this.$router.push({name: 'readinglist'})
        }
      });
    },

    deleteFromReadingList(bookid) {
      ReadingList.deletReadingList(bookid).then((response) => {
        if (response.status == 200) {
          this.$router.push({ name: "readinglist" });
        }
      });
    },
    checkIfBookIsInReadingList(bookId) {
      let doesntContainBook = true;
      this.readingList.forEach((book) => {
        if (book.bookId == bookId) {
          doesntContainBook = false;
        }
      });
      return doesntContainBook;
    },

    getBook() {
      BookService.getBookFromAPI(this.bookToAdd.title).then((response) => {
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
        };
        this.bookToAdd = {
          author: "",
          title: "",
          isbn: "",
        };
        this.addBook(bookToAdd);
      });
    },
    addBook(book) {
      BookService.addBook(book).then((response) => {
        this.books.push(response.data);
      });
    },
    markBookReadUnread(bookId) {
      ReadingList.markBookReadOrUnread(bookId).then(() => {
        this.readingList.forEach((book) => {
          if (book.bookId == bookId) {
            book.read = !book.read;
          }
        });
      });
    },
    startDrag(evt, book) {
      // console.log("inside start drag" + book.title);
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
      evt.dataTransfer.setData("itemID", book.bookId);
      
    },
    onDrop(evt, list) {
      // console.log("inside on drop" + list);
      const itemID = evt.dataTransfer.getData("itemID");
      const book = this.books.find((book) => book.bookId == itemID);
      book.list = list;
      let containsBook = false;
      this.readingList.forEach( (book) => {
        if (book.bookId == itemID) {
          containsBook = true;
        }
      })
      if (list == 1 && !containsBook  && this.$store.state.token != '') {
        this.addToReadingList(book)
      } else if (list == 2 && this.$store.state.token != '') {
        this.readingList = this.readingList.filter(
          (currentBook) => currentBook.bookId != itemID
        );
        ReadingList.deletReadingList(itemID)
      }
      
    },
  },
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
  background: #3c3744;
  /* justify-content: space-around; */
  margin-top: 2%;
  margin-bottom: 10%;
}

#book_card {
  max-height: fit-content;
  /* max-width: fit-content; */
}
.card {
  display: flex;
  /* border: 2px solid black; */
  /* offset-x | offset-y | blur-radius | color */
  /* box-shadow: 10px 5px 5px black; */
  box-shadow: 10px 8px 16px rgba(0, 0, 0, 0.4);
  border-radius: 30px;
  /* margin: 5vw 5vw 5vh 5vh; */
  transition: 0.3s;
}
#reading-books .card:hover { 
box-shadow: 10px 5px 5px rgba(0, 0, 0, .6);
}

.books .card:hover {
  box-shadow: 10px 5px 5px rgba(0, 0, 0, .8);
}
.card-img-top {
  border-radius: 30px;
}

.card-body {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}

h5 {
  font-size: 1.5em;
  font-weight: bold;
}

h4 {
  color: #faf5e9
}

#list-title {
  font-family: "David Libre", serif;
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
  font-size: 1.5em;
}

.error-message > p {
  margin-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
}

#main {
  text-align: center;
  background: #3c3744;
  padding-bottom: 1%;
}

.heading {
  display: flex;
  justify-content: center;
}

h1 {
  font-size: 5em;
}

button {
  border-radius: 10px;
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
  margin-left: 8%;
  row-gap: 20px;
  text-align: center;
}

#buttons {
  display: flex;
  justify-content: center;
}

.btn-primary {
  font-family: "Poppins", sans-serif;
  display: inline-flex;
  margin: 20px 20px 20px 20px;
  color: black;
  background-color: #ffff33;
  justify-content: center;
  border-color: black;
}

.btn-primary:hover {
  background-color: #28a745;
}

#toggle-button {
  font-family: "Poppins", sans-serif;
  color: #faf5e9;
}

.read {
  color: green;
}

.not-read {
  color: red;
}

.not-read > button,
.read > button {
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
  color: #faf5e9;
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
  color: #faf5e9;
  text-align: center;
}

#no-books {
  color: #faf5e9;
  font-size: 3em;
}

form {
  margin-top: 5px;
  margin-bottom: 15px;
  margin-left: 30px;
  margin-right: 30px;
  color: #faf5e9;
  text-align: left;
}

.rendered-form {
  text-align: left;
}
.book-inventory {
  color: #faf5e9;
  font-family: "David Libre", serif;
  margin-top: 3%;
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