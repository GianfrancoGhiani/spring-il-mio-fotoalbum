import axios, { Axios } from 'axios';
import {reactive} from 'vue';

export const store = reactive({
    apiBaseUrl: 'http://localhost:8080/api/photos',

    
}); 