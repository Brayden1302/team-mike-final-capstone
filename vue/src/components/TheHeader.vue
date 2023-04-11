<template>
  <div id="nav">
      
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-show="isAdmin" v-bind:to="{ name: 'addBook'}">Add Book</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'search'}">View Books</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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

<style>

</style>