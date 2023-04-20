<template>
  <div class="home">
    <div class="contetnt">
    <img src="../assets/LO Logo-off-wht.png" alt="">
    <div class="headings">
    <h1 class="main-head">Library|Oasis</h1>
    <h4>Where words bloom and minds flourish</h4>
    </div>
    <b-button v-on:click="routeToBooks" class="button" variant="info">View Books</b-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  methods: {
    routeToBooks() {
      this.$router.push({ name : 'search' })
    }
  }
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 2%;
  padding-bottom: 2.75%;
}

.headings {
  color: #faf5e9;
}

.main-head {
  font-size: 8em;
  margin-bottom: 20px;
}

.content {
  margin-top: 5%;
}

  img {
    width: 25%;
    height: auto;
  }

.button {
  margin-top: 1.5%;
  font-size: 3em;
  border-radius: 10px;
}
</style>