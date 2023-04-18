import axios from 'axios';


export default {
    getForums(){
        return axios.get('/forum')
    },
    getMessages(forumId){
        return axios.get(`/forum/${forumId}`)   
    },
    addForum(forum) {
        return axios.post('/forum', forum)
    },
    addMessage(forumId, message) {
        return axios.post(`/forum/${forumId}`, message)
    }

}