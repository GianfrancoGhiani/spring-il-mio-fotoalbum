<template>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center top-bar">
            <h1>Album</h1>
            <div>
                <a :href="categories" class="btn btn-info">Categories Section</a>
            </div>
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
        }
    },
    methods: {
        getAllPhotos() {
            axios.get(store.apiBaseUrl, { params: { name: this.nameQuery } }).then((res) => {
                //   console.log(res);
                this.photos = res.data;
            })
        }
    },
    mounted() {
        this.getAllPhotos();
    },
}

</script>
  
<style></style>
  