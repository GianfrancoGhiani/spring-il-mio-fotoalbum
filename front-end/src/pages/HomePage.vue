<template>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center top-bar">
            <h1>Album</h1>

        </div>
        <div class="wrapper row row-cols-3">
            <div class="offset-9 col-3">
                <form class="form-inline my-2 my-lg-0 d-flex">
                    <input class="form-control mr-sm-2 me-2" type="search" placeholder="Search" aria-label="Search"
                        name="name" v-model="nameQuery">
                    <button class="btn btn-info my-2 my-sm-0" @click.prevent="getAllPhotos()"><i
                            class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
            </div>
            <div class="p-2 col" v-for="photo in photos" :key="photo.id">
                <div class="card container-fluid p-0">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5>{{ photo.title }}</h5>
                        <div>
                            <span v-for="(cat, index) in photo.categories" :key="index"
                                class="bg-secondary text-white rounded-pill cat me-3">{{ cat.name }}</span>
                        </div>
                    </div>
                    <div class="card-body d-flex flex-column align-items-center">
                        <router-link :to="{ name: 'show', params: { id: photo.id } }">
                            <div class="body-img">
                                <img :src="photo.url">
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
            <div class="col-12 d-flex justify-content-center">
                <form action="#" class="p-3 my-3 creation w-50 d-flex flex-column">
                    <h2>Contact us</h2>
                    <div class="alert alert-danger text-center" v-if="!messageStatus">{{ alertMessage }}</div>
                    <div class="">
                        <label for="email">
                            <h6>Email</h6>
                        </label>
                        <input type="text" class="form-control w-auto" name="email" id="email" v-model="formModel.email"
                            :class="{ 'is-invalid': !messageStatus }">
                        <div class="invalid-feedback" v-if="!messageStatus">
                            <p v-if="errors.email.length > 0">{{ errors.email }}</p>
                        </div>
                    </div>
                    <div class="my-3">
                        <label for="message">
                            <h6>Message</h6>
                        </label>
                        <textarea class="form-control" name="message" id="message" v-model="formModel.message"
                            :class="{ 'is-invalid': !messageStatus }"></textarea>
                        <div class="invalid-feedback" v-if="!messageStatus">
                            <p v-if="errors.message.length > 0">{{ errors.message }}</p>
                        </div>
                    </div>

                    <div class=""><button class="btn btn-info w-auto" @click.prevent="sendMessage()">Send</button></div>
                </form>
            </div>
        </div>
    </div>
</template>
  
<script >
import axios from 'axios'
import { store } from '../store'
export default {
    data() {
        return {
            store,
            photos: [],
            nameQuery: '',
            formModel: {
                email: '',
                message: ''
            },
            alertMessage: '',
            messageStatus: true,
            errors: {
                email: '',
                message: ''
            }
        }
    },
    methods: {
        getAllPhotos() {
            axios.get(store.apiBaseUrl, { params: { name: this.nameQuery } }).then((res) => {
                //   console.log(res);
                this.photos = res.data;
            })
        },
        sendMessage() {
            this.alertMessage = '';
            axios.post(`${store.apiBaseUrl}/contacts`, JSON.stringify(this.formModel), { headers: { 'Content-Type': 'application/json' } }).then((res) => {
                if (res.data.success == "true") {
                    this.formModel.email = '';
                    this.formModel.message = '';
                    this.alertMessage = 'Message sent correctly';
                } else {
                    this.messageStatus = false;
                    this.alertMessage = 'Error found into the message form';
                    if (res.data.email) {
                        this.errors.email = res.data.email;
                    }
                    this.errors.message = res.data.message;
                    console.log(res.data)
                }
            })
        }
    },
    mounted() {
        this.getAllPhotos();
    },
}

</script>
  
<style>
.creation {
    background-color: rgba(255, 255, 255, 0.8);
    padding: 3rem 7rem;
    border-radius: 1rem;
    border: 4px solid rgba(128, 128, 128, .6);
    outline: 1px solid black;
}
</style>
  