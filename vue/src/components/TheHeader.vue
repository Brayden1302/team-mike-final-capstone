<template>
  <div>
      
<nav class="navbar navbar-expand-lg navbar fixed-top navbar-custom" id="main-nav-bar">
  <a class="navbar-brand" href="#">Library|Oasis</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation" v-on:click="expanded = !expanded">
    
<b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
<b-icon v-else icon="chevron-bar-down"></b-icon>
    
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home <span class="sr-only">(current)</span></router-link>
      </li>
      <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'search'}">Books</router-link>
      </li>
      <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'forum'}">Forums</router-link>
      </li>
      <!-- <li class="nav-item">
       <router-link class="nav-link" v-bind:to="{ name: 'readinglist'}" v-if="$store.state.token != ''" >View Reading List</router-link>
      </li> -->
      <li class="nav-item">
       <router-link class="nav-link" v-show="$store.state.token == ''" v-bind:to="{name: 'login'}">Login</router-link>
      </li>
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
    data() {
      return {
        expanded: false
      }
    },
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
.hidden {
  display: none;
}

  .nav-link {
    font-family: 'Poppins', sans-serif;
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
    font-family: 'Poppins', sans-serif;
    text-transform: uppercase;
    font-size: 25px;
    letter-spacing: 2px;
    word-spacing: 2px;
    font-weight: 800;
    color: #FAF5E9;
    font-size: 25px;
  }

svg {
  color: #FAF5E9;
  height: 35px;
  width: 35px
}

  
</style>