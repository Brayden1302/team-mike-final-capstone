<template>
  <div>
      <div id="forum-info">
          <p>{{forum.forumName}}</p>
          <p>{{forum.forumDescription}}</p>
      </div>
       <b-button variant="outline-primary" v-on:click="show = !show">Reply</b-button>
       <!--  @reset="onReset" -->
       <b-form v-on:submit.prevent="addMessage(messageToAdd)" v-on:reset="reset"  v-if="show">
      <b-form-group
        id="input-group-1"
        label="Topic:"
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
      <div v-for="message in messages" v-bind:key="message.messageId">
          <p>{{message.messageContent}}</p>
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

<style>

</style>