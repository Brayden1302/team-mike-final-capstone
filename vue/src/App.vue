<template>
  <div id="app">
    <the-header> </the-header>
   
    <router-view />
     <the-footer/>
  </div>
</template>
<script>
import TheHeader  from './components/TheHeader.vue'
import TheFooter from './components/TheFooter.vue'

export default {
  components: {TheHeader, TheFooter}
 
}
document.addEventListener('drag', () => {
  const nav = document.getElementById('main-nav-bar')
  nav.classList.add('hidden')
})

document.addEventListener('drop', () => {
  const nav = document.getElementById('main-nav-bar')
  nav.classList.remove('hidden')
})

      
      
</script>

<style scoped>
#app {
  background: #3C3744;
  padding-top: 70px;
}

.hidden {
  display: none;
}
</style>