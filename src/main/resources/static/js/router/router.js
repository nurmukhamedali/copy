import Vue from 'vue'
import VueRouter from "vue-router"
import Auth from "pages/Auth.vue";
import Profile from "pages/Profile.vue";
import ProductList from "pages/ProductList.vue";
import CategoryList from "pages/CategoryList.vue";
import CartItemList from "pages/CartItemList.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/categories', component: CategoryList },
    { path: '/products', component: ProductList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/cart', component: CartItemList},
    { path: '*', component: CategoryList }
]

export default new VueRouter({
    mode: 'history',
    routes // routes: routes
})
