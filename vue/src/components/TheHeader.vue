<template>
  <div>
      <nav class="navbar navbar-dark bg-dark">
  <!-- Navbar content -->
  <ul class="navbar-nav mr-auto">
      
      <li class="nav-item">
        
      <!-- </li>
      <li class="nav-item navbar-dark"> -->
        
      </li>
      <li class="nav-item">
        
      </li>
      <li class="nav-item">
        
      </li>
      <li class="nav-item">
        
      </li>
      
    </ul>     
</nav>
<nav class="navbar navbar-expand-lg navbar fixed-top navbar-custom">
  <a class="navbar-brand" href="#">BookGo!</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home <span class="sr-only">(current)</span></router-link>
      </li>
      <!-- <li class="nav-item">
       <router-link class="nav-link" v-show="isAdmin" v-bind:to="{ name: 'addBook'}">Add Book</router-link>
      </li> -->
      <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'search'}">View Books</router-link>
      </li>
      <li class="nav-item">
       <!-- <router-link class="nav-link" v-bind:to="{ name: 'readinglist'}" v-if="$store.state.token != ''" >View Reading List</router-link>
      </li> -->
      <li class="nav-item">
        <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </li>
      
    </ul>
  </div>
</nav>
  </div>  
      
   
</template>

<script>

export default {
    name: 'the-header',
   computed: {
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
                    console.log(element.name);
                    if (element.name == 'ROLE_ADMIN') {
                        found = true;
                    }
                })
            }
            return found;
        }
    }

}
</script>


<style scoped>
  .nav-link {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 25px;
    margin-right: 20px;
    color: #FAF5E9 ;
  }

  .nav-link:hover {
    color: #3C3744;
  }

  nav {
    background-color: #28a745;
  }

  .navbar-brand {
    font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    text-transform: uppercase;;
    font-size: 25px;
    letter-spacing: 2px;
    word-spacing: 2px;
    font-weight: 800;
    color: #FAF5E9;
    font-size: 25px;
  }

  
</style>