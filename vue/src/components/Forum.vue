<template>
  <div id="main">
    
    <!--  @reset="onReset" -->

    


    <div class="divTable minimalistBlack">
<div class="divTableHeading">
<div class="divTableRow">
<div class="divTableHead">Topic</div>
<div class="divTableHead">Username</div>
</div>
</div>
<div class="divTableBody">
<div class="divTableRow topic" v-for="forum in forums" v-bind:key="forum.forumId" v-on:click="routeToForum(forum.forumId)">
<div class="divTableCell">{{forum.forumName}}</div>
<div class="divTableCell">{{forum.username}}</div>
<div class="bar"></div>
</div>
</div>
</div>

<div id="more-bottom-part">
<div id="bottom-part">
<h3>Don't see your topic?</h3>
<b-button id="topic-button" variant="outline-primary" v-show="$store.state.token != ''" v-on:click="show = !show"
      >Create A Topic!</b-button
    >
    <div class="error-message">
    <p v-show="$store.state.token == ''">You must be logged in to add a topic <router-link v-bind:to="{ name: 'login' }">login here</router-link></p>
    </div>
    </div>
</div>
<b-form
      class="form"
      v-on:submit.prevent="addForum(forumToAdd)"
      v-on:reset="reset"
      v-if="show"
    >
      <b-form-group id="input-group-1" label="Topic:" label-for="input-1">
        <b-form-input
          id="input-1"
          type="text"
          placeholder="Topic"
          v-model="forumToAdd.forumName"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-2"
        label="Forum Description:"
        label-for="input-2"
      >
        <b-form-input
          id="input-2"
          placeholder="Enter description"
          v-model="forumToAdd.forumDescription"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
import ForumService from "../services/ForumService.js";
export default {
  name: "forum",
  data() {
    return {
      forums: [],
      store: [],
      show: false,
      forumToAdd: {
        forumName: "",
        forumDescription: "",
      },
    };
  },
  created() {
    ForumService.getForums()
      .then((response) => {
        if (response.status === 200) {
          this.forums = response.data;
          this.$store.commit("SET_FORUMS", response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    addForum(forum) {
      ForumService.addForum(forum).then((response) => {
        if (response.status === 201) {
          this.forums.push(response.data);
          this.forumToAdd = {
            forumName: "",
            forumDescription: "",
          };
          this.show = false;
        }
      });
    },
    reset() {
      this.forumToAdd = {
        topic: "",
        description: "",
      };
    },
    routeToForum(forumId) {
      this.$router.push({
            name: 'forum-messages',
            params: { forumId: forumId },
          })
    }
  },
};
</script>
    
<style scoped>

#main {
  height: 100vh;
  color: #FAF5E9;
  
}

.topic:hover {
  cursor: pointer;
}

form {
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 2%
}

.error-message > p {
  color: #ba3939;
  background: #ffe0e0;
  border: 1px solid #a33a3a;
  height: fit-content;
  width: fit-content;
  text-align: center;
  border-radius: 30px;
  padding-left: 5px;
  padding-right: 5px;
  font-size: 1.5em;
}

.error-message {
  position: relative;
  right: 120px;
}



#more-bottom-part {
  display: flex;
  text-align: center;
  justify-content: center;


}

#bottom-part {
  display: flex;
  text-align: center;
  justify-content: center;
  flex-direction: column;
  align-content: center;
}

h3 {
  display: flex;
  text-align: center;
}

#topic-button {
  display: flex;
  width: 264px;
    text-align: center;
  justify-content: center;
}

.divTableHead {
font-family: 'David Libre', serif;
}

div.minimalistBlack {
  width: 100%;
  text-align: left;
  border-collapse: separate;
  border-spacing: 0 30px;
  padding-left: 5%;
  padding-right: 5%;
}

.divTable.minimalistBlack .divTableCell, .divTable.minimalistBlack .divTableHead {
  font-size: 1.8em;
  
  border-bottom: 3px solid #000000;
  padding-bottom: 40px;
  padding-left: 2%;
}
.divTable.minimalistBlack .divTableBody .divTableCell {
  font-family: 'PT Sans', sans-serif;
  color: #FAF5E9;
}

.divTable.minimalistBlack .divTableHeading {
  /* background: #CFCFCF;
  background: -moz-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: -webkit-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: linear-gradient(to bottom, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%); */
  border-bottom: 3px solid #000000;
}
.divTable.minimalistBlack .divTableHeading .divTableHead {
  font-size: 4em;
  font-weight: bold;
  text-align: left;
  color: #FAF5E9;
}
.minimalistBlack .tableFootStyle {
  font-size: 2em;
}
/* DivTable.com */
.divTable{ display: table;}
.divTableRow { display: table-row; }
.divTableHeading { display: table-header-group;}
.divTableCell, .divTableHead { display: table-cell;}
.divTableHeading { display: table-header-group;}
.divTableFoot { display: table-footer-group;}
.divTableBody { display: table-row-group; }
</style>