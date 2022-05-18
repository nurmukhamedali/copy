import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'

import 'vuetify/dist/vuetify.min.css'
import 'api/resource'

import router from "router/router";
import App from 'pages/App.vue'
import store from "store/store";
import { connect } from "./util/ws";

if (frontendData.profile){
    connect() // производим подключение к веб-сокету
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})
