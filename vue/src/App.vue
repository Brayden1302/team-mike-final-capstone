<template>
  <div id="app">
    <the-header> </the-header>
    <router-view />
  </div>
</template>
<script>
import TheHeader  from './components/TheHeader.vue'

export default {
  components: {TheHeader}
 
}
</script>

<style scoped>
#app {
  background-color: #3C3744;
}
</style>