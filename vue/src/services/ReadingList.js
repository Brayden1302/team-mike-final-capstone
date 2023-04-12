import axios from 'axios';


export default {
    addToReadingList(bookid){
        return axios.post('/readinglist', bookid)
    },
    deletReadingList(bookid) {
        return axios.delete('/readinglist', bookid)
    },
    getReadingList(){
        return axios.get('/readinglist')
    }

}