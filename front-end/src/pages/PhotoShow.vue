<template>
    <div class="container">
        <div class="wrapper">
            <div class="d-flex justify-content-between align-items-center top-bar">
                <h1 v-if="status == 200">{{ photo.title }}</h1>
                <h1 v-else class="text-danger">{{ errTitle }}</h1>
                <router-link :to="{ name: 'home' }"><i class="fa-solid fa-arrow-left"></i></router-link>
            </div>
            <div class="card container w-50 p-0" v-if="status == 200">
                <div class="card-header d-flex align-items-center justify-content-between">
                    <h5>{{ photo.title }}</h5>
                    <div>
                        <span v-for="(cat, index) in photo.categories" :key="index"
                            class="bg-secondary text-white rounded-pill cat me-3">{{ cat.name }}</span>
                    </div>
                </div>
                <div class="card-body d-flex flex-column align-items-center">
                    <div class="body-img">
                        <img :src="photo.url">
                    </div>
                    <div class="align-self-start">
                        <h4>Description:</h4>
                        <p>{{ photo.description }}</p>
                    </div>
                </div>
            </div>
            <div class="card container w-50 p-0 error" v-else>
                <div class="card-header d-flex align-items-center justify-content-between">
                    <h5 class="text-danger">{{ errTitle }}</h5>
                </div>
                <div class="card-body d-flex flex-column align-items-center">
                    <img v-if="status == 404" class="error-img"
                        src="https://img.freepik.com/free-vector/oops-404-error-with-broken-robot-concept-illustration_114360-1920.jpg?w=826&t=st=1681721919~exp=1681722519~hmac=41c56e5496407f6dae0a403bb054ea29a8ae50988bb9e59f273e480f52ce1597"
                        alt="Error 404">
                    <p>{{ errMessage }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { store } from '../store';
export default {
    data() {
        return {
            store,
            photo: {},
            status: null,
            errMessage: '',
            errTitle: '',
        }
    },
    methods: {
        getPhotoById() {
            axios.get(`${store.apiBaseUrl}/${this.$route.params.id}`).then((res) => {
                console.log(res)
                this.status = res.status;
                this.photo = res.data;
            }).catch((err) => {
                console.log(err.response)
                this.status = err.response.status;
                this.errTitle = err.response.data.error
                this.errMessage = err.response.data.message
            })
        }
    },
    mounted() {
        this.getPhotoById();
    },

}
</script>

<style lang="scss" scoped>
.error {
    .error-img {
        max-width: 70%;
    }

    p {
        font-weight: 600;
        font-style: italic;
    }
}
</style>