<template>

  <div id="main">
      <!-- "C:\Users\Student\source\repos\pairs\team-mike-final-capstone\vue\src\" -->
      <div id="about">
<h2 id="about-us">About Us</h2>
<div id="about_us">
    
<div class="card" style="width: 18rem;">
  <img src="../assets/Brayden.png" class="card-img-top" alt="...">
  <div class="card-body">
      <h3 class="card-text name">Brayden Flanagan</h3>
    <p class="card-text">Throughout my jouney at Tech Elevator I've made some life long friends, tons of memories, and a starving hunger for learning more about development. I've always had the fire for technology and I'm extremely honored to have had the opportunity to be apart of this program. This final capstone project has been and absolute pleasure to work on alongside my two classmates Hassan and Zakaria. Our goal was to create a book library that can keep track of digital books and allows a user to keep track of books they want to read and have read. We've also decided to create forums to allow users to communicate with each other and talk about books.</p>
  </div>
</div>
<div class="card" style="width: 18rem;">
  <img src="../assets/Hassan.png" class="card-img-top" alt="...">
  <div class="card-body">
      <h3 class="card-text name">Hassan Abdi</h3>
    <p class="card-text">During my time at Tech Elevator, I had the pleasure of working alongside Brayden and Zakaria on a project that allowed us to build a digital library. It was an incredible experience collaborating with my classmates, and we all put in countless hours of hard work and dedication to bring our vision to life. As someone who has always had a deep passion for technology, I was thrilled to have had the opportunity to hone my development skills and learn more about the field. Overall, my time at Tech Elevator has been a fantastic experience, and I have enjoyed every moment of it.</p>
  </div>
</div>
<div class="card" style="width: 18rem;">
  <img src="../assets/Zakaria.png" class="card-img-top" alt="...">
  <div class="card-body">
      <h3 class="card-text name">Zakaria Bashir</h3>
    <p class="card-text">In my time at Tech Elevator, I had the honor of being able to learn valuable lessons, incorporate new technical knowledge, and build unbreakable bonds. I have a knack for the field of technology as a whole, and with the help of Tech Elevator, I was able to hone in on these specific skill sets. For our Final Capstone Project, I had the ability and honor working with Braydan and Hassan, two exemplary classmates. We were able to work on a digital library that enables the user to keep track of digital books that have been read, and also create a forum that allows users to communicate with each other.</p>
  </div>
</div>
</div>

<div class="about-company">
    <h2>About The Company</h2>
    <p>In an age where technology moves faster than a blink of an eye, and society evolves quicker than  ever before, we at Library Oasis have a drive to provide access to one of the most comprehensive book libraries there is. Founded by the dreams and ambitions of Brayden Flanagan, Hassan Abdi, and Zakaria Bashir.  Our website is designed to be user-friendly, with a simple and intuitive interface that makes it easy for users to search, borrow, and read books online. Our mission at Library Oasis is to promote literacy and lifelong learning by providing access to a vast collection of books and other resources. We are committed to making reading accessible and enjoyable for everyone, regardless of their location or financial situation. Our vision at Library Oasis is to become a leading provider of digital books and other resources, promoting literacy and lifelong learning for everyone. We aim to be recognized for our commitment to accessibility, inclusivity, innovation, and customer satisfaction, and to provide our users with the best possible reading experience.</p>
</div>
</div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

h2 {
    text-decoration: underline;
}

#about {
    margin-top: 2%;
    margin-bottom: 4%;
}

#about-us {
    text-align: center;
    color: #faf5e9;
    margin-bottom: 1%;
}

.about-company {
    text-align: center;
    margin-left: 40px;
    margin-right: 40px;
    color: #faf5e9;
}

.name {
    border-bottom: 1px solid black
}


#main {
    height: fit-content;
}
#about_us {
    display: flex;
    justify-content: space-around;
    margin-bottom: 5%;
}
</style>