<template>
  <div id="main">
       <b-button variant="outline-primary" v-on:click="show = !show">Create Topic</b-button>
       <!--  @reset="onReset" -->
       <b-form v-on:submit.prevent="addForum(forumToAdd)" v-on:reset="reset"  v-if="show">
      <b-form-group
        id="input-group-1"
        label="Topic:"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          type="text"
          placeholder="Topic"
          v-model="forumToAdd.forumName"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Forum Description:" label-for="input-2">
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
      <div  v-for="forum in forums" v-bind:key="forum.forumId">
          <router-link v-bind:to="{ name: 'forum-messages', params : { forumId : forum.forumId} }">
          <div class="topic">
          <div class="content">
          <p>{{forum.forumName}}</p>
          <p>{{forum.forumDescription}}</p>
          <p>{{forum.username}}</p>
          </div>
          </div>
          </router-link>
      </div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService.js'
export default {
    name: 'forum',
    data() {
        return {
            forums: [],
            store: [],
            show: false,
            forumToAdd: {
                forumName: '',
                forumDescription: ''
            }
        }
    },
    created() {
        ForumService.getForums().then( (response) => {
            if (response.status === 200) {
                this.forums = response.data
                this.$store.commit('SET_FORUMS', response.data)
            }
        }).catch(error => {
            console.log(error)
        })
    },
    methods: {
        addForum(forum) {
            ForumService.addForum(forum).then(response => {
                if (response.status === 201) {
                    this.forums.push(response.data)
                }
            })
        },
        reset() {
            this.forumToAdd = {
                topic : '',
                description : ''
            }
        }
    }
}
</script>
    
<style scoped>
    #main {
        color: #FAF5E9;
    }
    .topic {
        
        background: red;
        width: fit-content;
        border-radius: 10px;
        margin: auto;
    }

    .content {
        padding: 10px 10px 20px 20px;
    }
</style>