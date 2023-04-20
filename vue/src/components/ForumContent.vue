<template>
  <div id="main">
      <div class="divTable minimalistBlack">
          <div id="content-holder">
      <div id="forum-info">
          <div id="forum-head">
          <p id="forum-name">{{`${forum.forumName}`}}</p>
          <p id="forum-username">Created By: {{forum.username}}</p>
          </div>
          <p id="forum-description">{{forum.forumDescription}}</p>
          <b-button v-show="$store.state.token != ''" id="reply-button" variant="outline-primary" v-on:click="show = !show">Reply</b-button>
          <div class="error-message">
    <p v-show="$store.state.token == ''">You must be logged in to add a topic <router-link v-bind:to="{ name: 'login' }">login here</router-link></p>
    </div>
      </div>
       <!--  @reset="onReset" -->
       <b-form v-on:submit.prevent="addMessage(messageToAdd)" v-on:reset="reset"  v-if="show">
      <b-form-group
        id="input-group-1"
        label="Message:"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          type="text"
          placeholder="Message"
          v-model="messageToAdd.messageContent"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
      <div class="messages" v-for="message in messages" v-bind:key="message.messageId">
          <p>{{message.messageContent + ' -' + message.username}}</p>
      </div>
      </div>
      </div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService'
export default {
    data() {
        return {
        forum : '',
        messages: [],
        messageToAdd: {
            messageContent: ''
        },
        show: false
        }
    },

    name: 'forum-content',
    created() {
        ForumService.getMessages(this.$route.params.forumId).then((response) => {
            if(response.status === 200) {
                this.messages = response.data;
            }
        })
        this.forum = this.$store.state.forums.filter( (forum) => {
            return this.$route.params.forumId == forum.forumId
        })[0]
    },
    methods: {
        addMessage(message) {
            ForumService.addMessage(this.$route.params.forumId, message).then(response => {
                if (response.status === 201) {
                    this.messages.push(response.data)
                    this.messageToAdd = {
                        messageContent: ''
                    }
                    this.show = false;
                }
            })
        },
        reset() {
            this.messageToAdd = {
                messageContent: ''
            }
        }
    }
}
</script>

<style scoped>

.error-message > p {
  color: #ba3939;
  background: #ffe0e0;
  border: 1px solid #a33a3a;
  height: fit-content;
  width: fit-content;
  text-align: center;
  border-radius: 30px;
  padding-left: 8px;
  padding-right: 8px;
  font-size: 1.5em;
}

.error-message {
    margin-left: 3px;
}

form {
  margin-left: 4%;
  margin-right: 10%;
  margin-top: .5%;
  margin-bottom: .5%;
}

#content-holder {
border: 3px solid #FAF5E9;
}

#forum-head {
font-family: 'David Libre', serif;
color: #FAF5E9;
font-size: 4em;
border-bottom: 3px solid #faf5e94f;
padding-left: 2%;
}

#forum-username {
    font-size: 20px;
    
}

#forum-name {
    margin-bottom: 0;
}

#forum-info {
border-bottom: 3px solid #FAF5E9;
}
#forum-description {
font-family: 'PT Sans', sans-serif;
color: #FAF5E9;
font-size: 1.8em;
padding-left: 2%;
padding-bottom: 1%;
}

.messages {
font-size: 1.8em;
border-top: 3px solid #faf5e94f;
font-family: 'PT Sans', sans-serif;
color: #FAF5E9;
padding-left: 2%;
padding-top: 1%;
}

#reply-button {
margin-left: 2%;
margin-bottom: 1%;
}

#main {
  height: 100vh;
  color: #FAF5E9
}

.topic:hover {
  cursor: pointer;
}

.bar {
  width:100%;
  height:10px;
  color: black;
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

#main {
    display: flex;
    justify-content: center;
}

.divTableHead {
font-family: 'David Libre', serif;
}

div.minimalistBlack {
  width: 75vw;
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